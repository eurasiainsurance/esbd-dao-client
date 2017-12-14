package tech.lapsa.insurance.esbd.beans.entities;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.jaxws.wsimport.Client;
import tech.lapsa.insurance.esbd.NotFound;
import tech.lapsa.insurance.esbd.dict.CompanyActivityKindEntityService.CompanyActivityKindEntityServiceLocal;
import tech.lapsa.insurance.esbd.entities.SubjectCompanyEntity;
import tech.lapsa.insurance.esbd.entities.SubjectCompanyEntityService.SubjectCompanyEntityServiceLocal;
import tech.lapsa.insurance.esbd.entities.SubjectCompanyEntityService.SubjectCompanyEntityServiceRemote;
import tech.lapsa.insurance.esbd.entities.SubjectPersonEntity;
import tech.lapsa.java.commons.exceptions.IllegalArgument;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;

@Stateless
public class SubjectCompanyEntityServiceBean extends ASubjectEntityService
	implements SubjectCompanyEntityServiceLocal, SubjectCompanyEntityServiceRemote {

    @EJB
    private CompanyActivityKindEntityServiceLocal companyActivityKindService;

    @Override
    public SubjectCompanyEntity getById(final Integer id) throws NotFound, IllegalArgument {
	MyNumbers.requireNonZero(IllegalArgument::new, id, "id");
	try (Connection con = pool.getConnection()) {
	    final Client source = con.getClientByID(id.intValue());
	    if (source == null)
		throw new NotFound(SubjectCompanyEntity.class.getSimpleName() + " not found with ID = '" + id + "'");
	    final boolean isNotPerson = source.getNaturalPersonBool() == 0;
	    if (!isNotPerson)
		throw new NotFound(SubjectCompanyEntity.class.getSimpleName() + " not found with ID = '" + id
			+ "'. It was a " + SubjectPersonEntity.class.getName());
	    return convert(source);
	}
    }

    @Override
    public SubjectCompanyEntity getByBIN(final TaxpayerNumber taxpayerNumber) throws NotFound, IllegalArgument {
	MyObjects.requireNonNull(IllegalArgument::new, taxpayerNumber, "taxpayerNumber"); //
	TaxpayerNumber.requireValid(IllegalArgument::new, taxpayerNumber);

	final Client source = fetchClientByIdNumber(taxpayerNumber, false, true);
	if (source == null)
	    throw new NotFound(
		    SubjectCompanyEntity.class.getSimpleName() + " not found with 'bin' = '"
			    + taxpayerNumber.getNumber() + "'");
	final boolean isNotPerson = source.getNaturalPersonBool() == 0;
	if (!isNotPerson)
	    throw new NotFound(SubjectCompanyEntity.class.getSimpleName() + " not found with 'bin' = '"
		    + taxpayerNumber.getNumber()
		    + "'. It was a " + SubjectPersonEntity.class.getName());
	return convert(source);
    }

    protected SubjectCompanyEntity convert(final Client source) {
	final SubjectCompanyEntity target = new SubjectCompanyEntity();
	fillValues(source, target);
	return target;
    }

    protected void fillValues(final Client source, final SubjectCompanyEntity target) {
	super.fillValues(source, target);

	// Juridical_Person_Name s:string Наименование (для юр. лица)
	target.setCompanyName(source.getJuridicalPersonName());

	// MAIN_CHIEF s:string Первый руководитель
	target.setHeadName(source.getMAINCHIEF());

	// MAIN_ACCOUNTANT s:string Главный бухгалтер
	target.setAccountantName(source.getMAINACCOUNTANT());

	// ACTIVITY_KIND_ID s:int Вид деятельности (справочник ACTIVITY_KINDS)
	target.setCompanyActivityKindId(new Long(source.getACTIVITYKINDID()));

	// non mandatory field
	target.setCompanyActivityKind(MyOptionals.of(source.getACTIVITYKINDID()) //
		.flatMap(id -> MyOptionals.ifAnyException(() -> companyActivityKindService.getById(id))) //
		.orElse(null));
    }
}
