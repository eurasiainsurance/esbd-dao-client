package tech.lapsa.esbd.dao.infos;

import java.time.LocalDate;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.esbd.dao.entities.UserEntity;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(83)
public class RecordOperationInfo extends Domain {

    private static final long serialVersionUID = 1L;

    private LocalDate date;
    private UserEntity author;

    // GENERATED

    public LocalDate getDate() {
	return date;
    }

    public void setDate(final LocalDate date) {
	this.date = date;
    }

    public UserEntity getAuthor() {
	return author;
    }

    public void setAuthor(final UserEntity author) {
	this.author = author;
    }

}
