package tech.lapsa.esbd.dao.beans.entities;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.dao.NotFound;
import tech.lapsa.esbd.dao.entities.SubjectCompanyEntityService.SubjectCompanyEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.SubjectEntity;
import tech.lapsa.esbd.dao.entities.SubjectEntityService;
import tech.lapsa.esbd.dao.entities.SubjectEntityService.SubjectEntityServiceLocal;
import tech.lapsa.esbd.dao.entities.SubjectEntityService.SubjectEntityServiceRemote;
import tech.lapsa.esbd.dao.entities.SubjectPersonEntityService.SubjectPersonEntityServiceLocal;
import tech.lapsa.esbd.jaxws.wsimport.Client;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.logging.MyLogger;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

@Stateless(name = SubjectEntityService.BEAN_NAME)
public class SubjectEntityServiceBean extends ASubjectEntityService
	implements SubjectEntityServiceLocal, SubjectEntityServiceRemote {

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(SubjectEntityService.class) //
	    .build();

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SubjectEntity getById(final Integer id) throws NotFound, IllegalArgument {
	try {
	    return _getById(id);
	} catch (IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SubjectEntity getByIdNumber(final TaxpayerNumber taxpayerNumber) throws NotFound, IllegalArgument {
	try {
	    return _getByIdNumber(taxpayerNumber);
	} catch (IllegalArgumentException e) {
	    throw new IllegalArgument(e);
	} catch (RuntimeException e) {
	    logger.WARN.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    // PRIVATE

    @EJB
    private SubjectPersonEntityServiceLocal subjectPersonService;

    @EJB
    private SubjectCompanyEntityServiceLocal subjectCompanyService;

    private SubjectEntity _getById(final Integer id) throws IllegalArgumentException, NotFound {
	MyNumbers.requireNonZero(id, "id");

	try (Connection con = pool.getConnection()) {
	    final Client source = con.getClientByID(id.intValue());
	    if (source == null)
		throw new NotFound(SubjectEntity.class.getSimpleName() + " not found with ID = '" + id + "'");

	    try {
		if (source.getNaturalPersonBool() == 1)
		    return subjectPersonService.getById(id);
		else
		    return subjectCompanyService.getById(id);
	    } catch (IllegalArgument e) {
		throw e.getRuntime();
	    }
	}
    }

    private SubjectEntity _getByIdNumber(final TaxpayerNumber taxpayerNumber)
	    throws IllegalArgumentException, NotFound {
	MyObjects.requireNonNull(taxpayerNumber, "taxpayerNumber"); //
	TaxpayerNumber.requireValid(taxpayerNumber);

	final Client source = fetchClientByIdNumber(taxpayerNumber, true, true);
	if (source == null)
	    throw new NotFound(SubjectEntity.class.getSimpleName() + " not found with IDNumber = '"
		    + taxpayerNumber.getNumber() + "'");
	try {
	    if (source.getNaturalPersonBool() == 1)
		return subjectPersonService.getByIIN(taxpayerNumber);
	    else
		return subjectCompanyService.getByBIN(taxpayerNumber);
	} catch (IllegalArgument e) {
	    throw e.getRuntime();
	}
    }
}
