package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Furgoneta extends Vehiculo {

	private final int FACTOR_PLAZAS = 1, FACTOR_PMA = 100;
	private int plazas, pma;

	public Furgoneta(String marca, String modelo, int plazas, int pma, String matricula) {

		super(marca, modelo, matricula);
		setPma(pma);
		setPlazas(plazas);

	}

	public Furgoneta(Furgoneta furgoneta) {
		super(furgoneta);
		if (furgoneta == null) {
			throw new NullPointerException("ERROR: No se puede copiar un turismo nulo");
		}

		setMarca(furgoneta.getMarca());
		setModelo(furgoneta.getModelo());
		setPlazas(furgoneta.getPlazas());
		setPma(furgoneta.getPma());
		setMatricula(furgoneta.getMatricula());

	}

	protected int getFactorPrecio() {

		int resultado = (this.getPma() / FACTOR_PMA) + (this.getPlazas() * FACTOR_PLAZAS);
		return resultado;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		if (plazas < 1) {
			throw new IllegalArgumentException("ERROR: El numero de plazas no puede ser inferior a 1");
		}
		this.plazas = plazas;
	}

	public int getPma() {
		return pma;
	}

	public void setPma(int pma) {
		if (pma < 1 || pma > 1000) {
			throw new IllegalArgumentException("ERROR: El peso no es correcto");
		}
		this.pma = pma;
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
		return super.toString() + " [" + plazas + " asientos]" + pma;
	}

}
