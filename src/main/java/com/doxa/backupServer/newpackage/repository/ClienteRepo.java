/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.doxa.backupServer.newpackage.repository;

import com.doxa.backupServer.model.Cliente;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author BlackSpider
 */
public interface ClienteRepo extends CrudRepository<Cliente, Long>{
    
}
