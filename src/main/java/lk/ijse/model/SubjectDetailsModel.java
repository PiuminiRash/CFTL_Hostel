package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.tm.AddTeacherTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SubjectDetailsModel {
    public boolean saveSubjectDetails(String SubjectCode, List<AddTeacherTm> addTeacherTmList) throws SQLException {
        for(AddTeacherTm tm : addTeacherTmList) {
            if(!saveSubjectDetails(SubjectCode, tm)) {
                return false;
            }
        }
        return true;
    }

    private boolean saveSubjectDetails(String SubjectCode, AddTeacherTm tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO SubjectDetails VALUES(?, ? ,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, SubjectCode);
        pstm.setString(2, tm.getTeacherId());
        pstm.setString(3, tm.getTeacherName());

        return pstm.executeUpdate() > 0;
    }
}
