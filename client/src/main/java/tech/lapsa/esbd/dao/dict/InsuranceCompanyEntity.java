package tech.lapsa.esbd.dao.dict;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(7)
public class InsuranceCompanyEntity extends Domain implements DictionaryEntity<Integer> {

    private static final long serialVersionUID = 1L;

    Integer id;
    String code;
    String name;

    // GENERATED

    @Override
    public Integer getId() {
	return id;
    }

    public String getCode() {
	return code;
    }

    public String getName() {
	return name;
    }
}
