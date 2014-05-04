/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fing.tagsi.mongodb.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author tagsi
 */
@Document
public class PackageInfo {   
        
    @Id
    private String idPaquete;
    private String idCliente;
    private List<PackageNode> nodes = new ArrayList<PackageNode>();

    public PackageInfo() {
    }
    
    public PackageInfo(String idPaquete, String idCliente) {
        this.idPaquete = idPaquete;
        this.idCliente = idCliente;
    }

    public String getIdPaquete() {
        return idPaquete;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public List<PackageNode> getNodes() {
        return nodes;
    }
    
    public void addNode(PackageNode pn)
    {
        this.nodes.add(pn);
    }
    
    @Override
	public String toString() {
		return "Package [idPaquete=" + idPaquete + ", idCliente=" + idCliente + ", nodes=" + nodes.toString() + "]";
	}
}
