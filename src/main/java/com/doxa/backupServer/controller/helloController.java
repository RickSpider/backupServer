/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doxa.backupServer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BlackSpider
 */
@RestController
public class helloController {
    
    @GetMapping(value = "/hello", produces = "application/json")
    public ResponseEntity sayHello() {
        
       return new ResponseEntity("Hello BackupUser",HttpStatus.OK);

    }
    
}
