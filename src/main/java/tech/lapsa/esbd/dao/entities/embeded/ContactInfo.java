package tech.lapsa.esbd.dao.entities.embeded;

import java.util.function.Consumer;

import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.esbd.dao.entities.AEntity;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

/**
 * Класс для представления контактных данных
 *
 * @author vadim.isaev
 *
 */
@HashCodePrime(43)
public class ContactInfo extends AEntity {

    private static final long serialVersionUID = 1L;

    public static final ContactInfoBuilder builder() {
	return new ContactInfoBuilder();
    }

    public static final class ContactInfoBuilder {

	private PhoneNumber phone;
	private String email;
	private String homeAdress;
	private String siteUrl;

	private ContactInfoBuilder() {
	}

	public ContactInfoBuilder withPhone(final PhoneNumber phone) {
	    this.phone = MyObjects.requireNonNull(phone, "phone");
	    return this;
	}

	public ContactInfoBuilder withEmail(final String email) {
	    this.email = MyStrings.requireNonEmpty(email, "email");
	    return this;
	}

	public ContactInfoBuilder withHomeAdress(final String homeAdress) {
	    this.homeAdress = MyStrings.requireNonEmpty(homeAdress, "homeAdress");
	    return this;
	}

	public ContactInfoBuilder withSiteUrl(final String siteUrl) {
	    this.siteUrl = MyStrings.requireNonEmpty(siteUrl, "siteUrl");
	    return this;
	}

	public ContactInfo build() {
	    final ContactInfo res = new ContactInfo();
	    res.phone = phone;
	    res.email = email;
	    res.homeAdress = homeAdress;
	    res.siteUrl = siteUrl;
	    return res;
	}

	public void buildTo(final Consumer<ContactInfo> consumer) {
	    consumer.accept(build());
	}
    }

    private ContactInfo() {
    }

    // phone

    private PhoneNumber phone;

    public PhoneNumber getPhone() {
	return phone;
    }

    // email

    private String email;

    public String getEmail() {
	return email;
    }

    // homeAdress

    private String homeAdress;

    public String getHomeAdress() {
	return homeAdress;
    }

    // siteUrl

    private String siteUrl;

    public String getSiteUrl() {
	return siteUrl;
    }
}
