package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades;

import java.io.File; 
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class UtilidadesXml {
	private UtilidadesXml() {

	}

	public static Document xmlToDom(String rutaXml) {
		Document documento = null;
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			documento = db.parse(rutaXml);

		} catch (Exception ex) {
			System.out.println("¡Error! No se ha podido cargar el documento XML.");
		}
		return documento;

	}

	public static void domToXml(Document dom, String rutaXml) {

		try {
			File f = new File(rutaXml);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			StreamResult result = new StreamResult(f);
			DOMSource source = new DOMSource(dom);
			transformer.transform(source, result);
		} catch (TransformerException ex) {
			System.out.println("¡Error! No se ha podido llevar a cabo la transformación.");
		}

	}

	public static Document crearDomVacio(String raiz) {
		
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db;
	        Document d = null ;
	        try {
	            db = dbf.newDocumentBuilder() ;
	            d = db.newDocument() ;
	            d.appendChild(d.createElement(raiz));
	            return d;
	        } catch (ParserConfigurationException ex) {
	        	System.out.println("¡Error! No se ha podido llevar a cabo la creación.");
	        }
	        return d ;
	    }

	
}
