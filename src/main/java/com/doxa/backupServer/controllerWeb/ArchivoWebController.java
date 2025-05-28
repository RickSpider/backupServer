/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doxa.backupServer.controllerWeb;

import com.doxa.backupServer.newpackage.repository.ArchivoRepo;
import com.doxa.backupServer.newpackage.repository.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;;

/**
 *
 * @author BlackSpider
 */
@Controller
public class ArchivoWebController {

    @Autowired
    private ArchivoRepo archivoRepository;
    
    @Autowired
    private ClienteRepo clienteRepository;


    @GetMapping("/archivos")
    public String verArchivos(Model model) {
       model.addAttribute("pageTitle", "Archivos");
       model.addAttribute("archivos", archivoRepository.findAll());
       model.addAttribute("clientes", clienteRepository.findAll());
       return "archivos"; // nombre del template Thymeleaf
       
    }
    
}
