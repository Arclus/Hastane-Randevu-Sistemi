package hastanerandevusistemi01;

import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Arclus
 */
public class kSayfasi
{

    public void start(Stage primaryStage)
    {
        primaryStage.setX(665);
        primaryStage.setY(186);
        primaryStage.setTitle("Kayıt");

        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(13);
        grid.setVgap(13);
        grid.setPadding(new Insets(0, 25, 25, 25));

        Label tcNo = new Label("TC No :");
        grid.add(tcNo, 0, 1);

        TextField tcNTextField = new TextField();
        tcNTextField.setPromptText("TC No");
        grid.add(tcNTextField, 1, 1);

        Label userName = new Label("İsim :");
        grid.add(userName, 0, 2);

        TextField uNTextField = new TextField();
        uNTextField.setPromptText("İsim");
        grid.add(uNTextField, 1, 2);

        Label lastName = new Label("Soyisim :");
        grid.add(lastName, 0, 3);

        TextField lNTextField = new TextField();
        lNTextField.setPromptText("Soyisim");
        grid.add(lNTextField, 1, 3);

        Label pw = new Label("Şifre :");
        grid.add(pw, 0, 4);

        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Şifre");
        grid.add(pwBox, 1, 4);

        Label tNo = new Label("Telefon Numarası :");
        grid.add(tNo, 0, 5);

        TextField tNTextField = new TextField();
        tNTextField.setPromptText("Telefon Numarası");
        grid.add(tNTextField, 1, 5);

        Label bLocation = new Label("Doğum Yeri :");
        grid.add(bLocation, 0, 6);

        TextField bLTextField = new TextField();
        bLTextField.setPromptText("Doğum Yeri");
        grid.add(bLTextField, 1, 6);

        Label bDate = new Label("Doğum Tarihi :");
        grid.add(bDate, 0, 7);

        DatePicker bDatePicker = new DatePicker();
        bDatePicker.setPromptText("Gün/Ay/Yıl");
        grid.add(bDatePicker, 1, 7);

        Label eMail = new Label("E-Mail :");
        grid.add(eMail, 0, 8);

        TextField eMTextField = new TextField();
        eMTextField.setPromptText("E-Mail");
        grid.add(eMTextField, 1, 8);

        Label sT = new Label("Sigarta Türü :");
        grid.add(sT, 0, 9);

        final ComboBox sTComboBox = new ComboBox();
        sTComboBox.getItems().addAll(
                "SSK",
                "BAĞKUR",
                "EMEKLİ SANDIĞI",
                "GSS"
        );
        grid.add(sTComboBox, 1, 9);

        Label sSNo = new Label("Sosyal Güvenlik Numarası :");
        grid.add(sSNo, 0, 10);

        TextField sSNTextField = new TextField();
        sSNTextField.setPromptText("Sosyal Güvenlik Numarası");
        grid.add(sSNTextField, 1, 10);

        Label gndr = new Label("Cinsiyet :");
        grid.add(gndr, 0, 11);

        final ToggleGroup radioGroup = new ToggleGroup();

        RadioButton male = new RadioButton("Erkek");
        male.setToggleGroup(radioGroup);
        male.setSelected(false);
        grid.add(male, 1, 11);

        RadioButton female = new RadioButton("Kadın");
        female.setToggleGroup(radioGroup);
        female.setSelected(false);
        grid.add(female, 2, 11);

        Button btn = new Button("Kayıt Ol");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 12);

        btn.setOnAction((ActionEvent e)
                -> 
                {
                    RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();

                    String result;

                    if (tcNTextField.getText().equals("") || uNTextField.getText().equals("") || lNTextField.getText().equals("") || pwBox.getText().equals("") || tNTextField.getText().equals("") || bLTextField.getText().equals("") || bDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).equals("") || eMTextField.getText().equals("") || sTComboBox.getSelectionModel().getSelectedItem().toString().equals("") || sSNTextField.getText().equals("") || selectedRadioButton.getText().equals(""))
                    {
                        result = "2";
                    }

                    else
                    {
                        result = DBIslemleri.addRegister(tcNTextField.getText(), uNTextField.getText(), lNTextField.getText(), pwBox.getText(), tNTextField.getText(), bLTextField.getText(), bDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), eMTextField.getText(), sTComboBox.getSelectionModel().getSelectedItem().toString(), sSNTextField.getText(), selectedRadioButton.getText());
                    }

                    switch (result)
                    {
                        case "1":
                        {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("INFORMATION");
                            alert.setHeaderText("Kayıt Onaylandı!");
                            alert.setContentText("Giriş Yapmak İçin 'Giriş Yap'`a Tıklayınız!");
                            alert.show();
                            
                            
                            Button btn2 = new Button("Giriş Yap!");
                            
                            HBox hbBtn2 = new HBox(10);
                            hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
                            hbBtn2.getChildren().add(btn2);
                            
                            grid.add(hbBtn2, 0, 12);

                            btn2.setOnAction((ActionEvent e2)
                                    -> 
                                    {
                                        HastaneRandevuSistemi01 hRS = new HastaneRandevuSistemi01();
                                        hRS.start(primaryStage);
                            });
                            
                            tcNTextField.setText("");
                            uNTextField.setText("");
                            lNTextField.setText("");
                            pwBox.setText("");
                            tNTextField.setText("");
                            bLTextField.setText("");
                            bDatePicker.setValue(null);
                            eMTextField.setText("");
                            sTComboBox.getSelectionModel().clearSelection();
                            sSNTextField.setText("");
                            male.setSelected(false);
                            female.setSelected(false);
                            
                            break;
                        }
                        case "2":
                        {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("EWARNING");
                            alert.setHeaderText("Information Alert");
                            alert.setContentText("Tüm Alanların Doldurulması Zorunludur!");
                            alert.show();
                            break;
                        }
                        default:
                        {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("Information Alert");
                            alert.setContentText(result);
                            alert.show();
                            break;
                        }
                    }
        });


        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
