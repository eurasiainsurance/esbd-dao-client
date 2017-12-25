package tech.lapsa.esbd.dao.dict;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(3)
public class BranchEntity extends Domain implements DictionaryEntity<Integer> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String code;
    private String name;

    // GENERATED

    @Override
    public Integer getId() {
	return id;
    }

    public void setId(final Integer id) {
	this.id = id;
    }

    public String getCode() {
	return code;
    }

    public void setCode(final String code) {
	this.code = code;
    }

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }
}
