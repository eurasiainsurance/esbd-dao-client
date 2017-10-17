package com.lapsa.insurance.esbd.services.impl.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

public abstract class AbstractESBDServiceWS {
    private static final String ESBD_DATE_FORMAT_PATTERN = "dd.MM.yyyy";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(ESBD_DATE_FORMAT_PATTERN);
    private static final DateTimeFormatter DITE_TIME_FORMATTER = new DateTimeFormatterBuilder()
	    .appendPattern(ESBD_DATE_FORMAT_PATTERN).toFormatter();

    protected Calendar convertESBDDateToCalendar(String esbdDate) {
	if (esbdDate == null || esbdDate.trim().equals(""))
	    return null;
	try {
	    Calendar date = Calendar.getInstance();
	    date.setTime(DATE_FORMAT.parse(esbdDate));
	    return date;
	} catch (ParseException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    protected LocalDate convertESBDDateToLocalDate(String esbdDate) {
	if (esbdDate == null || esbdDate.trim().equals(""))
	    return null;
	try {
	    return LocalDate.parse(esbdDate, DITE_TIME_FORMATTER);
	} catch (DateTimeParseException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    protected Date convertESBDDateToDate(String esbdDate) {
	if (esbdDate == null || esbdDate.trim().equals(""))
	    return null;
	try {
	    return DATE_FORMAT.parse(esbdDate);
	} catch (ParseException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    protected String convertCalendarToESBDDate(Calendar calendar) {
	if (calendar == null)
	    return null;
	return convertDateToESBDDate(calendar.getTime());
    }

    protected String convertLocalDateToESBDDate(LocalDate localDate) {
	if (localDate == null)
	    return null;
	return localDate.format(DITE_TIME_FORMATTER);
    }

    protected String convertDateToESBDDate(Date date) {
	if (date == null)
	    return null;
	return DATE_FORMAT.format(date);
    }
}
