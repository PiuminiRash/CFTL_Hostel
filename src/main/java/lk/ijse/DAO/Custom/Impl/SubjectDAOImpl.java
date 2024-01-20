package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.SubjectDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.Subject;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectDAOImpl implements SubjectDAO {
    @Override
    public ArrayList<Subject> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Subject entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Subject VALUES(?, ?, ?)",
                entity.getSubjectCode(),entity.getSubjectName(),entity.getBucket());
    }

    @Override
    public boolean update(Subject dto) throws SQLException, ClassNotFoundException {
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
    public Subject search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
