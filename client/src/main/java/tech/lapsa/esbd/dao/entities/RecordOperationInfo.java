package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;
import java.util.function.Consumer;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(83)
public class RecordOperationInfo extends Domain {

    private static final long serialVersionUID = 1L;

    public static final RecordOperationInfoBuilder builder() {
	return new RecordOperationInfoBuilder();
    }

    public static final class RecordOperationInfoBuilder {

	private LocalDate date;
	private UserEntity author;

	private RecordOperationInfoBuilder() {
	}

	public RecordOperationInfoBuilder withDate(final LocalDate date) {
	    this.date = MyObjects.requireNonNull(date, "date");
	    return this;
	}

	public RecordOperationInfoBuilder withAuthor(final UserEntity author) {
	    this.author = MyObjects.requireNonNull(author, "author");
	    return this;
	}

	public RecordOperationInfo build() {
	    return new RecordOperationInfo(date, author);
	}

	public void buildTo(final Consumer<RecordOperationInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private RecordOperationInfo(final LocalDate date, final UserEntity author) {
	this.date = MyObjects.requireNonNull(date, "date");
	this.author = MyObjects.requireNonNull(author, "author");
    }

    private final LocalDate date;

    public LocalDate getDate() {
	return date;
    }

    private final UserEntity author;

    public UserEntity getAuthor() {
	return author;
    }
}
