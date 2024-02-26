/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doxa.backupServer.controller;

import com.doxa.backupServer.model.Archivo;
import com.doxa.backupServer.model.Cliente;
import com.doxa.backupServer.newpackage.repository.ArchivoRepo;
import com.doxa.backupServer.newpackage.repository.ClienteRepo;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author BlackSpider
 */
@RestController
public class uploadController {

    private static final int BUFFER_SIZE = 65536; // Tamanho del buffer en bytes
    
    @Autowired
    private ClienteRepo clienteRepo;
    
    @Autowired
    private ArchivoRepo archivoRepo;

    @PostMapping("/upload/{clienteid}")
    public ResponseEntity<String> handleFileUpload(@PathVariable("clienteid") Long clienteid,@RequestParam("file") MultipartFile file) {
        
        Optional<Cliente> c = clienteRepo.findById(clienteid);
        
        if (!c.isPresent()){
        
            return new ResponseEntity<>("El Cliente esta no existe", HttpStatus.NOT_FOUND);
            
        }
        
        if (file.isEmpty()) {

            return new ResponseEntity<>("El archivo esta vacio", HttpStatus.BAD_REQUEST);

        }
        
        String directorio = c.get().getPathDirectorio();
        Archivo archivo = new Archivo();
        archivo.setCliente(c.get());
        archivo.setPath(directorio+file.getOriginalFilename());
        
       

        try {

            // byte[] bytes = file.getBytes();
            System.out.println(file.getOriginalFilename());

            Path directorioPath = Paths.get(directorio);
            if (!Files.exists(directorioPath)) {
                // Si el directorio no existe, intenta crearlo
                Files.createDirectories(directorioPath);
            }

            try ( //Files.write(Paths.get("C:\\bkArchivos\\"+file.getOriginalFilename()), bytes);
                
                InputStream inputStream = file.getInputStream(); OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(archivo.getPath()))) {
                /*outputStream.write(file.getBytes());
                outputStream.close();*/

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

            }
            /*outputStream.write(file.getBytes());
            outputStream.close();*/

            this.archivoRepo.save(archivo);
            return new ResponseEntity<>("Archivos Cargados", HttpStatus.OK);

        } catch (IOException e) {

            e.printStackTrace();
            return new ResponseEntity<>("Error al cargar Archivos", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        
        

    }

}
