package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.StudentDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.Room;
import lk.ijse.Entity.Staff;
import lk.ijse.Entity.Student;
import lk.ijse.dto.RoomDto;

import java.sql.ResultSet;
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
        return SQLUtil.execute("UPDATE Student SET StudentName = ?, Address = ?, Section = ? ,Bucket1 =? ,Bucket2 = ? , Bucket3 = ? , RoomNo = ? WHERE StudentId = ?",
                entity.getStudentName(),entity.getStudentAddress(),entity.getSection(),entity.getBucket01(),entity.getBucket02(),entity.getBucket03(),entity.getRoomNo(),entity.getStudentId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Student WHERE  StudentId= ?" , id );
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Student search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Student WHERE StudentId = ?",id);
        if (resultSet.next()) {
            return new Student(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }else {
            return null;
        }
    }
}
