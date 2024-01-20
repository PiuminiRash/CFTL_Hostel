package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.IncomeBO;
import lk.ijse.DAO.Custom.IncomeDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DB.DbConnection;
import lk.ijse.Entity.Income;
import lk.ijse.dto.IncomeDto;
import lk.ijse.dto.tm.IncomeTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncomeBOImpl implements IncomeBO {
    IncomeDAO incomeDAO = (IncomeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INCOME);
    @Override
    public boolean saveIncome(IncomeDto dto) throws SQLException, ClassNotFoundException {
        return incomeDAO.save(new Income(dto.getDesc(), dto.getAmount(),dto.getYear(), dto.getMonth(), dto.getDate()));
    }

    @Override
    public List<IncomeTm> searchIncome(int year, String month) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Income WHERE Year = ? AND Month = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, year);
        pstm.setString(2, month);

        ResultSet resultSet = pstm.executeQuery();

        List<IncomeTm> incomeList = new ArrayList<>();

        while (resultSet.next()) {
            String desc = resultSet.getString(2);
            double amount = resultSet.getDouble(3);
            String date = String.valueOf(resultSet.getDate(6));

            IncomeTm dto = new IncomeTm(date, desc, amount);
            incomeList.add(dto);
        }

        return incomeList;
    }
}
