package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(83)
public class RecordOperationInfo extends Domain {

    private static final long serialVersionUID = 1L;

    LocalDate date;

    public LocalDate getDate() {
	return date;
    }

    int _author;
    UserEntity author;

    public UserEntity getAuthor() {
	return author;
    }

    void setAuthor(UserEntity author) {
        this.author = author;
    }
}
