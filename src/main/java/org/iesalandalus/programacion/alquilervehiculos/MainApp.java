package org.iesalandalus.programacion.alquilervehiculos;

import javax.naming.OperationNotSupportedException; 

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
//import org.iesalandalus.programacion.alquilervehiculos.vista.texto.VistaTexto;

public class MainApp {

	public static void main(String[] args) {
		// Ánimo!!!!
		// Muchas gracias!
		
		Modelo modeloApp = new ModeloCascada(FactoriaFuenteDatos.FICHEROS);
		Vista vistaApp = new VistaGrafica();
		IControlador controladorApp = new Controlador(modeloApp, vistaApp);
		try {
			controladorApp.comenzar();
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
