package edu.fing.tagsi.neo4j.services;



import edu.fing.tagsi.neo4j.domain.Vehicle;
import java.util.*;
import org.springframework.stereotype.Service;


@Service("vehicleService")
public class VehicleService {

    // Dummy list of vehicles
    static final Map<Long, Vehicle> vehicles = new HashMap<Long, Vehicle>();

    // Dummy entry to be returned if not found
    static final Vehicle notFound = new Vehicle(0L, "Vehicle", "Not Found");

    static {
        vehicles.put(1L, new Vehicle(1L, "Audi", "A1"));
        vehicles.put(2L, new Vehicle(2L, "Audi", "S4"));
        vehicles.put(3L, new Vehicle(3L, "Audi", "RS5"));
        vehicles.put(4L, new Vehicle(4L, "BMW", "120i"));
        vehicles.put(5L, new Vehicle(5L, "BMW", "330d"));
        vehicles.put(6L, new Vehicle(6L, "BMW", "M3"));
        vehicles.put(7L, new Vehicle(7L, "Ford", "Focus"));
        vehicles.put(8L, new Vehicle(8L, "Ford", "Mondeo"));
        vehicles.put(9L, new Vehicle(9L, "Volkswagen", "Polo TSI"));
        vehicles.put(10L,new Vehicle(10L, "Volkswagen", "Golf GTI"));
        vehicles.put(11L,new Vehicle(11L, "Volkswagen", "Tiguan"));
    }

    public Vehicle getVehicleById(Long vehicleId) {
        if (vehicleId < vehicles.size() + 1) {
            return vehicles.get(vehicleId);
        } else {
            // Return not found
            return notFound;
        }
    }

    /**
     * Return all vehicles as a List.
     */
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<Vehicle>(vehicles.values());
    }

}