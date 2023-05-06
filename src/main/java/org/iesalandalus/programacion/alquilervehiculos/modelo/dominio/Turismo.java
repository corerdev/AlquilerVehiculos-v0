package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Turismo extends Vehiculo {

	private final int FACTOR_CILINDRADA = 10;
	private int cilindrada;

	public Turismo(String marca, String modelo, int cilindrada, String matricula) {

		super(marca, modelo, matricula);
		setCilindrada(cilindrada);

	}

	public Turismo(Turismo turismo) {
		super(turismo);
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede copiar un turismo nulo");
		}

		setMarca(turismo.getMarca());
		setModelo(turismo.getModelo());
		setCilindrada(turismo.getCilindrada());
		setMatricula(turismo.getMatricula());

	}

	protected int getFactorPrecio() {

		int resultado = this.getCilindrada() / FACTOR_CILINDRADA;
		return resultado;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	private void setCilindrada(int cilindrada) {
		if (cilindrada < 1 || cilindrada > 5000) {
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta");
		}
		this.cilindrada = cilindrada;
	}
	@Override
	public int hashCode() {
	return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return super.toString() + " [" + cilindrada + " VC]";
	}

}
