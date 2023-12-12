package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class StudentPageController {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXButton ManageStudent;

    @FXML
    private JFXButton ViewStudent;

    @FXML
    private JFXButton Payments;

    public void initialize() throws IOException {
        btnManageStudentOnAction(null);
    }
    void setForms(String forms) throws IOException {
        String [] formArrays = {"/view/managestudent_form.fxml","/view/viewstudent_form.fxml","/view/studentpayments_form.fxml"
        };

        JFXButton[] btnArray = {ManageStudent,ViewStudent,Payments};

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
    public void btnManageStudentOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/managestudent_form.fxml");
    }

    public void btnViweStudentOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/viewstudent_form.fxml");
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        setForms("/view/studentpayments_form.fxml");
    }
}
