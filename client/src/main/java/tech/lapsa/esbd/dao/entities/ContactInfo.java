package tech.lapsa.esbd.dao.entities;

import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.esbd.dao.Domain;
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
    String phoneRaw;
    PhoneNumber phone;

    public PhoneNumber getPhone() {
	return phone;
    }

    // EMAIL s:string Адрес электронной почты
    String email;

    public String getEmail() {
	return email;
    }

    // Address s:string Адрес
    String homeAdress;

    public String getHomeAdress() {
	return homeAdress;
    }

    // WWW s:string Сайт
    String siteUrl;

    public String getSiteUrl() {
	return siteUrl;
    }
}
