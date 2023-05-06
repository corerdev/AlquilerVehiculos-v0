package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.util.ArrayList; 
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Clientes implements IClientes {

	private static final String RUTA_FICHERO = "..\\AlquilerVehiculos-v1\\Datos\\Clientes.xml";
	private static final String RAIZ = "Clientes";
	private static final String CLIENTE = "Cliente";
	private static final String NOMBRE = "Nombre";
	private static final String DNI = "Dni";
	private static final String TELEFONO = "Telefono";
	private List<Cliente> coleccionClientes;
	private static Clientes instancia;

	private Clientes() throws OperationNotSupportedException {

		coleccionClientes = new ArrayList<Cliente>();
		comenzar();

	}

	
	static Clientes getInstancia() throws OperationNotSupportedException {
		if (instancia == null) {
			instancia = new Clientes();
        }
        return instancia;
    } 
	
	public void comenzar() throws OperationNotSupportedException {
		
		leerXml();
		
	}
	private void leerXml() throws OperationNotSupportedException {
		
		Document leerCliente = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		NodeList raizCliente = leerCliente.getElementsByTagName(CLIENTE);
		Element cliente;
		
		for (int i=0; i < raizCliente.getLength(); i++ ) {
			cliente = (Element) raizCliente.item(i);
			Cliente clienteAInsertar = elementToCliente(cliente);
			insertar(clienteAInsertar);
			
			
		}
		
	}
	private Cliente elementToCliente(Element elemento) {
		
		String nombre = elemento.getElementsByTagName(NOMBRE).item(0).getTextContent();
		String dni = elemento.getAttribute(DNI);
		String telefono = elemento.getElementsByTagName(TELEFONO).item(0).getTextContent();
		Cliente cliente = new Cliente(nombre, dni, telefono);
		return cliente;
		
	}
	public void terminar() {
		escribirXml();
		
	}
	private void escribirXml() {
		
		Document clientes = UtilidadesXml.crearDomVacio(RAIZ);
		Element raizCliente = clientes.getDocumentElement();
		
		for (Cliente cliente: coleccionClientes) {
			raizCliente.appendChild(clienteToElement(clientes, cliente));
			UtilidadesXml.domToXml(clientes, RUTA_FICHERO);
		}
		
	}
	private Element clienteToElement(Document dom, Cliente cliente) {
		
		Element clienteTemp = dom.createElement(CLIENTE);
		clienteTemp.setAttribute(DNI, cliente.getDni());
		Element nombreCliente = dom.createElement(NOMBRE);
		nombreCliente.appendChild(dom.createTextNode(cliente.getNombre()));
		clienteTemp.appendChild(nombreCliente);
		Element telefonoCliente = dom.createElement(TELEFONO);
		telefonoCliente.appendChild(dom.createTextNode(cliente.getTelefono()));
		clienteTemp.appendChild(telefonoCliente);
		return clienteTemp;
		
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
}
