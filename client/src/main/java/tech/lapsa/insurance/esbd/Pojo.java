package tech.lapsa.insurance.esbd;

import java.io.Serializable;

import tech.lapsa.patterns.domain.MyHcEqToStr;

public abstract class Pojo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
	return MyHcEqToStr.toString(this);
    }

    @Override
    public final int hashCode() {
	return MyHcEqToStr.hashCode(this);
    }

    @Override
    public final boolean equals(final Object other) {
	return MyHcEqToStr.equals(this, other);
    }
}
