package hastanerandevusistemi01;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Arclus
 */

public class HastaneRandevuSistemi01 extends Application
{
    static String tcNo;
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setX(707);
        primaryStage.setY(223);
        primaryStage.setTitle("Hastane Randevu Sistemi");
        
        GridPane grid = new GridPane();
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("TC No ile Giriş Yapınız.");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("TC No :");
        grid.add(userName, 0, 1);

        TextField tcNTextField = new TextField();
        tcNTextField.setPromptText("TC No");
        grid.add(tcNTextField, 1, 1);

        Label pw = new Label("Şifre :");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Şifre");
        grid.add(pwBox, 1, 2);
        

        Button btn = new Button("Giriş Yap");    
        
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        
        Button btn2 = new Button("Kayıt Ol");
        
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 0, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction((ActionEvent e) ->
        {
            
            tcNo = tcNTextField.getText();
                
            String result = DBIslemleri.loginCheck(tcNo, pwBox.getText());
            
            if (tcNo.equals("") || pwBox.getText().equals(""))
            {
                result = "2";
            }
            
            switch (result)
            {
                case "1":
                {
                    rSayfasi rS = new rSayfasi();
                    rS.start(primaryStage);
                    break;
                }
                
                case "2":
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Information Alert Example");
                    String s = "Tüm Alanların" +
                            "Doldurulması" + " \n \n" +
                            "Zorunludur!";
                    alert.setContentText(s);
                    alert.show();
                    break;
                }
                    
                case "0":
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Information Alert Example");
                    String s = "TC No" +
                            "veya " + " \n \n" +
                            "Şifre Yanlış!";
                    alert.setContentText(s);
                    alert.show();
                    break;
                }
                default:
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Alert Information");
                    alert.setContentText(result);
                    alert.show();
                    break;
                } 
            }      
        });
        
        btn2.setOnAction((ActionEvent e) -> 
        {
            kSayfasi kS = new kSayfasi();
            kS.start(primaryStage);
        });
        
        Button btn3 = new Button("Randevu Sorgula");
        grid.add(btn3, 1, 7);
        
        btn3.setOnAction((ActionEvent e) -> 
        {
            rSorgula rSG = new rSorgula();
            rSG.start(primaryStage);
        });

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args)
    {
        launch(args);
    }
}