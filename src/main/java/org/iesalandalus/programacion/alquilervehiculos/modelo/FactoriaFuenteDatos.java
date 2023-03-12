package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.FuenteDatosMemoria;

public enum FactoriaFuenteDatos {
MEMORIA


{
	@Override
	public IFuenteDatos crear() {
		// TODO Auto-generated method stub
		FuenteDatosMemoria fuenteDatos = new FuenteDatosMemoria();
		return fuenteDatos;
	}
};

public abstract IFuenteDatos crear();









}
