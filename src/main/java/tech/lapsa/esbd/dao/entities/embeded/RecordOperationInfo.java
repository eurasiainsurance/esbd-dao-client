package tech.lapsa.esbd.dao.entities.embeded;

import java.time.Instant;
import java.util.function.Consumer;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.esbd.dao.entities.complex.UserEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(83)
public class RecordOperationInfo extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final RecordOperationInfoBuilder builder() {
	return new RecordOperationInfoBuilder();
    }

    public static final class RecordOperationInfoBuilder {

	private Instant instant;
	private UserEntity author;

	private RecordOperationInfoBuilder() {
	}

	public RecordOperationInfoBuilder withInstant(final Instant instant) {
	    this.instant = MyObjects.requireNonNull(instant, "instant");
	    return this;
	}

	public RecordOperationInfoBuilder withAuthor(final UserEntity author) {
	    this.author = MyObjects.requireNonNull(author, "author");
	    return this;
	}

	public RecordOperationInfo build() {
	    final RecordOperationInfo res = new RecordOperationInfo();
	    res.instant = MyObjects.requireNonNull(instant, "instant");
	    res.author = MyObjects.requireNonNull(author, "author");
	    return res;
	}

	public void buildTo(final Consumer<RecordOperationInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private RecordOperationInfo() {
    }

    // res

    private Instant instant;

    public Instant getInstant() {
	return instant;
    }

    // author

    private UserEntity author;

    public UserEntity getAuthor() {
	return author;
    }
}
