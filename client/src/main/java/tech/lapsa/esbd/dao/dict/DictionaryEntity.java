package tech.lapsa.esbd.dao.dict;

import java.util.function.Consumer;

import tech.lapsa.esbd.dao.Domain;

public abstract class DictionaryEntity extends Domain {

    private static final long serialVersionUID = 1L;

    public static abstract class DictionaryEntityBuilder<T extends DictionaryEntity> {

	protected Integer id;
	protected String code;
	protected String name;

	public DictionaryEntityBuilder<T> withId(final Integer id) {
	    this.id = id;
	    return this;
	}

	public DictionaryEntityBuilder<T> withCode(final String code) {
	    this.code = code;
	    return this;
	}

	public DictionaryEntityBuilder<T> withName(final String name) {
	    this.name = name;
	    return this;
	}

	public abstract T build();

	public void buildTo(final Consumer<T> consumer) {
	    consumer.accept(build());
	}

    }

    private final Integer id;
    private final String code;
    private final String name;

    protected DictionaryEntity(final Integer id, final String code, final String name) {
	this.id = id;
	this.code = code;
	this.name = name;
    }

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
