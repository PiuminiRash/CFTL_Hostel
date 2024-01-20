package lk.ijse.dto;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.PaymentDto;
import lk.ijse.dto.tm.PaymentTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentModel {
    public static boolean savePayment(PaymentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Payment (Month,Date,StudentId,Amount) VALUES(?,?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, String.valueOf(dto.getMonth()));
        pstm.setString(2, dto.getDate());
        pstm.setString(3, dto.getStudentId());
        pstm.setDouble(4, dto.getAmt());

        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;
    }

    public static List<PaymentTm> searchPayment(String month) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Payment WHERE Month = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, month);

        ResultSet resultSet = pstm.executeQuery();

        List<PaymentTm> paymentTmList = new ArrayList<>();

        while (resultSet.next()) {
            String date = resultSet.getString(3);
            String id = resultSet.getString(4);
            double amt = resultSet.getDouble(5);

            PaymentTm dto = new PaymentTm(date,id,amt);
            paymentTmList.add(dto);
        }

        return paymentTmList;
    }

    public static List<PaymentDto> getAllPayment(String studentId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Payment WHERE StudentId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,studentId);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<PaymentDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new PaymentDto(
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    )
            );
        }
        return dtoList;
    }
}
