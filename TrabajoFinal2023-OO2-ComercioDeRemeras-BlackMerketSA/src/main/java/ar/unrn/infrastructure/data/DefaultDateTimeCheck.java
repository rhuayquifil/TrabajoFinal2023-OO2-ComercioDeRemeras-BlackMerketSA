package ar.unrn.infrastructure.data;

import java.util.Calendar;

import ar.unrn.domain.portsout.DateTimeCheck;

public class DefaultDateTimeCheck implements DateTimeCheck {

	public DefaultDateTimeCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean esDomingo() {
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}
		return false;
	}

	@Override
	public boolean esSabado() {
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			return true;
		}
		return false;
	}

	@Override
	public boolean horarioEntreLas8amY10am() {
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.HOUR_OF_DAY) >= 8 && calendar.get(Calendar.HOUR_OF_DAY) < 10) {
			return true;
		}
		return false;
	}

}
