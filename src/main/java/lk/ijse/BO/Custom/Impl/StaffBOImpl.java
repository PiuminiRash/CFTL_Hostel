package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.StaffBO;
import lk.ijse.DAO.Custom.StaffDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DB.DbConnection;
import lk.ijse.Entity.Staff;
import lk.ijse.dto.StaffDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffBOImpl implements StaffBO {
    StaffDAO staffDAO = (StaffDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STAFF);
    @Override
    public boolean saveStaff(StaffDto dto) throws SQLException, ClassNotFoundException {
        return staffDAO.save(new Staff(dto.getStaffType(), dto.getStaffId(), dto.getStaffName(), dto.getContactNo(), dto.getNIC(), dto.getEmail()));
    }

    @Override
    public boolean updateStaff(StaffDto dto) throws SQLException, ClassNotFoundException {
        return staffDAO.update(new Staff(dto.getStaffType(), dto.getStaffId(), dto.getStaffName(), dto.getContactNo(), dto.getNIC(), dto.getEmail()));
    }

    @Override
    public boolean deleteStaff(String id) throws SQLException, ClassNotFoundException {
        return staffDAO.delete(id);
    }

    @Override
    public List<StaffDto> getAllStaffs() throws SQLException {
        return null;
    }

    @Override
    public Staff searchStaff(String id) throws SQLException, ClassNotFoundException {
        return staffDAO.search(id);
    }

    @Override
    public List<StaffDto> getAllStaffTypes(String type) throws SQLException {
        return null;
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
}
