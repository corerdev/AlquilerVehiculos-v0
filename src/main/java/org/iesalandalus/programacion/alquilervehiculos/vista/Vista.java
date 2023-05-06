package org.iesalandalus.programacion.alquilervehiculos.vista;

import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;

import javafx.application.Application;


public abstract class Vista extends Application {
	
	protected IControlador controlador;
	
	public void setControlador(IControlador controlador) {

		if (controlador == null) {
			throw new NullPointerException("Oye, me estas pasando un controlador nulo");
		} else {

			this.controlador = controlador;
		}

	}
	
	public abstract void comenzar();
	
	public abstract void terminar();
	
	

}
