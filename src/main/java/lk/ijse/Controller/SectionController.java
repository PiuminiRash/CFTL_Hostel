package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.SectionsDto;
import lk.ijse.dto.tm.SectionTm;
import lk.ijse.dto.SectionModel;
import lk.ijse.dto.StudentModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class SectionController {
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SectionTm> tblSectionDetails;

    @FXML
    private TableColumn<?, ?> colSection;

    @FXML
    private TableColumn<?, ?> colStudentCount;

    @FXML
    private TableColumn<?, ?> colDelete;

    private SectionModel sectionModel = new SectionModel();

    private StudentModel studentModel = new StudentModel();

    private ObservableList<SectionTm> obList = FXCollections.observableArrayList();

    public void initialize(){
        setCellValueFactory();
        loadAllSection();

        tblSectionDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                openSectionDetails(newValue.getSectionName());
            }
        });
    }

    private void openSectionDetails(String sectionName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subject_form.fxml"));
            AnchorPane RoomManagePane = loader.load();

            SubjectController subjectController = loader.getController();

            subjectController.loadDetail(sectionName);

            root.getChildren().clear();
            root.getChildren().add(RoomManagePane);
        } catch (IOException e) {

        }
    }

    private void setCellValueFactory() {
        colSection.setCellValueFactory(new PropertyValueFactory<>("SectionName"));
        colStudentCount.setCellValueFactory(new PropertyValueFactory<>("StudentCount"));
        colDelete.setCellValueFactory(new  PropertyValueFactory<>("Delete"));
    }

    @FXML
    void btnAddSectionOnAction(ActionEvent event) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/addsection_form.fxml")));
    }

    @FXML
    void btnAddSubjectOnAction(ActionEvent event) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/managesubject_form.fxml")));
    }

    private void loadAllSection(){
        var model = new SectionModel();

        try {
            List<SectionsDto> dtoList = sectionModel.getAllSections();

            for (SectionsDto dto : dtoList) {
                Button deleteBtn = new Button("Delete");

                int studentCount = studentModel.getCount(dtoList.toString());
                //setDeleteButtonOnAction(deleteBtn,dto.getStudentId());

                obList.add(
                        new SectionTm(
                                dto.getSectionName(),
                                studentCount,
                                deleteBtn
                        )
                );
            }

            tblSectionDetails.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtSearchSection(MouseEvent mouseEvent) {
    }
}
