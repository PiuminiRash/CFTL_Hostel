package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class EmployeePageController {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton ViewEmployee;

    @FXML
    private JFXButton JobType;

    @FXML
    private JFXButton ManageEmployee;

    @FXML
    private AnchorPane rootNode;

    public void initialize() throws IOException {
       btnManageEmployeeOnAction(null);
    }
    void setForms(String forms) throws IOException {
        String [] formArrays = {"/view/employeemanage_form.fxml","/view/viewemployee_form.fxml", "/view/jobtype_form.fxml"
        };

        JFXButton[] btnArray = {ManageEmployee,ViewEmployee,JobType};

        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(forms)));
        rootNode.getChildren().clear();
        rootNode.getChildren().add(load);
        for (int i = 0; i < formArrays.length; i++) {
            btnArray[i].setStyle("-fx-background-color:  #fffdfd; -fx-font-size: 12");

            if (forms.equals(formArrays[i])){
                btnArray[i].setStyle("-fx-background-color: #9ea266; -fx-text-fill: #000000");
            }
        }
    }

    public void btnManageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/employeemanage_form.fxml");
    }

    public void btnViewEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/viewemployee_form.fxml");
    }

    public void btnJobTypeOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/jobtype_form.fxml");
    }
}
