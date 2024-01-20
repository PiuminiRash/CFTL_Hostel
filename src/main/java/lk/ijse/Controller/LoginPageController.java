package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.UserDto;
import lk.ijse.dto.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class LoginPageController {
    @FXML
    private AnchorPane root;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserEmail;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPassword;

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

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        String username = txtUserName.getText();
        String email = txtUserEmail.getText();
        String password = txtPassword.getText();

        List<UserDto> userDtoList = UserModel.loginUser();
        for (UserDto userDto:userDtoList) {
            if (userDto.getUserName().equals(username)) {
                if (userDto.getEmail().equals(email)) {
                    if (userDto.getPassword().equals(password)) {
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashbord_form.fxml"));
                        Scene scene = new Scene(anchorPane);
                        Stage stage = (Stage) root.getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("DashBord");
                        stage.centerOnScreen();
                    } else {
                        lblPassword.setText("Invalid Password");
                    }

                }else {
                    lblEmail.setText("Invalid Email");
                }
            }else {
                lblUserName.setText("Invalid Username");
            }
        }

    }

    public void SingInOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/register_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.centerOnScreen();
    }
}
