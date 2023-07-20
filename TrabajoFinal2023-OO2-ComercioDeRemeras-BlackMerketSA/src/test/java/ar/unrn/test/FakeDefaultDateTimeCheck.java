package ar.unrn.test;

import java.time.LocalDateTime;

import ar.unrn.domain.portsout.DateTimeCheck;

public class FakeDefaultDateTimeCheck implements DateTimeCheck {

	private LocalDateTime date;

	public FakeDefaultDateTimeCheck(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public boolean esDomingo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esSabado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean horarioEntreLas8amY10am() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esFeriado(LocalDateTime dateTime) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LocalDateTime now() {
		return date;
	}

}
