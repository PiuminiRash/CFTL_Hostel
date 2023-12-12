package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.ExpenditureDto;
import lk.ijse.dto.IncomeDto;
import lk.ijse.dto.tm.ExpenditureTm;
import lk.ijse.dto.tm.IncomeTm;
import lk.ijse.model.ExpenditureModel;
import lk.ijse.model.IncomeModel;
import net.sf.jasperreports.engine.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;


public class IncomeController {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<Month> cmbMonth;

    @FXML
    private JFXTextField txtYear;

    @FXML
    private DatePicker dateDate;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXButton btnExpenditure;

    @FXML
    private JFXButton btnIncome;

    @FXML
    private TableView<IncomeTm> tblIncome;

    @FXML
    private TableColumn<?, ?> colInDate;

    @FXML
    private TableColumn<?, ?> colInDesc;

    @FXML
    private TableColumn<?, ?> colInAmount;

    @FXML
    private TableView<ExpenditureTm> tblExpenditure;

    @FXML
    private TableColumn<?, ?> colExDate;

    @FXML
    private TableColumn<?, ?> colExDesc;

    @FXML
    private TableColumn<?, ?> colExAmount;

    @FXML
    private Label lblIncomeTotal;

    @FXML
    private Label lblExpenditureTotal;

    @FXML
    private JFXButton btnBack;

    @FXML
    private Label lblFinalIncome;

    @FXML
    private JFXButton btnSaveIn;

    @FXML
    private JFXButton btnnSaveEx;

    private int year;

    private String month;

    private IncomeModel incomeModel = new IncomeModel();

    private ExpenditureModel expenditureModel = new ExpenditureModel();

    private ObservableList<ExpenditureTm> ExobList = FXCollections.observableArrayList();

    private ObservableList<IncomeTm> InobList = FXCollections.observableArrayList();

    public void initialize() {
        dateDate.setPromptText(String.valueOf(LocalDate.now()));
        setMonth();
        setYear();
        setCellValueFactoryIncome();
        setCellValueFactoryExpenditure();

        month = String.valueOf(cmbMonth.getValue());
        year = Integer.parseInt(txtYear.getText());

        YearMonth currentYearMonth = YearMonth.now();
        int year = currentYearMonth.getYear();
        String monthName = currentYearMonth.getMonth().name();
    }

    private void setCellValueFactoryIncome() {
        colInDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colInDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colInAmount.setCellValueFactory(new PropertyValueFactory<>("amount1"));

    }

    private void setCellValueFactoryExpenditure() {
        colExDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colExDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colExAmount.setCellValueFactory(new PropertyValueFactory<>("amount2"));
    }

    public void setMonth() {
        ObservableList<Month> months = FXCollections.observableArrayList(Month.values());
        cmbMonth.setItems(months);

        cmbMonth.setPromptText("Select a month");
    }

    public void setYear(){
        int currentYear = Year.now().getValue();
        txtYear.setText(String.valueOf(currentYear));
    }

    public void btnExpenditureOnAction(ActionEvent actionEvent) {
        String desc = txtDesc.getText();
        double amount = Double.parseDouble(txtAmount.getText());

        String date = String.valueOf(dateDate.getValue());

        var expenditureTm = new ExpenditureTm(date,desc,amount);

        ExobList.add(expenditureTm);

        tblExpenditure.setItems(ExobList);

        calIExpenditure();
    }

    public void btnIncomeOnAction() {
        String date = String.valueOf(dateDate.getValue());
        String desc = txtDesc.getText();
        double amount = Double.parseDouble(txtAmount.getText());

        var incomeTm = new IncomeTm(date,desc,amount);

        InobList.add(incomeTm);

        tblIncome.setItems(InobList);

        calIncome();
    }

    public void calIncome(){
        double total = 0;
        for (int i = 0; i < tblIncome.getItems().size(); i++) {
            total += (double) colInAmount.getCellData(i);
        }
        lblIncomeTotal.setText(String.valueOf(total));

        calNetIncom();
    }

    public void calIExpenditure(){
        double total = 0;
        for (int i = 0; i < tblExpenditure.getItems().size(); i++) {
            total += (double) colExAmount.getCellData(i);
        }
        lblExpenditureTotal.setText(String.valueOf(total));

        calNetIncom();
    }

    public void calNetIncom(){
        try {
            double incomeTotal = Double.parseDouble(lblIncomeTotal.getText());
            double expenditureTotal = Double.parseDouble(lblExpenditureTotal.getText());
            double netIncome = incomeTotal - expenditureTotal;
            lblFinalIncome.setText(String.valueOf(netIncome));
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid format in lblIncomeTotal or lblExpenditureTotal");
            e.printStackTrace();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveInOnAction(ActionEvent actionEvent) {
        String desc = txtDesc.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        int year = Integer.parseInt(txtYear.getText());
        String month = String.valueOf(cmbMonth.getValue());
        String date = dateDate.getPromptText();

        List<IncomeTm> incomeTmList = new ArrayList<>();
        for (int i = 0; i < tblIncome.getItems().size(); i++) {
            IncomeTm incomeTm = InobList.get(i);

            incomeTmList.add(incomeTm);
        }

        System.out.println("Income Details: " + incomeTmList);
        var incomeDto = new IncomeDto(desc,amount,year,month,date);
        try {
            boolean isSuccess = IncomeModel.addIncome(incomeDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Incomes Save Success!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSaveExOnAction(ActionEvent actionEvent) {
        String desc = txtDesc.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        int year = Integer.parseInt(txtYear.getText());
        String month = String.valueOf(cmbMonth.getValue());
        String date = dateDate.getPromptText();

        List<ExpenditureTm> expenditureTmList = new ArrayList<>();
        for (int i = 0; i < tblExpenditure.getItems().size(); i++) {
            ExpenditureTm expenditureTm = ExobList.get(i);

            expenditureTmList.add(expenditureTm);
        }

        System.out.println("Expencive Details: " +expenditureTmList);
        var expenditureDto = new ExpenditureDto(desc,amount,year,month,date);
        try {
            boolean isSuccess = ExpenditureModel.addExpenditure(expenditureDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Expenditure Payment Save Success!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbMonthOnAction(ActionEvent actionEvent) {
        InobList.clear();
        ExobList.clear();
        try {
            Month selectedMonth = cmbMonth.getValue();
            if (selectedMonth != null) {
                month = selectedMonth.name();
            }
            List<IncomeTm> dtoList = IncomeModel.searchIncome(year, month);

            for (IncomeTm dto : dtoList) {
                InobList.add(
                        new IncomeTm(
                                dto.getDate(),
                                dto.getDesc(),
                                dto.getAmount1()
                        )
                );
            }

            tblIncome.setItems(InobList);

            List<ExpenditureTm> dtoList1 = ExpenditureModel.searchExpenditure(year, month);

            for ( ExpenditureTm dto : dtoList1) {
                ExobList.add(
                        new ExpenditureTm(
                                dto.getDate(),
                                dto.getDesc(),
                                dto.getAmount2()
                        )
                );
            }

            tblExpenditure.setItems(ExobList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        calIncome();
        calIExpenditure();
        calNetIncom();
    }

    public void btnPrintOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        /*InputStream resourceAsStream = getClass().getResourceAsStream("/report/Waves.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DbConnection.getInstance().getConnection()
        );

        JasperViewer.viewReport(jasperPrint,false);*/
    }

    public void btnSalaryOnAction(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/salary_form.fxml")));
    }
}
