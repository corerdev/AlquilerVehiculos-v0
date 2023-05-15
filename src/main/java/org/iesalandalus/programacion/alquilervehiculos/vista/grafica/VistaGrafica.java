package org.iesalandalus.programacion.alquilervehiculos.vista.grafica;

import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.ControladorVistaGrafica;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VistaGrafica extends Vista{

	private IControlador controladorMVC;
	private static VistaGrafica instancia = null;

	public VistaGrafica() {
		
		if (instancia != null) {
			controladorMVC = instancia.controladorMVC;
		} else {
			instancia = this;
		}

	}

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			ControladorVistaGrafica controlador = new ControladorVistaGrafica();
			loader.setController(controlador);
			loader.setLocation(getClass().getResource("fxml/Menuprincipal.fxml"));
			Parent raiz = loader.load();
			controlador = loader.getController();
			System.out.println(controladorMVC);
			controlador.setControladorMVC(controladorMVC);
			controlador.setClientes();
			Scene escena = new Scene(raiz);
			primaryStage.setTitle("Gestión de Alquileres");
			primaryStage.setScene(escena);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void comenzar() {
		launch(this.getClass());
		// TODO Auto-generated method stub

	}

	@Override
	public void terminar() {
		controladorMVC.terminar();
		// TODO Auto-generated method stub

	}

	public IControlador getControladorMVC() {
		return controladorMVC;
	}

	public void setControlador(IControlador controlador) {
		this.controladorMVC = controlador;
	}
}
