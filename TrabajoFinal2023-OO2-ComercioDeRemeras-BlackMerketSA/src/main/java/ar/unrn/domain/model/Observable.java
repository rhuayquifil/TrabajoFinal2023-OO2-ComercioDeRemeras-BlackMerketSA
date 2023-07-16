package ar.unrn.domain.model;

import java.util.ArrayList;
import java.util.List;

import ar.unrn.domain.portsin.Observer;

public abstract class Observable {

	private List<Observer> listaSubscriptores;

	public Observable() {
		super();
		this.listaSubscriptores = new ArrayList<Observer>();
	}

	public void subscribir(Observer subscriptor) {
		this.listaSubscriptores.add(subscriptor);
	}

	protected void notificar(String valor) {
		for (Observer subscriptor : listaSubscriptores) {
			subscriptor.actualizar(valor);
		}
	}
}
