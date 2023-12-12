package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.BranchDto;
import lk.ijse.dto.TeacherDto;
import lk.ijse.dto.tm.AddTeacherTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherModel {
    public String generateNextTeacherId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT TeacherId FROM Teacher ORDER BY TeacherId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitTeacherId(resultSet.getString(1));
        }
        return splitTeacherId(null);
    }

    private String splitTeacherId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("T");

            int id = Integer.parseInt(split[1]);
            id++;
            return "T00" + id;
        } else {
            return "T001";
        }
    }

    public static boolean saveTeacher(TeacherDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Teacher VALUES(?,?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getTeacherId());
        pstm.setString(2, dto.getTeacherName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getNIC());

        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;
    }

    public static boolean updateTeacher(TeacherDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Teacher SET TeacherName = ?, Email = ?, NIC = ?  WHERE TeacherId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getTeacherName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getNIC());
        pstm.setString(4, dto.getTeacherId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean deleteTeacher(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM Teacher WHERE TeacherId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<TeacherDto> getAllTeacher() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Teacher";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<TeacherDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new TeacherDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;
    }

    public static TeacherDto searchTeacher(String teacherId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM Teacher WHERE TeacherId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, teacherId);

        ResultSet resultSet = pstm.executeQuery();

        TeacherDto dto = null;

        if(resultSet.next()) {
            String TeacherId = resultSet.getString(1);
            String TeacherName = resultSet.getString(2);
            String Address = resultSet.getString(3);
            String NIC = resultSet.getString(4);

            dto = new TeacherDto(TeacherId,TeacherName,Address,NIC);
        }
        return dto;
    }

    public List<TeacherDto> loadAllTeacher() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Teacher";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<TeacherDto> teacherList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            teacherList.add(new TeacherDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }

        return teacherList;
    }

    public boolean updateTeacher(List<AddTeacherTm> addTeacherTmList) throws SQLException {
        for(AddTeacherTm tm : addTeacherTmList) {
            System.out.println("Teacher: " + tm);
        }
        return true;
    }
}
