package org.iesalandalus.programacion.alquilervehiculos;

import controlador.Controlador;
import modelo.Modelo;
import vista.Vista;

public class MainApp {

	public static void main(String[] args) {
		// Ánimo!!!!
		// Muchas gracias!
		
		Modelo modeloApp = new Modelo();
		Vista vistaApp = new Vista();
		Controlador controladorApp = new Controlador(modeloApp, vistaApp);
		controladorApp.comenzar();
	}

}
