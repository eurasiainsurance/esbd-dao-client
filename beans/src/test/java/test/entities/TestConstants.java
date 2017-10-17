package test.entities;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.SubjectType;
import com.lapsa.insurance.esbd.domain.entities.general.SubjectCompanyEntity;
import com.lapsa.insurance.esbd.domain.entities.general.SubjectPersonEntity;

public final class TestConstants {
    // Branch
    public static final long INVALID_BRANCH_ID = 999999999L;

    // CancelationReason
    public static final int INVALID_CANCELATION_REASON_ID = 999999999;

    // City
    public static final long INVALID_CITY_ID = 999999999L;

    // CompanyActivityKind
    public static final long INVALID_COMPANY_ACTIVITY_KIND_ID = 999999999L;

    // CountryRegion
    public static final int INVALID_COUNTRY_REGION_ID = 999999999;

    // Country
    public static final int INVALID_COUNTRY_ID = 999999999;

    // EconomicsSector
    public static final int INVALID_ECONOMICS_SECTOR_ID = 999999999;

    // IndentityCardType
    public static final int INVALID_IDENTITY_CARD_TYPE_ID = 999999999;

    // InsuranceCompany
    public static final long INVALID_INSURANCE_COMPANY_ID = 999999999L;

    // MaritalStatus
    public static final int INVALID_MARITAL_STATUS_ID = 999999999;

    // Sex
    public static final int INVALID_SEX_ID = 999999999;

    // User
    public static final long INVALID_USER_ID = 999999999L;

    // InsuranceAgeExpirience
    public static final int INVALID_INSURANCE_AGE_EXPIRIENCE_ID = 999999999;

    // InsuranceClassType
    public static final int VALID_INSURANCE_CLASS_TYPE_ID = 14;
    public static final int INVALID_INSURANCE_CLASS_TYPE_ID = 999999999;
    public static final String INVALID_INSURANCE_CLASS_TYPE_CODE = "QQ";

    // Subject
    public static final long INVALID_SUBJECT_PERSON_ID = 999999999L;
    public static final long VALID_SUBJECT_PERSON_ID = 14132412; // Evsyukovs ID
    public static final InsuranceClassType VALID_CLASS_TYPE_FOR_CLIENT = InsuranceClassType.CLASS_13; // Evsyukovs
													      // Class
    public static final int INVALID_VEHICLE_AGE_CLASS_ID = 999999999;
    public static final int INVALID_VEHICLE_CLASS_ID = 999999999;

    // VehicleManufacturer
    public static final long VALID_VEHICLE_MANUFACTURER_ID = 45755L; // INFINTI
								     // FX35
    public static final String VALID_VEHICLE_MANUFACTURER_NAME = "INFINTI FX35";
    public static final long ININVALID_VEHICLE_MANUFACTURER_ID = 999999999L;
    public static final String INVALID_VEHICLE_MANUFACTURER_NAME = "QQQ";

    // VehicleModel
    public static final long VALID_VEHICLE_MODEL_ID = 143827L;
    public static final String VALID_VEHICLE_MODEL_NAME = "INFINITI FX35";
    public static final long ININVALID_VEHICLE_MODEL_ID = 999999999L;

    // Subject
    public static final long[] VALID_SUBJECT_IDS = new long[] { 1, 100 };
    public static final String[] VALID_SUBJECT_ID_NUMBERS = new String[] {
	    "930840000071",
	    "581114350286" };
    public static final SubjectType[] VALID_SUBJECT_TYPES = new SubjectType[] {
	    SubjectType.COMPANY,
	    SubjectType.PERSON };
    public static final Class<?>[] VALID_SUBJECT_CLASSES = new Class<?>[] {
	    SubjectCompanyEntity.class,
	    SubjectPersonEntity.class };

    public static final long INVALID_SUBJECT_ID = -1L;
    public static final String INVALID_SUBJECT_ID_NUMBER = "6666";

    // SubjectCompany
    public static final long[] VALID_SUBJECT_COMPANY_IDS = new long[] { 1, 2 };
    public static final long[] INVALID_SUBJECT_COMPANY_IDS = new long[] { 100, -1 };
    public static final String[] VALID_SUBJECT_COMPANY_BINS = new String[] { "930840000071" };
    public static final String[] INVALID_SUBJECT_COMPANY_BINS = new String[] { "581114350286", "ZZZZ" };

    // SubjectPerson
    public static final long[] VALID_SUBJECT_PERSON_IDS = new long[] { 100, 14132412 };
    public static final long[] INVALID_SUBJECT_PERSON_IDS = new long[] { 1, 2, -1 };
    public static final String[] VALID_SUBJECT_PERSON_IINS = new String[] { "581114350286", "870622300359" };
    public static final String[] INVALID_SUBJECT_PERSON_IINS = new String[] { "930840000071", "ZZZZ" };

    // Policy
    public static final Long INVALID_POLICY_ID = 1L;
    public static final String INVALID_POLICY_NUMBER = "ZZZ";

    // KZCity
    public static final int INVALID_KZ_CITY_ID = 666;

}
