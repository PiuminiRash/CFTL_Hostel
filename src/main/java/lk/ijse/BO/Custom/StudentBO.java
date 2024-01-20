package lk.ijse.BO.Custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.Entity.Student;
import lk.ijse.dto.StudentDto;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDto dto) throws SQLException, ClassNotFoundException;
    boolean updateStudent(StudentDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;
    List<StudentDto> getAllStudent() throws SQLException;
    StudentDto searchStudent(String id) throws SQLException, ClassNotFoundException;
    int getCount(String roomNo) throws SQLException;
    List<StudentDto> searchRoomStudent(String roomNo) throws SQLException;
}
