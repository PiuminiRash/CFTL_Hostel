package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Session;
import jakarta.mail.Authenticator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.Custom.ExpenditureBO;
import lk.ijse.DB.DbConnection;
import lk.ijse.dto.SalaryModel;
import lk.ijse.dto.StaffModel;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.SalaryTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Properties;

public class SalaryController {
    @FXML
    private JFXComboBox<String> cmbType;

    @FXML
    private JFXTextField txtBonus;

    @FXML
    private JFXTextField txtE;

    @FXML
    private JFXComboBox<String> cmbStaffId;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private Label lblStaffName;

    @FXML
    private Label lblEmail;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private Label lblFinalSalary;

    @FXML
    private ComboBox<Month> cmbMonth;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private TableColumn<?,?> colStaffId;

    @FXML
    private TableColumn<?,?> colStaffName;

    @FXML
    private TableColumn<?,?> colSalary;

    @FXML
    private TableColumn<?,?> colDiscount;

    @FXML
    private TableColumn<?,?> colVat;

    private String month;

    @FXML
    private TableColumn<?,?> colFinalSalary;

    StaffModel staffModel = new StaffModel();

    ExpenditureBO expenditureBO = (ExpenditureBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.EXPENDITURE);

    private ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        cmbType.getItems().addAll("Employee", "Teacher");

        setMonth();
        month = String.valueOf(cmbMonth.getValue());

