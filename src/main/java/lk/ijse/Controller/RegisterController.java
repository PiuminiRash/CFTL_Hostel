package lk.ijse.Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.UserDto;
import lk.ijse.dto.UserModel;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class RegisterController {
    @FXML
    private AnchorPane root;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtemail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    public void initialize(){
        setDate();

        setTime();
    }

    private void setTime(){
        lblTime.setText(String.valueOf(LocalTime.now()));
    }

    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    void LogInOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginpage_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.centerOnScreen();
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String name = txtUserName.getText();
        String password = txtPassword.getText();
        String email = txtemail.getText();
        String Cpassword = txtConfirmPassword.getText();

        var dto = new UserDto(name, password, email);

        try {
            if (!validateReg()){
                return;
            }
            if (Cpassword.equals(password)) {
                boolean isSaved = UserModel.saveUser(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "User saved!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save user. Please try again.").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }

    private boolean validateReg() {
        boolean isValidate = true;
        boolean username = Pattern.matches("[A-Za-z]{5,}", txtUserName.getText());
        if (!username){
            showErrorNotification("Invalid username", "The username you entered is invalid");
            isValidate = false;
        }
//        boolean password = Pattern.matches("^(.+)@(.+)$",txtPassword.getText());
//        if(!password){
//            showErrorNotification("invalid password","Enter the strong password Ex: @CollageOfFastTrack2013");
//            isValidate = false;
//        }
        boolean email = Pattern.matches("^(.+)@(.+)$",txtemail.getText());
        if (!email){
            showErrorNotification("Invalid email", "The email you entered is invalid");
            isValidate = false;
        }
        return isValidate;
    }

    private void showErrorNotification(String title, String text) {
        Notifications.create()
                .title(title)
                .text(text)
                .showError();

    }



    private void clearFields() {
        txtUserName.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
        txtemail.setText("");
    }
}
