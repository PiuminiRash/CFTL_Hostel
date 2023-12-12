package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.SubjectDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectModel {
    public String generateNextSubjectCode() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT SubjectCode FROM Subject ORDER BY SubjectCode DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitSubjectCode(resultSet.getString(1));
        }
        return splitSubjectCode(null);
    }

    private String splitSubjectCode(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("S");

            int id = Integer.parseInt(split[1]);
            id++;
            return "S00" + id;
        } else {
            return "S001";
        }
    }

    public static boolean saveSubject(String SectionName, String Bucket, String SubjectCode, String SubjectName) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Subject VALUES(?, ?, ? ,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, SectionName);
        pstm.setString(2, Bucket);
        pstm.setString(3, SubjectCode);
        pstm.setString(4, SubjectName);

        return pstm.executeUpdate() > 0;
    }

    public List<SubjectDto> getAllSubject() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Subject";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<SubjectDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new SubjectDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;
    }

    public static List<SubjectDto> bucketSub(String section , String bucket) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Subject WHERE (SectionName = ?) AND (Bucket = ?) ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, section);
        pstm.setString(2, bucket);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<SubjectDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new SubjectDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;
    }

    public static List<SubjectDto> SearchSub(String section) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Subject WHERE (SectionName = ?) ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, section);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<SubjectDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new SubjectDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;
    }

    public static boolean deleteSubject(String SubjectCode) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM Subject WHERE SubjectCode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, SubjectCode);

        return pstm.executeUpdate() > 0;
    }

}
