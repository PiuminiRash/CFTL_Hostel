package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.JobTypeDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobTypeModel {
    public static String generateNextJobCode() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT JobCode FROM JobType ORDER BY JobCode DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitJobCode(resultSet.getString(1));
        }
        return splitJobCode(null);
    }

    private static String splitJobCode(String currentEmployeeId) {
        if(currentEmployeeId != null) {
            String[] split = currentEmployeeId.split("J");

            int id = Integer.parseInt(split[1]);
            id++;
            return "J00" + id;
        } else {
            return "J001";
        }
    }

    public boolean saveJobType(JobTypeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO JobType VALUES(?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getJobCode());
        pstm.setString(2, dto.getJobType());
        pstm.setString(3, dto.getDescription());

        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;
    }

    public boolean updateJobType(JobTypeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE JobType SET JobType = ?, Description = ? WHERE JobCode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getJobType());
        pstm.setString(2, dto.getDescription());
        pstm.setString(3, dto.getJobCode());

        return pstm.executeUpdate() > 0;
    }

    public static boolean deleteJobType(String jobCode) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM JobType WHERE JobCode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, jobCode);

        return pstm.executeUpdate() > 0;
    }

    public static List<JobTypeDto> getAllJobType() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM JobType";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<JobTypeDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new JobTypeDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    )
            );
        }
        return dtoList;
    }

    public static JobTypeDto searchJobType(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM JobType WHERE JobCode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        JobTypeDto dto = null;

        if(resultSet.next()) {
            String code = resultSet.getString(1);
            String type = resultSet.getString(2);
            String desc = resultSet.getString(3);

            dto = new JobTypeDto(code,type,desc);
        }
        return dto;
    }
}
