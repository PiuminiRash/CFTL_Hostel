package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.tm.AttendanceTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public String generateNextEmployeeId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT EmployeeId FROM Employee ORDER BY EmployeeId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitEmployeeId(resultSet.getString(1));
        }
        return splitEmployeeId(null);
    }

    private String splitEmployeeId(String currentEmployeeId) {
        if(currentEmployeeId != null) {
            String[] split = currentEmployeeId.split("E");

            int id = Integer.parseInt(split[1]);
            id++;
            return "E00" + id;
        } else {
            return "E001";
        }
    }

    public static boolean saveEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Employee VALUES(?,?,?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getEmployeeId());
        pstm.setString(2, dto.getEmployeeName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getJobType());
        pstm.setString(5, dto.getNIC());

        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;
    }

    public static boolean deleteEmployee(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM Employee WHERE EmployeeId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Employee SET EmployeeName = ?, Email = ?, JobCode = ?, NIC = ? WHERE EmployeeId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getEmployeeName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getJobType());
        pstm.setString(4, dto.getNIC());
        pstm.setString(5, dto.getEmployeeId());

        return pstm.executeUpdate() > 0;
    }

    public static List<EmployeeDto> getAllEmployee() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Employee";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<EmployeeDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new EmployeeDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return dtoList;
    }

    public static EmployeeDto searchEmployee(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM Employee WHERE EmployeeId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto dto = null;

        if(resultSet.next()) {
            String empName = resultSet.getString(2);
            String address = resultSet.getString(3);
            String jobType = resultSet.getString(4);
            String NIC = resultSet.getString(5);

            dto = new EmployeeDto(id,empName,address,jobType,NIC);
        }
        return dto;
    }

    public static boolean updateEmployee(List<AttendanceTm> employeeAttendanceTmList) {
        for(AttendanceTm tm : employeeAttendanceTmList) {
            System.out.println("Employee: " + tm);
        }
        return true;
    }
}
