package lk.ijse.BO.Custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.PaymentDto;
import lk.ijse.dto.tm.PaymentTm;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    boolean savePayment(PaymentDto dto) throws SQLException, ClassNotFoundException;
    List<PaymentTm> searchPayment(String month) throws SQLException;
    List<PaymentDto> getAllPay(String id) throws SQLException, ClassNotFoundException;
}
