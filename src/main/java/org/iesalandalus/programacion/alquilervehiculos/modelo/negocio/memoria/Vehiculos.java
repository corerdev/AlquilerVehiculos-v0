package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public class Vehiculos implements IVehiculos {

	

	List<Vehiculo> coleccionVehiculos;

	public Vehiculos() {

		coleccionVehiculos = new ArrayList<Vehiculo>();

	}
	 
	

	@Override
	public List<Vehiculo> get() {

		List<Vehiculo> coleccionTemp = new ArrayList<Vehiculo>();

		for (Vehiculo vehiculo : coleccionVehiculos) {
			coleccionTemp.add(vehiculo);

		}

		return coleccionTemp;

	}

	@Override
	public int getCantidad() {
		int cant = coleccionVehiculos.size();
		return cant;
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehiculo nulo.");

		} else if (coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehiculo con esa matrícula.");
		} else {
			coleccionVehiculos.add(vehiculo);
		}

	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehiculo nulo.");

		}
		if (!coleccionVehiculos.contains(vehiculo)) {
			return null;
		} else {
			int vehiculoADevolver = coleccionVehiculos.indexOf(vehiculo);
			return coleccionVehiculos.get(vehiculoADevolver);
		}

	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehiculo nulo.");

		} else if (!coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehiculo con esa matrícula.");
		} else {
			coleccionVehiculos.remove(vehiculo);
		}

	}



	@Override
	public void comenzar() throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		
	}

	
}