        setCellValueFactory();
    }

    public void setMonth() {
        ObservableList<Month> months = FXCollections.observableArrayList(Month.values());
        cmbMonth.setItems(months);

        cmbMonth.setPromptText("Select a month");
    }

    private void setCellValueFactory() {
        colStaffId.setCellValueFactory(new PropertyValueFactory<>("StaffId"));
        colStaffName.setCellValueFactory(new PropertyValueFactory<>("StaffName"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("SalaryAmt"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("Bonus"));
        colVat.setCellValueFactory(new PropertyValueFactory<>("E"));
        colFinalSalary.setCellValueFactory(new PropertyValueFactory<>("FinalSalary"));
    }

    public void cmbMonthOnAction(ActionEvent actionEvent) throws SQLException {
        String selectedType = cmbType.getValue();
        obList.clear();
        if ("Teacher".equals(selectedType)) {
            Month selectMonth = cmbMonth.getValue();
            if (selectMonth != null) {
                month = selectMonth.name();
            }
            List<SalaryTm> dtoList = SalaryModel.searchSalaryTeacher(month);

            for (SalaryTm dto : dtoList) {
                obList.add(
                        new SalaryTm(
                                dto.getStaffId(),
                                dto.getStaffName(),
                                dto.getSalaryAmt(),
                                dto.getBonus(),
                                dto.getE(),
                                dto.getFinalSalary()
                        )
                );
            }
            tblSalary.setItems(obList);
        } else if ("Employee".equals(selectedType)) {
            Month selectMonth = cmbMonth.getValue();
            if (selectMonth!=null){
                month = selectMonth.name();
            }
            List<SalaryTm> dtoList = SalaryModel.searchSalaryEmployee(month);

            for (SalaryTm dto : dtoList) {
                obList.add(
                        new SalaryTm(
                                dto.getStaffId(),
                                dto.getStaffName(),
                                dto.getSalaryAmt(),
                                dto.getBonus(),
                                dto.getE(),
                                dto.getFinalSalary()
                        )
                );
            }
            tblSalary.setItems(obList);
        }
        /*String selectedType = cmbType.getValue();

        if ("Teacher".equals(selectedType) || "Employee".equals(selectedType)) {
            Month selectMonth = cmbMonth.getValue();

            if (selectMonth != null) {
                month = selectMonth.name();
            }

            List<SalaryTm> dtoList = SalaryModel.searchSalary(selectedType, month);

            obList.clear();

            for (SalaryTm dto : dtoList) {
                obList.add(new SalaryTm(
                        dto.getStaffId(),
                        dto.getStaffName(),
                        dto.getSalaryAmt(),
                        dto.getBonus(),
                        dto.getE(),
                        dto.getFinalSalary()
                ));
            }
        }
            tblSalary.setItems(obList);*/
    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        String type = cmbType.getValue();
        String month = String.valueOf(cmbMonth.getValue());
        String Id = cmbStaffId.getValue();
        String Name = lblStaffName.getText();
        double salaryAmt = Double.parseDouble(txtSalary.getText());
        double bonus = Double.parseDouble(txtBonus.getText());
        double et = Double.parseDouble(txtE.getText());
        calFinalSalary();
        double finalSalary = Double.parseDouble(lblFinalSalary.getText());
        //double finalSalary = (salaryAmt + bonus) - et;

        try {
            var dto = new SalaryDto(type,month,Id,Name,salaryAmt,bonus,et,finalSalary);
            boolean isSaved = SalaryModel.saveSalary(dto);

            if (isSaved) {
                sendEmail();
                new Alert(Alert.AlertType.CONFIRMATION,"Salary Pay Sucssus!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR , e.getMessage()).show();
        }
        GoExpenditure();
    }

    private void sendEmail() {
        System.out.println("Start");
        //lblStatus.setText("sending...");
        Mail mail = new Mail();
        mail.setMsg("Your month salary payment successful "+lblFinalSalary.getText());
        mail.setTo(lblEmail.getText());
        mail.setSubject("Collage Of Fast Track Learning");

        Thread thread = new Thread(mail);
        thread.start();
        new Alert(Alert.AlertType.CONFIRMATION,"Email sent");
        System.out.println("end");
        //lblStatus.setText("sended");

    }
    public static class Mail implements Runnable{
        private String msg;
        private String to;
        private String subject;
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public void setTo(String to) {
            this.to = to;
        }
        public void setSubject(String subject) {
            this.subject = subject;
        }

        public boolean outMail() throws MessagingException {
            String from = "piuminirashmika@gmail.com";
            String host = "localhost";

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);
            Session session = Session.getInstance(properties, new Authenticator() {


                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("piuminirashmika@gmail.com", "mdcz fyoi itrh qvbd");
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(this.subject);
            mimeMessage.setText(this.msg);
            Transport.send(mimeMessage);
            return true;
        }
        public void run() {
            if (msg != null) {
                try {
                    outMail();
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("not sent. empty msg!");
            }
        }
    }


    private void calFinalSalary() {
        try {
            double salary = Double.parseDouble(txtSalary.getText());
            double bonus = Double.parseDouble(txtBonus.getText());
            double et = Double.parseDouble(txtE.getText());
            double finalSalary = (salary + bonus) - et;

            lblFinalSalary.setText(String.valueOf(finalSalary));
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid format in Salary");
            e.printStackTrace();
        }
    }

    public void cmbTypeOnAction(ActionEvent actionEvent) throws SQLException {
        String selectedType = cmbType.getValue();
        obList.clear();
        if ("Teacher".equals(selectedType)) {
            loadTeacherId();
        } else if ("Employee".equals(selectedType)) {
            loadEmployeesId();
        }
    }

    private void loadTeacherId() {
//        ObservableList<String> obList = FXCollections.observableArrayList();
//        try {
//            List<TeacherDto> teacherDtos = TeacherModel.getAllTeacher();
//
//            for (TeacherDto dto : teacherDtos) {
//                obList.add(dto.getTeacherId());
//            }
//            cmbStaffId.setItems(obList);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void loadEmployeesId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<StaffDto> employeeDtos = staffModel.getAllStaff();

            for (StaffDto dto : employeeDtos) {
                obList.add(dto.getStaffName());
            }
            cmbStaffId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbStaffIdOnAction(ActionEvent actionEvent) {
//        String selectedType = cmbType.getValue();
//        if ("Teacher".equals(selectedType)) {
//            String id = cmbStaffId.getValue();
//
//            try {
//                TeacherDto dto = TeacherModel.searchTeacher(id);
//                lblStaffName.setText(dto.getTeacherName());
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        } else if ("Employee".equals(selectedType)) {
//            String id = cmbStaffId.getValue();
//
//            try {
//                StaffDto dto = staffModel.searchStaff(id);
//                lblStaffName.setText(dto.getStaffName());
//                lblEmail.setText(dto.getEmail());
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    public void GoExpenditure() {
        LocalDate currentDate = LocalDate.now();
        String desc = "Salary Payment";
        double amount = Double.parseDouble(lblFinalSalary.getText());
        int year = currentDate.getYear();
        String month = String.valueOf(cmbMonth.getValue());
        String localDate = currentDate.toString();

        var expenditureDto = new ExpenditureDto(desc,amount,year,month,localDate);
        try {
            boolean isSuccess = expenditureBO.saveExpenditure(expenditureDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Expenditure Payment Save Success!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnPrintOnAction(ActionEvent actionEvent) throws JRException, SQLException {
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
}
