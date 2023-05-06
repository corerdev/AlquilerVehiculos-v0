package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ControladorVistaGrafica {

	private IControlador controladorMVC;

	private ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();
	private ObservableList<Vehiculo> listaVehiculo = FXCollections.observableArrayList();
	private ObservableList<Alquiler> listaAlquiler = FXCollections.observableArrayList();
	private ObservableList<Alquiler> filtroAlquiler = FXCollections.observableArrayList();

	private VistaGrafica vista = new VistaGrafica();

	@FXML
	private Button alquileres_button;

	@FXML
	private Button borrar_alquiler;

	@FXML
	private Button borrar_cliente;

	@FXML
	private Button borrar_vehiculo;

	@FXML
	private Button buscar_cliente;

	@FXML
	private Button buscar_vehiculo;

	@FXML
	private TableColumn<Vehiculo, String> cilindrada_vehiculo_table;

	@FXML
	private Button clientes_button;

	@FXML
	private Button devolver_cliente;

	@FXML
	private Button devolver_vehiculo;

	@FXML
	private TableColumn<Cliente, String> dni_cliente_table;

	@FXML
	private TableColumn<Alquiler, String> fechaAlq_alquiler_table;

	@FXML
	private TableColumn<Alquiler, String> fechaDev_alquiler_table;

	@FXML
	private Button insertar_alquiler;

	@FXML
	private Button insertar_cliente;

	@FXML
	private Button insertar_vehiculo;

	@FXML
	private TableColumn<Vehiculo, String> marca_vehiculo_table;

	@FXML
	private TableColumn<Alquiler, String> matricula_alquiler_table;

	@FXML
	private TableColumn<Vehiculo, String> matricula_telefono_table;

	@FXML
	private TableColumn<Vehiculo, String> modelo_vehiculo_table;

	@FXML
	private Button modificar_cliente;

	@FXML
	private Button mostrar_estadisticas;

	@FXML
	private TableColumn<Alquiler, String> nombre_alquiler_table;

	@FXML
	private TableColumn<Cliente, String> nombre_cliente_table;

	@FXML
	private Text textMatriculaBuscar;

	@FXML
	private Text textoClienteBuscar;

	@FXML
	private TextField campoBuscar;

	@FXML
	private TableColumn<Vehiculo, String> plazas_vehiculo_table;

	@FXML
	private TableColumn<Vehiculo, String> pma_vehiculo_table;

	@FXML
	private Button salir;

	@FXML
	private TableView<Alquiler> tabla_alquileres;

	@FXML
	private TableView<Cliente> tabla_clientes;

	@FXML
	private TableView<Vehiculo> tabla_vehiculos;

	@FXML
	private ToggleGroup tablalistas;

	@FXML
	private TableView<Alquiler> tabla_alquileresBuscador;

	@FXML
	private TableColumn<Cliente, String> telefono_cliente_table;

	@FXML
	private TableColumn<Vehiculo, String> tipo_vehiculo_table;

	@FXML
	private Button vehiculos_button;

	@FXML
	private TableColumn<Alquiler, String> fechaAlq_alquiler_filtro;

	@FXML
	private TableColumn<Alquiler, String> fechaDev_alquiler_filtro;

	@FXML
	private TableColumn<Alquiler, String> matricula_alquiler_filtro;

	@FXML
	private TableColumn<Alquiler, String> nombre_alquiler_filtro;

	@FXML
	private RadioButton listAlquileresRb;

	@FXML
	private RadioButton listClientesRb;

	@FXML
	private RadioButton listVehiculosRb;

	@FXML
	private RadioButton listPorCliente;

	@FXML
	private RadioButton listPorVehiculo;

	@FXML
	private void initialize() {

		System.out.println("Inicializador");

	}

	private void listaClientes() {
		setClientes();
		nombre_cliente_table.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getNombre()));
		telefono_cliente_table
				.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getTelefono()));
		dni_cliente_table.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getDni()));
		tabla_clientes.setItems(listaCliente);
	}

	private void listaVehiculos() {
		setVehiculos();
		modelo_vehiculo_table
				.setCellValueFactory(vehiculo -> new SimpleStringProperty(vehiculo.getValue().getModelo()));
		marca_vehiculo_table.setCellValueFactory(vehiculo -> new SimpleStringProperty(vehiculo.getValue().getMarca()));
		matricula_telefono_table
				.setCellValueFactory(vehiculo -> new SimpleStringProperty(vehiculo.getValue().getMatricula()));
		pma_vehiculo_table.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Vehiculo, String>, ObservableValue<String>>() {

					public ObservableValue<String> call(TableColumn.CellDataFeatures<Vehiculo, String> vehiculo) {
						if (vehiculo.getValue() instanceof Furgoneta) {
							Furgoneta furgoneta = (Furgoneta) vehiculo.getValue();
							return new SimpleStringProperty(String.valueOf(furgoneta.getPma()));
						} else {
							return new SimpleStringProperty("Not eligible");
						}
					}
				});
		plazas_vehiculo_table.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Vehiculo, String>, ObservableValue<String>>() {

					public ObservableValue<String> call(TableColumn.CellDataFeatures<Vehiculo, String> vehiculo) {
						if (vehiculo.getValue() instanceof Furgoneta) {
							Furgoneta furgoneta = (Furgoneta) vehiculo.getValue();
							return new SimpleStringProperty(String.valueOf(furgoneta.getPlazas()));
						} else if (vehiculo.getValue() instanceof Autobus) {
							Autobus urtobus = (Autobus) vehiculo.getValue();
							return new SimpleStringProperty(String.valueOf(urtobus.getPlazas()));

						} else {
							return new SimpleStringProperty("Not eligible");
						}
					}
				});
		cilindrada_vehiculo_table.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Vehiculo, String>, ObservableValue<String>>() {

					public ObservableValue<String> call(TableColumn.CellDataFeatures<Vehiculo, String> vehiculo) {
						if (vehiculo.getValue() instanceof Turismo) {
							Turismo turismo = (Turismo) vehiculo.getValue();
							return new SimpleStringProperty(String.valueOf(turismo.getCilindrada()));
						} else {
							return new SimpleStringProperty("Not eligible");
						}
					}
				});
		tipo_vehiculo_table.setCellValueFactory(
				vehiculo -> new SimpleStringProperty(vehiculo.getValue().getClass().getSimpleName()));
		tabla_vehiculos.setItems(listaVehiculo);
	}

	private void listaAlquileres() {
		setAlquileres();
		nombre_alquiler_table
				.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getCliente().getDni()));
		matricula_alquiler_table.setCellValueFactory(
				alquiler -> new SimpleStringProperty(alquiler.getValue().getVehiculo().getMatricula()));
		fechaAlq_alquiler_table.setCellValueFactory(
				alquiler -> new SimpleStringProperty(alquiler.getValue().getFechaAlquiler().toString()));
		fechaDev_alquiler_table.setCellValueFactory(alquiler -> alquiler.getValue().getFechaDevolucion() == null
				? new SimpleStringProperty("Sin devolución")
				: new SimpleStringProperty(alquiler.getValue().getFechaDevolucion().toString()));
		tabla_alquileres.setItems(listaAlquiler);

	}

	private void listaFiltro() {
		setAlquileres();
		nombre_alquiler_filtro
				.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getCliente().getNombre()));
		matricula_alquiler_filtro.setCellValueFactory(
				alquiler -> new SimpleStringProperty(alquiler.getValue().getVehiculo().getMatricula()));
		fechaAlq_alquiler_filtro.setCellValueFactory(
				alquiler -> new SimpleStringProperty(alquiler.getValue().getFechaAlquiler().toString()));
		fechaDev_alquiler_filtro.setCellValueFactory(alquiler -> alquiler.getValue().getFechaDevolucion() == null
				? new SimpleStringProperty("Sin devolución")
				: new SimpleStringProperty(alquiler.getValue().getFechaDevolucion().toString()));
		tabla_alquileresBuscador.setItems(listaAlquiler);

	}

	public void setControladorMVC(IControlador controlador) {
		controladorMVC = controlador;
	}

	@FXML
	void activarClientes(ActionEvent event) {
		clientesVisible();
		vehiculosInvisible();
		alquileresInvisible();
		tablaAlquileresInvisible();
		tablaVehiculosInvisible();
		tablaClientesInvisible();
		deseleccionarBotones();

	};

	@FXML
	void activarVehiculos(ActionEvent event) {
		clientesInvisible();
		vehiculosVisible();
		alquileresInvisible();
		tablaAlquileresInvisible();
		tablaVehiculosInvisible();
		tablaClientesInvisible();
		listBuscadorClienteInvisible();
		listBuscadorVehiculoInvisible();
		deseleccionarBotones();

	};

	@FXML
	void activarAlquileres(ActionEvent event) {
		clientesInvisible();
		vehiculosInvisible();
		alquileresVisible();
		tablaAlquileresInvisible();
		tablaVehiculosInvisible();
		tablaClientesInvisible();
		listBuscadorClienteInvisible();
		listBuscadorVehiculoInvisible();
		deseleccionarBotones();

	};

	@FXML
	void listClientesVisibles(ActionEvent event) {
		clientesInvisible();
		vehiculosInvisible();
		alquileresInvisible();
		tablaAlquileresInvisible();
		tablaVehiculosInvisible();
		tablaClientesVisible();
		listaClientes();
		listBuscadorClienteInvisible();
		listBuscadorVehiculoInvisible();

	};

	@FXML
	void listVehiculosVisibles(ActionEvent event) {
		clientesInvisible();
		vehiculosInvisible();
		alquileresInvisible();
		tablaAlquileresInvisible();
		tablaVehiculosVisible();
		tablaClientesInvisible();
		listaVehiculos();
		listBuscadorClienteInvisible();
		listBuscadorVehiculoInvisible();

	};

	@FXML
	void listAlquileresVisibles(ActionEvent event) {
		clientesInvisible();
		vehiculosInvisible();
		alquileresInvisible();
		tablaAlquileresVisible();
		tablaVehiculosInvisible();
		tablaClientesInvisible();
		listaAlquileres();
		listBuscadorClienteInvisible();
		listBuscadorVehiculoInvisible();

	};

	@FXML
	void listAlquileresClienteVisibles(ActionEvent event) {
		clientesInvisible();
		vehiculosInvisible();
		alquileresInvisible();
		tablaAlquileresVisible();
		tablaVehiculosInvisible();
		tablaClientesInvisible();
		listaAlquileres();
		listBuscadorClienteInvisible();
		listBuscadorVehiculoInvisible();

	};

	@FXML
	void listAlquileresVehiculoVisibles(ActionEvent event) {
		clientesInvisible();
		vehiculosInvisible();
		alquileresInvisible();
		tablaAlquileresVisible();
		tablaVehiculosInvisible();
		tablaClientesInvisible();
		listaAlquileres();
		listBuscadorClienteInvisible();
		listBuscadorVehiculoInvisible();

	};

	@FXML
	void alquileresPorClienteVisibles(ActionEvent event) {
		clientesInvisible();
		vehiculosInvisible();
		alquileresInvisible();
		tablaAlquileresInvisible();
		tablaVehiculosInvisible();
		tablaClientesInvisible();
		listaAlquileres();

		listBuscadorVehiculoInvisible();
		listBuscadorClienteVisible();
		listaFiltro();

	};

	@FXML
	void alquileresPorVehiculoVisibles(ActionEvent event) {
		clientesInvisible();
		vehiculosInvisible();
		alquileresInvisible();
		tablaAlquileresInvisible();
		tablaVehiculosInvisible();
		tablaClientesInvisible();
		listaAlquileres();
		listBuscadorClienteInvisible();
		listBuscadorVehiculoVisible();
		listaFiltro();

	}

	void clientesVisible() {
		insertar_cliente.setVisible(true);
		insertar_cliente.setDisable(false);
		borrar_cliente.setVisible(true);
		borrar_cliente.setDisable(false);
		modificar_cliente.setVisible(true);
		modificar_cliente.setDisable(false);
		buscar_cliente.setVisible(true);
		buscar_cliente.setDisable(false);
	}

	void clientesInvisible() {
		insertar_cliente.setVisible(false);
		insertar_cliente.setDisable(true);
		borrar_cliente.setVisible(false);
		borrar_cliente.setDisable(true);
		modificar_cliente.setVisible(false);
		modificar_cliente.setDisable(true);
		buscar_cliente.setVisible(false);
		buscar_cliente.setDisable(true);
	}

	void vehiculosVisible() {
		insertar_vehiculo.setVisible(true);
		insertar_vehiculo.setDisable(false);
		borrar_vehiculo.setVisible(true);
		borrar_vehiculo.setDisable(false);
		mostrar_estadisticas.setVisible(true);
		mostrar_estadisticas.setDisable(false);
		buscar_vehiculo.setVisible(true);
		buscar_vehiculo.setDisable(false);
	}

	void vehiculosInvisible() {
		insertar_vehiculo.setVisible(false);
		insertar_vehiculo.setDisable(true);
		borrar_vehiculo.setVisible(false);
		borrar_vehiculo.setDisable(true);
		mostrar_estadisticas.setVisible(false);
		mostrar_estadisticas.setDisable(true);
		buscar_vehiculo.setVisible(false);
		buscar_vehiculo.setDisable(true);
	}

	void alquileresVisible() {
		insertar_alquiler.setVisible(true);
		insertar_alquiler.setDisable(false);
		borrar_alquiler.setVisible(true);
		borrar_alquiler.setDisable(false);
		devolver_cliente.setVisible(true);
		devolver_cliente.setDisable(false);
		devolver_vehiculo.setVisible(true);
		devolver_vehiculo.setDisable(false);

	}

	void alquileresInvisible() {
		insertar_alquiler.setVisible(false);
		insertar_alquiler.setDisable(true);
		borrar_alquiler.setVisible(false);
		borrar_alquiler.setDisable(true);
		devolver_cliente.setVisible(false);
		devolver_cliente.setDisable(true);
		devolver_vehiculo.setVisible(false);
		devolver_vehiculo.setDisable(true);

	}

	void tablaClientesVisible() {
		tabla_clientes.setDisable(false);
		tabla_clientes.setVisible(true);
	}

	void tablaClientesInvisible() {
		tabla_clientes.setDisable(true);
		tabla_clientes.setVisible(false);
	}

	void tablaVehiculosVisible() {
		tabla_vehiculos.setDisable(false);
		tabla_vehiculos.setVisible(true);
	}

	void tablaVehiculosInvisible() {
		tabla_vehiculos.setDisable(true);
		tabla_vehiculos.setVisible(false);
	}

	void tablaAlquileresVisible() {
		tabla_alquileres.setDisable(false);
		tabla_alquileres.setVisible(true);
	}

	void tablaAlquileresInvisible() {
		tabla_alquileres.setDisable(true);
		tabla_alquileres.setVisible(false);
	}

	void listBuscadorClienteVisible() {

		tabla_alquileresBuscador.setDisable(false);
		tabla_alquileresBuscador.setVisible(true);
		textoClienteBuscar.setDisable(false);
		textoClienteBuscar.setVisible(true);
		campoBuscar.setDisable(false);
		campoBuscar.setVisible(true);
	}

	void listBuscadorClienteInvisible() {

		tabla_alquileresBuscador.setDisable(true);
		tabla_alquileresBuscador.setVisible(false);
		textoClienteBuscar.setDisable(true);
		textoClienteBuscar.setVisible(false);
		campoBuscar.setDisable(true);
		campoBuscar.setVisible(false);
	}

	void listBuscadorVehiculoVisible() {

		tabla_alquileresBuscador.setDisable(false);
		tabla_alquileresBuscador.setVisible(true);
		textMatriculaBuscar.setDisable(false);
		textMatriculaBuscar.setVisible(true);
		campoBuscar.setDisable(false);
		campoBuscar.setVisible(true);
	}

	void listBuscadorVehiculoInvisible() {

		tabla_alquileresBuscador.setDisable(true);
		tabla_alquileresBuscador.setVisible(false);
		textMatriculaBuscar.setDisable(true);
		textMatriculaBuscar.setVisible(false);
		campoBuscar.setDisable(true);
		campoBuscar.setVisible(false);
	}

	void deseleccionarBotones() {
		listClientesRb.setSelected(false);
		listVehiculosRb.setSelected(false);
		listAlquileresRb.setSelected(false);
		listPorCliente.setSelected(false);
		listPorVehiculo.setSelected(false);
	}

	@FXML
	void filtrar(KeyEvent event) {

		String filtro = campoBuscar.getText();
		if (listPorCliente.isSelected()) {
			if (filtro.isEmpty()) {
				tabla_alquileresBuscador.setItems(listaAlquiler);
			} else {
				filtroAlquiler.clear();

				for (Alquiler alquiler : listaAlquiler) {

					if (alquiler.getCliente().getNombre().toLowerCase().contains(filtro.toLowerCase())) {
						filtroAlquiler.add(alquiler);
					}
				}
				tabla_alquileresBuscador.setItems(filtroAlquiler);
			}
		}
		if (listPorVehiculo.isSelected()) {
			if (filtro.isEmpty()) {
				tabla_alquileresBuscador.setItems(listaAlquiler);
			} else {
				filtroAlquiler.clear();

				for (Alquiler alquiler : listaAlquiler) {

					if (alquiler.getVehiculo().getMatricula().toLowerCase().contains(filtro.toLowerCase())) {
						filtroAlquiler.add(alquiler);
					}
				}
				tabla_alquileresBuscador.setItems(filtroAlquiler);
			}
		}

	}

	@FXML
	public void insertar_cliente_ventana(ActionEvent primaryStage) {

		try {
			setClientes();
			FXMLLoader loader = new FXMLLoader();
			InsertarCliente controlador = new InsertarCliente();
			loader.setController(controlador);
			loader.setLocation(getClass().getResource("../fxml/InsertarCliente.fxml"));
			Parent raiz = loader.load();
			controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setObs(listaCliente);
			Scene escena = new Scene(raiz);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(escena);
			stage.setTitle("Insertar un cliente.");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			String error = e.getMessage();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se produjo un error");
			alert.setContentText(error);

			alert.showAndWait();
			;
		}
	}

	@FXML
	public void modificar_cliente_ventana(ActionEvent primaryStage) {
		try {
			setClientes();
			FXMLLoader loader = new FXMLLoader();
			ModificarCliente controlador = new ModificarCliente();
			loader.setController(controlador);
			loader.setLocation(getClass().getResource("../fxml/ModificarCliente.fxml"));
			Parent raiz = loader.load();
			controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setObs(listaCliente);
			Scene escena = new Scene(raiz);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(escena);
			stage.setTitle("Modificar un cliente.");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			String error = e.getMessage();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se produjo un error");
			alert.setContentText(error);

			alert.showAndWait();
			;
		}
	}

	@FXML
	public void borrar_cliente_ventana(ActionEvent primaryStage) {
		try {
			setClientes();
			FXMLLoader loader = new FXMLLoader();
			BorrarCliente controlador = new BorrarCliente();
			loader.setController(controlador);
			loader.setLocation(getClass().getResource("../fxml/BorrarCliente.fxml"));
			Parent raiz = loader.load();
			controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setObs(listaCliente);
			Scene escena = new Scene(raiz);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(escena);
			stage.setTitle("Borrar un cliente.");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			String error = e.getMessage();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se produjo un error");
			alert.setContentText(error);

			alert.showAndWait();
			;
		}
	}

	@FXML
	public void buscar_cliente_ventana(ActionEvent primaryStage) {
		try {
			setClientes();
			FXMLLoader loader = new FXMLLoader();
			BuscarCliente controlador = new BuscarCliente();
			loader.setController(controlador);
			loader.setLocation(getClass().getResource("../fxml/BuscarCliente.fxml"));
			Parent raiz = loader.load();
			controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setObs(listaCliente);
			Scene escena = new Scene(raiz);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(escena);
			stage.setTitle("Buscar un cliente.");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			String error = e.getMessage();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se produjo un error");
			alert.setContentText(error);

			alert.showAndWait();
			;
		}
	}

	@FXML
	public void insertar_vehiculos_ventana(ActionEvent primaryStage) {
		try {
			listarmierdas();
			setVehiculos();
			FXMLLoader loader = new FXMLLoader();
			VentanaVehiculos controlador = new VentanaVehiculos();
			loader.setController(controlador);
			loader.setLocation(getClass().getResource("../fxml/VentanaVehiculos.fxml"));
			Parent raiz = loader.load();
			controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setObs(listaVehiculo);
			Scene escena = new Scene(raiz);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(escena);
			stage.setTitle("Insertar un vehiculo.");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			String error = e.getMessage();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se produjo un error");
			alert.setContentText(error);

			alert.showAndWait();
			;
		}
	}

	@FXML
	public void borrar_vehiculos_ventana(ActionEvent primaryStage) {
		try {
			setVehiculos();
			FXMLLoader loader = new FXMLLoader();
			BorrarVehiculos controlador = new BorrarVehiculos();
			loader.setController(controlador);
			loader.setLocation(getClass().getResource("../fxml/BorrarVehiculo.fxml"));
			Parent raiz = loader.load();
			controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setObs(listaVehiculo);
			Scene escena = new Scene(raiz);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(escena);
			stage.setTitle("Insertar un vehiculo.");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			String error = e.getMessage();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se produjo un error");
			alert.setContentText(error);

			alert.showAndWait();
			;
		}
	}

	@FXML
	public void buscar_vehiculos_ventana(ActionEvent primaryStage) {
		try {
			setVehiculos();
			FXMLLoader loader = new FXMLLoader();
			BuscarVehiculo controlador = new BuscarVehiculo();
			loader.setController(controlador);
			loader.setLocation(getClass().getResource("../fxml/BuscarVehiculo.fxml"));
			Parent raiz = loader.load();
			controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setObs(listaVehiculo);
			Scene escena = new Scene(raiz);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(escena);
			stage.setTitle("buscar un vehiculo.");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			String error = e.getMessage();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se produjo un error");
			alert.setContentText(error);

			alert.showAndWait();
			;
		}
	}

	@FXML
	public void insertar_alquiler_ventana(ActionEvent primaryStage) {
		try {
			listarmierdas();
			FXMLLoader loader = new FXMLLoader();
			AñadirAlquiler controlador = new AñadirAlquiler();
			loader.setController(controlador);
			loader.setLocation(getClass().getResource("../fxml/VentanaAlquileres.fxml"));
			Parent raiz = loader.load();
			controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setObs(listaAlquiler);
			Scene escena = new Scene(raiz);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(escena);
			stage.setTitle("Insertar un alquiler.");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			String error = e.getMessage();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se produjo un error");
			alert.setContentText(error);

			alert.showAndWait();
			;
		}
	}

	@FXML
	public void borrar_alquiler_ventana(ActionEvent primaryStage) {
		try {
			listarmierdas();
			FXMLLoader loader = new FXMLLoader();
			BorrarAlquiler controlador = new BorrarAlquiler();
			loader.setController(controlador);
			loader.setLocation(getClass().getResource("../fxml/BorrarAlquiler.fxml"));
			Parent raiz = loader.load();
			controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setObs(listaAlquiler);
			Scene escena = new Scene(raiz);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(escena);
			stage.setTitle("Borrar un alquiler.");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			String error = e.getMessage();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se produjo un error");
			alert.setContentText(error);

			alert.showAndWait();
			;
		}
	}

	@FXML
	public void devolverVehiculo_alquiler_ventana(ActionEvent primaryStage) {
		try {
			listarmierdas();
			FXMLLoader loader = new FXMLLoader();
			DevolverVehiculo controlador = new DevolverVehiculo();
			loader.setController(controlador);
			loader.setLocation(getClass().getResource("../fxml/DevolverVehiculo.fxml"));
			Parent raiz = loader.load();
			controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setObs(listaAlquiler);
			Scene escena = new Scene(raiz);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(escena);
			stage.setTitle("Devolver un alquiler.");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			String error = e.getMessage();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se produjo un error");
			alert.setContentText(error);

			alert.showAndWait();
			;
		}
	}

	@FXML
	public void devolverCliente_alquiler_ventana(ActionEvent primaryStage) {
		try {
			listarmierdas();
			FXMLLoader loader = new FXMLLoader();
			DevolverCliente controlador = new DevolverCliente();
			loader.setController(controlador);
			loader.setLocation(getClass().getResource("../fxml/DevolverCliente.fxml"));
			Parent raiz = loader.load();
			controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setObs(listaAlquiler);
			Scene escena = new Scene(raiz);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(escena);
			stage.setTitle("Devolver un alquiler.");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			String error = e.getMessage();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se produjo un error");
			alert.setContentText(error);

			alert.showAndWait();
			;
		}
	}

	public void listarmierdas() {
		System.out.println(listaCliente);
		System.out.println(listaVehiculo);
		System.out.println(listaAlquiler);
	}

	public void setClientes() {

		listaCliente.setAll(controladorMVC.getClientes());
	}

	public void setVehiculos() {

		listaVehiculo.setAll(controladorMVC.getVehiculos());

	}

	public void setAlquileres() {

		listaAlquiler.setAll(controladorMVC.getAlquileres());
	}

	@FXML
	void salir(ActionEvent event) {
		vista.terminar();
		Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
		escenario.close();
	}
}
