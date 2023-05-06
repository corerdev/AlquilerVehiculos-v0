package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.Clientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.Vehiculos;

public class ModeloCascada extends Modelo {

	public ModeloCascada(FactoriaFuenteDatos fuentedatos) {

		super(fuentedatos);

	}

	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: El alquiler a devolver.");
		}

		Vehiculo vehiculoTemp = Vehiculo.copiar(vehiculo);
		this.vehiculos.insertar(vehiculoTemp);
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: El alquiler a devolver.");
		}

		Cliente clienteTemp = new Cliente(cliente);
		this.clientes.insertar(clienteTemp);
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		Cliente clienteTemp = clientes.buscar(alquiler.getCliente());

		Vehiculo vehiculoTemp = vehiculos.buscar(alquiler.getVehiculo());

		if (clienteTemp == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		} else if (vehiculoTemp == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
		} else {
			Alquiler alquilerTemp = new Alquiler(clienteTemp, vehiculoTemp, alquiler.getFechaAlquiler());
			this.alquileres.insertar(alquilerTemp);
		}

	}

	public Cliente buscar(Cliente cliente) {
		if (clientes.buscar(cliente) == null) {
			return null;
		} else {
			Cliente clienteABuscar = new Cliente(clientes.buscar(cliente));

			return clienteABuscar;
		}

	}

	public Vehiculo buscar(Vehiculo vehiculo) {

		if (vehiculos.buscar(vehiculo) == null) {
			return null;
		} else {
			Vehiculo vehiculoABuscar = Vehiculo.copiar(vehiculos.buscar(vehiculo));

			return vehiculoABuscar;
		}

	}

	public Alquiler buscar(Alquiler alquiler) {
		if (alquileres.buscar(alquiler) == null) {
			return null;
		} else {
			Alquiler alquilerABuscar = new Alquiler(alquileres.buscar(alquiler));

			return alquilerABuscar;
		}

	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		this.clientes.modificar(cliente, nombre, telefono);
	}

	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: El alquiler a devolver.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("Error fechaDevolucion alquiler nulo");
		}

		alquileres.devolver(cliente, fechaDevolucion);

	}


	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: El alquiler a devolver.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("Error fechaDevolucion alquiler nulo");
		}

		alquileres.devolver(vehiculo, fechaDevolucion);

	}


	public void borrar(Cliente cliente) throws OperationNotSupportedException {

		for (Alquiler alquiler : this.alquileres.get(cliente)) {
			// if (alquiler.getCliente() != null && alquiler.getCliente().equals(cliente)) {

			alquileres.borrar(alquiler);
			// }

		}
		this.clientes.borrar(cliente);

	}

	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {

		for (Alquiler alquiler : this.alquileres.get(vehiculo)) {

			this.alquileres.borrar(alquiler);

		}
		this.vehiculos.borrar(vehiculo);

	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		this.alquileres.borrar(alquiler);
	}

	public List<Cliente> getClientes() {

		List<Cliente> coleccionTemp = new ArrayList<Cliente>();
		Cliente clienteTemp;

		for (Cliente cliente : clientes.get()) {
			clienteTemp = new Cliente(cliente);
			coleccionTemp.add(clienteTemp);

		}

		return coleccionTemp;

	}

	public List<Vehiculo> getVehiculos() {

		List<Vehiculo> coleccionTemp = new ArrayList<Vehiculo>();
		Vehiculo vehiculoTemp;

		for (Vehiculo vehiculo : vehiculos.get()) {
			vehiculoTemp = Vehiculo.copiar(vehiculo);
			coleccionTemp.add(vehiculoTemp);

		}

		return coleccionTemp;

	}

	public List<Alquiler> getAlquileres() {

		/*List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();
		Alquiler alquilerTemp;

		for (Alquiler alquiler : alquileres.get()) {
			alquilerTemp = new Alquiler(alquiler);
			coleccionTemp.add(alquilerTemp);

		}

		return coleccionTemp;*/
		return alquileres.get();

	}

	public List<Alquiler> getAlquileres(Cliente cliente) {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();
		Alquiler alquilerTemp = null;

		for (Alquiler alquiler : alquileres.get(cliente)) {

			alquilerTemp = new Alquiler(alquiler);
			coleccionTemp.add(alquilerTemp);

		}

		return coleccionTemp;

	}

	public List<Alquiler> getAlquileres(Vehiculo vehiculo) {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();
		Alquiler alquilerTemp = null;

		for (Alquiler alquiler : alquileres.get(vehiculo)) {
			alquilerTemp = new Alquiler(alquiler);
			coleccionTemp.add(alquilerTemp);

		}

		return coleccionTemp;

	}
}
