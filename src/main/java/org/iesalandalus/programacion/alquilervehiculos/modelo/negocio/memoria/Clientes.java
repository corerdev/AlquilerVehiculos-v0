package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.util.ArrayList; 
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;

public class Clientes implements IClientes {

	List<Cliente> coleccionClientes;


	public Clientes() {

		coleccionClientes = new ArrayList<Cliente>();

	}


	
	
	@Override
	public List<Cliente> get() {

		List<Cliente> coleccionTemp = new ArrayList<Cliente>();

		for (Cliente cliente : coleccionClientes) {
			coleccionTemp.add(cliente);

		}

		return coleccionTemp;

	}

	@Override
	public int getCantidad() {
		int cant = coleccionClientes.size();
		return cant;
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");

		} else if (coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		} else {
			coleccionClientes.add(cliente);
		}

	}

	@Override
	public Cliente buscar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");

		}
		if (!coleccionClientes.contains(cliente)) {
			return null;
		} else {
			int clienteADevolver = coleccionClientes.indexOf(cliente);
			return coleccionClientes.get(clienteADevolver);
		}

	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");

		} else if (!coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		} else {
			coleccionClientes.remove(cliente);
		}

	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}

		if (coleccionClientes.contains(cliente)) {
			int index = coleccionClientes.indexOf(cliente);
			if (nombre != null) {
				coleccionClientes.get(index).setNombre(nombre);
			}
			if (telefono != null) {

				coleccionClientes.get(index).setTelefono(telefono);
			}
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
	}




	@Override
	public void comenzar() throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		
	}
}
