package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class SubjectController {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXButton ViewSubject;

    @FXML
    private JFXButton TimeTable;

    @FXML
    private JFXButton ManageSubject;

    @FXML
    private JFXButton SubjectStudent;

    public void initialize() throws IOException {
        btnManageSubjectOnAction(null);
    }
    void setForms(String forms) throws IOException {
        String [] formArrays = {"/view/managesubject_form.fxml","/view/viewsubject_form.fxml","/view/subjecttimetable_form.fxml","/view/subjectstudent_form.fxml"
        };

        JFXButton[] btnArray = {ManageSubject,ViewSubject,TimeTable,SubjectStudent};

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

    public void btnManageSubjectOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/managesubject_form.fxml");
    }

    public void btnViewSubjectOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/viewsubject_form.fxml");
    }

    public void btnSubjectStudentOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/subjectstudent_form.fxml");
    }

    public void btnTimeTableOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/subjecttimetable_form.fxml");
    }
}
