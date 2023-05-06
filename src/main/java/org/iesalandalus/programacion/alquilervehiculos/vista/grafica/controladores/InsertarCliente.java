package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InsertarCliente {

	private IControlador controladorMVC;

	private ObservableList<Cliente> listaCliente;

	@FXML
	private Button añadir_botón;

	@FXML
	private Button cancelar;

	@FXML
	private TextField dni_field;

	@FXML
	private Text dni_text;

	@FXML
	private TextField nombre_field;

	@FXML
	private Text nombre_text;

	@FXML
	private TextField telefono_field;

	@FXML
	private Text telefono_text;

	@FXML
	void anadirCliente(ActionEvent event) {
		try {
			Cliente cliente = new Cliente(nombre_field.getText(), dni_field.getText(), telefono_field.getText());
			controladorMVC.insertar(cliente);
			listaCliente.add(cliente);
			Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
			escenario.close();
			
			String error= "El cliente se ha insertado correctamente";
            Alert alert = new Alert(AlertType.CONFIRMATION);
               alert.setTitle("Ha ocurrido.");
               alert.setHeaderText("Mensaje de confirmación.");
               alert.setContentText(error);

               alert.showAndWait();

		} catch (Exception e) {
			String error= e.getMessage();
            Alert alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Se produjo un error");
               alert.setContentText(error);

               alert.showAndWait();
		}

	}

	public void setObs(ObservableList<Cliente> cliente) {
		this.listaCliente = cliente;
	}

	@FXML
	void cancelar(ActionEvent event) {
		Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
		escenario.close();

	}

	public void setControladorMVC(IControlador controlador) {
		this.controladorMVC = controlador;
	}

}
