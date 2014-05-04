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
    private String idpaquete;
    private String idcliente;
    private String idlugar;
    private Date fecha;
    private Boolean esdestino;
         
    public PackageInfo getPackageInfo()
    {
        return new PackageInfo(this.idpaquete, this.idcliente);
    }
    
    public PackageNode getPackageNode()
    {
        return new PackageNode(this.idlugar, this.fecha, this.esdestino);
    }
}