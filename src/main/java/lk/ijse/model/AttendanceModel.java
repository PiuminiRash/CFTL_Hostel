package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.AttendanceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*public class AttendanceModel {
    public static boolean addAttendance(AttendanceDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Attendance (Date,TeacherId,TeacherName) VALUES(?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getDate());
        pstm.setString(2, dto.getTeacherid());
        pstm.setString(3,dto.getTeachername());

        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;
    }
}*/
public class AttendanceModel {
    public static boolean addAttendance(AttendanceDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Attendance (Date, TeacherId, TeacherName) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, dto.getDate());
            pstm.setString(2, dto.getTeacherId());
            pstm.setString(3, dto.getTeacherName());

            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean addAttendanceList(List<AttendanceDto> attendanceDtoList) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Attendance (Date, TeacherId, TeacherName) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (AttendanceDto dto : attendanceDtoList) {
                pstm.setString(1, dto.getDate());
                pstm.setString(2, dto.getTeacherId());
                pstm.setString(3, dto.getTeacherName());

                pstm.addBatch();
            }

            int[] result = pstm.executeBatch();
            connection.commit();

            for (int i : result) {
                if (i <= 0) {
                    return false;
                }
            }

            return true;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public AttendanceDto searchAttendanceCount(String month,String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) AS attendanceCount FROM Attendance WHERE Month(Date) = ? AND TeacherId = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, month);
            pstm.setString(2, id);

            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    int attendanceCount = resultSet.getInt("attendanceCount");
                    return new AttendanceDto(null, null, null, false);
                }
            }
        }
        return null;
    }

    public static List<AttendanceDto> getAllAttendance() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Attendance";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<AttendanceDto> dtoList = new ArrayList<>();

        AttendanceDto dto = null;

        while (resultSet.next()) {
            String date = resultSet.getString(1);
            String id = resultSet.getString(2);
            String name = resultSet.getString(3);

            dto = new AttendanceDto(date,id,name,false);
        }
        return (List<AttendanceDto>) dto;
    }

    public static List<AttendanceDto> getAttendance(String date) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Attendance WHERE Date = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, date); // Set the parameter value

        ResultSet resultSet = pstm.executeQuery();

        List<AttendanceDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(2);
            String name = resultSet.getString(3);
            String day = resultSet.getString(4);

            AttendanceDto dto = new AttendanceDto(id, name, day, false);

            dtoList.add(dto);
        }

        return dtoList;
    }

}