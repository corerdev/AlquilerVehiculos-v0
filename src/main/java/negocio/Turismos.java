package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;


import dominio.Turismo;

public class Turismos {

	

	List<Turismo> coleccionTurismos;

	public Turismos() {

		coleccionTurismos = new ArrayList<Turismo>();

	}

	public List<Turismo> get() {

		List<Turismo> coleccionTemp = new ArrayList<Turismo>();

		for (Turismo turismo : coleccionTurismos) {
			coleccionTemp.add(turismo);

		}

		return coleccionTemp;

	}

	public int getCantidad() {
		int cant = coleccionTurismos.size();
		return cant;
	}

	public void insertar(Turismo turismo) throws OperationNotSupportedException {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un turismo nulo.");

		} else if (coleccionTurismos.contains(turismo)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un turismo con esa matrícula.");
		} else {
			coleccionTurismos.add(turismo);
		}

	}

	public Turismo buscar(Turismo turismo) {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un turismo nulo.");

		} else if (!coleccionTurismos.contains(turismo)) {
			return null;
		} else {
			return turismo;
		}

	}

	public void borrar(Turismo turismo) throws OperationNotSupportedException {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un turismo nulo.");

		} else if (!coleccionTurismos.contains(turismo)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún turismo con esa matrícula.");
		} else {
			coleccionTurismos.remove(turismo);
		}

	}

	
}
