package edu.fing.tagsi.neo4j.rest;

/**
 * @author Farid
 */
import edu.fing.tagsi.neo4j.domain.Vehicle;
import edu.fing.tagsi.neo4j.services.VehicleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class VehicleController {

    private static final Log log = LogFactory.getLog(VehicleController.class);


    @Autowired
    private VehicleService vehicleService;

    /**
     * Get all vehicles.
     */
    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    @ResponseBody
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    /**
     * Get the vehicle matching a specific id.
     *
     * @param vehicleId the identifier of the requested vehicle
     */
    @RequestMapping(value = "/vehicles/{vehicleId}", method = RequestMethod.GET)
    @ResponseBody
    public Vehicle getVehicleById(@PathVariable Long vehicleId) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        log.debug("Got request for vehicle " + vehicleId + ", returning " + vehicle);
        return vehicle;
    }

}