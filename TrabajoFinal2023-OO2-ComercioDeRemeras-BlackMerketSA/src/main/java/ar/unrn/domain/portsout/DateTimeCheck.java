package ar.unrn.domain.portsout;

import java.time.LocalDateTime;

public interface DateTimeCheck {

	LocalDateTime now();

	boolean esDomingo();

	boolean esSabado();

	boolean horarioEntreLas8amY10am();

	boolean esFeriado(LocalDateTime dateTime);
}
