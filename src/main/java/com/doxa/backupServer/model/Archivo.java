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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author BlackSpider
 */
@Entity
@Table(name="archivos")
public class Archivo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long archivoid;
    
    @ManyToOne()
    @JoinColumn(name = "clienteid")
    private Cliente cliente;
    
    @CreationTimestamp
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha = new Date();
    
    private String path;

    public long getArchivoid() {
        return archivoid;
    }

    public void setArchivoid(long archivoid) {
        this.archivoid = archivoid;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
    
}
