package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public class FuenteDatosMemoria implements IFuenteDatos {

	@Override
	public IClientes crearClientes() {
		Clientes coleccionClientes = new Clientes();
		return coleccionClientes;

	}

	@Override
	public IVehiculos crearVehiculos() {
		Vehiculos coleccionVehiculos = new Vehiculos();
		return coleccionVehiculos;

	}

	@Override
	public IAlquileres crearAlquileres() {
		
		Alquileres coleccionAlquileres = new Alquileres();
		return coleccionAlquileres;

	}

}
