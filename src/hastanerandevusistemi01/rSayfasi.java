package hastanerandevusistemi01;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Arclus
 */

public class rSayfasi extends Application
{
    static Button btn01 = new Button("09:00");
    static Button btn02 = new Button("09:10");
    static Button btn03 = new Button("09:20");
    static Button btn04 = new Button("09:30");
    static Button btn05 = new Button("09:40");
    static Button btn06 = new Button("09:50");
    static Button btn07 = new Button("10:00");
    static Button btn08 = new Button("10:10");
    static Button btn09 = new Button("10:20");
    static Button btn10 = new Button("10:30");
    static Button btn11 = new Button("10:40");
    static Button btn12 = new Button("10:50");
    static Button btn13 = new Button("11:00");
    static Button btn14 = new Button("11:10");
    static Button btn15 = new Button("11:20");
    static Button btn16 = new Button("11:30");
    static Button btn17 = new Button("11:40");
    static Button btn18 = new Button("11:50");
    static Button btn19 = new Button("13:00");
    static Button btn20 = new Button("13:10");
    static Button btn21 = new Button("13:20");
    static Button btn22 = new Button("13:30");
    static Button btn23 = new Button("13:40");
    static Button btn24 = new Button("13:50");
    static Button btn25 = new Button("14:00");
    static Button btn26 = new Button("14:10");
    static Button btn27 = new Button("14:20");
    static Button btn28 = new Button("14:30");
    static Button btn29 = new Button("14:40");
    static Button btn30 = new Button("14:50");
    static Button btn31 = new Button("15:00");
    static Button btn32 = new Button("15:10");
    static Button btn33 = new Button("15:20");
    static Button btn34 = new Button("15:30");
    static Button btn35 = new Button("15:40");
    static Button btn36 = new Button("15:50");
    static Button rAl = new Button("Randevu Al");
    static Button rG = new Button("Randevu Güncelle");
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setX(445);
        primaryStage.setY(180);
        primaryStage.setTitle("Randevu Al");

        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label date = new Label("Tarih :");
        grid.add(date, 0, 1);

        DatePicker rDatePicker = new DatePicker();
        rDatePicker.setMinSize(300, 35);
        rDatePicker.setMaxSize(300, 35);
        rDatePicker.setPromptText("Gün/Ay/Yıl");
        rDatePicker.setEditable(false);
        rDatePicker.setShowWeekNumbers(true);

        Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
        rDatePicker.setDayCellFactory(dayCellFactory);

        grid.add(rDatePicker, 1, 1);

        Label cityName = new Label("Şehir :");
        grid.add(cityName, 0, 2);

        final ComboBox cityComboBox = new ComboBox();
        cityComboBox.setMinSize(300, 35);
        cityComboBox.setMaxSize(300, 35);
        cityComboBox.setDisable(true);
        grid.add(cityComboBox, 1, 2);

        Label districtName = new Label("İlçe :");
        grid.add(districtName, 0, 3);

        final ComboBox districtComboBox = new ComboBox();
        districtComboBox.setMinSize(300, 35);
        districtComboBox.setMaxSize(300, 35);
        districtComboBox.setDisable(true);

        grid.add(districtComboBox, 1, 3);

        Label hospitalName = new Label("Hastane :");
        grid.add(hospitalName, 0, 4);

        final ComboBox hospitalComboBox = new ComboBox();

        hospitalComboBox.setDisable(true);
        hospitalComboBox.setMinSize(300, 35);
        hospitalComboBox.setMaxSize(300, 35);
        grid.add(hospitalComboBox, 1, 4);

        Label kName = new Label("Klinik :");
        grid.add(kName, 0, 5);

        final ComboBox kComboBox = new ComboBox();
        kComboBox.setMinSize(300, 35);
        kComboBox.setMaxSize(300, 35);
        kComboBox.setDisable(true);
        grid.add(kComboBox, 1, 5);

        Label doctorName = new Label("Doktor :");
        grid.add(doctorName, 0, 6);

        final ComboBox doctorComboBox = new ComboBox();
        doctorComboBox.setMinSize(300, 35);
        doctorComboBox.setMaxSize(300, 35);
        doctorComboBox.setDisable(true);
        grid.add(doctorComboBox, 1, 6);
        
        disableButton();
        
