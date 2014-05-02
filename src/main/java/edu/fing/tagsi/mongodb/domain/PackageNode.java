/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.fing.tagsi.mongodb.domain;

import java.util.Date;
/**
 *
 * @author tagsi
 */
public class PackageNode {

    private String idLugar;
    private Date date;
    private Boolean destination;

    public PackageNode(String idLugar, Date date, Boolean destination) {
        this.idLugar = idLugar;
        this.date = date;
        this.destination = destination;
    }

    public String getIdLugar() {
        return idLugar;
    }

    public Date getDate() {
        return date;
    }

    public Boolean isDestination() {
        return destination;
    }
    
    @Override
	public String toString() {
		return "Node [idLugar=" + idLugar + ", date=" + date.toString()
				+ ", destination=" + destination.toString() + "]";
	}
    
}