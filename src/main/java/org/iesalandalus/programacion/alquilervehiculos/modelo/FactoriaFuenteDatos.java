package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;  
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.FuenteDatosFicheros;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.FuenteDatosMemoria;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mysql.FuenteDatosMysql;

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
	}, MYSQL 
	{
		@Override
		IFuenteDatos crear() {
			// TODO Auto-generated method stub
			FuenteDatosMysql fuenteDatos = new FuenteDatosMysql();
			return fuenteDatos;
		}
	}
	
	
	
	
	;
	
	
	

	abstract IFuenteDatos crear();

}
