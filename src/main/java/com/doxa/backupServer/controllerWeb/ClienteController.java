/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doxa.backupServer.controllerWeb;

import com.doxa.backupServer.model.Cliente;
import com.doxa.backupServer.model.Formato;
import com.doxa.backupServer.newpackage.repository.ClienteRepo;
import com.doxa.backupServer.newpackage.repository.FormatoRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author BlackSpider
 */
@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepo clienteRepository;
    
     @Autowired
    private FormatoRepo formatoRepository;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("pageTitle", "Clientes");
        return "clientes/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoCliente(Model model) {
        
        List<Formato> formatos = (List<Formato>) formatoRepository.findAll();
        
        model.addAttribute("cliente", new Cliente());
         model.addAttribute("formatos", formatos);
        return "clientes/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        
        List<Formato> formatos = (List<Formato>) formatoRepository.findAll();
        
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + id));
        model.addAttribute("cliente", cliente);
        
         model.addAttribute("formatos", formatos);
        
        return "clientes/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }

   
}
