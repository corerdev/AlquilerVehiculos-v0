package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.Consola;

public class Alquileres implements IAlquileres {

	List<Alquiler> coleccionAlquileres;


	public Alquileres() {

		coleccionAlquileres = new ArrayList<Alquiler>();

	}


	
	
	@Override
	public List<Alquiler> get() {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();

		for (Alquiler alquiler : coleccionAlquileres) {
			coleccionTemp.add(alquiler);

		}

		return coleccionTemp;

	}

	@Override
	public List<Alquiler> get(Cliente cliente) {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente))
				coleccionTemp.add(alquiler);

		}

		return coleccionTemp;

	}

	@Override
	public List<Alquiler> get(Vehiculo vehiculo) {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getVehiculo().equals(vehiculo))
				coleccionTemp.add(alquiler);

		}

		return coleccionTemp;

	}

	@Override
	public int getCantidad() {
		int cant = coleccionAlquileres.size();
		return cant;
	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
			} else if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
			} else if (alquiler.getVehiculo().equals(vehiculo) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
					|| alquiler.getFechaDevolucion().equals(fechaAlquiler))) {
				throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
			} else if (alquiler.getCliente().equals(cliente) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
					|| alquiler.getFechaDevolucion().equals(fechaAlquiler))) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");

			}

		}

	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");

		} /*
			 * else if (coleccionAlquileres.contains(alquiler)) { throw new
			 * OperationNotSupportedException("ERROR: Ya existe un turismo con esa matrícula."
			 * ); }
			 */ else {
			comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
			coleccionAlquileres.add(alquiler);
		}

	}
	
	private Alquiler getAlquilerAbierto (Cliente cliente) {
		List<Alquiler> listaTemporal = get(cliente);
		Alquiler alquilerADevolver = null;
		for (Alquiler alquiler : listaTemporal) {
			if (alquiler.getFechaDevolucion()==null) {
				alquilerADevolver = alquiler;
			}
		}
		return alquilerADevolver;
	}

	private Alquiler getAlquilerAbierto (Vehiculo vehiculo) {
		List<Alquiler> listaTemporal = get(vehiculo);
		Alquiler alquilerADevolver = null;
		for (Alquiler alquiler : listaTemporal) {
			if (alquiler.getFechaDevolucion()==null) {
				alquilerADevolver = alquiler;
			}
		}
		return alquilerADevolver;
	}
	
	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler con cliente nulo.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		if (getAlquilerAbierto(cliente)==null) {
			throw new OperationNotSupportedException("ERROR: El cliente no tiene ningun alquiler para devolver.");
		}
		int index = coleccionAlquileres.indexOf(getAlquilerAbierto(cliente));
		if (index != -1) {
			coleccionAlquileres.get(index).devolver(fechaDevolucion);

		} else if (index == -1) {

			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}
	
	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler con vehiculo nulo.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		if (getAlquilerAbierto(vehiculo)==null) {
			throw new OperationNotSupportedException("ERROR: El cliente no tiene ningun alquiler para devolver.");
		}
		int index = coleccionAlquileres.indexOf(getAlquilerAbierto(vehiculo));
		if (index != -1) {
			coleccionAlquileres.get(index).devolver(fechaDevolucion);

		} else if (index == -1) {

			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");

		} else if (!coleccionAlquileres.contains(alquiler)) {
			return null;
		} else {
			int alquilerADevolver = coleccionAlquileres.indexOf(alquiler);
			return coleccionAlquileres.get(alquilerADevolver);
		}
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");

		} else if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		} else {
			coleccionAlquileres.remove(alquiler);
		}
	}




	@Override
	public void comenzar() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		
	}
}
