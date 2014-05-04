/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.fing.tagsi.mongodb.rest;

import edu.fing.tagsi.mongodb.rest.transfer.AddPackageRequest;
import edu.fing.tagsi.mongodb.rest.transfer.GetAllPackageResponse;
import edu.fing.tagsi.mongodb.services.PackageTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author henry
 */
@Controller
public class PackageController {
    
    @Autowired
    private PackageTrackingService packageService;
    
    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    public @ResponseBody void Add(@RequestBody AddPackageRequest apr){
        packageService.Add(apr.getPackageInfo(), apr.getPackageNode());
    }
    
    @RequestMapping(value = "/GetAllPackages", method = RequestMethod.POST)
    public @ResponseBody GetAllPackageResponse GetAllPackages(){
        return new GetAllPackageResponse(packageService.GetAllPackages());
    }
}
