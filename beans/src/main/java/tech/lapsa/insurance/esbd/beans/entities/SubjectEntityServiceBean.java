package tech.lapsa.insurance.esbd.beans.entities;

import javax.ejb.Stateless;
import javax.inject.Inject;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.jaxws.wsimport.Client;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.entities.SubjectCompanyEntityService;
import tech.lapsa.insurance.esbd.entities.SubjectEntity;
import tech.lapsa.insurance.esbd.entities.SubjectEntityService;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntityService;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyStrings;

@Stateless
public class SubjectEntityServiceBean extends ASubjectEntityService implements SubjectEntityService {

    @Inject
    private SubjectPersonEntityService subjectPersonService;

    @Inject
    private SubjectCompanyEntityService subjectCompanyService;

    @Override
    public SubjectEntity getById(Integer id) throws NotFound {
	MyNumbers.requireNonZero(id, "id");

	try (Connection con = pool.getConnection()) {
	    Client source = con.getClientByID(id.intValue());
	    if (source == null)
		throw new NotFound(SubjectEntity.class.getSimpleName() + " not found with ID = '" + id + "'");

	    if (source.getNaturalPersonBool() == 1) {
		// частник SubjectPerson
		return subjectPersonService.getById(id);
	    } else {
		// юрлицо SubjectCompany
		return subjectCompanyService.getById(id);
	    }
	}
    }

    @Override
    public SubjectEntity getByIdNumber(String idNumber) throws NotFound {
	MyStrings.requireNonEmpty(idNumber, "idNumber");
	Client source = fetchClientByIdNumber(idNumber, true, true);
	if (source == null)
	    throw new NotFound(SubjectEntity.class.getSimpleName() + " not found with IDNumber = '" + idNumber + "'");
	if (source.getNaturalPersonBool() == 1) {
	    // частник SubjectPerson
	    return subjectPersonService.getByIIN(idNumber);
	} else {
	    // юрлицо SubjectCompany
	    return subjectCompanyService.getByBIN(idNumber);
	}
    }
}
