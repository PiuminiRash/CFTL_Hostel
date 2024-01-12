package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.SectionDetailsDto;
import lk.ijse.dto.SubjectDto;
import lk.ijse.dto.tm.SubjectTm;
import lk.ijse.model.SectionModel;
import lk.ijse.model.SubjectModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ViewSubjectController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<SubjectTm> tblSubjectDetails;

    @FXML
    private TableColumn<?, ?> colSubCode;

    @FXML
    private TableColumn<?, ?> colSubName;

    @FXML
    private TableColumn<?, ?> colSection;

    @FXML
    private TableColumn<?, ?> colBucket;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?, ?> colDelete;

    private SubjectModel subjectModel = new SubjectModel();

    private SectionModel sectionModel = new SectionModel();

    private ObservableList<SubjectTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setCellValueFactory();
        loadAllSubjectDetails();
    }

    private void setCellValueFactory() {
        colSubCode.setCellValueFactory(new PropertyValueFactory<>("SubjectCode"));
        colSubName.setCellValueFactory(new PropertyValueFactory<>("SubjectName"));
        colSection.setCellValueFactory(new PropertyValueFactory<>("Section"));
        colBucket.setCellValueFactory(new PropertyValueFactory<>("Bucket"));
        //colUpdate.setCellValueFactory(new PropertyValueFactory<>("Update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("Delete"));
    }

    private void loadAllSubjectDetails() {
        var model = new SubjectModel();

        ObservableList<SubjectTm> obList = FXCollections.observableArrayList();

        try {
            List<SubjectDto> dtoList = subjectModel.getAllSubject();

            for (SubjectDto dto : dtoList) {
                Button updateBtn = new Button("Update");
                Button deleteBtn = new Button("Delete");

                String section = dto.getSubjectCode();

                setDeleteButtonOnAction(deleteBtn,dto.getSubjectCode());
                obList.add(
                        new SubjectTm(
                                dto.getSubjectCode(),
                                dto.getSubjectName(),
                                section,
                                dto.getBucket(),
                                deleteBtn
                        )
                );
            }

            tblSubjectDetails.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDeleteButtonOnAction(Button deleteBtn , String subjectCode) {
        deleteBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove subject ?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                obList.removeIf(subjectTm -> subjectTm.getSubjectCode().equals(subjectCode));
                tblSubjectDetails.refresh();

                DeleteSub(subjectCode);
                loadAllSubjectDetails();
            }
        });
    }

    private void DeleteSub(String studentId){
        try {
            boolean isDeleted = SubjectModel.deleteSubject(studentId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Subject deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Subject not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblSubjectDetails.refresh();
    }

    private void setUpdateButtonOnAction(Button updateBtn, String StudentId) {
        updateBtn.setOnAction((e) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/studentupdate_form.fxml"));
                AnchorPane updateForm = loader.load();
                rootNode.getChildren().clear();
                rootNode.getChildren().add(updateForm);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
