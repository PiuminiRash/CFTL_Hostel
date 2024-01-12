package lk.ijse.BO;

import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.StudentDAO;
import lk.ijse.dto.StudentDto;

import java.sql.SQLException;
import java.util.List;

public class StudentBOImpl implements StudentBO{
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public boolean saveStudent(StudentDto dto) throws SQLException {
        return false;
    }

    @Override
    public List<StudentDto> getAllStudent() throws SQLException {
        return null;
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateStudent(StudentDto dto) throws SQLException {
        return false;
    }

    @Override
    public StudentDto searchStudent(String id) throws SQLException {
        return null;
    }

    @Override
    public int getCount(String roomNo) throws SQLException {
        return 0;
    }

    @Override
    public List<StudentDto> searchRoomStudent(String roomNo) throws SQLException {
        return null;
    }
}
