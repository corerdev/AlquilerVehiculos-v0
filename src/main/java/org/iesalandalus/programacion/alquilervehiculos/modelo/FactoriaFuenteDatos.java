package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.FuenteDatosFicheros;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.FuenteDatosMemoria;

public enum FactoriaFuenteDatos {
	MEMORIA

	{
		@Override
		IFuenteDatos crear() {
			// TODO Auto-generated method stub
			FuenteDatosMemoria fuenteDatos = new FuenteDatosMemoria();
			return fuenteDatos;
		}
	}, FICHEROS 
	{
		@Override
		IFuenteDatos crear() {
			// TODO Auto-generated method stub
			FuenteDatosFicheros fuenteDatos = new FuenteDatosFicheros();
			return fuenteDatos;
		}
	};
	
	
	

	abstract IFuenteDatos crear();

}
