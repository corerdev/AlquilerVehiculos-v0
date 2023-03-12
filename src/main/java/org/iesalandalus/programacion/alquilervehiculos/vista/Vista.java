package org.iesalandalus.programacion.alquilervehiculos.vista;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;


public abstract class Vista {
	
	protected Controlador controlador;
	
	public void setControlador(Controlador controlador) {

		if (controlador == null) {
			throw new NullPointerException("Oye, me estas pasando un controlador nulo");
		} else {

			this.controlador = controlador;
		}

	}
	
	public abstract void comenzar();
	
	public abstract void terminar();
	
	

}
