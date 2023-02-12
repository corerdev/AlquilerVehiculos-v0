package dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Cliente {

	private final String ER_NOMBRE = "([A-Z][a-z]+)([\s][A-Z][a-z]+)*";
	private final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private final String ER_TELEFONO = "([0-9]{9})";
	private String dni, telefono, nombre;
	
	public Cliente(String nombre, String dni, String telefono) {

		this.setDni(dni);
		this.setNombre(nombre);
		this.setTelefono(telefono);
	}

	public Cliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		} else {
		dni = cliente.getDni();
		nombre = cliente.getNombre();
		telefono = cliente.getTelefono();
		}

	}
	
	private Boolean comprobarLetraDni(String dni) {
		Pattern patron = Pattern.compile(ER_DNI);
		Matcher comparador = patron.matcher(dni);
		if (comparador.matches()) {
			final char[] LETRADNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S',
					'Q', 'V', 'H', 'L', 'C', 'K', 'E' };
			int numDni = Integer.parseInt(comparador.group(1));
			char letraDniTemp = LETRADNI[numDni % 23];
			String letraDniTempString = String.valueOf(letraDniTemp);
			if (letraDniTempString.matches(comparador.group(2)))
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public static Cliente getClienteConDni (String dni) {
		
		Cliente clienteDummy = new Cliente("Dummy", dni, "615646789");
		return clienteDummy;
		
	}
	
	public String getDni() {
		if (dni == null)
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		else
		return dni;
	}
	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		Pattern patron = Pattern.compile(ER_DNI);
		Matcher comparador = patron.matcher(dni);
		if (comparador.matches())
			if (comprobarLetraDni(dni)) {
				this.dni = dni;
			} else
				throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		else {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");

		}
	}
	public String getTelefono() {
		if (telefono == null)
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		else
		return telefono;
	}
	public void setTelefono(String telefono) {
		if (telefono == null)
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		else if ((telefono.matches(ER_TELEFONO)))
			this.telefono = telefono;
		else
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
	}
	public String getNombre() {
		if (nombre == null)
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		else
		
		return nombre;
	}
	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}else if ((nombre.matches(ER_NOMBRE))){
			this.nombre = nombre;
		}else {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return nombre + " - " + dni + " (" +  telefono + ")";
	}
	
	
	
	
}
