/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doxa.backupServer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author BlackSpider
 */
@Entity
@Table(name="Clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clienteid;
   
    private String nombre;
    private String pass;
    
    @Column(name="pathdirectorio")
    private String pathDirectorio;

    public long getClienteid() {
        return clienteid;
    }

    public void setClienteid(long clienteid) {
        this.clienteid = clienteid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPathDirectorio() {
        return pathDirectorio;
    }

    public void setPathDirectorio(String pathDirectorio) {
        this.pathDirectorio = pathDirectorio;
    }

}
