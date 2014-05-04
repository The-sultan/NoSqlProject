/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fing.tagsi.mongodb.services;

import edu.fing.tagsi.mongodb.domain.PackageInfo;
import edu.fing.tagsi.mongodb.domain.PackageNode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
            //_id = idPaquete
            query.addCriteria(Criteria.where("_id").is(pi.getIdPaquete()).and("idCliente").is(pi.getIdCliente()));
            Update update = new Update();
            update.push("nodes", pn);

            mongoOperations.upsert(query, update, PackageInfo.class);
        }
    }
    
    public PackageInfo GetTracking(String idPaquete)
    {
       Query query = new Query();
       query.addCriteria(Criteria.where("_id").is(idPaquete));
       
       List<PackageInfo> packs = mongoOperations.find(query, PackageInfo.class);
       if (packs.size() > 0)
           return packs.get(0);
       else 
           return null;
    }

    public List<PackageInfo> GetAllPackages() {
        return mongoOperations.find(new Query(), PackageInfo.class);
    }
}
