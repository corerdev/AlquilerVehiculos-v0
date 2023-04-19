package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import javax.naming.OperationNotSupportedException;

public interface IFuenteDatos {

	IClientes crearClientes() throws OperationNotSupportedException;

	IVehiculos crearVehiculos() throws OperationNotSupportedException;

	IAlquileres crearAlquileres() throws OperationNotSupportedException;

}