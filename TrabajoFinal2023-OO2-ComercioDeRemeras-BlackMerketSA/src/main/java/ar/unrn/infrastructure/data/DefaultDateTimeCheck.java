package ar.unrn.infrastructure.data;

import ar.unrn.domain.portsout.DateTimeCheck;

public class DefaultDateTimeCheck implements DateTimeCheck {

	public DefaultDateTimeCheck() {
		super();
		// TODO Auto-generated constructor stub
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

}
