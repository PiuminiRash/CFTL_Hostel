package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class DashBordController {
    @FXML
    private Label lblUseraName;

    @FXML
    private JFXButton exam;

    @FXML
    private JFXButton staff;

    @FXML
    private JFXButton student;

    @FXML
    private JFXButton subject;

    @FXML
    private JFXButton income;

    @FXML
    private JFXButton room;

    @FXML
    private JFXButton home;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton account;

    public void initialize() throws IOException {
        btnHomeOnAction(null);
    }

    void setForms(String forms) throws IOException {
        String [] formArrays = {"/view/home_form.fxml","/view/viewstudents_form.fxml","/view/staff_form.fxml","/view/room_form.fxml","/view/section_form.fxml","/view/income_form.fxml"
        };

        JFXButton[] btnArray = {home,student,staff,room,subject,income};

        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(forms)));
        root.getChildren().clear();
        root.getChildren().add(load);
        for (int i = 0; i < formArrays.length; i++) {
            btnArray[i].setStyle("-fx-background-color:  #ffffff; -fx-font-size: 12");

            if (forms.equals(formArrays[i])){
                btnArray[i].setStyle("-fx-background-color: #b3c1a3; -fx-text-fill: #000000");
            }
        }
    }

    public void btnAccountOnAction(ActionEvent actionEvent) throws IOException {
        //setForms("/view/account_form.fxml");
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/home_form.fxml");
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/viewstudents_form.fxml");
    }

    public void btnStaffOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/staff_form.fxml");
    }

    public void btnRoomOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/room_form.fxml");
    }

    public void btnSubjectOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/section_form.fxml");
    }

    public void btnIncomeOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/income_form.fxml");
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginpage_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.centerOnScreen();
    }

//    public void btnExamOnAction(ActionEvent actionEvent) {
//    }
}

