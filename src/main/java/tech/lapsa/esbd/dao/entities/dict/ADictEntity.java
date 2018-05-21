package tech.lapsa.esbd.dao.entities.dict;

import java.util.function.Consumer;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyStrings;

public abstract class ADictEntity extends AEntity {

    private static final long serialVersionUID = 1L;

    public static abstract class DictionaryEntityBuilder<T extends ADictEntity> {

	protected Integer id;
	protected String code;
	protected String name;

	public DictionaryEntityBuilder<T> withId(final Integer id) {
	    this.id = MyNumbers.requirePositive(id, "id");
	    return this;
	}

	public DictionaryEntityBuilder<T> withCode(final String code) {
	    this.code = MyStrings.requireNonEmpty(code, "code");
	    return this;
	}

	public DictionaryEntityBuilder<T> withName(final String name) {
	    this.name = MyStrings.requireNonEmpty(name, "name");
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

    protected ADictEntity(final Integer id, final String code, final String name) {
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
