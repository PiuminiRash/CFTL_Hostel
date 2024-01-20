package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.ExpenditureBO;
import lk.ijse.DAO.Custom.ExpenditureDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DB.DbConnection;
import lk.ijse.Entity.Expenditure;
import lk.ijse.dto.ExpenditureDto;
import lk.ijse.dto.tm.ExpenditureTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenditureBOImpl implements ExpenditureBO {
    ExpenditureDAO expenditureDAO = (ExpenditureDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EXPENDITURE);
    @Override
    public boolean saveExpenditure(ExpenditureDto dto) throws SQLException, ClassNotFoundException {
        return expenditureDAO.save(new Expenditure(dto.getDesc(),dto.getAmount(),dto.getYear(), dto.getMonth(), dto.getDate()));
    }

    @Override
    public List<ExpenditureTm> searchExpenditure(int year, String month) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Expenditure WHERE Year = ? AND Month = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, year);
        pstm.setString(2, month);

        ResultSet resultSet = pstm.executeQuery();

        List<ExpenditureTm> expenditureTmList = new ArrayList<>();

        while (resultSet.next()) {
            String desc = resultSet.getString(2);
            double amount = resultSet.getDouble(3);
            String date = String.valueOf(resultSet.getDate(6));

            ExpenditureTm dto = new ExpenditureTm(date, desc, amount);
            expenditureTmList.add(dto);
        }

        return expenditureTmList;
    }
}
