package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.SectionDetailsDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.SectionDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class SectionDetailsDAOImpl implements SectionDetailsDAO {
    @Override
    public ArrayList<SectionDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SectionDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO SectionDetails (SectionName,SubjectCode) VALUES (?, ?)",
                entity.getSectionName(),entity.getSubjectCode());
    }

    @Override
    public boolean update(SectionDetails dto) throws SQLException, ClassNotFoundException {
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
    public SectionDetails search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
