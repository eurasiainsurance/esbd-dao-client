package tech.lapsa.esbd.dao.dict;

import tech.lapsa.esbd.dao.Domain;

public abstract class DictionaryEntity extends Domain {

    private static final long serialVersionUID = 1L;

    protected Integer id;
    protected String code;
    protected String name;

    // GENERATED

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
