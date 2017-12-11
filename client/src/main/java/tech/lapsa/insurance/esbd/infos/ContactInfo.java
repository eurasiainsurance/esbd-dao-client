package tech.lapsa.insurance.esbd.infos;

import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.insurance.esbd.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

/**
 * Класс для представления контактных данных
 * 
 * @author vadim.isaev
 *
 */
@HashCodePrime(43)
public class ContactInfo extends Domain {

    private static final long serialVersionUID = 1L;

    // PHONES s:string Номера телефонов
    private PhoneNumber phone;

    // EMAIL s:string Адрес электронной почты
    private String email;

    // Address s:string Адрес
    private String homeAdress;

    // WWW s:string Сайт
    private String siteUrl;

    // GENERATED

    public PhoneNumber getPhone() {
	return phone;
    }

    public void setPhone(PhoneNumber phone) {
	this.phone = phone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getHomeAdress() {
	return homeAdress;
    }

    public void setHomeAdress(String postAdress) {
	this.homeAdress = postAdress;
    }

    public String getSiteUrl() {
	return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
	this.siteUrl = siteUrl;
    }

}
