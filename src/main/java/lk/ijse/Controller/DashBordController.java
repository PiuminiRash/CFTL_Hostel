package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class DashBordController {
    @FXML
    private JFXButton employee;

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
    private JFXButton teacher;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXButton account;

    public void initialize() throws IOException {
        btnHomeOnAction(null);
    }
    void setForms(String forms) throws IOException {
        String [] formArrays = {"/view/account_form.fxml","/view/home_form.fxml","/view/employee_form.fxml",
                "/view/teacher_form.fxml","/view/student_form.fxml","/view/room_form.fxml", "/view/income_form.fxml","/view/subject_form.fxml"
        };

        JFXButton[] btnArray = {account,home,employee,teacher,student,room,income,subject};

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

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/employeepage_form.fxml");
    }

    public void btnTeacherOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/teacher_form.fxml");
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/student_form.fxml");
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
       setForms("/view/home_form.fxml");
    }

    public void btnRoomOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/room_form.fxml");
    }

    public void btnIncomeOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/income_form.fxml");
    }

    public void btnSubjectOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/subject_form.fxml");
    }

    public void btnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/account_form.fxml");
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.centerOnScreen();
    }
}

