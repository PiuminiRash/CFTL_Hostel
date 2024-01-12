package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.SectionDetailsDto;
import lk.ijse.dto.SubjectDetailsDto;
import lk.ijse.dto.tm.SectionTm;
import lk.ijse.dto.tm.StaffTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SectionDetailsModel {
//    public static boolean saveSectionDetail(SectionDetailsDto dto) throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        // Assuming SectionDetails table has two columns: SectionName and SubjectId
//        String sql = "INSERT INTO SectionDetails (SectionName, SubjectCode) VALUES (?, ?)";
//
//        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
//            //pstm.setString(1, dto.getSectionName());
//            pstm.setString(2, dto.getSectionName());
//
//            return pstm.executeUpdate() > 0;
//        }
//    }

    public boolean saveSectionDetails(List<SectionDetailsDto> sectionDetailsDtoList) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO SectionDetails (SectionName,SubjectCode) VALUES (?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);

            for (SectionDetailsDto dto : sectionDetailsDtoList) {
                pstm.setString(1, dto.getSectionName());
                pstm.setString(2, dto.getSubjectCode());

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

//    public boolean saveSectionDetails(List<SectionDetailsDto> sectionDetailsDtoList) throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "INSERT INTO SectionDetails VALUES (?, ?)";
//        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
//            connection.setAutoCommit(false);
//            for (SectionDetailsDto dto : sectionDetailsDtoList) {
//                pstm.setString(1, dto.getSectionName());
//                pstm.setString(2, dto.getSubjectCode());
//
//                pstm.addBatch();
//            }
//
//            int[] result = pstm.executeBatch();
//            connection.commit();
//
//            for (int i : result) {
//                if (i <= 0) {
//                    return false;
//                }
//            }
//
//            return true;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }

    public static void saveSectionDetails(String subjectId, String sectionName) {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "INSERT INTO Subject VALUES(?, ?, ?)";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setString(1, dto.getSectionId());
//        pstm.setString(2, dto.getSubjectCode());
//
//        return pstm.executeUpdate() > 0;
    }
}
