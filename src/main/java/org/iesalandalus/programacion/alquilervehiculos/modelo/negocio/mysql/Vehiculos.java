package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;


import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mysql.utilidades.MySQL;



public class Vehiculos implements IVehiculos {


	private static Vehiculos instancia;
	private Connection conexion = null;

	private Vehiculos() {
		comenzar();
	}

	static Vehiculos getInstancia() {
		if (instancia == null)
			instancia = new Vehiculos();

		return instancia;
	}

	public void comenzar() {
		conexion = MySQL.establecerConexion();
	}

	public void terminar() {
		MySQL.cerrarConexion();
	}

	@Override
	public List<Vehiculo> get() {
		List<Vehiculo> vehiculos = new ArrayList<>();

		try {
			String sentenciaStr = "select matricula from vehiculos order by matricula";
			Statement sentencia = conexion.createStatement();
			ResultSet filas = sentencia.executeQuery(sentenciaStr);

			while (filas.next()) {

				String matricula = filas.getString(1);

				Vehiculo vehiculo = Vehiculo.getVehiculoConMatricula(matricula);
				Vehiculo vehiculoBuscado = Vehiculos.getInstancia().buscar(vehiculo);

				vehiculos.add(vehiculoBuscado);
			}

		} catch (SQLException e) {
			throw new IllegalArgumentException("ERROR:" + e.getMessage());
		}

		return vehiculos;
	}

	@Override
	public int getCantidad() {
		int tamano = 0;
		try {
			String sentenciaStr = "select count(*) from vehiculos";
			Statement sentencia = conexion.createStatement();
			ResultSet filas = sentencia.executeQuery(sentenciaStr);
			if (filas.next()) {
				tamano = filas.getInt(1);
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException("ERROR:" + e.getMessage());
		}

		return tamano;
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null)
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");

		try {
			String sentenciaStr = "insert into cliente values (?, ?, ?, ?, ? , ?, ?)";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaStr);

			if (vehiculo instanceof Turismo) {
				sentencia.setString(1, vehiculo.getMatricula());
				sentencia.setString(2, vehiculo.getModelo());
				sentencia.setString(3, vehiculo.getMarca());
				sentencia.setString(4, vehiculo.getClass().getSimpleName());
				Turismo turismo = new Turismo((Turismo) vehiculo);
				sentencia.setInt(5, turismo.getCilindrada());
				sentencia.setString(6, null);
				sentencia.setString(7, null);
				sentencia.executeUpdate();
			}
			if (vehiculo instanceof Autobus) {
				sentencia.setString(1, vehiculo.getMatricula());
				sentencia.setString(2, vehiculo.getModelo());
				sentencia.setString(3, vehiculo.getMarca());
				sentencia.setString(4, vehiculo.getClass().getSimpleName());
				Autobus autobus = new Autobus((Autobus) vehiculo);
				sentencia.setString(5, null);
				sentencia.setInt(6, autobus.getPlazas());
				sentencia.setString(7, null);
				sentencia.executeUpdate();
			}
			if (vehiculo instanceof Furgoneta) {
				sentencia.setString(1, vehiculo.getMatricula());
				sentencia.setString(2, vehiculo.getModelo());
				sentencia.setString(3, vehiculo.getMarca());
				sentencia.setString(4, vehiculo.getClass().getSimpleName());
				Furgoneta furgoneta = new Furgoneta((Furgoneta) vehiculo);
				sentencia.setString(5, null);
				sentencia.setInt(6, furgoneta.getPlazas());
				sentencia.setInt(7, furgoneta.getPma());
				sentencia.executeUpdate();
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehiculo igual.");
		} catch (SQLException e) {
			throw new OperationNotSupportedException("ERROR:" + e.getMessage());
		}

	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		Vehiculo vehiculoBuscado = null;

		if (vehiculo == null)
			throw new IllegalArgumentException("ERROR: No se puede buscar un alquiler nulo.");

		try {
			String sentenciaStr = "select matricula from vehiculos where matricula=?";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaStr);

			sentencia.setString(1, vehiculo.getMatricula());

			ResultSet filas = sentencia.executeQuery();
			if (filas.next()) {
				
				String matricula = filas.getString(1);
				vehiculoBuscado = Vehiculos.getInstancia().buscar(Vehiculo.getVehiculoConMatricula(matricula));
				
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException("ERROR:" + e.getMessage());}
	

		return vehiculoBuscado;

	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null)
			throw new NullPointerException("ERROR: No se puede borrar un vehiculo nulo.");

		try {
			String sentenciaStr = "delete from vehiculos where matricula=?";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaStr);

			sentencia.setString(1, vehiculo.getMatricula());

			if (sentencia.executeUpdate() == 0)
				throw new OperationNotSupportedException("ERROR: No existe ningún vehiculo con los datos indicados.");

		} catch (SQLException e) {
			throw new OperationNotSupportedException("ERROR:" + e.toString());
		}
	}

}
