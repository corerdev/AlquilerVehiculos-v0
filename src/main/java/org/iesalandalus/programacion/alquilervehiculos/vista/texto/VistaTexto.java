package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto extends Vista {

	// Controlador controlador;

	public void comenzar() {
		Accion opcionTemp;

		Consola.mostrarCabecera("Bienvenido al programa de alquiler de vehiculos");
		do {
			Consola.mostrarMenuAcciones();
			opcionTemp = Consola.elegirAccion();
			ejecutar(opcionTemp);
		} while (opcionTemp != Accion.SALIR);
		terminar();
		System.exit(0);

	}

	public void terminar() {

		System.out.println("Gracias por usar la aplicación de gestión de vehiculos.");

	}

	private void ejecutar(Accion opcion) {

		switch (opcion) {

		case SALIR:
			break;
		case INSERTAR_CLIENTE:
			insertarCliente();
			break;
		case INSERTAR_VEHICULO:
			insertarVehiculo();
			break;
		case INSERTAR_ALQUILER:
			insertarAlquiler();
			break;
		case BUSCAR_CLIENTE:
			buscarCliente();
			break;
		case BUSCAR_VEHICULO:
			buscarVehiculo();
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
		case BORRAR_VEHICULO:
			borrarVehiculo();
			break;
		case BORRAR_ALQUILER:
			borrarAlquiler();
			break;
		case LISTAR_CLIENTES:
			listarClientes();
			break;
		case LISTAR_VEHICULOS:
			listarVehiculos();
			break;
		case LISTAR_ALQUILERES:
			listarAlquileres();
			break;
		case LISTAR_AQUILERES_CLIENTE:
			listarAlquileresCliente();
			break;
		case LISTAR_ALQUILERES_VEHICULO:
			listarAlquileresVehiculo();
			break;
		default:
			break;
		}

	}

	protected void insertarCliente() {
		Consola.mostrarCabecera("Ha elegido insertar un cliente.");

		try {
			controlador.insertar(Consola.leerCliente());
			System.out.println("El cliente se ha insertado correctamente.");
			System.out.println("----------------");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void insertarVehiculo() {
		Consola.mostrarCabecera("Ha elegido insertar un vehiculo.");

		try {
			controlador.insertar(Consola.leerVehiculo());
			System.out.println("El vehiculo se ha insertado correctamente.");
			System.out.println("----------------");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void insertarAlquiler() {
		Consola.mostrarCabecera("Ha elegido insertar un alquiler.");
		try {
			controlador.insertar(Consola.leerAlquiler());
			System.out.println("El alquiler se ha insertado correctamente.");
			System.out.println("----------------");
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

	protected void buscarCliente() {
		Consola.mostrarCabecera("Ha elegido buscar un cliente.");
		try {
			Cliente clienteParaBuscar = controlador.buscar(Consola.leerClienteDni());
			if (clienteParaBuscar != null) {
				System.out.println(clienteParaBuscar);
			} else {
				System.out.println("El cliente no se encuentra en la base de datos.");
			}
			System.out.println("----------------");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void buscarVehiculo() {
		Consola.mostrarCabecera("Ha elegido buscar un turismo.");
		try {
			Vehiculo vehiculoParaBuscar = controlador.buscar(Consola.leerVehiculoMatricula());
			if (vehiculoParaBuscar != null) {
				System.out.println(vehiculoParaBuscar);
			} else {
				System.out.println("El vehiculo no se encuentra en la base de datos.");
			}
			System.out.println("----------------");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void buscarAlquiler() {
		Consola.mostrarCabecera("Ha elegido buscar un alquiler.");

		try {
			Alquiler alquilerParaBuscar = controlador.buscar(Consola.leerAlquiler());
			if (alquilerParaBuscar != null) {
				System.out.println(alquilerParaBuscar);
			} else {
				System.out.println("El alquiler no se encuentra en la base de datos.");
			}
			System.out.println("----------------");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void modificarCliente() {
		Consola.mostrarCabecera("Ha elegido modificar un cliente.");

		try {
			controlador.modificar(controlador.buscar(Consola.leerClienteDni()), Consola.leerNombre(),
					Consola.leerTelefono());
			System.out.println("----------------");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void devolverAlquiler() {
		Consola.mostrarCabecera("Ha elegido devolver un alquiler.");

		try {
			controlador.devolver(controlador.buscar(Consola.leerAlquiler()), Consola.leerFechaDevolución());
			System.out.println("----------------");
			// controlador.devolver(Consola.leerAlquiler(), Consola.leerFechaDevolución());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void borrarCliente() {
		Consola.mostrarCabecera("Ha elegido borrar un cliente.");
		try {
			controlador.borrar(controlador.buscar(Consola.leerClienteDni()));
			System.out.println("El cliente se ha borrado correctamente.");
			System.out.println("----------------");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void borrarVehiculo() {
		Consola.mostrarCabecera("Ha elegido borrar un turismo.");
		try {
			controlador.borrar(controlador.buscar(Consola.leerVehiculoMatricula()));
			System.out.println("El vehiculo se ha borrado correctamente.");
			System.out.println("----------------");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void borrarAlquiler() {
		Consola.mostrarCabecera("Ha elegido borrar un alquiler.");
		try {
			controlador.borrar(controlador.buscar(Consola.leerAlquiler()));
			System.out.println("El alquiler se ha borrado correctamente.");
			System.out.println("----------------");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void listarClientes() {
		Consola.mostrarCabecera("Ha elegido listar los clientes.");
		try {
			if (controlador.getClientes().size() != 0) {
				List<Cliente> listaTemporal = controlador.getClientes();
				listaTemporal.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
				for (Cliente cliente : listaTemporal) {

					System.out.println(cliente);
				}
				System.out.println("----------------------");
			} else {
				System.out.println("La lista de clientes está vacia.");
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void listarVehiculos() {
		Consola.mostrarCabecera("Ha elegido listar los turismos.");
		try {
			if (controlador.getVehiculos().size() != 0) {
				List<Vehiculo> listaTemporal = controlador.getVehiculos();
				listaTemporal.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo)
						.thenComparing(Vehiculo::getMatricula));
				for (Vehiculo turismo : listaTemporal) {

					System.out.println(turismo);
				}
				System.out.println("----------------------");
			} else {
				System.out.println("La lista de vehiculos esta vacia");
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * En este metodo no está terminado el sort porque he sido absolutamente incapaz
	 * de conseguir que me acepte los clientes de una u otra manera como atributo
	 * para ordenar. De momento he dejado que se ordene solo por fecha de alquiler,
	 * e intentare corregirlo de cara a la siguiente tarea con la retroalimentación
	 * o preguntando en clase.
	 */
	protected void listarAlquileres() {
		Consola.mostrarCabecera("Ha elegido listar los alquileres.");
		try {

			if (controlador.getAlquileres().size() != 0) {
				List<Alquiler> listaTemporal = controlador.getAlquileres();

				// Comparator<Alquiler> comparameEstaElectricBogaloo =
				// Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
				// Comparator<Alquiler> comparameEsta =
				// Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(comparameEstaElectricBogaloo);
				// Collections.sort(listaTemporal, comparameEsta);
				// listaTemporal.sort(Comparator.comparameEsta.thenComparing(Vehiculo::getModelo)
				// .thenComparing(Vehiculo::getMatricula));

				listaTemporal.sort(Comparator.comparing(Alquiler::getFechaAlquiler));
				for (Alquiler alquiler : listaTemporal) {

					System.out.println(alquiler);
				}
				System.out.println("----------------------");
			} else {
				System.out.println("La lista de alquileres está vacia.");
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void listarAlquileresCliente() {
		Consola.mostrarCabecera("Ha elegido listar los alquileres de un cliente.");
		try {
			if (controlador.getAlquileres().size() != 0) {
				List<Alquiler> listaTemporal = controlador.getAlquileres(Consola.leerClienteDni());
				listaTemporal.sort(Comparator.comparing(Alquiler::getFechaAlquiler));
				for (Alquiler alquiler : listaTemporal) {

					System.out.println(alquiler);
				}
				System.out.println("----------------------");
			} else {
				System.out.println("El cliente no tiene alquileres.");
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	protected void listarAlquileresVehiculo() {
		Consola.mostrarCabecera("Ha elegido listar los alquileres de un turismo.");
		try {
			if (controlador.getClientes().size() != 0) {
				List<Alquiler> listaTemporal = controlador.getAlquileres(Consola.leerVehiculoMatricula());
				listaTemporal.sort(Comparator.comparing(Alquiler::getFechaAlquiler));
				for (Alquiler alquiler : listaTemporal) {

					System.out.println(alquiler);
				}
				System.out.println("----------------------");
			} else {
				System.out.println("El turismo no tiene alquileres.");
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}
}
