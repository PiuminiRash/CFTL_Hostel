package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.tm.SubjectTm;

public class SubjectController {
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SubjectTm> tblSubjectDetails;

    @FXML
    private TableColumn<?, ?> colSubjectId;

    @FXML
    private TableColumn<?, ?> colSubjectName;

    @FXML
    private TableColumn<?, ?> colBucket;

    @FXML
    private TableColumn<?, ?> colStudentCount;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    void txtSearchSection(MouseEvent event) {

    }

    @FXML
    void txtSubjectOnAction(ActionEvent event) {

    }

    public void loadDetail(String sectionName) {
    }
}
