package lk.ijse.DAO.Custom.Impl;

import com.lowagie.text.Section;
import lk.ijse.DAO.Custom.SectionDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SectionDAOImpl implements SectionDAO {
    @Override
    public ArrayList<Section> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Section entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Section dto) throws SQLException, ClassNotFoundException {
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
    public Section search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
