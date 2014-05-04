/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.fing.tagsi.mongodb.rest.transfer;

import edu.fing.tagsi.mongodb.domain.PackageInfo;
import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 *
 * @author henry
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GetAllPackageResponse implements Serializable{
    
    List<PackageInfo> packageInfo;

    public GetAllPackageResponse() {
    }

    public GetAllPackageResponse(List<PackageInfo> packageInfo) {
        this.packageInfo = packageInfo;
    }

    public List<PackageInfo> getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(List<PackageInfo> packageInfo) {
        this.packageInfo = packageInfo;
    }
    
    
}
