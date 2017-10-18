package tech.lapsa.insurance.esbd.elements;

import javax.ejb.Local;

import com.lapsa.international.country.Country;

@Local
public interface CountryService extends ElementsService<Country, Integer> {
}
