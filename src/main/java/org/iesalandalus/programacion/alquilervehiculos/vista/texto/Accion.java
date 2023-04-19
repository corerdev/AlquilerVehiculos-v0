package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

public enum Accion {
	SALIR("Salir"), INSERTAR_CLIENTE("Insertar cliente"), INSERTAR_VEHICULO("Insertar vehiculo"),
	INSERTAR_ALQUILER("Insertar alquiler"), BUSCAR_CLIENTE("Buscar cliente"), BUSCAR_VEHICULO("Buscar vehiculo"),
	BUSCAR_ALQUILER("Buscar alquiler"), MODIFICAR_CLIENTE("Modificar cliente"), DEVOLVER_ALQUILER_CLIENTE("Devolver alquiler utilizando su cliente"),
	DEVOLVER_ALQUILER_VEHICULO("Devolver alquiler utilizando su vehiculo"), BORRAR_CLIENTE("Borrar cliente"), BORRAR_VEHICULO("Borrar vehiculo"), BORRAR_ALQUILER("Borrar aqluiler"),
	LISTAR_CLIENTES("Listar clientes"), LISTAR_VEHICULOS("Listar vehiculos"), LISTAR_ALQUILERES("Listar alquileres"),
	LISTAR_AQUILERES_CLIENTE("Listar alquileres cliente"), LISTAR_ALQUILERES_VEHICULO("Listar alquileres vehiculo"),
	MOSTRAR_ESTADISTICAS_MENSUALES("Mostrar estadisticas mensuales por tipo de vehiculo");

	String texto;

	private Accion(String texto) {

		this.texto = texto;
	}

	private static boolean esOrdinalValido(int ordinal) {

		if (ordinal < 0 || ordinal > 19) {
			return false;
		} else {
			return true;
		}
	}

	public static Accion get(int ordinal) {

		if (esOrdinalValido(ordinal)) {

			switch (ordinal) {

			case 0:
				return SALIR;
			case 1:
				return INSERTAR_CLIENTE;
			case 2:
				return INSERTAR_VEHICULO;
			case 3:
				return INSERTAR_ALQUILER;
			case 4:
				return BUSCAR_CLIENTE;
			case 5:
				return BUSCAR_VEHICULO;
			case 6:
				return BUSCAR_ALQUILER;
			case 7:
				return MODIFICAR_CLIENTE;
			case 8:
				return DEVOLVER_ALQUILER_CLIENTE;
			case 9:
				return DEVOLVER_ALQUILER_VEHICULO;
			case 10:
				return BORRAR_CLIENTE;
			case 11:
				return BORRAR_VEHICULO;
			case 12:
				return BORRAR_ALQUILER;
			case 13:
				return LISTAR_CLIENTES;
			case 14:
				return LISTAR_VEHICULOS;
			case 15:
				return LISTAR_ALQUILERES;
			case 16:
				return LISTAR_AQUILERES_CLIENTE;
			case 17:
				return LISTAR_ALQUILERES_VEHICULO;
			case 18:
				return MOSTRAR_ESTADISTICAS_MENSUALES;
			default:
				return SALIR;

			}
		} else {
			
			throw new IllegalArgumentException("Jeje");
			
		}

	}
	
	@Override
	public String toString() {

		return texto;

	}

}
