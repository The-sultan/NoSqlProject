/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fing.tagsi.mongodb.services;

import edu.fing.tagsi.mongodb.domain.Person;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

/**
 *
 * @author Farid
 */
@Service
public class MongoService {

  @Autowired
  MongoOperations mongoOperations;
  
  @PostConstruct
  public void savePerson(){
    Person p = new Person("John", 39);
    mongoOperations.insert(p);
  }
}
