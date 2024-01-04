package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.ExpenditureDto;
import lk.ijse.dto.IncomeDto;
import lk.ijse.dto.PaymentDto;
import lk.ijse.dto.StudentDto;
import lk.ijse.dto.tm.PaymentTm;
import lk.ijse.model.ExpenditureModel;
import lk.ijse.model.IncomeModel;
import lk.ijse.model.PaymentModel;
import lk.ijse.model.StudentModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class StudentPaymentController {
  /*  @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private DatePicker dateDate;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXComboBox<Month> cmbMonth;

    @FXML
    private TableView<PaymentTm> payment;

    @FXML
    private TableColumn<?,?> colDate;

    @FXML
    private TableColumn<?,?> colStudentId;

    @FXML
    private TableColumn<?,?> colStudentName;

    @FXML
    private JFXTextField txtStudentId;

    @FXML
    private Label lblStudentName;

    @FXML
    private JFXButton btnPaymentMark;


    @FXML
    private Label lblDate;

    @FXML
    private JFXButton btnSavePayment;

    @FXML
    private TableColumn<?,?> colPayAmount;

    @FXML
    private TableColumn<?,?> colAction;

    @FXML
    private JFXComboBox<String> cmbStudentId;

    private String month;

    StudentModel studentModel = new StudentModel();

    private ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

    public void initialize() {
//        dateDate.setPromptText(String.valueOf(LocalDate.now()));
//
//        setMonth();
//
//        System.out.println("Selected Month: " + cmbMonth.getValue());
//
//        if (cmbMonth.getValue() != null) {
//            month = cmbMonth.getValue().toString(); // Use toString() to get the month name
//        }
//
//
//        loadStudent();
//        setCellValueFactory();
//        loadAllPayment();
    }

    public void setMonth() {
        ObservableList<Month> months = FXCollections.observableArrayList(Month.values());
        cmbMonth.setItems(months);

        cmbMonth.setPromptText("Select a month");
    }

    private void loadAllPayment() {
        try {
            obList.clear();

            Month selectedMonth = cmbMonth.getValue();

            if (selectedMonth != null) {
                month = selectedMonth.name();
                List<PaymentTm> dtoList = PaymentModel.searchPayment(month);

                if (dtoList != null) {
                    for (PaymentTm dto : dtoList) {
                        obList.add(new PaymentTm(dto.getDate(), dto.getStudentId(), dto.getStudentName(), dto.getAmt()));
                    }

                    payment.setItems(obList);
                } else {
                    System.out.println("Payment list is null");
                }
            } else {
                System.out.println("Selected month is null");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void btnPaymentMarkOnAction(ActionEvent actionEvent) {
       Month month = cmbMonth.getValue();
       String date = dateDate.getPromptText();
       String studentId = cmbStudentId.getValue();
       String studentName = lblStudentName.getText();
       double amt = Double.parseDouble(txtAmount.getText());

       try {
           var dto = new PaymentDto(month,date,studentId,studentName,amt);
           boolean isSaved = PaymentModel.savePayment(dto);
           if (isSaved) {
               new Alert(Alert.AlertType.CONFIRMATION,"Payment Sucssusful!!!").show();
           }
       } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
       }
       GoIncome();
    }

    public void cmbStudentIdOnAction(ActionEvent actionEvent) {
        String id = cmbStudentId.getValue();

        try {
            StudentDto studentDto = studentModel.searchStudent(id);
            lblStudentName.setText(studentDto.getStudentName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbMonthOnAction(ActionEvent actionEvent) {
        try {
            Month selectedMonth = cmbMonth.getValue();
            if (selectedMonth != null) {
                month = selectedMonth.name();
            }
            List<PaymentTm> dtoList = PaymentModel.searchPayment(month);

            for (PaymentTm dto : dtoList) {
                obList.add(
                        new PaymentTm(
                                dto.getDate(),
                                dto.getStudentId(),
                                dto.getStudentName(),
                                dto.getAmt()
                        )
                );
            }

            payment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStudent() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<StudentDto> studentDtos = studentModel.getAllStudent();

            for (StudentDto dto : studentDtos) {
                obList.add(dto.getStudentId());
            }
            cmbStudentId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void GoIncome() {
        LocalDate currentDate = LocalDate.now();
        String desc = "Student Payment";
        double amount = Double.parseDouble(txtAmount.getText());
        int year = currentDate.getYear();
        String month = String.valueOf(cmbMonth.getValue());
        String localDate = currentDate.toString();

        var incomeDto = new IncomeDto(desc,amount,year,month,localDate);
        try {
            boolean isSuccess = IncomeModel.addIncome(incomeDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Income Payment Save Success!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
    @FXML
    private AnchorPane root;

    @FXML
    private ListView<?> listPaid;

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXComboBox<String> cmbStudentId;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtPayment;

    @FXML
    private JFXComboBox<Month> cmbMonth;

    StudentModel studentModel = new StudentModel();

    private ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        txtDate.setPromptText(String.valueOf(LocalDate.now()));
        setMonth();
        loadStudent();
    }

    public void setMonth() {
        ObservableList<Month> months = FXCollections.observableArrayList(Month.values());
        cmbMonth.setItems(months);

        cmbMonth.setPromptText("Select a month");
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {

    }

    @FXML
    void cmbStudentOnAction(ActionEvent event) {
        String id = cmbStudentId.getValue();

        try {
            StudentDto studentDto = studentModel.searchStudent(id);
            txtStudentName.setText(studentDto.getStudentName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStudent() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<StudentDto> studentDtos = studentModel.getAllStudent();

            for (StudentDto dto : studentDtos) {
                obList.add(dto.getStudentId());
            }
            cmbStudentId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
