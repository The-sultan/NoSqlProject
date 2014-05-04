/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.fing.tagsi.mongodb.rest;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

import java.util.Arrays;
import java.net.UnknownHostException;
import java.util.Arrays;

public class prueba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBCursor cursor = null;
		try {
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
			DB db = mongoClient.getDB("tagsidb");			
			DBCollection coll = db.getCollection("empresas");
			cursor = coll.find();

		   while(cursor.hasNext()) {
		       System.out.println(cursor.next());
		   }
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		   cursor.close();
		}		
	}

}