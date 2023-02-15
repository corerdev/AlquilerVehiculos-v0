package dominio;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.time.temporal.ChronoUnit;

import javax.naming.OperationNotSupportedException;

public class Alquiler {

	public static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final int PRECIO_DIA = 20;
	private LocalDate fechaAlquiler, fechaDevolucion;
	private Cliente cliente;
	private Turismo turismo;

	public Alquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler) {

		this.setCliente(cliente);
		this.setTurismo(turismo);
		this.setFechaAlquiler(fechaAlquiler);
		this.fechaDevolucion = null;

	}

	public Alquiler(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		} else {
			cliente = new Cliente(alquiler.cliente);
			turismo = new Turismo(alquiler.turismo);
			fechaAlquiler = alquiler.getFechaAlquiler();
			if (alquiler.getFechaDevolucion() == null) {
				fechaDevolucion = null;
			} else {
				fechaDevolucion = alquiler.getFechaDevolucion();
			}

		}

	}

	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (this.fechaDevolucion != null) {
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		} else {

			this.setFechaDevolucion(fechaDevolucion);
		}

	}

	public int getPrecio() {
		if (this.getFechaDevolucion()==null) {
			return 0;
		} else {
		int factorCilindrada = this.turismo.getCilindrada() / 10;
		long numDiasTemp = Duration.between(this.fechaAlquiler.atStartOfDay(), this.fechaDevolucion.atStartOfDay()).toDays();
		int numDias = (int) numDiasTemp;
		int precio = (PRECIO_DIA + factorCilindrada) * numDias;
		return precio;
		}

	}

	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	private void setFechaAlquiler(LocalDate fechaAlquiler) {

		if (fechaAlquiler == null) {
			throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
		} else if (fechaAlquiler.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
		} else {
			this.fechaAlquiler = fechaAlquiler;
		}
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	private void setFechaDevolucion(LocalDate fechaDevolucion) {
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		} else if (fechaDevolucion.isBefore(this.fechaAlquiler)) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		} else if (fechaDevolucion.equals(this.fechaAlquiler)) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		} else if (fechaDevolucion.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		} else {
			this.fechaDevolucion = fechaDevolucion;
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	private void setCliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		} else {
			this.cliente = cliente;
		}
	}

	public Turismo getTurismo() {
		return turismo;
	}

	private void setTurismo(Turismo turismo) {
		if (turismo == null) {
			throw new NullPointerException("ERROR: El turismo no puede ser nulo.");
		} else {
			this.turismo = turismo;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, turismo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(turismo, other.turismo);
	}

	@Override
	public String toString() {
		if (this.getFechaDevolucion()==null) {
			
			return cliente+" <---> " + turismo + ", " + fechaAlquiler.format(FORMATO_FECHA) + " - Aún no devuelto (" + this.getPrecio() + "€)";
		} else {
		return cliente+" <---> " + turismo + ", " + fechaAlquiler.format(FORMATO_FECHA) + " - " + fechaDevolucion.format(FORMATO_FECHA) + " (" + this.getPrecio() + "€)";
		}
	}

}
