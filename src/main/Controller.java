package main;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    private Connection connection;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView table;

    @FXML
    @SuppressWarnings ("unchecked")
    void find(ActionEvent event) {
        TableColumn nameColumn = new TableColumn("ФИО");
        nameColumn.setCellValueFactory(new PropertyValueFactory<> ("name"));

        TableColumn surnameColumn = new TableColumn("Должность");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("kto"));

        table.getColumns().addAll(nameColumn, surnameColumn);

        try {
            PreparedStatement statement = connection.prepareStatement ("SELECT * FROM workers WHERE 'position' = 'Программист'");
            ResultSet resultSet = statement.executeQuery ();
            while (resultSet.next ()) {
                Person person = new Person (resultSet.getString ("fName"), resultSet.getString ("position"));
                table.getItems ().add (person);
            }
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }

    @FXML
    void initialize() {
        String url = "";
        String name = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection (url, name, password);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }
}
