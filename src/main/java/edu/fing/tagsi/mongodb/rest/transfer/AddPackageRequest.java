/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.fing.tagsi.mongodb.rest.transfer;

import edu.fing.tagsi.mongodb.domain.*;
import java.io.Serializable;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 *
 * @author tagsi
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AddPackageRequest  implements Serializable{
    private String idPaquete;
    private String idCliente;
    private String idLugar;
    private Date date;
    private Boolean destination;
    
    public PackageInfo getPackageInfo()
    {
        return new PackageInfo(this.idPaquete, this.idCliente);
    }
    
    public PackageNode getPackageNode()
    {
        return new PackageNode(this.idLugar, this.date, this.destination);
    }
}