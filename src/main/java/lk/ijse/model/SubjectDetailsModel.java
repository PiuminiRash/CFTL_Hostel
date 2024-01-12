package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.SubjectDetailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SubjectDetailsModel {
    public boolean saveSubjectDetails(SubjectDetailsDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO SubjectDetails (SubjectCode,StaffId) VALUES (?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getSubjectCode());
        pstm.setString(2, dto.getStaffId());

        return pstm.executeUpdate() > 0;
    }

    public boolean SaveSubjectDetails(List<SubjectDetailsDto> subjectDetailsDtoList) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO SubjectDetails VALUES (?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (SubjectDetailsDto dto : subjectDetailsDtoList) {
                pstm.setString(1, dto.getSubjectCode());
                pstm.setString(2, dto.getStaffId());

                pstm.addBatch();
            }

            int[] result = pstm.executeBatch();
            connection.commit();

            for (int i : result) {
                if (i <= 0) {
                    return false;
                }
            }

            return true;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
