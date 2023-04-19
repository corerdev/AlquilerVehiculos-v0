package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public class FuenteDatosFicheros implements IFuenteDatos {

	@Override
	public IClientes crearClientes() throws OperationNotSupportedException {
		Clientes coleccionClientes = Clientes.getInstancia();
		return coleccionClientes;

	}

	@Override
	public IVehiculos crearVehiculos() throws OperationNotSupportedException {
		Vehiculos coleccionVehiculos = Vehiculos.getInstancia();
		return coleccionVehiculos;

	}

	@Override
	public IAlquileres crearAlquileres() throws OperationNotSupportedException {
		
		Alquileres coleccionAlquileres = Alquileres.getInstancia();
		return coleccionAlquileres;

	}
}
