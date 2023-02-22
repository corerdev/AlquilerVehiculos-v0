package vista;

import javax.management.openmbean.OpenDataException;

public enum Opcion {
	SALIR("Salir"), INSERTAR_CLIENTE("Insertar cliente"), INSERTAR_TURISMO("Insertar turismo"),
	INSERTAR_ALQUILER("Insertar alquiler"), BUSCAR_CLIENTE("Buscar cliente"), BUSCAR_TURISMO("Buscar turismo"),
	BUSCAR_ALQUILER("Buscar alquiler"), MODIFICAR_CLIENTE("Modificar cliente"), DEVOLVER_ALQUILER("Devolver alquiler"),
	BORRAR_CLIENTE("Borrar cliente"), BORRAR_TURISMO("Borrar turismo"), BORRAR_ALQUILER("Borrar aqluiler"),
	LISTAR_CLIENTES("Listar clientes"), LISTAR_TURISMOS("Listar turismos"), LISTAR_ALQUILERES("Listar alquileres"),
	LISTAR_AQUILERES_CLIENTE("Listar alquileres cliente"), LISTAR_ALQUILERES_TURISMO("Listar alquileres clientes");

	String texto;

	private Opcion(String texto) {

		this.texto = texto;
	}

	private static boolean esOrdinalValido(int ordinal) {

		if (ordinal < 0 || ordinal > 16) {
			return false;
		} else {
			return true;
		}
	}

	public static Opcion get(int ordinal) {

		if (esOrdinalValido(ordinal)) {

			switch (ordinal) {

			case 0:
				return SALIR;
			case 1:
				return INSERTAR_CLIENTE;
			case 2:
				return INSERTAR_TURISMO;
			case 3:
				return INSERTAR_ALQUILER;
			case 4:
				return BUSCAR_CLIENTE;
			case 5:
				return BUSCAR_TURISMO;
			case 6:
				return BUSCAR_ALQUILER;
			case 7:
				return MODIFICAR_CLIENTE;
			case 8:
				return DEVOLVER_ALQUILER;
			case 9:
				return BORRAR_CLIENTE;
			case 10:
				return BORRAR_TURISMO;
			case 11:
				return BORRAR_ALQUILER;
			case 12:
				return LISTAR_CLIENTES;
			case 13:
				return LISTAR_TURISMOS;
			case 14:
				return LISTAR_ALQUILERES;
			case 15:
				return LISTAR_AQUILERES_CLIENTE;
			case 16:
				return LISTAR_ALQUILERES_TURISMO;
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
