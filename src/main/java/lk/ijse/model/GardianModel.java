package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.GardianDto;
import lk.ijse.dto.StudentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GardianModel {
    public static boolean saveGardian(GardianDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO Gardian VALUES (?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getStudentId());
        pstm.setString(2, dto.getGardianName());
        pstm.setString(3, dto.getEmail());
        pstm.setString(4, dto.getContactNo());

        int rowsAffected = pstm.executeUpdate();
        return rowsAffected > 0;
    }

    public GardianDto searchGuardian(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM Gardian WHERE StudentId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        GardianDto dto = null;

        if(resultSet.next()) {
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String contactNo = resultSet.getString(4);

            dto = new GardianDto(id,name,email,contactNo);
        }
        return dto;
    }
}
