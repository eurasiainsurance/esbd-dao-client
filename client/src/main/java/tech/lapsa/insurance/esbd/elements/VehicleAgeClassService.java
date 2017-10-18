package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;

import com.lapsa.insurance.elements.VehicleAgeClass;

@Local
public interface VehicleAgeClassService extends ElementsService<VehicleAgeClass, Integer> {
}