/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.fing.tagsi.mongodb.rest.transfer;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 *
 * @author tagsi
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PackageDTO {
    private String idpaquete;
    private String idcliente;
    private String idlugar;
    private Date fecha;
    private boolean esdestino;

    public PackageDTO() {
    }

    public PackageDTO(String idpaquete, String idcliente, String idlugar, Date fecha, boolean esdestino) {
        this.idpaquete = idpaquete;
        this.idcliente = idcliente;
        this.idlugar = idlugar;
        this.fecha = fecha;
        this.esdestino = esdestino;
    }

    public String getIdpaquete() {
        return idpaquete;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public String getIdlugar() {
        return idlugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public boolean isEsdestino() {
        return esdestino;
    }

    public void setIdpaquete(String idpaquete) {
        this.idpaquete = idpaquete;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public void setIdlugar(String idlugar) {
        this.idlugar = idlugar;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setEsdestino(boolean esdestino) {
        this.esdestino = esdestino;
    }   
    
}
