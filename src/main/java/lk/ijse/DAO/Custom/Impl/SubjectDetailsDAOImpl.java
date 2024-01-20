package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.SubjectDetailsDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.SubjectDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectDetailsDAOImpl implements SubjectDetailsDAO {
    @Override
    public ArrayList<SubjectDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SubjectDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO SubjectDetails(SubjectCode,StaffId) VALUES (?, ?)",
                entity.getSubjectCode(),entity.getStaffId());
    }

    @Override
    public boolean update(SubjectDetails dto) throws SQLException, ClassNotFoundException {
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
    public SubjectDetails search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
