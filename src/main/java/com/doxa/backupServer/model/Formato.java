/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doxa.backupServer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author BlackSpider
 */
@Entity
@Table(name = "formatos")
public class Formato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formatoid;
    private String nombre;
    private String extension;

    @ManyToMany(mappedBy = "formatos")
    private Set<Cliente> clientes = new HashSet<>();

    public Long getFormatoid() {
        return formatoid;
    }

    public void setFormatoid(Long formatoid) {
        this.formatoid = formatoid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Formato formato = (Formato) o;
        return Objects.equals(formatoid, formato.formatoid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formatoid);
    }
}
