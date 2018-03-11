package hastanerandevusistemi01;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Arclus
 */
public class rEdit
{

    static ObservableList<FTable> data = null;
    static TableView<FTable> table = null;

    static FTable selectedRandevu;
    static String selectedRID;
    static String selectedDID;
    static String selectedDAdi;
    static String selectedRTarih;
    static String selectedRSaat;
    public void start(Stage primaryStage)
    {
        primaryStage.setX(320);
        primaryStage.setY(60);
        primaryStage.setTitle("Randevu Güncelle");

        GridPane grid = new GridPane();

        grid.setHgap(30);
        grid.setVgap(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        data = FXCollections.observableArrayList();
        table = new TableView<>();

      
        TableColumn column1 = new TableColumn("TC");
        column1.setMinWidth(70);
        column1.setCellValueFactory(new PropertyValueFactory<>("tc"));

        TableColumn column2 = new TableColumn("HASTANE");
        column2.setMinWidth(450);
        column2.setCellValueFactory(new PropertyValueFactory<>("hastane"));

        TableColumn column3 = new TableColumn("KLİNİK");
        column3.setMinWidth(70);
        column3.setCellValueFactory(new PropertyValueFactory<>("klinik"));

        TableColumn column4 = new TableColumn("DOKTOR");
        column4.setMinWidth(190);
        column4.setCellValueFactory(new PropertyValueFactory<>("hekim"));

        TableColumn column5 = new TableColumn("TARİH");
        column5.setMinWidth(70);
        column5.setCellValueFactory(new PropertyValueFactory<>("tarih"));

        TableColumn column6 = new TableColumn("SAAT");
        column6.setMinWidth(70);
        column6.setCellValueFactory(new PropertyValueFactory<>("saat"));
		
        table.getColumns().addAll(column1, column2, column3, column4, column5, column6);
        GridPane.setConstraints(table, 0, 1);
        table.setMinSize(1072, 350);
        table.setMaxSize(1072, 350);
        grid.add(table, 0, 0);

        Button btn = new Button("Geri Dön");
        btn.setMinSize(160, 30);

        Label lbl = new Label("Güncellemek için Tarih Seçiniz");

        DatePicker rDatePicker = new DatePicker();
        rDatePicker.setMinSize(300, 35);
        rDatePicker.setMaxSize(300, 35);
        rDatePicker.setPromptText("Gün/Ay/Yıl");
        rDatePicker.setEditable(false);
        rDatePicker.setShowWeekNumbers(true);
        rDatePicker.setDisable(true);

        Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
        rDatePicker.setDayCellFactory(dayCellFactory);

        Button searchBtn = new Button("Sorgula");
        searchBtn.setDisable(true);
        searchBtn.setMinSize(160, 30);
        Button updateBtn = new Button("Güncelle");
        updateBtn.setDisable(true);
        updateBtn.setMinSize(160, 30);

        final ComboBox rSComboBox = new ComboBox();
        rSComboBox.setDisable(true);
        rSComboBox.setMinSize(300, 35);
        rSComboBox.setMaxSize(300, 35);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> 
                {
                    if (newSelection != null)
                    {
                        selectedRandevu = table.getSelectionModel().getSelectedItem();

                        selectedDID = selectedRandevu.getDoktorID();
                        selectedDAdi = selectedRandevu.getHekim();
                        selectedRTarih = selectedRandevu.getTarih();
                        selectedRSaat = selectedRandevu.getSaat();
                        selectedRID = selectedRandevu.getRandevuID();

                        searchBtn.setDisable(false);
                        rDatePicker.setDisable(false);
                    }
        });

        String gR = DBIslemleri.getR();

        if (gR.equals("1"))
        {
            //System.out.println("Çalıştı");
        }

        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Information of Alert");
            alert.setContentText(gR);
            alert.show();
        }

        Button deleteBtn = new Button("Sil");
        deleteBtn.setMinSize(160, 30);

        deleteBtn.setOnAction((ActionEvent e)
                -> 
                {
                    String aNR = DBIslemleri.addNewR(selectedDID, selectedRTarih, selectedRSaat);

                    if (aNR.equals("1"))
                    {
                        //System.out.println("Çalıştı2");
                    }

                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(aNR);
                        alert.show();
                    }

                    DBIslemleri.deleteRandevu(selectedRID);

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Information");
                    alert2.setHeaderText("Information of Alert");
                    alert2.setContentText("Randevunuz İptal Edilmiştir");
                    alert2.show();

                    table.refresh();

                    String gR2 = DBIslemleri.getR();

                    if (gR2.equals("1"))
                    {
                        System.out.println("Çalıştı");
                    }

                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(gR2);
                        alert.show();
                    }
        });

        btn.setOnAction((ActionEvent e)
                -> 
                {
                    rSayfasi rS = new rSayfasi();
                    rS.start(primaryStage);
        });

        searchBtn.setOnAction((ActionEvent e)
                -> 
                {
                    String gB = DBIslemleri.getB(rDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), selectedDAdi);

                    if (gB.equals("1"))
                    {
                        rSComboBox.setItems(DBIslemleri.hS);
                        rSComboBox.setDisable(false);
                    }

                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(gB);
                        alert.show();
                    }
        });

        rSComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                if (t1 != null)
                {
                    updateBtn.setDisable(false);
                }
            }
        });

        updateBtn.setOnAction((ActionEvent e)
                -> 
                {
                    String aNR = DBIslemleri.addNewR(selectedDID, selectedRTarih, selectedRSaat);

                    if (aNR.equals("1"))
                    {
                        //System.out.println("Hizmete eklendi");
                    }

                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(aNR);
                        alert.show();
                    }

                    String uR = DBIslemleri.updateR(selectedRID, rDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), rSComboBox.getSelectionModel().getSelectedItem().toString());

                    if (uR.equals("1"))
                    {
                        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                        alert3.setTitle("INFORMATION");
                        alert3.setHeaderText("Information of Alert");
                        alert3.setContentText("Randevunuz Güncellendi.");
                        alert3.show();

                        DBIslemleri.deleteH(rDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), rSComboBox.getSelectionModel().getSelectedItem().toString());

                        table.refresh();

                        String gR2 = DBIslemleri.getR();

                        if (gR2.equals("1"))
                        {
                            System.out.println("Çalıştı");
                        }

                        else
                        {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("Information of Alert");
                            alert.setContentText(gR2);
                            alert.show();
                        }
                    }

                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(uR);
                        alert.show();
                    }

        });

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(lbl, rDatePicker, searchBtn, rSComboBox, updateBtn);
        vBox.setAlignment(Pos.CENTER);
        GridPane.setConstraints(vBox, 0, 2);

        grid.add(vBox, 0, 1);

        VBox vBox2 = new VBox();
        vBox2.setSpacing(50);
        vBox2.getChildren().addAll(deleteBtn, btn);
        vBox2.setAlignment(Pos.CENTER);
        GridPane.setConstraints(vBox2, 1, 0);

        grid.add(vBox2, 1, 0);

        Scene scene = new Scene(grid, 1300, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Callback<DatePicker, DateCell> getDayCellFactory()
    {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>()
        {
            @Override
            public DateCell call(final DatePicker datePicker)
            {
                return new DateCell()
                {
                    @Override
                    public void updateItem(LocalDate item, boolean empty)
                    {
                        super.updateItem(item, empty);

                        // Disable Monday, Tueday, Wednesday.
                        if (item.getDayOfWeek() == DayOfWeek.SATURDAY //
                                || item.getDayOfWeek() == DayOfWeek.SUNDAY)
                        {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };

        return dayCellFactory;
    }
}
