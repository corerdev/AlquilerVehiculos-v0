package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import dominio.Alquiler;
import dominio.Cliente;
import dominio.Turismo;
import negocio.Alquileres;
import negocio.Clientes;
import negocio.Turismos;

public class Modelo {

	private Clientes clientes;
	private Alquileres alquileres;
	private Turismos turismos;

	public void comenzar() {

		clientes = new Clientes();
		alquileres = new Alquileres();
		turismos = new Turismos();

	}

	public void terminar() {
		System.out.println("El modelo ha terminado.");
	}

	public void insertar(Turismo turismo) throws OperationNotSupportedException {
		if (turismo==null) {
			throw new NullPointerException("ERROR: El alquiler a devolver.");
		}

		Turismo turismoTemp = new Turismo(turismo);
		this.turismos.insertar(turismoTemp);
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente==null) {
			throw new NullPointerException("ERROR: El alquiler a devolver.");
		}

		Cliente clienteTemp = new Cliente(cliente);
		this.clientes.insertar(clienteTemp);
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler==null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		Cliente clienteTemp = clientes.buscar(alquiler.getCliente());
		Turismo turismoTemp = turismos.buscar(alquiler.getTurismo());
		//int i = this.clientes.get().indexOf(alquiler.getCliente());
		//int u = this.turismos.get().indexOf(alquiler.getTurismo());
		
		if (clienteTemp == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		} else if (turismoTemp == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
		}else {
			Alquiler alquilerTemp = new Alquiler(clienteTemp, turismoTemp, alquiler.getFechaAlquiler());
			this.alquileres.insertar(alquilerTemp);
		} 
			

		
		
	}

	public Cliente buscar(Cliente cliente) {
		Cliente clienteABuscar = new Cliente(clientes.buscar(cliente));

		/*
		 * if (clienteABuscar == null) { throw new
		 * NullPointerException("Error cliente no encontrado");
		 * 
		 * } else {
		 */

		return clienteABuscar;

	}

	public Turismo buscar(Turismo turismo) {
		Turismo turismoABuscar = new Turismo(turismos.buscar(turismo));

		/*
		 * if (turismoABuscar == null) { throw new
		 * NullPointerException("Error turismo no encontrado");
		 * 
		 * } else {
		 */

		return turismoABuscar;

	}

	public Alquiler buscar(Alquiler alquiler) {
		Alquiler alquilerABuscar = new Alquiler(alquileres.buscar(alquiler));

		/*
		 * if (alquilerABuscar == null) { throw new
		 * NullPointerException("Error turismo no encontrado");
		 * 
		 * } else {
		 */

		return alquilerABuscar;

	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		this.clientes.modificar(cliente, nombre, telefono);
	}

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (alquiler==null) {
			throw new NullPointerException("ERROR: El alquiler a devolver.");
		}
		if (fechaDevolucion==null) {
			throw new NullPointerException("Error fechaDevolucion alquiler nulo");
		}
		
		if (this.alquileres.buscar(alquiler)==null) {
			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
		} else {
			
			alquiler.devolver(fechaDevolucion);

		}

	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {

		for (Alquiler alquiler : this.alquileres.get(cliente)) {
			// if (alquiler.getCliente() != null && alquiler.getCliente().equals(cliente)) {

			alquileres.borrar(alquiler);
			// }

		}
		this.clientes.borrar(cliente);

	}

	public void borrar(Turismo turismo) throws OperationNotSupportedException {

		for (Alquiler alquiler : this.alquileres.get(turismo)) {

			this.alquileres.borrar(alquiler);

		}
		this.turismos.borrar(turismo);

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

	public List<Turismo> getTurismos() {

		List<Turismo> coleccionTemp = new ArrayList<Turismo>();
		Turismo turismoTemp;

		for (Turismo turismo : turismos.get()) {
			turismoTemp = new Turismo(turismo);
			coleccionTemp.add(turismoTemp);

		}

		return coleccionTemp;

	}

	public List<Alquiler> getAlquileres() {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();
		Alquiler alquilerTemp;

		for (Alquiler alquiler : alquileres.get()) {
			alquilerTemp = new Alquiler(alquiler);
			coleccionTemp.add(alquilerTemp);

		}

		return coleccionTemp;

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

	public List<Alquiler> getAlquileres(Turismo turismo) {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();
		Alquiler alquilerTemp = null;

		for (Alquiler alquiler : alquileres.get(turismo)) {
			alquilerTemp = new Alquiler(alquiler);
			coleccionTemp.add(alquilerTemp);

		}

		return coleccionTemp;

	}
}
