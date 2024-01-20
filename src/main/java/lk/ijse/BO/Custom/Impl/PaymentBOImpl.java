package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.PaymentBO;
import lk.ijse.DAO.Custom.PaymentDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.Payment;
import lk.ijse.dto.PaymentDto;
import lk.ijse.dto.tm.PaymentTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public boolean savePayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getMonth(), dto.getDate(), dto.getStudentId(), dto.getAmt()));
    }

    @Override
    public List<PaymentTm> searchPayment(String month) throws SQLException {
        return null;
    }

    @Override
    public List<PaymentDto> getAllPay(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Payment>payments = paymentDAO.getAllPay(id);
        ArrayList<PaymentDto>paymentDtos = new ArrayList<>();

        for (Payment payment:payments) {
            paymentDtos.add(new PaymentDto(
                    payment.getMonth(),
                    payment.getDate(),
                    payment.getStudentId(),
                    payment.getAmt()
            ));
        }
        return paymentDtos;
    }
}
