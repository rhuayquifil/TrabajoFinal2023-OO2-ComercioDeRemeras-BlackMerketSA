package ar.unrn.test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

import ar.unrn.domain.portsout.DateTimeCheck;

public class FakeDefaultDateTimeCheck implements DateTimeCheck {

	private LocalDateTime date;
	private Calendar calendar;

	public FakeDefaultDateTimeCheck(LocalDateTime date) {
		this.date = date;

		ZoneId zonaHoraria = ZoneId.systemDefault();

		// Convertir LocalDateTime a Calendar
		this.calendar = Calendar.getInstance(TimeZone.getTimeZone(zonaHoraria));
		calendar.set(Calendar.YEAR, date.getYear());
		calendar.set(Calendar.MONTH, date.getMonthValue() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
		calendar.set(Calendar.HOUR_OF_DAY, date.getHour());
		calendar.set(Calendar.MINUTE, date.getMinute());
		calendar.set(Calendar.SECOND, date.getSecond());
		calendar.set(Calendar.MILLISECOND, date.getNano() / 1000000);
	}

	@Override
	public boolean esDomingo() {
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}
		return false;
	}

	@Override
	public boolean esSabado() {
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			return true;
		}
		return false;
	}

	@Override
	public boolean horarioEntreLas8amY10am() {
		if (calendar.get(Calendar.HOUR_OF_DAY) >= 8 && calendar.get(Calendar.HOUR_OF_DAY) < 10) {
			return true;
		}
		return false;
	}

	@Override
	public boolean esFeriado(LocalDateTime dateTime) {
		//
		return true;
	}

	@Override
	public LocalDateTime now() {
		return date;
	}

}
