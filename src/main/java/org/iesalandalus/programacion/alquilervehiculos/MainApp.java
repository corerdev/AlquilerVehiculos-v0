package org.iesalandalus.programacion.alquilervehiculos;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.VistaTexto;

public class MainApp {

	public static void main(String[] args) {
		// Ánimo!!!!
		// Muchas gracias!
		
		Modelo modeloApp = new ModeloCascada(FactoriaFuenteDatos.MEMORIA.crear());
		Vista vistaApp = new VistaTexto();
		Controlador controladorApp = new Controlador(modeloApp, vistaApp);
		controladorApp.comenzar();
	}

}
