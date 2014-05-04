/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.fing.tagsi.mongodb.rest.transfer;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 *
 * @author tagsi
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GetTrackingRequest implements Serializable{
    private String idPaquete;

    public GetTrackingRequest() {
    }

    public String getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(String idPaquete) {
        this.idPaquete = idPaquete;
    }
}
