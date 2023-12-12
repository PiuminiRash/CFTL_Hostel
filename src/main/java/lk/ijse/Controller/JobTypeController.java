package lk.ijse.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.JobTypeDto;
import lk.ijse.dto.tm.EmployeeTm;
import lk.ijse.dto.tm.JobTypeTm;
import lk.ijse.model.EmployeeModel;
import lk.ijse.model.JobTypeModel;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class JobTypeController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXTextField txtJobCode;

    @FXML
    private JFXTextField txtJobType;

    @FXML
    private TableColumn<? ,?> colJobCode;

    @FXML
    private TableColumn<? ,?> colJobType;

    @FXML
    private TableColumn<?,?> colDesc;

    @FXML
    private TableColumn<?,?> colAction;

    @FXML
    private TableView<JobTypeTm> tblJobTypeDetail;

    private JobTypeModel jobTypeModel = new JobTypeModel();

    private ObservableList<JobTypeTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setCellValueFactory();
        loadAllJobTypeDetail();
        tableListener();
        generateNextJobCode();
    }

    private void generateNextJobCode() {
        try {
            String orderId = JobTypeModel.generateNextJobCode();
            txtJobCode.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colJobCode.setCellValueFactory(new PropertyValueFactory<>("JobCode"));
        colJobType.setCellValueFactory(new PropertyValueFactory<>("JobType"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("DayPayment"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("Remove"));
    }

    private void tableListener() {
        tblJobTypeDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {

            setData(newValue);
        });
    }

    private void setData(JobTypeTm row) {
        txtJobCode.setText(row.getJobCode());
        txtJobType.setText(row.getJobType());
        txtDesc.setText(row.getDayPayment());
    }

    private void loadAllJobTypeDetail() {
        var model = new JobTypeModel();

        try {
            List<JobTypeDto> dtoList = model.getAllJobType();

            for (JobTypeDto dto : dtoList) {
                Button deleteBtn = new Button("Delete");

                setDeleteButtonOnAction(deleteBtn,dto.getJobType());
                obList.add(
                        new JobTypeTm(
                                dto.getJobCode(),
                                dto.getJobType(),
                                dto.getDescription(),
                                deleteBtn
                        )
                );
            }

            tblJobTypeDetail.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDeleteButtonOnAction(Button deleteBtn , String jobCode) {
        deleteBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove jobType ?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                obList.removeIf(jobTypeTm -> jobTypeTm.getJobCode().equals(jobCode));
                tblJobTypeDetail.refresh();

                DeleteEmployee(jobCode);
            }
        });
    }

    private void DeleteEmployee(String employeeId){
        try {
            boolean isDeleted = JobTypeModel.deleteJobType(employeeId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "JobType deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "JobType not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblJobTypeDetail.refresh();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String code = txtJobCode.getText();
        String type = txtJobType.getText();
        String payment = txtDesc.getText();

        try {
            if (!validateJob()){
                return;
            }
            var dto = new JobTypeDto(code,type,payment);
            boolean isUpdated = jobTypeModel.updateJobType(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Job Type Details Updated!").show();
                clearFields();
                tblJobTypeDetail.refresh();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean validateJob() {
        boolean isValidate = true;

//        boolean code = Pattern.matches("[A-Za-z]{3,}", txtJobCode.getText());
//        if (!code){
//            showErrorNotification("Invalid job code", "The Job code you entered is invalid");
//            isValidate = false;
//        }
        boolean type = Pattern.matches("[A-Za-z]{5,}",txtJobType.getText());
        if(!type){
            showErrorNotification("invalid type","The type you entered is invalid");
            isValidate = false;
        }
        boolean payment = Pattern.matches("[A-Za-z]{5,}",txtDesc.getText());
        if (!payment){
            showErrorNotification("Invalid Description", "The Description you entered is invalid");
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
    public void btnSaveOnAction(ActionEvent actionEvent) {
        String code = txtJobCode.getText();
        String type = txtJobType.getText();
        String payment = txtDesc.getText();



        try {
            if (!validateJob()){
                return;
            }
            var dto = new JobTypeDto(code,type,payment);
            boolean isSaved = jobTypeModel.saveJobType(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Job Type Saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String type = txtJobType.getText();

        try {
            boolean isDeleted = jobTypeModel.deleteJobType(type);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Job Type deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Job Type not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtJobCode.setText("");
        txtJobType.setText("");
        txtDesc.setText("");
    }
}
