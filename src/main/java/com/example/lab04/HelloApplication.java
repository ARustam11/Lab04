package com.example.lab04;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        //Nodes
        Text txtNote = new Text("Notification");
        txtNote.setFont(Font.font("Comic Sans MS",25));
        txtNote.setFill(Color.GREEN);


        TextField fldAdd = new TextField();
        fldAdd.setPromptText("Enter name");


        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(85);


        Button btnDelete = new Button("Delete");
        btnDelete.setMinWidth(85);

        //ListView and Observable List
        ListView<String> listView = new ListView<>();
        ObservableList<String> names = FXCollections.observableArrayList();
        listView.setItems(names);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.setMaxSize(300, 300);

        //ObservableList ChangeListener
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String input = fldAdd.getText().trim();
                if (validateInput(input)) {
                    names.add(input);
                    txtNote.setText("Name added successfully.");
                    txtNote.setFill(Color.GREEN);
                } else {
                    txtNote.setText("Name cannot be empty\nName must start with uppercase letter\nName must be at least 5 characters long");
                    txtNote.setFill(Color.RED);
                    txtNote.setFont(Font.font("Comic Sans MS",15));
                }
                fldAdd.clear();
                listView.getSelectionModel().clearSelection();
            }
        });



        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String selectedName = listView.getSelectionModel().getSelectedItem();
                if (selectedName != null) {
                    names.remove(selectedName);
                    txtNote.setText("Name deleted successfully.");
                    txtNote.setFill(Color.GREEN);
                } else {
                    txtNote.setText("Select a name to delete.");
                    txtNote.setFill(Color.RED);
                }
                listView.getSelectionModel().clearSelection();
            }
        });


        VBox right = new VBox(10);
        right.setPadding(new Insets(10));
        right.setAlignment(Pos.CENTER);
        right.getChildren().addAll(fldAdd, btnAdd, btnDelete);

        BorderPane root = new BorderPane();
        root.setCenter(listView);
        root.setRight(right);
        root.setBottom(txtNote);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Username listing Lab04");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validateInput(String input) {
        if (input.isEmpty() || input.length() < 5 || !Character.isUpperCase(input.charAt(0))) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}