/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doxa.backupServer.controller;

import com.doxa.backupServer.model.Archivo;
import com.doxa.backupServer.model.Cliente;
import com.doxa.backupServer.newpackage.repository.ArchivoRepo;
import com.doxa.backupServer.newpackage.repository.ClienteRepo;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author BlackSpider
 */
@RestController
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private ArchivoRepo archivoRepo;

    @GetMapping("/ultimo/{clienteid}")
    public ResponseEntity<Object> getLastBackup(@PathVariable("clienteid") Long clienteid) throws MalformedURLException, IOException {

        Optional<Cliente> oCliente = clienteRepo.findById(clienteid);

        Optional<Archivo> archivo = archivoRepo.findTopByClienteOrderByFechaDesc(oCliente.get());

        Resource resource = new FileSystemResource(archivo.get().getPath());

        // Verificar si el archivo existe y es legible
        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable Long id) throws MalformedURLException {
        Archivo archivo = archivoRepo.findById(id).get();
        Path path = Paths.get(archivo.getPath());

        Resource recurso = new UrlResource(path.toUri());
        if (!recurso.exists()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Archivo no encontrado");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getFileName().toString() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(recurso);
    }
}
