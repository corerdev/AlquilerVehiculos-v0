package negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import dominio.Alquiler;
import dominio.Cliente;
import dominio.Turismo;

public class Alquileres {

	List<Alquiler> coleccionAlquileres;

	public Alquileres() {

		coleccionAlquileres = new ArrayList<Alquiler>();

	}

	public List<Alquiler> get() {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();

		for (Alquiler alquiler : coleccionAlquileres) {
			coleccionTemp.add(alquiler);

		}

		return coleccionTemp;

	}

	public List<Alquiler> get(Cliente cliente) {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente))
				coleccionTemp.add(alquiler);

		}

		return coleccionTemp;

	}

	public List<Alquiler> get(Turismo turismo) {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getTurismo().equals(turismo))
				coleccionTemp.add(alquiler);

		}

		return coleccionTemp;

	}

	public int getCantidad() {
		int cant = coleccionAlquileres.size();
		return cant;
	}

	private void comprobarAlquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getTurismo().equals(turismo) && alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
			} else if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
			} else if (alquiler.getTurismo().equals(turismo) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
					|| alquiler.getFechaDevolucion().equals(fechaAlquiler))) {
				throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
			} else if (alquiler.getCliente().equals(cliente) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
					|| alquiler.getFechaDevolucion().equals(fechaAlquiler))) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");

			}

		}

	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");

		} /*
			 * else if (coleccionAlquileres.contains(alquiler)) { throw new
			 * OperationNotSupportedException("ERROR: Ya existe un turismo con esa matrícula."
			 * ); }
			 */ else {
			comprobarAlquiler(alquiler.getCliente(), alquiler.getTurismo(), alquiler.getFechaAlquiler());
			coleccionAlquileres.add(alquiler);
		}

	}

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		int index = coleccionAlquileres.indexOf(alquiler);
		if (index != -1) {
			coleccionAlquileres.get(index).devolver(fechaDevolucion);

		} else if (index == -1) {

			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}

	public Alquiler buscar(Alquiler alquiler) {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");

		} else if (!coleccionAlquileres.contains(alquiler)) {
			return null;
		} else {
			return alquiler;
		}
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");

		} else if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		} else {
			coleccionAlquileres.remove(alquiler);
		}
	}
}
