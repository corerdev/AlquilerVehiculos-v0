package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Vehiculos implements IVehiculos {

	

	List<Vehiculo> coleccionVehiculos;
	private static Vehiculos instancia;
	private final String RUTA_FICHERO = "..\\AlquilerVehiculos-v1\\Datos\\Vehiculos.xml";
	private final String RAIZ = "Vehiculos";
	private final String VEHICULO = "Vehiculo";
	private final String MARCA = "Marca";
	private final String MODELO = "Modelo";
	private final String MATRICULA = "Matricula";
	private final String CILINDRADA = "Cilindrada";
	private final String PLAZAS = "Plazas";
	private final String PMA = "Pma";
	private final String TIPO = "Tipo";
	private final String TURISMO = "Turismo";
	private final String AUTOBUS = "AUTOBUS";
	private final String FURGONETA = "Furgoneta";

	private Vehiculos() throws OperationNotSupportedException {

		coleccionVehiculos = new ArrayList<Vehiculo>();
		comenzar();

	}
	
	
public void comenzar() throws OperationNotSupportedException {
		
		leerXml();
		
	}
	private void leerXml() throws OperationNotSupportedException {
		
		Document leerVehiculo = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		NodeList raizVehiculo = leerVehiculo.getElementsByTagName(VEHICULO);
		Element vehiculo;
		
		for (int i=0; i < raizVehiculo.getLength(); i++ ) {
			vehiculo = (Element) raizVehiculo.item(i);
			Vehiculo vehiculoAInsertar = elementToVehiculo(vehiculo);
			insertar(vehiculoAInsertar);
			
			
		}
		
	}
	private Vehiculo elementToVehiculo(Element elemento) {
		Vehiculo vehiculo = null;
		String matricula = elemento.getAttribute(MATRICULA);
		String tipo = elemento.getAttribute(TIPO);
		String marca = elemento.getElementsByTagName(MARCA).item(0).getTextContent();
		String modelo = elemento.getElementsByTagName(MODELO).item(0).getTextContent();
		if (tipo == "Turismo" ) {
			String cilindrada = elemento.getElementsByTagName(CILINDRADA).item(0).getTextContent();
			int cilindradaForReal = Integer.parseInt(cilindrada);
			vehiculo = new Turismo(marca, modelo, cilindradaForReal, matricula);
		}
		if (tipo == "Autobus" ) {
			String plazas = elemento.getElementsByTagName(PLAZAS).item(0).getTextContent();
			int plazasForReal = Integer.parseInt(plazas);
			vehiculo = new Autobus(marca, modelo, plazasForReal, matricula);
		}
		if (tipo == "Furgoneta" ) {
			String plazas = elemento.getElementsByTagName(PLAZAS).item(0).getTextContent();
			int plazasForReal = Integer.parseInt(plazas);
			String pma = elemento.getElementsByTagName(PMA).item(0).getTextContent();
			int pmaForReal = Integer.parseInt(pma);
			
			vehiculo = new Furgoneta(marca, modelo, plazasForReal, pmaForReal, matricula);
		}
	
		return vehiculo;
		
	}
	public void terminar() {
		escribirXml();
		
	}
	private void escribirXml() {
		
		Document vehiculos = UtilidadesXml.crearDomVacio(RAIZ);
		Element raizCliente = vehiculos.getDocumentElement();
		
		for (Vehiculo vehiculo : coleccionVehiculos) {
			raizCliente.appendChild(vehiculoToElement(vehiculos, vehiculo));
			UtilidadesXml.domToXml(vehiculos, VEHICULO);
		}
		
	}
	private Element vehiculoToElement(Document dom, Vehiculo vehiculo) {
		
		Element vehiculoTemp = dom.createElement(VEHICULO);
		
		Attr matricula = dom.createAttribute(MATRICULA);
		vehiculoTemp.setAttributeNode(matricula);
		Attr tipo = dom.createAttribute(TIPO);
		vehiculoTemp.setAttributeNode(tipo);
		Element marca = dom.createElement(MARCA);
		marca.appendChild(dom.createTextNode(vehiculo.getMarca()));
		vehiculoTemp.appendChild(marca);
		Element modelo = dom.createElement(MODELO);
		modelo.appendChild(dom.createTextNode(vehiculo.getModelo()));
		vehiculoTemp.appendChild(modelo);
		if (vehiculo instanceof Turismo) { 
			Element turismo = dom.createElement(TURISMO);
			Element cilindrada = dom.createElement(CILINDRADA);
			cilindrada.appendChild(dom.createTextNode(Integer.toString(((Turismo) turismo).getCilindrada())));
			vehiculoTemp.appendChild(turismo);
			turismo.appendChild(cilindrada);
			
		}
		if (vehiculo instanceof Autobus) { 
			Element autobus = dom.createElement(AUTOBUS);
			Element plazas = dom.createElement(PLAZAS);
			plazas.appendChild(dom.createTextNode(Integer.toString(((Autobus) autobus).getPlazas())));
			vehiculoTemp.appendChild(autobus);
			autobus.appendChild(plazas);
			
		}
		if (vehiculo instanceof Furgoneta) { 
			Element furgoneta = dom.createElement(FURGONETA);
			Element plazas = dom.createElement(PLAZAS);
			plazas.appendChild(dom.createTextNode(Integer.toString(((Furgoneta) furgoneta).getPlazas())));
			Element pma = dom.createElement(PLAZAS);
			pma.appendChild(dom.createTextNode(Integer.toString(((Furgoneta) furgoneta).getPma())));
			vehiculoTemp.appendChild(furgoneta);
			furgoneta.appendChild(plazas);
			furgoneta.appendChild(pma);
			
		}
		return vehiculoTemp;
		
	}
	
	static Vehiculos getInstancia() throws OperationNotSupportedException {
		if (instancia == null) {
			instancia = new Vehiculos();
        }
        return instancia;
    } 
	

	@Override
	public List<Vehiculo> get() {

		List<Vehiculo> coleccionTemp = new ArrayList<Vehiculo>();

		for (Vehiculo vehiculo : coleccionVehiculos) {
			coleccionTemp.add(vehiculo);

		}

		return coleccionTemp;

	}

	@Override
	public int getCantidad() {
		int cant = coleccionVehiculos.size();
		return cant;
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehiculo nulo.");

		} else if (coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehiculo con esa matrícula.");
		} else {
			coleccionVehiculos.add(vehiculo);
		}

	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehiculo nulo.");

		}
		if (!coleccionVehiculos.contains(vehiculo)) {
			return null;
		} else {
			int vehiculoADevolver = coleccionVehiculos.indexOf(vehiculo);
			return coleccionVehiculos.get(vehiculoADevolver);
		}

	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehiculo nulo.");

		} else if (!coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehiculo con esa matrícula.");
		} else {
			coleccionVehiculos.remove(vehiculo);
		}

	}

	
}
