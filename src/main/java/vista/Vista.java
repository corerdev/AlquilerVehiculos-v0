package vista;

import controlador.Controlador;

public class Vista {

	Controlador controlador;

	public void setControlador(Controlador controlador) {

		if (controlador == null) {
			throw new NullPointerException("Oye, me estas pasando un controlador nulo");
		} else {

			this.controlador = controlador;
		}

	}

	public void comenzar() {
		Opcion opcionTemp;

		Consola.mostrarCabecera("Bienvenido al programa de alquiler de vehiculos");
		do {
			Consola.mostrarMenu();
			opcionTemp = Consola.elegirOpcion();
			ejecutar(opcionTemp);
		} while (opcionTemp != Opcion.SALIR);
		terminar();

	}

	public void terminar() {

		System.out.println("Gracias por usar la aplicación de gestión de vehiculos.");

	}

	private void ejecutar(Opcion opcion) {

		switch (opcion) {

		case SALIR:
			break;
		case INSERTAR_CLIENTE:
			insertarCliente();
			break;
		case INSERTAR_TURISMO:
			insertarTurismo();
			break;
		case INSERTAR_ALQUILER:
			insertarAlquiler();
			break;
		case BUSCAR_CLIENTE:
			buscarCliente();
			break;
		case BUSCAR_TURISMO:
			buscarTurismo();
			break;
		case BUSCAR_ALQUILER:
			buscarAlquiler();
			break;
		case MODIFICAR_CLIENTE:
			modificarCliente();
			break;
		case DEVOLVER_ALQUILER:
			devolverAlquiler();
			break;
		case BORRAR_CLIENTE:
			borrarCliente();
			break;
		case BORRAR_TURISMO:
			borrarTurismo();
			break;
		case BORRAR_ALQUILER:
			borrarAlquiler();
			break;
		case LISTAR_CLIENTES:
			listarClientes();
			break;
		case LISTAR_TURISMOS:
			listarTurismos();
			break;
		case LISTAR_ALQUILERES:
			listarAlquileres();
			break;
		case LISTAR_AQUILERES_CLIENTE:
			listarAlquileresCliente();
			break;
		case LISTAR_ALQUILERES_TURISMO:
			listarAlquileresTurismo();
			break;
		default:
			break;
		}

	}

	private void insertarCliente() {
		Consola.mostrarCabecera("Ha elegido insertar un cliente.");

	}

	private void insertarTurismo() {
		Consola.mostrarCabecera("Ha elegido insertar un turismo.");

	}
	private void insertarAlquiler() {
		Consola.mostrarCabecera("Ha elegido insertar un alquiler.");

	}
	private void buscarCliente() {
		Consola.mostrarCabecera("Ha elegido buscar un cliente.");

	}
	private void buscarTurismo() {
		Consola.mostrarCabecera("Ha elegido buscar un turismo.");

	}
	private void buscarAlquiler() {
		Consola.mostrarCabecera("Ha elegido buscar un alquiler.");

	}
	private void modificarCliente() {
		Consola.mostrarCabecera("Ha elegido modificar un cliente.");

	}
	private void devolverAlquiler() {
		Consola.mostrarCabecera("Ha elegido devolver un alquiler.");

	}
	private void borrarCliente() {
		Consola.mostrarCabecera("Ha elegido borrar un cliente.");

	}
	private void borrarTurismo() {
		Consola.mostrarCabecera("Ha elegido borrar un turismo.");

	}
	private void borrarAlquiler() {
		Consola.mostrarCabecera("Ha elegido borrar un alquiler.");

	}
	private void listarClientes() {
		Consola.mostrarCabecera("Ha elegido listar los clientes.");

	}
	private void listarTurismos() {
		Consola.mostrarCabecera("Ha elegido listar los turismos.");

	}
	private void listarAlquileres() {
		Consola.mostrarCabecera("Ha elegido listar los alquileres.");

	}
	private void listarAlquileresCliente() {
		Consola.mostrarCabecera("Ha elegido listar los alquileres de un cliente.");

	}
	private void listarAlquileresTurismo() {
		Consola.mostrarCabecera("Ha elegido listar los alquileres de un turismo.");

	}
}
