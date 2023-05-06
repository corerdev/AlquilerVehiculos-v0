package org.iesalandalus.programacion.alquilervehiculos.controlador;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public interface IControlador {

	void comenzar() throws OperationNotSupportedException;

	void terminar();

	void insertar(Cliente cliente) throws OperationNotSupportedException;

	void insertar(Vehiculo vehiculo) throws OperationNotSupportedException;

	void insertar(Alquiler alquiler) throws OperationNotSupportedException;

	Cliente buscar(Cliente cliente);

	Vehiculo buscar(Vehiculo vehiculo);

	Alquiler buscar(Alquiler alquiler);

	void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException;

	void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException;

	void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException;

	void borrar(Vehiculo vehiculo) throws OperationNotSupportedException;

	void borrar(Cliente cliente) throws OperationNotSupportedException;

	void borrar(Alquiler alquiler) throws OperationNotSupportedException;

	List<Cliente> getClientes();

	List<Vehiculo> getVehiculos();

	List<Alquiler> getAlquileres();

	List<Alquiler> getAlquileres(Cliente cliente);

	List<Alquiler> getAlquileres(Vehiculo vehiculo);

}