package cz.vse.java.funwithjpa.gui;

import cz.vse.java.funwithjpa.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Map;

public class Gui {
    private Controller controller;

    private ObservableList<TableRecord> tableData;

    private Pane pnlPane;
    private TableView tblTable;
    private TextField txtLoginFilter;

    public Gui(Controller controller) {
        this.controller = controller;

        this.tableData = FXCollections.observableArrayList();

        initGui();
    }

    private void initGui() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(0, 10, 10, 10));
        vbox.setSpacing(5);

        createHeading("Data", vbox);
        tblTable = new TableView();
        createTableColumns(tblTable);
        tblTable.setPrefHeight(250);
        tblTable.setItems(tableData);
        vbox.getChildren().add(tblTable);

        createHeading("Filter", vbox);
        Label lblLoginFilter = new Label("Login:");
        txtLoginFilter = new TextField();
        vbox.getChildren().add(new HBox(10, lblLoginFilter, txtLoginFilter));

        createHeading("Actions", vbox);
        Button btnReload = new Button("Reload data");
        btnReload.setOnAction( event -> controller.reloadData(txtLoginFilter.getText(), tableData) );
        Button btnInsert = new Button("Insert new record");
        btnInsert.setOnAction( event -> controller.insertNewRecord(tableData) );
        vbox.getChildren().add(new HBox(10, btnReload, btnInsert));

        pnlPane = vbox;
    }

    public Pane getPane() {
        return pnlPane;
    }

    private void createHeading(String caption, Pane pane) {
        Label label = new Label(caption);
        label.setFont(new Font("Verdana", 15));
        label.setPadding(new Insets(10, 0, 0, 0));

        pane.getChildren().add(label);
    }

    private void createTableColumns(TableView tableView) {
        for (Map.Entry<String, String> columnDefinition : TableRecord.COLUMNS.entrySet()) {
            TableColumn column = new TableColumn<>(columnDefinition.getValue());
            column.setCellValueFactory(new PropertyValueFactory<TableRecord, String>(columnDefinition.getKey()));

            tableView.getColumns().add(column);
        }
    }

}
