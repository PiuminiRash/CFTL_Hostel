package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.PaymentDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAllPay(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment");
        ArrayList<Payment>getAllPayment = new ArrayList<>();
        while (resultSet.next()){
            Payment payment = new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)

            );
            getAllPayment.add(payment);
        }
        return getAllPayment;
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Payment (Month,Date,StudentId,Amount) VALUES(?,?,?,?)",
                entity.getMonth(),entity.getDate(),entity.getStudentId(),entity.getAmt());
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
