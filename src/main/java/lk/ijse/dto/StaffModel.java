package lk.ijse.dto;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.StaffDto;
import lk.ijse.dto.StudentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffModel {
    public String generateStaffEmployeeId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT StaffId FROM Staff ORDER BY StaffId DESC LIMIT 1";
        PreparedStatement pstm =connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitStaffEmployeeId(resultSet.getString(1));
        }
        return splitStaffEmployeeId(null);
    }

    private String splitStaffEmployeeId(String currentStaffId) {
        if(currentStaffId != null) {
            String[] split = currentStaffId.split("E");

            int id = Integer.parseInt(split[1]);
            id++;
            return "E00" + id;
        } else {
            return "E001";
        }
    }

    public String generateStaffTeacherId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT StaffId FROM Staff ORDER BY StaffId DESC LIMIT 1";
        PreparedStatement pstm =connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitStaffTeacherId(resultSet.getString(1));
        }
        return splitStaffTeacherId(null);
    }

    private String splitStaffTeacherId(String currentStaffId) {
        if(currentStaffId != null) {
            String[] split = currentStaffId.split("T");

            int id = Integer.parseInt(split[1]);
            id++;
            return "T00" + id;
        } else {
            return "T001";
        }
    }

    public boolean saveStaff(StaffDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO Staff VALUES (?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getStaffType());
        pstm.setString(2, dto.getStaffId());
        pstm.setString(3, dto.getStaffName());
        pstm.setInt(4, dto.getContactNo());
        pstm.setString(5, dto.getNIC());
        pstm.setString(6, dto.getEmail());

        int rowsAffected = pstm.executeUpdate();
        return rowsAffected > 0 ;
    }

    public boolean updateStaff(StaffDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Staff SET StaffType = ? , StaffName = ? , ContactNo = ? , NIC = ? , Email = ? WHERE StaffId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getStaffType());
        pstm.setString(2, dto.getStaffName());
        pstm.setInt(3, dto.getContactNo());
        pstm.setString(4, dto.getNIC());
        pstm.setString(5, dto.getEmail());
        pstm.setString(6, dto.getStaffId());

        return pstm.executeUpdate() > 0;
    }

    public List<StaffDto> getAllStaff() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Staff";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<StaffDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new StaffDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return dtoList;
    }

    public boolean deleteStaff(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM Staff WHERE  StaffId= ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public List<StaffDto> getAllStaffType(String type) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Staff WHERE StaffType = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, type);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<StaffDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new StaffDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return dtoList;
    }

    public StaffDto searchStaff(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM Staff WHERE StaffId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        StaffDto dto = null;

        if(resultSet.next()) {
            String type = resultSet.getString(2);
            String name = resultSet.getString(3);
            int contactNo = Integer.parseInt(resultSet.getString(4));
            String NIC = resultSet.getString(5);
            String email = resultSet.getString(6);

            dto = new StaffDto(type,id,name,contactNo,NIC,email);
        }
        return dto;
    }
}