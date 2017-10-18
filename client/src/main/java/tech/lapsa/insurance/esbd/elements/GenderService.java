package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;

import com.lapsa.insurance.elements.Sex;

@Local
public interface GenderService extends ElementsService<Sex, Integer> {
}
