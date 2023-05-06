package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BorrarCliente {

	private IControlador controladorMVC;

	private ObservableList<Cliente> listaCliente;

	@FXML
	private Button borrar;

	@FXML
	private Button buscar;

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
	void borrarCliente(ActionEvent event) {

		try {
			Cliente clienteABuscar = new Cliente("Dummy", dni_field.getText(), "666444555");
			controladorMVC.borrar(controladorMVC.buscar(clienteABuscar));
			listaCliente.setAll(controladorMVC.getClientes());
			listaCliente.remove(clienteABuscar);
			String error= "El cliente se ha borrado correctamente";
            Alert alert = new Alert(AlertType.CONFIRMATION);
               alert.setTitle("Ha ocurrido.");
               alert.setHeaderText("Mensaje de confirmación.");
               alert.setContentText(error);

               alert.showAndWait();
			Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
			escenario.close();
			

		} catch (Exception e) {
			String error= e.getMessage();
            Alert alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Se produjo un error");
               alert.setContentText(error);

               alert.showAndWait();;
		}

	}

	@FXML
	void buscarCliente(ActionEvent event) {

		try {
			Cliente clienteDummy = new Cliente("Dummy", dni_field.getText(), "666444555");
			Cliente clienteABuscar = controladorMVC.buscar(clienteDummy);
			buscar.setDisable(true);
			borrar.setDisable(false);
			dni_field.setDisable(true);
			telefono_field.setText(clienteABuscar.getTelefono());
			nombre_field.setText(clienteABuscar.getNombre());

		} catch (Exception e) {
			String error= e.getMessage();
            Alert alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Se produjo un error");
               alert.setContentText(error);

               alert.showAndWait();;
		}

	}

	@FXML
	void cancelar(ActionEvent event) {

		Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
		escenario.close();

	}

	
	
	public void setObs(ObservableList<Cliente> cliente) {
		this.listaCliente = cliente;
	}



	public void setControladorMVC(IControlador controlador) {
		this.controladorMVC = controlador;
	}

}
