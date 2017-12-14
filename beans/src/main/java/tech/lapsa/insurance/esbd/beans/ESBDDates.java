package tech.lapsa.insurance.esbd.beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

public final class ESBDDates {

    private ESBDDates() {
    }

    private static final String ESBD_DATE_FORMAT_PATTERN = "dd.MM.yyyy";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(ESBD_DATE_FORMAT_PATTERN);
    private static final DateTimeFormatter DITE_TIME_FORMATTER = new DateTimeFormatterBuilder()
	    .appendPattern(ESBD_DATE_FORMAT_PATTERN).toFormatter();

    public static Calendar convertESBDDateToCalendar(final String esbdDate) {
	if (esbdDate == null || esbdDate.trim().equals(""))
	    return null;
	try {
	    final Calendar date = Calendar.getInstance();
	    date.setTime(DATE_FORMAT.parse(esbdDate));
	    return date;
	} catch (final ParseException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public static LocalDate convertESBDDateToLocalDate(final String esbdDate) {
	if (esbdDate == null || esbdDate.trim().equals(""))
	    return null;
	try {
	    return LocalDate.parse(esbdDate, DITE_TIME_FORMATTER);
	} catch (final DateTimeParseException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public static Date convertESBDDateToDate(final String esbdDate) {
	if (esbdDate == null || esbdDate.trim().equals(""))
	    return null;
	try {
	    return DATE_FORMAT.parse(esbdDate);
	} catch (final ParseException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public static String convertCalendarToESBDDate(final Calendar calendar) {
	if (calendar == null)
	    return null;
	return convertDateToESBDDate(calendar.getTime());
    }

    public static String convertLocalDateToESBDDate(final LocalDate localDate) {
	if (localDate == null)
	    return null;
	return localDate.format(DITE_TIME_FORMATTER);
    }

    public static String convertDateToESBDDate(final Date date) {
	if (date == null)
	    return null;
	return DATE_FORMAT.format(date);
    }
}
