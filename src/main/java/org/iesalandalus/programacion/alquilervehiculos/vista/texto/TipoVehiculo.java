package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public enum TipoVehiculo {

	TURISMO("Turismo"), AUTOBUS("Autobus"), FURGONETA("Furgoneta");

	private String nombre;

	private TipoVehiculo(String nombre) {

		this.nombre = nombre;

	}

	private static boolean esOrdinalValido(int ordinal) {

		if (ordinal < 0 || ordinal > TipoVehiculo.values().length) {
			return false;
		} else {
			return true;
		}

	}

	public static TipoVehiculo get(int ordinal) {

		if (esOrdinalValido(ordinal)) {

			switch (ordinal) {

			case 0:
				return TURISMO;
			case 1:
				return AUTOBUS;
			case 2:
				return FURGONETA;
			default:
				return TURISMO;
			}
		} else {

			throw new IllegalArgumentException("Jeje");

		}

	}

	public static TipoVehiculo get(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No es posible analizar un turismo nulo.");
		}
		if (vehiculo instanceof Turismo) {
			return TURISMO;

		} else if (vehiculo instanceof Autobus) {
			return AUTOBUS;

		} else if (vehiculo instanceof Furgoneta) {
			return FURGONETA;

		} else {
			return null;

		}

	}

	@Override
	public String toString() {

		return nombre;

	}

}
