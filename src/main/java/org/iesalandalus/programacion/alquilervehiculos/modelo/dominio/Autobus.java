package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Autobus extends Vehiculo {
	
	private final int FACTOR_PLAZAS = 2;
	private int plazas;
	
	public Autobus(String marca, String modelo, int plazas, String matricula) {

		super(marca, modelo, matricula);
		setPlazas(plazas);

	}

	public Autobus(Autobus autobus) {
		super(autobus);
		if (autobus == null) {
			throw new NullPointerException("ERROR: No se puede copiar un turismo nulo");
		}

		setMarca(autobus.getMarca());
		setModelo(autobus.getModelo());
		setPlazas(autobus.getPlazas());
		setMatricula(autobus.getMatricula());

	}
	
protected int getFactorPrecio() {
		
		int resultado = this.getPlazas()*FACTOR_PLAZAS;
		return resultado;
	}


	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		if (plazas<1) {
			throw new IllegalArgumentException("ERROR: El numero de plazas no puede ser inferior a 1");
		}
		this.plazas = plazas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(plazas);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Turismo))
			return false;
		Turismo other = (Turismo) obj;
		return getMatricula() == other.getMatricula();
	}

	@Override
	public String toString() {
		return super.toString() +  " [" + plazas + " asientos]";
	}
	
	
	
	
	
	
	

}