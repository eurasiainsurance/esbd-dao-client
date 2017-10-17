package com.lapsa.insurance.esbd.services.elements;

import java.time.LocalDate;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.esbd.domain.entities.general.SubjectPersonEntity;
import com.lapsa.insurance.esbd.services.NotFound;

public interface InsuranceClassTypeServiceDAO extends ElementsServiceDAO<InsuranceClassType, Integer> {
    InsuranceClassType getByCode(String code) throws NotFound;

    InsuranceClassType getForSubject(SubjectPersonEntity individual) throws NotFound;

    InsuranceClassType getForSubject(SubjectPersonEntity individual, LocalDate date) throws NotFound;

    InsuranceClassType getDefault();
}
