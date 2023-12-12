package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.SalaryDto;
import lk.ijse.dto.tm.SalaryTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryModel {
    public static boolean saveSalary(SalaryDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Salary (Type,Month,StaffId,StaffName,SalaryAmt,Bonus,E,FinalSalary) VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getType());
        pstm.setString(2,dto.getMonth());
        pstm.setString(3, dto.getStaffId());
        pstm.setString(4, dto.getStaffName());
        pstm.setDouble(5,dto.getSalaryAmt());
        pstm.setDouble(6, dto.getBonus());
        pstm.setDouble(7, dto.getE());
        pstm.setDouble(8,dto.getFinalSalary());

        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;
    }

    public static List<SalaryTm> searchSalaryTeacher(String month) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Salary WHERE Type = 'Teacher' AND Month = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        //pstm.setString(1,type);
        pstm.setString(1, month);

        ResultSet resultSet = pstm.executeQuery();

        List<SalaryTm> salaryTmList = new ArrayList<>();

        while (resultSet.next()) {
            String Id = resultSet.getString(4);
            String Name = resultSet.getString(5);
            double SalaryAmt = resultSet.getDouble(6);
            double Bonus = resultSet.getDouble(7);
            double E = resultSet.getDouble(8);
            double finalSalary = resultSet.getDouble(9);

            SalaryTm dto = new SalaryTm(Id,Name,SalaryAmt,Bonus,E,finalSalary);
            salaryTmList.add(dto);
        }
        return salaryTmList;
    }

    public static List<SalaryTm> searchSalaryEmployee(String month) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Salary WHERE Type = 'Employee' AND Month = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        //pstm.setString(1,type);
        pstm.setString(1, month);

        ResultSet resultSet = pstm.executeQuery();

        List<SalaryTm> salaryTmList = new ArrayList<>();

        while (resultSet.next()) {
            String Id = resultSet.getString(4);
            String Name = resultSet.getString(5);
            double SalaryAmt = resultSet.getDouble(6);
            double Bonus = resultSet.getDouble(7);
            double E = resultSet.getDouble(8);
            double finalSalary = resultSet.getDouble(9);

            SalaryTm dto = new SalaryTm(Id,Name,SalaryAmt,Bonus,E,finalSalary);
            salaryTmList.add(dto);
        }
        return salaryTmList;
    }
}
