package lk.ijse.Controller;

import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import lk.ijse.DB.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomePageController {
    public StackedBarChart barChart;

    public void initialize() {
        try {
            incomeChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void incomeChart() throws SQLException {
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
}
