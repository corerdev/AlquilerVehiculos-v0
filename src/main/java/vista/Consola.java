package vista;

import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import vista.Opcion;

import org.iesalandalus.programacion.utilidades.Entrada;

import controlador.Controlador;
import dominio.Alquiler;
import dominio.Cliente;
import dominio.Turismo;
import modelo.Modelo;
import negocio.Clientes;

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

	public static void mostrarMenu() {

		System.out.println("Esta es una aplicacion para registrar el alquiler y devolución de vehículos.");
		System.out.println("Las opciones son:");
		System.out.println("1 - Salir de la aplicación");
		System.out.println("2 - Insertar un cliente");
		System.out.println("3 - Insertar un turismo");
		System.out.println("4 - Insertar un alquiler");
		System.out.println("5 - Buscar un cliente");
		System.out.println("6 - Buscar un turismo");
		System.out.println("7 - Buscar un alquiler");
		System.out.println("8 - Modificar un cliente");
		System.out.println("9 - Devolver un alquiler");
		System.out.println("10 - Borrar un cliente");
		System.out.println("11 - Borrar un turismo");
		System.out.println("12 - Borrar un alquiler");
		System.out.println("13 - Listar los clientes");
		System.out.println("14 - Listar los turismos");
		System.out.println("15 - Listar los alquileres");
		System.out.println("16 - Listar los alquileres de un cliente");
		System.out.println("17 - Listar los alquileres de un turismo");

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

	private static LocalDate leerFecha(String mensaje) {
		System.out.println(mensaje);
		String fechaTemp;
		do {
			fechaTemp = Entrada.cadena();
		} while (fechaTemp.length() != 10);

		LocalDate fechaCorrecta = LocalDate.parse(fechaTemp, FORMATO_FECHA);
		return fechaCorrecta;
	}

	public static Opcion elegirOpcion() {

		int opcionInt;
		do {
			opcionInt = leerEntero("Introduzca el numero de la opcion que desee.");
		} while (opcionInt < 1 || opcionInt > 17);
		opcionInt = opcionInt - 1;

		Opcion opcionTemp = Opcion.get(opcionInt);
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
		Cliente clienteDummy = new Cliente("Cliente Dummy", dni, "615000000");
		return clienteDummy;
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

	public static Turismo leerTurismo() {
		String marca = leerCadena("Introduzca la marca del vehiculo.");
		String modelo = leerCadena("Introduzca el modelo del vehiculo.");
		int cilindrada = leerEntero("Introduzca la cilindrada del vehiculo.");
		String matricula = leerCadena("Introduzca la matricula del vehiculo.");
		Turismo turismoTemp = new Turismo(marca, modelo, cilindrada, matricula);
		return turismoTemp;

	}

	public static Turismo leerTurismoMatricula() {
		String matricula = leerCadena("Introduzca la matricula del vehiculo.");
		Turismo turismoTemp = new Turismo("Dummy", "Dummy", 10, matricula);
		return turismoTemp;

	}
	
	public static Alquiler leerAlquiler() {
		Cliente clienteTemp = leerCliente();
		Turismo turismoTemp = leerTurismo();
		LocalDate fechaTemp = leerFecha("Introduzca la fecha del alquiler");
		Alquiler alquilerTemp = new Alquiler(clienteTemp, turismoTemp, fechaTemp);
		return alquilerTemp;

	}
	
	public static LocalDate leerFechaDevolución() {
		LocalDate fecha = leerFecha("Introduzca la fecha de la devolución.");
		return fecha;
				
	}
}
