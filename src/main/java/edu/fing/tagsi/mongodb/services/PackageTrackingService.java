/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fing.tagsi.mongodb.services;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import edu.fing.tagsi.mongodb.domain.PackageInfo;
import edu.fing.tagsi.mongodb.domain.PackageNode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 *
 * @author tagsi
 */
@Service
public class PackageTrackingService {

    @Autowired
    MongoOperations mongoOperations;

    public PackageTrackingService() {
    }

    public void Add(PackageInfo pi, PackageNode pn) { 
                 
        if (pi != null && pn != null) {
            Query query = new Query();
            query.addCriteria(Criteria.where("idPaquete").is(pi.getIdPaquete()).and("idCliente").is(pi.getIdCliente()));
            Update update = new Update();
            //update.set("idCliente", pi.getIdCliente());        
//            DBObject dbo = new BasicDBObject();
//            mongoOperations.getConverter().write(pn, dbo);
            update.push("nodes", pn);

            mongoOperations.upsert(query, update, PackageInfo.class);
        }
    }

    public List<PackageInfo> GetAllPackages() {
        return mongoOperations.find(new Query(), PackageInfo.class);
    }
}
