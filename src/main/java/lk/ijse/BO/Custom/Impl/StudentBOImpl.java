package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.StudentBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.Custom.StudentDAO;
import lk.ijse.Entity.Student;
import lk.ijse.dto.StudentDto;

import java.sql.SQLException;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public boolean saveStudent(StudentDto dto) throws SQLException, ClassNotFoundException {
        return studentDAO.save(new Student(dto.getStudentId(), dto.getStudentName(), dto.getStudentAddress(), dto.getSection(), dto.getBucket01(), dto.getBucket02(), dto.getBucket03(), dto.getRoomNo()));
    }

    @Override
    public boolean updateStudent(StudentDto dto) throws SQLException, ClassNotFoundException {
        return studentDAO.update(new Student(dto.getStudentId(), dto.getStudentName(), dto.getStudentAddress(), dto.getSection(), dto.getBucket01(), dto.getBucket02(), dto.getBucket03(), dto.getRoomNo()));
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }

    @Override
    public List<StudentDto> getAllStudent() throws SQLException {
        return null;
    }

    @Override
    public StudentDto searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student student = studentDAO.search(id);
        return new StudentDto(
                student.getStudentId(),
                student.getStudentName(),
                student.getStudentAddress(),
                student.getSection(),
                student.getBucket01(),
                student.getBucket02(),
                student.getBucket03(),
                student.getRoomNo()
        );
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
