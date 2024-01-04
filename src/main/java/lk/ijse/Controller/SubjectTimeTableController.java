package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.SalaryTm;
import lk.ijse.dto.tm.TimeTableTm;
import lk.ijse.dto.tm.UserTm;
import lk.ijse.model.*;

import java.sql.SQLException;
import java.util.List;

public class SubjectTimeTableController {
    @FXML
    private TableView<TimeTableTm> tblTimeTable;

    @FXML
    private TableColumn<?,?> colThue;

    @FXML
    private TableColumn<?, ?> colSection;

    @FXML
    private TableColumn<?, ?> colMon;

    @FXML
    private TableColumn<?, ?> colTue;

    @FXML
    private TableColumn<?, ?> colWen;

    @FXML
    private TableColumn<?, ?> colFri;

    @FXML
    private TableColumn<?, ?> colSat;

    @FXML
    private TableColumn<?, ?> colSun;

    @FXML
    private JFXComboBox<String> cmbSection;

    @FXML
    private JFXComboBox<String> cmbSubject;

    @FXML
    private JFXComboBox<String> cmbDay;

    private SectionModel sectionModel = new SectionModel();

    private ObservableList<TimeTableTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        loadSection();
        setCellValueFactory();
        loadWeekdays();
        loadTimeTable();
    }

    private void loadWeekdays() {
        cmbDay.getItems().clear();

        cmbDay.getItems().addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday","SateDay","SunDay");

        cmbDay.getSelectionModel().selectFirst();
    }

    private void setCellValueFactory() {
        colSection.setCellValueFactory(new PropertyValueFactory<>("section"));
        colMon.setCellValueFactory(new PropertyValueFactory<>("mon"));
        colThue.setCellValueFactory(new PropertyValueFactory<>("thu"));
        colWen.setCellValueFactory(new PropertyValueFactory<>("wen"));
        colTue.setCellValueFactory(new PropertyValueFactory<>("tue"));
        colFri.setCellValueFactory(new PropertyValueFactory<>("fri"));
        colSat.setCellValueFactory(new PropertyValueFactory<>("sat"));
        colSun.setCellValueFactory(new PropertyValueFactory<>("sun"));
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String section = cmbSection.getValue();
        String subject = cmbSubject.getValue();
        String week = cmbDay.getValue();
        try{

            var dto = new TimeTableDto();
            boolean isUpdate = TimeTableModel.updateTimeTable(section,subject,week);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Time Table Create").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        obList.clear();
        loadTimeTable();
    }

    private void loadSection() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SectionsDto> sectionsDtos = sectionModel.getAllSections();

            for (SectionsDto dto : sectionsDtos) {
                obList.add(dto.getSectionName());
            }
            cmbSection.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbSectionOnAction(ActionEvent actionEvent) {
        String selectSection = cmbSection.getValue();
        if (selectSection != null) {
            loadSubject(selectSection);
        }
    }

    private void loadSubject(String section) {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SubjectDto> subjectDtos = SubjectModel.SearchSub(section);

            for (SubjectDto dto : subjectDtos) {
                obList.add(dto.getSubjectName());
            }
            cmbSubject.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTimeTable(){
        var model = new TeacherModel();

        ObservableList<TimeTableTm> obList = FXCollections.observableArrayList();

        try {
            List<TimeTableDto> dtoList = TimeTableModel.getTimeTable();

            for (TimeTableDto dto : dtoList) {
                obList.add(
                        new TimeTableTm(
                                dto.getSection(),
                                dto.getMon(),
                                dto.getThu(),
                                dto.getWen(),
                                dto.getThu(),
                                dto.getFri(),
                                dto.getSat(),
                                dto.getSun()
                        )
                );
            }

            tblTimeTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
