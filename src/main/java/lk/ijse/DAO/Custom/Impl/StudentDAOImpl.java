package lk.ijse.DAO;

import lk.ijse.Entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<Student> getAll() throws SQLException,ClassNotFoundException{
        return getAll();
    }

    @Override
    public boolean save(Student entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Student VALUES (?, ?, ?, ?, ?, ?, ? ,?)",
                entity.getStudentId(), entity.getStudentName(), entity.getStudentAddress(),entity.getSection(),entity.getBucket01(),entity.getBucket02(),entity.getBucket03(),entity.getRoomNo());
    }

    @Override
    public boolean update(Student entity) throws SQLException, ClassNotFoundException {
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
    public Student search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
