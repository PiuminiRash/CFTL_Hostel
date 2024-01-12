package lk.ijse.BO;

import lk.ijse.dto.StudentDto;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO{
    boolean saveStudent(StudentDto dto) throws SQLException;
    List<StudentDto> getAllStudent() throws SQLException;
    boolean deleteStudent(String id) throws SQLException;
    boolean updateStudent(StudentDto dto) throws SQLException;
    StudentDto searchStudent(String id) throws SQLException;
    int getCount(String roomNo) throws SQLException;
    List<StudentDto> searchRoomStudent(String roomNo) throws SQLException;
}
