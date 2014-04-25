package edu.fing.tagsi.neo4j.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Vehicle {

    private static final Log log = LogFactory.getLog(Vehicle.class);

    private Long id;
    private String make;
    private String model;

    public Vehicle(Long id, String make, String model) {
        this.id = id;
        this.make = make;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean equals(Object obj) {
            if (obj == this)
                    return true;
            if (this.id == null || !(obj instanceof Vehicle))
                    return false;
            Vehicle vehicle = (Vehicle) obj;
            return this.id.equals(vehicle.getId());
    }

    @Override
    public int hashCode() {
            return this.id == null ? 1 : this.id.hashCode();
    }

}
