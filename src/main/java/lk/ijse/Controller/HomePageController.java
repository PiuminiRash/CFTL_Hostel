package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.DB.DbConnection;
import lk.ijse.dto.TimeTableDto;
import lk.ijse.dto.tm.TimeTableTm;
import lk.ijse.model.TeacherModel;
import lk.ijse.model.TimeTableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HomePageController {
    public StackedBarChart barChart;
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

    public void initialize() {
        setCellValueFactory();
        loadTimeTable();

        try {
            orderChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void orderChart() throws SQLException {
        barChart.getData().clear();
        String sql = "SELECT month, SUM(Amount) FROM income WHERE date >= CURDATE() - INTERVAL 6 MONTH GROUP BY month";

        Connection connection = DbConnection.getInstance().getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data(resultSet.getString(1), resultSet.getInt(2)));
            }

            barChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
