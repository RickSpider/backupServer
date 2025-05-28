/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doxa.backupServer.controllerWeb;

import com.doxa.backupServer.newpackage.repository.FormatoRepo;
import com.doxa.backupServer.model.Formato;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author BlackSpider
 */
@Controller
@RequestMapping("/formatos")
public class FormatoController {

    @Autowired
    private FormatoRepo formatoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("formatos", formatoRepository.findAll());
        return "formatos/lista";
    }

     @GetMapping("/nuevo")
    public String nuevoFormato(Model model) {
        model.addAttribute("formato", new Formato());
        return "formatos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarFormato(@PathVariable Long id, Model model) {
        Optional<Formato> formato = formatoRepository.findById(id);
        if (formato.isPresent()) {
            model.addAttribute("formato", formato.get());
            return "formatos/formulario";
        } else {
            return "redirect:/formatos";
        }
    }

    @PostMapping("/guardar")
    public String guardarFormato(@ModelAttribute("formato") Formato formato) {
        formatoRepository.save(formato);
        return "redirect:/formatos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFormato(@PathVariable Long id) {
        formatoRepository.deleteById(id);
        return "redirect:/formatos";
    }
}
