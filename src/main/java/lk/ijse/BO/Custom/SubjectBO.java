package lk.ijse.BO.Custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.SubjectDto;

import java.sql.SQLException;
import java.util.List;

public interface SubjectBO extends SuperBO {
    String generateNextSubjectCode() throws SQLException;
    String splitSubjectCode(String currentOrderId);
    boolean saveSubject(SubjectDto dto) throws SQLException, ClassNotFoundException;
    List<SubjectDto> getAllSubject() throws SQLException;
    List<SubjectDto> bucketSub(String section , String bucket) throws SQLException;
    List<SubjectDto> SearchSub(String section) throws SQLException;
    boolean deleteSubject(String SubjectCode) throws SQLException;
}
