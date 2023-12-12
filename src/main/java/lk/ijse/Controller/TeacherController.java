package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class TeacherController {
    public AnchorPane root;
    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXButton ManageTeacher;

    @FXML
    private JFXButton ViewTeacher;

    @FXML
    private JFXButton TeacherSalary;

    @FXML
    private JFXButton TeacherAttendance;


    public void initialize() throws IOException {
        btnManageTeacherOnAction(null);
    }
    void setForms(String forms) throws IOException {
        String [] formArrays = {"/view/manageteacher_form.fxml", "/view/viewteacher_form.fxml","/view/attendanceteacher_form.fxml",
        };

        JFXButton[] btnArray = {ManageTeacher,ViewTeacher,TeacherAttendance};

        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(forms)));
        rootNode.getChildren().clear();
        rootNode.getChildren().add(load);
        for (int i = 0; i < formArrays.length; i++) {
            btnArray[i].setStyle("-fx-background-color:  #ffffff; -fx-font-size: 12");

            if (forms.equals(formArrays[i])){
                btnArray[i].setStyle("-fx-background-color:  #9ea266; -fx-text-fill: #000000");
            }
        }
    }

    public void btnManageTeacherOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/manageteacher_form.fxml");
    }

    public void btnViewTeacherOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/viewteacher_form.fxml");
    }

    public void btnTeacherAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/attendanceteacher_form.fxml");
    }
}
