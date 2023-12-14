package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TabelJava extends Application {

    private TableView<Mahasiswa> table = new TableView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        final ObservableList<Mahasiswa> data = FXCollections.observableArrayList (
                new Mahasiswa ("Fitra Romeo Winky", "202210370311056", "fitraromeowinky@gmail.com"));

        Scene scene = new Scene(new Group());
        stage.setTitle("Test TableView");
        stage.setWidth(450);
        stage.setHeight(800);
        final Label label = new Label("Daftar Mahasiswa");
        label.setFont(new Font("Arial", 30));
        table.setEditable(true);


        TableColumn<Mahasiswa, String> nameCol = new TableColumn<>("Nama");
        TableColumn<Mahasiswa, String> nimCol = new TableColumn<>("NIM");
        TableColumn<Mahasiswa, String> emailCol = new TableColumn<>("Email");

        table.getColumns().addAll(nameCol, nimCol, emailCol);

        nameCol.setCellValueFactory(
                new PropertyValueFactory<Mahasiswa, String>("name")
        );

        nimCol.setCellValueFactory(
                new PropertyValueFactory<Mahasiswa, String>("nim")
        );

        emailCol.setCellValueFactory(
                new PropertyValueFactory<Mahasiswa, String>("email")
        );

        table.setItems(data);


        TextField addName = new TextField();
        addName.setMaxWidth(nameCol.getPrefWidth());
        addName.setPromptText("Nama Mahasiswa");

        TextField addNim = new TextField();
        addNim.setMaxWidth(nimCol.getPrefWidth());
        addNim.setPromptText("NIM");

        TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        final Button addButton = new Button("Add");
        addButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                data.add(new Mahasiswa (
                        addName.getText(),
                        addNim.getText(),
                        addEmail.getText()
                ));
                addName.clear();
                addNim.clear();
                addEmail.clear();
            }
        });

        final HBox hBoxInput = new HBox();
        hBoxInput.getChildren().addAll(addName,addNim,addEmail,addButton);
        hBoxInput.setSpacing(10);


        final VBox vbox = new VBox();
        vbox.setSpacing(8);
        vbox.setPadding(new Insets(20, 10, 10, 10));
        vbox.getChildren().addAll(label, table, hBoxInput);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();

    }

    public static class Mahasiswa {

        private final SimpleStringProperty name;
        private final SimpleStringProperty nim;
        private final SimpleStringProperty email;

        private Mahasiswa(String name, String nim, String email) {
            this.name = new SimpleStringProperty(name);
            this.nim = new SimpleStringProperty(nim);
            this.email = new SimpleStringProperty(email);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String fName) {
            name.set(fName);
        }

        public String getNim() {
            return nim.get();
        }

        public void setNim(String fName) {
            nim.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }
    }

}