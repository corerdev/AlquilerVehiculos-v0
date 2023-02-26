package vista;

import java.time.format.DateTimeParseException;

import javax.naming.OperationNotSupportedException;

import controlador.Controlador;
import dominio.Alquiler;
import dominio.Cliente;
import dominio.Turismo;

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
		System.exit(0);

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

		try {
			controlador.insertar(Consola.leerCliente());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	private void insertarTurismo() {
		Consola.mostrarCabecera("Ha elegido insertar un turismo.");

		try {
			controlador.insertar(Consola.leerTurismo());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	private void insertarAlquiler() {
		Consola.mostrarCabecera("Ha elegido insertar un alquiler.");
		try {
			controlador.insertar(Consola.leerAlquiler());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (DateTimeParseException e) {
			System.out.println("ERROR: Ha introducido un formato de fecha inválido");
		}

	}

	private void buscarCliente() {
		Consola.mostrarCabecera("Ha elegido buscar un cliente.");
		try {
		if (controlador.buscar(Consola.leerCliente())!=null) {
			System.out.println("El cliente está en la base de datos.");
		}else {
			System.out.println("El cliente no se encuentra en la base de datos.");
		}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	private void buscarTurismo() {
		Consola.mostrarCabecera("Ha elegido buscar un turismo.");
		try {
			if (controlador.buscar(Consola.leerTurismo())!=null) {
				System.out.println("El turismo está en la base de datos.");
			}else {
				System.out.println("El turismo no se encuentra en la base de datos.");
			}
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

	}

	private void buscarAlquiler() {
		Consola.mostrarCabecera("Ha elegido buscar un alquiler.");
		
		try {
			if (controlador.buscar(Consola.leerAlquiler())!=null) {
				System.out.println("El alquiler está en la base de datos.");
			}else {
				System.out.println("El alquiler no se encuentra en la base de datos.");
			}
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

	}

	private void modificarCliente() {
		Consola.mostrarCabecera("Ha elegido modificar un cliente.");
		
		try {
			controlador.modificar(controlador.buscar(Consola.leerClienteDni()), Consola.leerNombre(), Consola.leerTelefono());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

}

	private void devolverAlquiler() {
		Consola.mostrarCabecera("Ha elegido devolver un alquiler.");
		
		try {
			controlador.devolver(controlador.buscar(Consola.leerAlquiler()), Consola.leerFechaDevolución());
			//controlador.devolver(Consola.leerAlquiler(), Consola.leerFechaDevolución());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

}

	private void borrarCliente() {
		Consola.mostrarCabecera("Ha elegido borrar un cliente.");
		try {
			controlador.borrar(controlador.buscar(Consola.leerClienteDni()));
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

}

	private void borrarTurismo() {
		Consola.mostrarCabecera("Ha elegido borrar un turismo.");
		try {
			controlador.borrar(controlador.buscar(Consola.leerTurismo()));
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

}

	private void borrarAlquiler() {
		Consola.mostrarCabecera("Ha elegido borrar un alquiler.");
		try {
			controlador.borrar(controlador.buscar(Consola.leerAlquiler()));
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

}

	private void listarClientes() {
		Consola.mostrarCabecera("Ha elegido listar los clientes.");
		
		for (Cliente cliente : controlador.getClientes()) {
			
			System.out.println(cliente);
		}
		System.out.println("----------------------");

	}

	private void listarTurismos() {
		Consola.mostrarCabecera("Ha elegido listar los turismos.");
		
	for (Turismo turismo : controlador.getTurismos()) {
			
			System.out.println(turismo);
		}
	System.out.println("----------------------");

	}

	private void listarAlquileres() {
		Consola.mostrarCabecera("Ha elegido listar los alquileres.");
		
	for (Alquiler alquiler : controlador.getAlquileres()) {
			
			System.out.println(alquiler);
		}
	System.out.println("----------------------");

	}

	private void listarAlquileresCliente() {
		Consola.mostrarCabecera("Ha elegido listar los alquileres de un cliente.");
		
	for (Alquiler alquiler : controlador.getAlquileres(Consola.leerClienteDni())) {
			
			System.out.println(alquiler);
		}
	System.out.println("----------------------");

	}

	private void listarAlquileresTurismo() {
		Consola.mostrarCabecera("Ha elegido listar los alquileres de un turismo.");
	for (Alquiler alquiler : controlador.getAlquileres(Consola.leerTurismoMatricula())) {
			
			System.out.println(alquiler);
		}
	System.out.println("----------------------");
	}
}
