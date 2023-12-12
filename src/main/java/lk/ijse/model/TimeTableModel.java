package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.StudentDto;
import lk.ijse.dto.TimeTableDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeTableModel {
    public static boolean updateTimeTable(String section, String subject, String week) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE TimeTable SET " + week + " = ? WHERE SectionName = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, subject);
        pstm.setString(2, section);

        return pstm.executeUpdate() > 0;
    }

    public static List<TimeTableDto> getTimeTable() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM TimeTable";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<TimeTableDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new TimeTableDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8)
                    )
            );
        }
        return dtoList;
    }
}
