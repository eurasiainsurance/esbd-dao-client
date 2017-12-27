package tech.lapsa.esbd.dao.beans.entities;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.entities.SubjectEntity;
import tech.lapsa.esbd.dao.entities.SubjectEntityService;
import tech.lapsa.esbd.dao.entities.SubjectCompanyEntityService.SubjectCompanyEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.SubjectEntityService.SubjectEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.SubjectEntityService.SubjectEntityServiceRemote;
import tech.lapsa.esbd.dao.entities.SubjectPersonEntityService.SubjectPersonEntityServiceLocal;
import tech.lapsa.esbd.jaxws.wsimport.Client;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

@Stateless(name = SubjectEntityService.BEAN_NAME)
public class SubjectEntityServiceBean extends ASubjectEntityService
	implements SubjectEntityServiceLocal, SubjectEntityServiceRemote {

    @EJB
    private SubjectPersonEntityServiceLocal subjectPersonService;

    @EJB
    private SubjectCompanyEntityServiceLocal subjectCompanyService;

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SubjectEntity getById(final Integer id) throws NotFound, IllegalArgument {
	MyNumbers.requireNonZero(IllegalArgument::new, id, "id");

	try (Connection con = pool.getConnection()) {
	    final Client source = con.getClientByID(id.intValue());
	    if (source == null)
		throw new NotFound(SubjectEntity.class.getSimpleName() + " not found with ID = '" + id + "'");

	    if (source.getNaturalPersonBool() == 1)
		// частник SubjectPerson
		return subjectPersonService.getById(id);
	    else
		// юрлицо SubjectCompany
		return subjectCompanyService.getById(id);
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SubjectEntity getByIdNumber(final TaxpayerNumber taxpayerNumber) throws NotFound, IllegalArgument {
	MyObjects.requireNonNull(IllegalArgument::new, taxpayerNumber, "taxpayerNumber"); //
	TaxpayerNumber.requireValid(taxpayerNumber);

	final Client source = fetchClientByIdNumber(taxpayerNumber, true, true);
	if (source == null)
	    throw new NotFound(SubjectEntity.class.getSimpleName() + " not found with IDNumber = '"
		    + taxpayerNumber.getNumber() + "'");
	if (source.getNaturalPersonBool() == 1)
	    // частник SubjectPerson
	    return subjectPersonService.getByIIN(taxpayerNumber);
	else
	    // юрлицо SubjectCompany
	    return subjectCompanyService.getByBIN(taxpayerNumber);
    }
}
