package ar.unrn.domain.portsout;

public interface DateTimeCheck {
	boolean esDomingo();

	boolean esSabado();

	boolean horarioEntreLas8amY10am();
}
