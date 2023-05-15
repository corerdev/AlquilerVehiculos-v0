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



import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mysql.utilidades.MySQL;



public class Clientes implements IClientes {

	private static final String COLECCION = "clientes";

	private static Clientes instancia;

	private Connection conexion = null;

	private Clientes() {
		comenzar();
	}

	static Clientes getInstancia() {
		if (instancia == null)
			instancia = new Clientes();

		return instancia;
	}

	public void comenzar() {
		conexion = MySQL.establecerConexion();
	}

	public void terminar() {
		MySQL.cerrarConexion();
	}

	@Override
	public List<Cliente> get() {
		List<Cliente> clientes = new ArrayList<>();

		try {
			String sentenciaStr = "select dni, nombre, telefono from clientes order by dni";
			Statement sentencia = conexion.createStatement();
			ResultSet filas = sentencia.executeQuery(sentenciaStr);

			while (filas.next()) {
				String dni = filas.getString(1);

				Cliente cliente = Cliente.getClienteConDni(dni);
				Cliente clienteBuscado = Clientes.getInstancia().buscar(cliente);

				clientes.add(clienteBuscado);
			}

		} catch (SQLException e) {
			throw new IllegalArgumentException("ERROR:" + e.getMessage());
		}

		return clientes;
	}

	@Override
	public int getCantidad() {
		int tamano = 0;
		try {
			String sentenciaStr = "select count(*) from clientes";
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
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null)
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");

		try {
			String sentenciaStr = "insert into cliente values (?, ?, ?)";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaStr);
			sentencia.setString(1, cliente.getNombre());
			sentencia.setString(2, cliente.getTelefono());
			sentencia.setString(0, cliente.getDni());
			sentencia.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente igual.");
		} catch (SQLException e) {
			throw new OperationNotSupportedException("ERROR:" + e.getMessage());
		}

	}

	@Override
	public Cliente buscar(Cliente cliente) {
		Cliente clienteABuscar = null;

		if (cliente == null)
			throw new IllegalArgumentException("ERROR: No se puede buscar un alquiler nulo.");

		try {
			String sentenciaStr = "select dni from clientes where dni=? ";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaStr);
			sentencia.setString(1, cliente.getDni());
			ResultSet filas = sentencia.executeQuery();

			if (filas.next()) {
				String dni = filas.getString(1);
				clienteABuscar = Clientes.getInstancia().buscar(Cliente.getClienteConDni(dni));

			}
		} catch (SQLException e) {
			throw new IllegalArgumentException("ERROR:" + e.getMessage());
		}

		return clienteABuscar;

	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null)
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");

		try {
			String sentenciaStr = "delete from clientes where dni=?";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaStr);

			sentencia.setString(1, cliente.getDni());

			if (sentencia.executeUpdate() == 0)
				throw new OperationNotSupportedException("ERROR: No existe ningún cliente con los datos indicados.");

		} catch (SQLException e) {
			throw new OperationNotSupportedException("ERROR:" + e.toString());
		}
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		if (cliente == null)
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");

		try {
			String sentenciaStr = "update clientes set nombre=?, telefono=? where dni='" + cliente.getDni() + "'";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaStr);
			if (nombre != null) {
				sentencia.setString(1, nombre);
			} else {
				sentencia.setString(1, cliente.getNombre());
			}
			if (telefono != null) {
				sentencia.setString(1, telefono);
			} else {
				sentencia.setString(1, cliente.getTelefono());
			}
			sentencia.executeUpdate();

			
		} catch (SQLException e) {
			throw new IllegalArgumentException("ERROR:" + e.getMessage());
		}
	}

}
