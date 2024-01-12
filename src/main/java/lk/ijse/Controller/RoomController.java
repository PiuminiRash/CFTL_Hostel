package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.RoomDto;
import lk.ijse.dto.StudentDto;
import lk.ijse.dto.tm.RoomTm;
import lk.ijse.model.RoomModel;
import lk.ijse.model.StudentModel;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class RoomController  {
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<RoomTm> tblRoomDetails;

    @FXML
    private TableColumn<?, ?> colRoomNo;

    @FXML
    private TableColumn<?, ?> colRoomName;

    @FXML
    private TableColumn<?, ?> colNoOfBed;

    @FXML
    private TableColumn<?, ?> colStudentCount;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private AnchorPane root1;

    @FXML
    private JFXTextField txtRoomNo;

    @FXML
    private JFXTextField txtRoomName;

    @FXML
    private JFXTextField txtNoOfBed;

    @FXML
    private JFXButton UpdateRoom;

    private RoomModel roomModel = new RoomModel();

    private StudentModel studentModel = new StudentModel();

    private ObservableList<RoomTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setCellValueFactory();
        loadAllRoomDetails();
        generateNextRoomNo();

        tblRoomDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                openRoomDetails(newValue.getRoomNo());
            }
        });
    }

    private void openRoomDetails(String roomNo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/roommanage_form.fxml"));
            AnchorPane RoomManagePane = loader.load();

            RoomManageController roomManageController = loader.getController();

            roomManageController.loadRoom(roomNo);

            root.getChildren().clear();
            root.getChildren().add(RoomManagePane);
        } catch (IOException e) {

        }
    }

    private void generateNextRoomNo() {
        try {
            String roomNo = roomModel.generateNextRoomNo();
            txtRoomNo.setText(roomNo);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colRoomNo.setCellValueFactory(new PropertyValueFactory<>("RoomNo"));
        colRoomName.setCellValueFactory(new PropertyValueFactory<>("RoomName"));
        colNoOfBed.setCellValueFactory(new PropertyValueFactory<>("NoOfBed"));
        colStudentCount.setCellValueFactory(new PropertyValueFactory<>("StudentCount"));
        colAction.setCellValueFactory(new  PropertyValueFactory<>("Delete"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Complete"));
    }

    private void loadAllRoomDetails() {
        var model = new RoomModel();

        try {
            List<RoomDto> dtoList = RoomModel.getAllRoom();

            for (RoomDto dto : dtoList) {
                Button deleteBtn = new Button("Delete");
                setDeleteButtonOnAction(deleteBtn,dto.getRoomNo());

                String room = dto.getRoomNo();

                int count = studentModel.getCount(room);

                String status = "Incomplete";
                if (dto.getNoOfBed() == count) {
                    status = "Complete";
                }
                obList.add(
                        new RoomTm(
                                dto.getRoomNo(),
                                dto.getRoomName(),
                                dto.getNoOfBed(),
                                count,
                                deleteBtn,
                                status
                        )
                );
            }

            tblRoomDetails.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDeleteButtonOnAction(Button deleteBtn , String roomNo) {
        deleteBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove Room ?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                obList.removeIf(roomTm -> roomTm.getRoomNo().equals(roomNo));
                tblRoomDetails.refresh();

                DeleteRoom(roomNo);
            }
        });
    }

    private void DeleteRoom(String roomNo){
        try {
            boolean isDeleted = RoomModel.deleteRoom(roomNo);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Room not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblRoomDetails.refresh();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String room_no = txtRoomNo.getText();
        String room_name = txtRoomName.getText();
        int no_of_beds = Integer.parseInt(txtNoOfBed.getText());

        try{
            if (!validateRoom()){
                return;
            }
            var dto = new RoomDto(room_no,room_name,no_of_beds);
            boolean isSaved = roomModel.saveRoom(dto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"room saved").show();
                clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        obList.clear();
        //generateNextRoomNo();
        //loadAllRoomDetails();
    }

    private void clearFields() {
        txtRoomNo.setText("");
        txtRoomName.setText("");
        txtNoOfBed.setText("");
    }

    private boolean validateRoom() {
        boolean isValidate = true;
        boolean roomNo = Pattern.matches("[R][\\d]{2,}", txtRoomNo.getText());
        if (!roomNo){
            showErrorNotification("Invalid Room no.", "The Room no you entered is invalid");
            isValidate = false;
        }
        boolean room_name = Pattern.matches("[A-Za-z]{5,}",txtRoomName.getText());
        if(!room_name){
            showErrorNotification("invalid room name","The room name you entered is invalid");
            isValidate = false;
        }
        boolean beds = Pattern.matches("[0-9]{1,}",txtNoOfBed.getText());
        if (!beds){
            showErrorNotification("Invalid count", "The beds count you entered is invalid");
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

    public void txtSearchRoomOnAction(ActionEvent actionEvent) {
    }
}
