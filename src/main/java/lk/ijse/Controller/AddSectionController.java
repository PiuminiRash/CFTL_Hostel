package lk.ijse.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AddSectionController {
    @FXML
    private JFXTextField txtSectionName;

    @FXML
    private ListView<?> listBucket01;

    @FXML
    private ListView<?> listBucket02;

    @FXML
    private ListView<?> listBucket03;

    @FXML
    private TableView<?> tblSubject;

    @FXML
    private TableColumn<?, ?> colSubjectId;

    @FXML
    private TableColumn<?, ?> colSubjectName;

    @FXML
    private TableColumn<?, ?> colBucket;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }
}