        rDatePicker.setOnAction((ActionEvent e) ->
        {
            cityComboBox.getItems().clear();
            districtComboBox.getItems().clear();
            hospitalComboBox.getItems().clear();
            kComboBox.getItems().clear();
            doctorComboBox.getItems().clear();
            disableButton();
            
            cityComboBox.setDisable(false);
            String cB = DBIslemleri.getCity();

        if (cB.equals("1"))
        {
            cityComboBox.setItems(DBIslemleri.cOptions);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Information of Alert");
            alert.setContentText(cB);
            alert.show();
        }
        });    

        cityComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                if (t1 != null)
                {
                    districtComboBox.getItems().clear();
                    districtComboBox.setDisable(false);
                    hospitalComboBox.getItems().clear();
                    kComboBox.getItems().clear();
                    doctorComboBox.getItems().clear();
                    disableButton();

                    DBIslemleri.sCityIndex(cityComboBox.getSelectionModel().getSelectedItem().toString());

                    final String dB = DBIslemleri.getDistrict();

                    if (dB.equals("1"))
                    {
                        districtComboBox.setItems(DBIslemleri.dOptions);
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(dB);
                        alert.show();
                    }
                }
                else
                {
                    districtComboBox.setDisable(true);
                }
            }
        });

        districtComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                if (t1 != null)
                {
                    hospitalComboBox.getItems().clear();
                    hospitalComboBox.setDisable(false);
                    kComboBox.getItems().clear();
                    doctorComboBox.getItems().clear();
                    disableButton();

                    final String hB = DBIslemleri.getHospital(cityComboBox.getSelectionModel().getSelectedItem().toString(), districtComboBox.getSelectionModel().getSelectedItem().toString());

                    if (hB.equals("1"))
                    {
                        hospitalComboBox.setItems(DBIslemleri.hOptions);
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(hB);
                        alert.show();
                    }
                }
                else
                {
                    hospitalComboBox.setDisable(true);
                }
            }
        });

        hospitalComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                if (t1 != null)
                {
                    kComboBox.getItems().clear();
                    kComboBox.setDisable(false);
                    doctorComboBox.getItems().clear();
                    disableButton();

                    DBIslemleri.sHospitalID(hospitalComboBox.getSelectionModel().getSelectedItem().toString());
                    String kB = DBIslemleri.getK();

                    if (kB.equals("1"))
                    {
                        kComboBox.setItems(DBIslemleri.kOptions);
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(kB);
                        alert.show();
                    }
                }
                else
                {
                    kComboBox.setDisable(true);
                }
            }
        });

        kComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                if (t1 != null)
                {
                    doctorComboBox.getItems().clear();
                    doctorComboBox.setDisable(false);
                    disableButton();
                    
                    DBIslemleri.getKID(kComboBox.getSelectionModel().getSelectedItem().toString(), DBIslemleri.SHI);
                    
                    String dOB = DBIslemleri.getDoctor(DBIslemleri.kID);

                    if (dOB.equals("1"))
                    {
                        doctorComboBox.setItems(DBIslemleri.dOOptions);
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(dOB);
                        alert.show();
                    }
                }
                else
                {
                    doctorComboBox.setDisable(true);
                }
            }
        });


        doctorComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                if (t1 != null)
                {
                    disableButton();
                    DBIslemleri.sDoctorID(doctorComboBox.getSelectionModel().getSelectedItem().toString());
                    checkButton(rDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), doctorComboBox.getSelectionModel().getSelectedItem().toString());
                }
                else
                {

                }
            }
        });
        
        hndButton();
        
        Label btn01L = new Label("09:00");
        grid.add(btn01L, 2, 1);



        HBox hbBtn01 = new HBox(10);
        hbBtn01.setAlignment(Pos.TOP_CENTER);
        hbBtn01.getChildren().addAll(btn01, btn02, btn03, btn04, btn05, btn06);
        grid.add(hbBtn01, 3, 1, 1, 1);

        Label btn02L = new Label("10:00");
        grid.add(btn02L, 2, 2);



        HBox hbBtn02 = new HBox(10);
        hbBtn02.setAlignment(Pos.TOP_CENTER);
        hbBtn02.getChildren().addAll(btn07, btn08, btn09, btn10, btn11, btn12);
        grid.add(hbBtn02, 3, 2, 1, 1);

        Label btn03L = new Label("11:00");
        grid.add(btn03L, 2, 3);



        HBox hbBtn03 = new HBox(10);
        hbBtn03.setAlignment(Pos.TOP_CENTER);
        hbBtn03.getChildren().addAll(btn13, btn14, btn15, btn16, btn17, btn18);
        grid.add(hbBtn03, 3, 3, 1, 1);

        Label btn04L = new Label("13:00");
        grid.add(btn04L, 2, 4);



        HBox hbBtn04 = new HBox(10);
        hbBtn04.setAlignment(Pos.TOP_CENTER);
        hbBtn04.getChildren().addAll(btn19, btn20, btn21, btn22, btn23, btn24);
        grid.add(hbBtn04, 3, 4, 1, 1);

        Label btn05L = new Label("14:00");
        grid.add(btn05L, 2, 5);



        HBox hbBtn05 = new HBox(10);
        hbBtn05.setAlignment(Pos.TOP_CENTER);
        hbBtn05.getChildren().addAll(btn25, btn26, btn27, btn28, btn29, btn30);
        grid.add(hbBtn05, 3, 5, 1, 1);

        Label btn06L = new Label("15:00");
        grid.add(btn06L, 2, 6);



        HBox hbBtn06 = new HBox(10);
        hbBtn06.setAlignment(Pos.TOP_CENTER);
        hbBtn06.getChildren().addAll(btn31, btn32, btn33, btn34, btn35, btn36);
        grid.add(hbBtn06, 3, 6, 1, 1);

        rAl.setMinSize(160, 30);
        grid.add(rAl, 0, 7);

        rAl.setOnAction((ActionEvent e)
                -> 
                {
                    String rA = DBIslemleri.addR(HastaneRandevuSistemi01.tcNo, DBIslemleri.sDID, rDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), DBIslemleri.time);

                    if (rA.equals("1"))
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText("Randevunuz Eklendi.");
                        alert.show();

                        DBIslemleri.deleteH(rDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), DBIslemleri.time);
                        rAl.setDisable(true);
                        disableButton();
                        checkButton(rDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), doctorComboBox.getSelectionModel().getSelectedItem().toString());
                    }

                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(rA);
                        alert.show();
                    }
        });
        
        rG.setMinSize(160, 30);
        grid.add(rG, 1, 7);
        
        rG.setOnAction((ActionEvent e)
                -> 
                {
                    rEdit rE = new rEdit();
                    rE.start(primaryStage);
        });

        Scene scene = new Scene(grid, 1100, 600);
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

    final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(final ActionEvent event)
        {
            String txt = ((Button) event.getSource()).getText();

            rAl.setDisable(false);

            //((Button) event.getSource()).setDisable(true);

            DBIslemleri.time = txt;
        }
    };
    
    public void hndButton()
    {
        btn01.setOnAction(myHandler);
        btn02.setOnAction(myHandler);
        btn03.setOnAction(myHandler);
        btn04.setOnAction(myHandler);
        btn05.setOnAction(myHandler);
        btn06.setOnAction(myHandler);
        btn07.setOnAction(myHandler);
        btn08.setOnAction(myHandler);
        btn09.setOnAction(myHandler);
        btn10.setOnAction(myHandler);
        btn11.setOnAction(myHandler);
        btn12.setOnAction(myHandler);
        btn13.setOnAction(myHandler);
        btn14.setOnAction(myHandler);
        btn15.setOnAction(myHandler);
        btn16.setOnAction(myHandler);
        btn17.setOnAction(myHandler);
        btn18.setOnAction(myHandler);
        btn19.setOnAction(myHandler);
        btn20.setOnAction(myHandler);
        btn21.setOnAction(myHandler);
        btn22.setOnAction(myHandler);
        btn23.setOnAction(myHandler);
        btn24.setOnAction(myHandler);
        btn25.setOnAction(myHandler);
        btn26.setOnAction(myHandler);
        btn27.setOnAction(myHandler);
        btn28.setOnAction(myHandler);
        btn29.setOnAction(myHandler);
        btn30.setOnAction(myHandler);
        btn31.setOnAction(myHandler);
        btn32.setOnAction(myHandler);
        btn33.setOnAction(myHandler);
        btn34.setOnAction(myHandler);
        btn35.setOnAction(myHandler);
        btn36.setOnAction(myHandler);
    }

    public void disableButton()
    {
        btn01.setDisable(true);
        btn02.setDisable(true);
        btn03.setDisable(true);
        btn04.setDisable(true);
        btn05.setDisable(true);
        btn06.setDisable(true);
        btn07.setDisable(true);
        btn08.setDisable(true);
        btn09.setDisable(true);
        btn10.setDisable(true);
        btn11.setDisable(true);
        btn12.setDisable(true);
        btn13.setDisable(true);
        btn14.setDisable(true);
        btn15.setDisable(true);
        btn16.setDisable(true);
        btn17.setDisable(true);
        btn18.setDisable(true);
        btn19.setDisable(true);
        btn20.setDisable(true);
        btn21.setDisable(true);
        btn22.setDisable(true);
        btn23.setDisable(true);
        btn24.setDisable(true);
        btn25.setDisable(true);
        btn26.setDisable(true);
        btn27.setDisable(true);
        btn28.setDisable(true);
        btn29.setDisable(true);
        btn30.setDisable(true);
        btn31.setDisable(true);
        btn32.setDisable(true);
        btn33.setDisable(true);
        btn34.setDisable(true);
        btn35.setDisable(true);
        btn36.setDisable(true);
    }

    public void checkButton(String rDatePicker, String doctorComboBox)
    {
        String cB = DBIslemleri.getB(rDatePicker, doctorComboBox);

        if (cB.equals("1"))
        {
            for (int i = 0; i < DBIslemleri.hS.size(); i++)
            {
                String s = DBIslemleri.hS.get(i).toString();
                
                switch (s)
                {
                    case "09:00":
                    {
                        btn01.setDisable(false);
                        break;
                    }
                    case "09:10":
                    {
                        btn02.setDisable(false);
                        break;
                    }
                    case "09:20":
                    {
                        btn03.setDisable(false);
                        break;
                    }
                    case "09:30":
                    {
                        btn04.setDisable(false);
                        break;
                    }
                    case "09:40":
                    {
                        btn05.setDisable(false);
                        break;
                    }
                    case "09:50":
                    {
                        btn06.setDisable(false);
                        break;
                    }
                    case "10:00":
                    {
                        btn07.setDisable(false);
                        break;
                    }
                    case "10:10":
                    {
                        btn08.setDisable(false);
                        break;
                    }
                    case "10:20":
                    {
                        btn09.setDisable(false);
                        break;
                    }
                    case "10:30":
                    {
                        btn10.setDisable(false);
                        break;
                    }
                    case "10:40":
                    {
                        btn11.setDisable(false);
                        break;
                    }
                    case "10:50":
                    {
                        btn12.setDisable(false);
                        break;
                    }
                    case "11:00":
                    {
                        btn13.setDisable(false);
                        break;
                    }
                    case "11:10":
                    {
                        btn14.setDisable(false);
                        break;
                    }
                    case "11:20":
                    {
                        btn15.setDisable(false);
                        break;
                    }
                    case "11:30":
                    {
                        btn16.setDisable(false);
                        break;
                    }
                    case "11:40":
                    {
                        btn17.setDisable(false);
                        break;
                    }
                    case "11:50":
                    {
                        btn18.setDisable(false);
                        break;
                    }
                    case "13:00":
                    {
                        btn19.setDisable(false);
                        break;
                    }
                    case "13:10":
                    {
                        btn20.setDisable(false);
                        break;
                    }
                    case "13:20":
                    {
                        btn21.setDisable(false);
                        break;
                    }
                    case "13:30":
                    {
                        btn22.setDisable(false);
                        break;
                    }
                    case "13:40":
                    {
                        btn23.setDisable(false);
                        break;
                    }
                    case "13:50":
                    {
                        btn24.setDisable(false);
                        break;
                    }
                    case "14:00":
                    {
                        btn25.setDisable(false);
                        break;
                    }
                    case "14:10":
                    {
                        btn26.setDisable(false);
                        break;
                    }
                    case "14:20":
                    {
                        btn27.setDisable(false);
                        break;
                    }
                    case "14:30":
                    {
                        btn28.setDisable(false);
                        break;
                    }
                    case "14:40":
                    {
                        btn29.setDisable(false);
                        break;
                    }
                    case "14:50":
                    {
                        btn30.setDisable(false);
                        break;
                    }
                    case "15:00":
                    {
                        btn31.setDisable(false);
                        break;
                    }
                    case "15:10":
                    {
                        btn32.setDisable(false);
                        break;
                    }
                    case "15:20":
                    {
                        btn33.setDisable(false);
                        break;
                    }
                    case "15:30":
                    {
                        btn34.setDisable(false);
                        break;
                    }
                    case "15:40":
                    {
                        btn35.setDisable(false);
                        break;
                    }
                    case "15:50":
                    {
                        btn36.setDisable(false);
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
            }
        }

        else
        {
            System.out.println("asdsdfsdf");
        }
    }
}
