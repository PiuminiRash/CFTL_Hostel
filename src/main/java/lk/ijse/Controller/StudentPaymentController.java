package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.DB.DbConnection;
import lk.ijse.dto.PaymentDto;
import lk.ijse.dto.StudentDto;
import lk.ijse.model.PaymentModel;
import lk.ijse.model.StudentModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class StudentPaymentController {
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
        String month = String.valueOf(cmbMonth.getValue());
        String date = txtDate.getPromptText();
        String studentId = cmbStudentId.getValue();
        double amt = Double.parseDouble(txtPayment.getText());

        try {
            var dto = new PaymentDto(month,date,studentId,amt);
            boolean isSaved = PaymentModel.savePayment(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Sucssusful!!!").show();
                printBill();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        //GoIncome();
    }

    private void printBill() throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/Blank_A4_3.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DbConnection.getInstance().getConnection()
        );

        JasperViewer.viewReport(jasperPrint, false);
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
