package dominio;

import java.util.Objects;

public class Turismo {

	private final String ER_MARCA = "([A-Z][A-Za-z]+)([\s][A-Z][a-z]+)*([-][A-Z][a-z]+)*([A-Z][a-z]+)*";
	private final String ER_MATRICULA = "([0-9]{4})([A-Z]{3})";
	private String marca, modelo, matricula;
	private int cilindrada;

	public Turismo(String marca, String modelo, int cilindrada, String matricula) {

		this.setMarca(marca);
		this.setModelo(modelo);
		this.setCilindrada(cilindrada);
		this.setMatricula(matricula);
	}

	public Turismo(Turismo turismo) {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un turismo nulo.");
		} else {
			marca = turismo.getMarca();
			modelo = turismo.getModelo();
			cilindrada = turismo.getCilindrada();
			matricula = turismo.getMatricula();
		}

	}

	public static Turismo getTurismoConMatricula(String matricula) {

		Turismo turismoDummy = new Turismo("Dummy", "A", 500, matricula);
		return turismoDummy;
	}

	public String getMarca() {
		if (marca == null)
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		else
			return marca;
	}

	private void setMarca(String marca) {
		if (marca == null) {
			throw new NullPointerException("ERROR: La marca no puede ser nula.");
		} else if ((marca.matches(ER_MARCA))) {
			this.marca = marca;
		} else {
			throw new IllegalArgumentException("ERROR: La marca no tiene un formato válido.");
		}
	}

	public String getModelo() {
		if (modelo == null)
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		else
			return modelo;
	}

	private void setModelo(String modelo) {
		if (modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		} else if (modelo.trim().isEmpty()) {
			throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
		} else {
			this.modelo = modelo;
		}
	}

	public String getMatricula() {
		if (matricula == null)
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		else
			return matricula;
	}

	private void setMatricula(String matricula) {
		if (matricula == null) {
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		} else if ((matricula.matches(ER_MATRICULA))) {
			this.matricula = matricula;
		} else {
			throw new IllegalArgumentException("ERROR: La matrícula no tiene un formato válido.");
		}
	}

	public int getCilindrada() {
		return cilindrada;
	}

	private void setCilindrada(int cilindrada) {
		if (cilindrada < 1 || cilindrada > 5000) {
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
		} else {
			this.cilindrada = cilindrada;
		}
	}

	@Override
	public String toString() {
		return marca + " " + modelo + " (" + cilindrada + "CV) - " + matricula ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turismo other = (Turismo) obj;
		return Objects.equals(matricula, other.matricula);
	}

}
