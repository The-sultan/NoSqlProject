/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fing.tagsi.mongodb.rest.transfer;

import edu.fing.tagsi.mongodb.domain.PackageInfo;
import edu.fing.tagsi.mongodb.domain.PackageNode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 *
 * @author tagsi
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PackageCollectionDTO implements Serializable {

    List<PackageDTO> packs;

    public PackageCollectionDTO() {
    }

    public PackageCollectionDTO(PackageInfo packInfo) {
        
        packs = new ArrayList<PackageDTO>();
        if (packInfo != null)
        {            
            for (PackageNode node : packInfo.getNodes()) {
                packs.add(new PackageDTO(packInfo.getIdPaquete(), packInfo.getIdCliente(), node.getIdLugar(), node.getDate(), node.isDestination()));
            }
        }
    }

    public List<PackageDTO> getPacks() {
        return packs;
    }

    public void setPackNodes(List<PackageDTO> packages) {
        this.packs = packages;
    }
}
