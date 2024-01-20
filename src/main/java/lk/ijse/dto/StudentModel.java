package lk.ijse.dto;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.StudentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    public String generateNextStudentId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT StudentId FROM Student ORDER BY StudentId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitStudentId(resultSet.getString(1));
        }
        return splitStudentId(null);
    }

    private String splitStudentId(String currentStudentId) {
        if(currentStudentId != null) {
            String[] split = currentStudentId.split("S");

            int id = Integer.parseInt(split[1]);
            id++;
            return "S00" + id;
        } else {
            return "S001";
        }
    }

    public static boolean saveStudent(StudentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO Student VALUES (?, ?, ?, ?, ?, ?, ? , ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getStudentId());
        pstm.setString(2, dto.getStudentName());
        pstm.setString(3, dto.getStudentAddress());
        pstm.setString(4, dto.getSection());
        pstm.setString(5, dto.getBucket01());
        pstm.setString(6, dto.getBucket02());
        pstm.setString(7, dto.getBucket03());
        pstm.setString(8, dto.getRoomNo());

        int rowsAffected = pstm.executeUpdate();
        return rowsAffected > 0;
    }

    public List<StudentDto> getAllStudent() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Student";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<StudentDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new StudentDto(
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

    public boolean deleteStudent(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM Student WHERE  StudentId= ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public boolean updateStudent(StudentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Student SET StudentName = ?, Address = ?, Section = ? ,Bucket1 =? ,Bucket2 = ? , Bucket3 = ? , RoomNo = ? WHERE StudentId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getStudentName());
        pstm.setString(2, dto.getStudentAddress());
        pstm.setString(3, dto.getSection());
        pstm.setString(4, dto.getBucket01());
        pstm.setString(5, dto.getBucket02());
        pstm.setString(6, dto.getBucket03());
        pstm.setString(7, dto.getRoomNo());
        pstm.setString(8, dto.getStudentId());

        return pstm.executeUpdate() > 0;
    }

    public StudentDto searchStudent(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM Student WHERE StudentId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        StudentDto dto = null;

        if(resultSet.next()) {
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String section = resultSet.getString(4);
            String bucket1 = resultSet.getString(5);
            String bucket2 = resultSet.getString(6);
            String bucket3 = resultSet.getString(7);
            String roomNo = resultSet.getString(8);


            dto = new StudentDto(id,name,address,section,bucket1,bucket2,bucket3,roomNo);
        }
        return dto;
    }

    public int getCount(String roomNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) AS StudentCount FROM Student WHERE RoomNo = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, roomNo);

            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    int studentCount = resultSet.getInt("StudentCount");
                    return studentCount;
                }
            }
        }
        return 0;
    }

    public List<StudentDto> searchRoomStudent(String roomNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM Student WHERE RoomNo = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, roomNo);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<StudentDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new StudentDto(
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

