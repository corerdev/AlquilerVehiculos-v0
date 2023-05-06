package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.Consola;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Alquileres implements IAlquileres {

	List<Alquiler> coleccionAlquileres;
	private static Alquileres instancia;
	private final String RUTA_FICHERO = "..\\AlquilerVehiculos-v1\\Datos\\Alquileres.xml";
	private final String RAIZ = "Alquileres";
	private final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final String ALQUILER = "Alquiler";
	private final String DNI_CLIENTE = "Dni";
	private final String MATRICULA_VEHICULO = "Matricula";
	private final String FECHA_ALQUILER = "FechaAlquiler";
	private final String FECHA_DEVOLUCION = "FechaDevolucion";
	private final String FORMATO = "Formato";

	private Alquileres() throws OperationNotSupportedException {

		coleccionAlquileres = new ArrayList<Alquiler>();
		comenzar();

	}
	
	public void comenzar() throws OperationNotSupportedException {
		
		leerXml();
		
	}
	private void leerXml() throws OperationNotSupportedException {
		
		Document leerAlquiler = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		NodeList raizAlquiler = leerAlquiler.getElementsByTagName(ALQUILER);
		Element alquiler;
		
		for (int i=0; i < raizAlquiler.getLength(); i++ ) {
			alquiler = (Element) raizAlquiler.item(i);
			Alquiler alquilerAInsertar = elementToAlquiler(alquiler);
			insertar(alquilerAInsertar);
			
			
		}
		
	}
	private Alquiler elementToAlquiler(Element elemento) throws OperationNotSupportedException {
		
		String dni = elemento.getAttribute(DNI_CLIENTE);
		Cliente clienteDummy = new Cliente("Bob Espoja", dni, "666444555");
		Cliente clienteABuscar = Clientes.getInstancia().buscar(clienteDummy);
		String matricula = elemento.getAttribute(MATRICULA_VEHICULO);
		Vehiculo vehiculoDummy = new Turismo("Cosa", "Coso", 500, matricula);
		Vehiculo vehiculoABuscar = Vehiculos.getInstancia().buscar(vehiculoDummy);
		String fechaTemp;
		fechaTemp = elemento.getElementsByTagName(FECHA_ALQUILER).item(0).getTextContent();
		LocalDate fechaAlq = LocalDate.parse(fechaTemp, FORMATO_FECHA);
		Alquiler alquiler = new Alquiler (clienteABuscar, vehiculoABuscar, fechaAlq);
		if (!(elemento.getElementsByTagName(FECHA_DEVOLUCION).item(0).getTextContent().isEmpty())) {
			String fechaDevtTemp = elemento.getElementsByTagName(FECHA_DEVOLUCION).item(0).getTextContent();
			LocalDate fechaDev = LocalDate.parse(fechaDevtTemp, FORMATO_FECHA);
			
			alquiler.devolver(fechaDev);
		
		}
		return alquiler;
		
	}
	public void terminar() {
		escribirXml();
		
	} 
	private void escribirXml() {
		
		Document alquileres = UtilidadesXml.crearDomVacio(RAIZ);
		Element raizCliente = alquileres.getDocumentElement();
		
		for (Alquiler alquiler: coleccionAlquileres) {
			raizCliente.appendChild(alquilerToElement(alquileres, alquiler));
			UtilidadesXml.domToXml(alquileres, RUTA_FICHERO);
		}
		
	}
	private Element alquilerToElement(Document doc, Alquiler alquiler) {
        Element eleAlquiler = doc.createElement(ALQUILER);


        eleAlquiler.setAttribute(DNI_CLIENTE, alquiler.getCliente().getDni());


        eleAlquiler.setAttribute(MATRICULA_VEHICULO, alquiler.getVehiculo().getMatricula());

        Element eleFecha = doc.createElement(FECHA_ALQUILER);
        eleFecha.appendChild(doc.createTextNode(alquiler.getFechaAlquiler().format(FORMATO_FECHA)));
        Attr atriFormato = doc.createAttribute(FORMATO);
        eleFecha.setAttributeNode(atriFormato);
        eleAlquiler.appendChild(eleFecha);

        Element eleFechaD = doc.createElement(FECHA_DEVOLUCION);
        if (alquiler.getFechaDevolucion() == null) {
            eleFechaD.appendChild(doc.createTextNode(""));
        }else {
            eleFechaD.appendChild(doc.createTextNode(alquiler.getFechaDevolucion().format(FORMATO_FECHA)));
        }

        Attr atriFormatoD = doc.createAttribute(FORMATO);
        eleFechaD.setAttributeNode(atriFormatoD);
        eleAlquiler.appendChild(eleFechaD);

        return eleAlquiler;

    }

	static Alquileres getInstancia() throws OperationNotSupportedException {
		if (instancia == null) {
			instancia = new Alquileres();
        }
        return instancia;
    } 
	
	
	@Override
	public List<Alquiler> get() {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();

		for (Alquiler alquiler : coleccionAlquileres) {
			coleccionTemp.add(alquiler);

		}

		return coleccionTemp;

	}

	@Override
	public List<Alquiler> get(Cliente cliente) {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente))
				coleccionTemp.add(alquiler);

		}

		return coleccionTemp;

	}

	@Override
	public List<Alquiler> get(Vehiculo vehiculo) {

		List<Alquiler> coleccionTemp = new ArrayList<Alquiler>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getVehiculo().equals(vehiculo))
				coleccionTemp.add(alquiler);

		}

		return coleccionTemp;

	}

	@Override
	public int getCantidad() {
		int cant = coleccionAlquileres.size();
		return cant;
	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
			} else if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
			} else if (alquiler.getVehiculo().equals(vehiculo) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
					|| alquiler.getFechaDevolucion().equals(fechaAlquiler))) {
				throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
			} else if (alquiler.getCliente().equals(cliente) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
					|| alquiler.getFechaDevolucion().equals(fechaAlquiler))) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");

			}

		}

	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");

		} /*
			 * else if (coleccionAlquileres.contains(alquiler)) { throw new
			 * OperationNotSupportedException("ERROR: Ya existe un turismo con esa matrícula."
			 * ); }
			 */ else {
			comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
			coleccionAlquileres.add(alquiler);
		}

	}
	
	private Alquiler getAlquilerAbierto (Cliente cliente) {
		List<Alquiler> listaTemporal = get(cliente);
		Alquiler alquilerADevolver = null;
		for (Alquiler alquiler : listaTemporal) {
			if (alquiler.getFechaDevolucion()==null) {
				alquilerADevolver = alquiler;
			}
		}
		return alquilerADevolver;
	}

	private Alquiler getAlquilerAbierto (Vehiculo vehiculo) {
		List<Alquiler> listaTemporal = get(vehiculo);
		Alquiler alquilerADevolver = null;
		for (Alquiler alquiler : listaTemporal) {
			if (alquiler.getFechaDevolucion()==null) {
				alquilerADevolver = alquiler;
			}
		}
		return alquilerADevolver;
	}
	
	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler con cliente nulo.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		if (getAlquilerAbierto(cliente)==null) {
			throw new OperationNotSupportedException("ERROR: El cliente no tiene ningun alquiler para devolver.");
		}
		int index = coleccionAlquileres.indexOf(getAlquilerAbierto(cliente));
		if (index != -1) {
			coleccionAlquileres.get(index).devolver(fechaDevolucion);

		} else if (index == -1) {

			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}
	
	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler con vehiculo nulo.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		if (getAlquilerAbierto(vehiculo)==null) {
			throw new OperationNotSupportedException("ERROR: El cliente no tiene ningun alquiler para devolver.");
		}
		int index = coleccionAlquileres.indexOf(getAlquilerAbierto(vehiculo));
		if (index != -1) {
			coleccionAlquileres.get(index).devolver(fechaDevolucion);

		} else if (index == -1) {

			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");

		} else if (!coleccionAlquileres.contains(alquiler)) {
			return null;
		} else {
			int alquilerADevolver = coleccionAlquileres.indexOf(alquiler);
			return coleccionAlquileres.get(alquilerADevolver);
		}
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");

		} else if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		} else {
			coleccionAlquileres.remove(alquiler);
		}
	}
}
