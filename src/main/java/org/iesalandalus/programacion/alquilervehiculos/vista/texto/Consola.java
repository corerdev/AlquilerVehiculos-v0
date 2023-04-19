package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;   
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private static String PATRON_FECHA = "dd/MM/yyyy";
	private static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);

	private Consola() {
	}

	public static void mostrarCabecera(String mensaje) {
		int longitud = mensaje.length();
		System.out.println(mensaje);
		for (int i = 0; i <= longitud; i++) {
			System.out.print("-");
		}
		System.out.println(" ");
	}

	public static void mostrarMenuAcciones() {

		System.out.println("Esta es una aplicacion para registrar el alquiler y devolución de vehículos.");
		System.out.println("Las opciones son:");
		for (Accion accion : Accion.values()) {
			
			System.out.println(accion.ordinal()+1 +  " - " + accion.toString());
		}
		/*System.out.println("1 - Salir de la aplicación");
		System.out.println("2 - Insertar un cliente");
		System.out.println("3 - Insertar un vehiculo");
		System.out.println("4 - Insertar un alquiler");
		System.out.println("5 - Buscar un cliente");
		System.out.println("6 - Buscar un vehiculo");
		System.out.println("7 - Buscar un alquiler");
		System.out.println("8 - Modificar un cliente");
		System.out.println("9 - Devolver un alquiler");
		System.out.println("10 - Borrar un cliente");
		System.out.println("11 - Borrar un vehiculo");
		System.out.println("12 - Borrar un alquiler");
		System.out.println("13 - Listar los clientes");
		System.out.println("14 - Listar los vehiculos");
		System.out.println("15 - Listar los alquileres");
		System.out.println("16 - Listar los alquileres de un cliente");
		System.out.println("17 - Listar los alquileres de un vehiculo");*/

	}

	private static String leerCadena(String mensaje) {
		System.out.println(mensaje);
		String cadenaTemp = Entrada.cadena();
		return cadenaTemp;
	}

	private static int leerEntero(String mensaje) {
		System.out.println(mensaje);
		int intTemp = Entrada.entero();
		return intTemp;

	}

	private static LocalDate leerFecha(String mensaje) throws DateTimeParseException {
		System.out.println(mensaje);
		String fechaTemp;
		do {
			fechaTemp = Entrada.cadena();
		} while (fechaTemp.length() != 10);

		LocalDate fechaCorrecta = LocalDate.parse(fechaTemp, FORMATO_FECHA);
		return fechaCorrecta;
	}

	public static Accion elegirAccion() {

		int opcionInt;
		do {
			opcionInt = leerEntero("Introduzca el numero de la opcion que desee.");
		} while (opcionInt < 1 || opcionInt > 19);
		opcionInt = opcionInt - 1;

		Accion opcionTemp = Accion.get(opcionInt);
		return opcionTemp;

	}

	public static Cliente leerCliente() {
		String nombre = leerNombre();
		String dni = leerCadena("Introduzca el dni del cliente.");
		String telefono = leerTelefono();
		Cliente clienteTemp = new Cliente(nombre, dni, telefono);
		return clienteTemp;

	}

	public static Cliente leerClienteDni() {
		
		//En principio intenté usar este metodo para el leerAlquiler, de forma de pudiera leer solo el cliente del dni
		// (igual que hice con el turismo y la matricula). Sin embargo, al no tener acceso a ningun metodo de buscar porque
		// la clase es estatica, me resulta imposible usar solo el dni para nada.

		String dni = leerCadena("Introduzca el dni del cliente.");
		return Cliente.getClienteConDni(dni);
	}

	public static String leerNombre() {
		String nombre = leerCadena("Introduzca el nombre del cliente.");
		return nombre;
	}

	public static String leerTelefono() {
		String telefono = leerCadena("Introduzca el telefono del cliente.");
		;
		return telefono;
	}
	public static Vehiculo leerVehiculo() {
		mostrarMenuTiposVehiculos();
		return leerVehiculo(elegirTipoVehiculo());
		
	}
	private static void mostrarMenuTiposVehiculos() {
		
		System.out.println("Los tipos de vehiculo a elegir son los siguientes:");
		for (TipoVehiculo tipoVehiculo : TipoVehiculo.values()) {
			System.out.println(tipoVehiculo.ordinal()+1 + "- " + tipoVehiculo.toString());
			
		}
		
		
	}
	
	private static TipoVehiculo elegirTipoVehiculo() {
		int vehiculoOpcion;
		do {
			vehiculoOpcion = leerEntero("Introduzca el numero de la opcion que desee.");
		} while (vehiculoOpcion < 1 || vehiculoOpcion > 3);
		vehiculoOpcion = vehiculoOpcion - 1;

		TipoVehiculo opcionTemp = TipoVehiculo.get(vehiculoOpcion);
		return opcionTemp;
		
	}
	
	
	private static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo) {
		if (tipoVehiculo == null) {
			throw new NullPointerException("ERROR: El tipo de vehiculo no puede ser nulo");
		}
		Vehiculo turismoTemp = null;
		String marca = leerCadena("Introduzca la marca del vehiculo.");
		String modelo = leerCadena("Introduzca el modelo del vehiculo.");
		String matricula = leerCadena("Introduzca la matricula del vehiculo.");
		if (tipoVehiculo == TipoVehiculo.AUTOBUS) {
			int plazas = leerEntero("Introduzca las plazas del vehiculo.");
			turismoTemp = new Autobus(marca, modelo,  plazas, matricula);
		} else if (tipoVehiculo == TipoVehiculo.FURGONETA) {
			int pma = leerEntero("Introduzca el pma vehiculo.");
			int plazas = leerEntero("Introduzca las plazas del vehiculo.");
			turismoTemp = new Furgoneta(marca, modelo, pma,  plazas, matricula);
			
		} else if (tipoVehiculo == TipoVehiculo.TURISMO) {
			int cilindrada = leerEntero("Introduzca la cilindrada del vehiculo.");
			turismoTemp = new Turismo(marca, modelo, cilindrada, matricula);
		}
		
		return turismoTemp;

	}

	public static Vehiculo leerVehiculoMatricula() {
		String matricula = leerCadena("Introduzca la matricula del vehiculo.");
		return Vehiculo.getVehiculoConMatricula(matricula);

	}
	
	public static Alquiler leerAlquiler() {
		Cliente clienteTemp = leerClienteDni();
		Vehiculo turismoTemp = leerVehiculoMatricula();
		LocalDate fechaTemp = leerFecha("Introduzca la fecha del alquiler");
		Alquiler alquilerTemp = new Alquiler(clienteTemp, turismoTemp, fechaTemp);
		return alquilerTemp;

	}
	
	public static LocalDate leerFechaDevolución() {
		LocalDate fecha = leerFecha("Introduzca la fecha de la devolución.");
		return fecha;
				
	}
	/**
	 * El método no está terminado porque no me queda claro que tiene que hacer el metodo, ni que devolver,
	 * ni como hacerlo. Al no tener una explicación, no se si debe devolver la primera fecha del mes segun el que
	 * nos pidan, ni de que año, o si deben introducir una fecha concreta y devolver el mes de esa fecha.
	 * Por ello, voy a esperar o bien a la retroalimentación para corregirlo, o a que se explique en la siguiente tarea.
	 * @return
	 * @throws DateTimeParseException
	 */
	public static LocalDate leerMes() throws DateTimeParseException {
		System.out.println("Introduzca un mes del 1 al 12.");
		int fechaTemp;
		do {
			fechaTemp = Entrada.entero();
		} while (fechaTemp<1 || fechaTemp>12 );
		
		switch (fechaTemp) {
		
		case 1: 
			return LocalDate.of(1, 1, 23);
		case 2: 
			return LocalDate.of(1, 2, 23);
		case 3: 
			return LocalDate.of(1, 3, 23);
		case 4: 
			return LocalDate.of(1, 4, 23);
		case 5: 
			return LocalDate.of(1, 5, 23);
		case 6: 
			return LocalDate.of(1, 6, 23);
		case 7: 
			return LocalDate.of(1, 7, 23);
		case 8: 
			return LocalDate.of(1, 8, 23);
		case 9: 
			return LocalDate.of(1, 9, 23);
		case 10: 
			return LocalDate.of(1, 10, 23);
		case 11: 
			return LocalDate.of(1, 11, 23);
		case 12: 
			return LocalDate.of(1, 12, 23);
		default:
			return null;
			
		}

	}
}
