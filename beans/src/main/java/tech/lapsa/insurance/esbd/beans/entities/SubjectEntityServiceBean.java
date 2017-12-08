package tech.lapsa.insurance.esbd.beans.entities;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.jaxws.wsimport.Client;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.entities.SubjectCompanyEntityService.SubjectCompanyEntityServiceLocal;
import tech.lapsa.insurance.esbd.entities.SubjectEntity;
import tech.lapsa.insurance.esbd.entities.SubjectEntityService.SubjectEntityServiceLocal;
import tech.lapsa.insurance.esbd.entities.SubjectEntityService.SubjectEntityServiceRemote;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntityService.SubjectPersonEntityServiceLocal;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

@Stateless
public class SubjectEntityServiceBean extends ASubjectEntityService
	implements SubjectEntityServiceLocal, SubjectEntityServiceRemote {

    @EJB
    private SubjectPersonEntityServiceLocal subjectPersonService;

    @EJB
    private SubjectCompanyEntityServiceLocal subjectCompanyService;

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
    public SubjectEntity getByIdNumber(TaxpayerNumber taxpayerNumber) throws NotFound {
	MyObjects.requireNonNull(taxpayerNumber, "taxpayerNumber"); //
	TaxpayerNumber.requireValid(taxpayerNumber);

	Client source = fetchClientByIdNumber(taxpayerNumber, true, true);
	if (source == null)
	    throw new NotFound(SubjectEntity.class.getSimpleName() + " not found with IDNumber = '"
		    + taxpayerNumber.getNumber() + "'");
	if (source.getNaturalPersonBool() == 1) {
	    // частник SubjectPerson
	    return subjectPersonService.getByIIN(taxpayerNumber);
	} else {
	    // юрлицо SubjectCompany
	    return subjectCompanyService.getByBIN(taxpayerNumber);
	}
    }
}
