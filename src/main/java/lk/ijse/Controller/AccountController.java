package lk.ijse.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.UserDto;
import lk.ijse.dto.tm.UserTm;
import lk.ijse.model.StudentModel;
import lk.ijse.model.UserModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class AccountController {
    @FXML
    private AnchorPane root;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private TableView<UserTm> tblUsers;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colAction;

    private StudentModel studentModel = new StudentModel();

    private ObservableList<UserTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        lblUserName.setText("Piumini Rashmika");
        lblPassword.setText("*********");
        setCellValueFactory();
        loadAllUser();
        tableListener();
    }

    private void tableListener() {
        tblUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(UserTm row) {
        txtUserName.setText(row.getUserName());
        txtPassword.setText(row.getPassword());
        txtEmail.setText(row.getEmail());
    }

    private void setCellValueFactory() {
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadAllUser(){
        var model = new UserModel();

        ObservableList<UserTm> obList = FXCollections.observableArrayList();

        try {
            List<UserDto> dtoList = UserModel.getAllUser();

            for (UserDto dto : dtoList) {
                Button deleteBtn = new Button("Delete");

                setDeleteButtonOnAction(deleteBtn,dto.getPassword());
                obList.add(
                        new UserTm(
                                dto.getUserName(),
                                dto.getPassword(),
                                dto.getEmail(),
                                deleteBtn
                        )
                );
            }

            tblUsers.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDeleteButtonOnAction(Button deleteBtn , String user) {
        deleteBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove student ?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                obList.removeIf(userTm -> userTm.getUserName().equals(user));
                tblUsers.refresh();

                DeleteStudent(user);
                loadAllUser();
            }
        });
    }

    private void DeleteStudent(String studentId){
        try {
            boolean isDeleted = studentModel.deleteStudent(studentId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "User deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "User not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblUsers.refresh();
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String name = txtUserName.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();

        try{

            var dto = new UserDto(name,password,email);
            boolean isUpdate = UserModel.updateUser(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"updated user Details").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void clearFields() {
    }
}
