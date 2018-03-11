package hastanerandevusistemi01;

import static hastanerandevusistemi01.rEdit.data;
import static hastanerandevusistemi01.rEdit.table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Arclus
 */

public class rSorgula
{
    static ObservableList<FTable> data2 = null;
    static TableView<FTable> table2 = null;
    
    public void start(Stage primaryStage)
    {
        primaryStage.setX(401);
        primaryStage.setY(198);
        primaryStage.setTitle("Randevu Sorgula");
        
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label tcNo = new Label("TC No : ");
		
        TextField tcNTextField = new TextField();
        tcNTextField.setPromptText("TC No");

        
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_CENTER);
        hBox.getChildren().addAll(tcNo, tcNTextField);
        grid.add(hBox, 0, 0);
        
        data2 = FXCollections.observableArrayList();
        table2 = new TableView<>();
        
        TableColumn column1 = new TableColumn("TC");
        column1.setMinWidth(70);
        column1.setCellValueFactory(new PropertyValueFactory<>("tc"));
        
        TableColumn column2 = new TableColumn("HASTANE");
        column2.setMinWidth(450);
        column2.setCellValueFactory(new PropertyValueFactory<>("hastane"));

        TableColumn column3 = new TableColumn("KLİNİK");
        column3.setMinWidth(150);
        column3.setCellValueFactory(new PropertyValueFactory<>("klinik"));

        TableColumn column4 = new TableColumn("DOKTOR");
        column4.setMinWidth(220);
        column4.setCellValueFactory(new PropertyValueFactory<>("hekim"));

        TableColumn column5 = new TableColumn("TARİH");
        column5.setMinWidth(90);
        column5.setCellValueFactory(new PropertyValueFactory<>("tarih"));

        TableColumn column6 = new TableColumn("SAAT");
        column6.setMinWidth(50);
        column6.setCellValueFactory(new PropertyValueFactory<>("saat"));
        
        table2.getColumns().addAll(column1, column2, column3, column4, column5,column6);
        GridPane.setConstraints(table2, 0, 1);
        table2.setMinSize(1072, 350);
        table2.setMaxSize(1072, 350);
        grid.add(table2, 0, 1);
        
        tcNTextField.textProperty().addListener((observable, oldValue, newValue) ->
        {

            if (newValue.length() <= 0)
            {
                data2.clear();
                table2.getItems().clear();
            }
            else
            {
                DBIslemleri.sorgulaR(newValue);
            }

        });
        
        Scene scene = new Scene(grid, 1200, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
