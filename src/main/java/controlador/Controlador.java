package controlador;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import dominio.Alquiler;
import dominio.Cliente;
import dominio.Turismo;
import modelo.Modelo;
import vista.Vista;

public class Controlador {

	Vista vista;
	Modelo modelo;

	public Controlador(Modelo modelo, Vista vista) {

		if (modelo == null) {

			throw new NullPointerException("Error modelo nulo");
		}
		if (vista == null) {

			throw new NullPointerException("Error vista nulo");
		}

		this.modelo = modelo;
		this.vista = vista;
		vista.setControlador(this);

	}

	public void comenzar() {

		modelo.comenzar();
		vista.comenzar();
	}

	public void terminar() {

		modelo.terminar();
		vista.terminar();
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		modelo.insertar(cliente);
	}

	public void insertar(Turismo turismo) throws OperationNotSupportedException {
		modelo.insertar(turismo);
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		modelo.insertar(alquiler);
	}

	public Cliente buscar(Cliente cliente) {

		return modelo.buscar(cliente);
	}

	public Turismo buscar(Turismo turismo) {

		return modelo.buscar(turismo);
	}

	public Alquiler buscar(Alquiler alquiler) {

		return modelo.buscar(alquiler);
	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		modelo.modificar(cliente, nombre, telefono);
	}

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		modelo.devolver(alquiler, fechaDevolucion);
	}

	public void borrar(Turismo turismo) throws OperationNotSupportedException {

		modelo.borrar(turismo);
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {

		modelo.borrar(cliente);
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		modelo.borrar(alquiler);
	}

	public List<Cliente> getClientes() {

		return modelo.getClientes();
	}

	public List<Turismo> getTurismos() {

		return modelo.getTurismos();
	}

	public List<Alquiler> getAlquileres() {

		return modelo.getAlquileres();
		
	}
	public List<Alquiler> getAlquileres(Cliente cliente) {

		return modelo.getAlquileres(cliente);
	}
	
	public List<Alquiler> getAlquileres(Turismo turismo) {

		return modelo.getAlquileres(turismo);
	}
	
}
