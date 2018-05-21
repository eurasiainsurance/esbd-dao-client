package tech.lapsa.esbd.dao.entities.embeded;

import java.time.LocalDate;
import java.util.function.Consumer;

import com.lapsa.insurance.elements.CancelationReason;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(101)
public class CancelationInfo extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final CancelationInfoBuilder builder() {
	return new CancelationInfoBuilder();
    }

    public static final class CancelationInfoBuilder {

	private LocalDate dateOf;
	private CancelationReason reason;

	private CancelationInfoBuilder() {
	}

	public CancelationInfoBuilder withDateOf(final LocalDate dateOf) {
	    this.dateOf = MyObjects.requireNonNull(dateOf, "dateOf");
	    return this;
	}

	public CancelationInfoBuilder withReason(final CancelationReason reason) {
	    this.reason = MyObjects.requireNonNull(reason, "reason");
	    return this;
	}

	public CancelationInfo build() {
	    return new CancelationInfo(dateOf,
		    reason);
	}

	public void buildTo(final Consumer<CancelationInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private CancelationInfo(final LocalDate dateOf,
	    final CancelationReason reason) {
	this.dateOf = dateOf;
	this.reason = reason;
    }

    // dateOf

    private final LocalDate dateOf;

    public LocalDate getDateOf() {
	return dateOf;
    }

    // reason

    private final CancelationReason reason;

    public CancelationReason getReason() {
	return reason;
    }
}
