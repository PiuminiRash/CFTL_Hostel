package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.BranchDto;
import lk.ijse.dto.BucketDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BucketModel {
    public static List<BucketDto> getAllBucket() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Bucket";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<BucketDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new BucketDto(
                            resultSet.getString(1),
                            resultSet.getString(2)
                    )
            );
        }
        return dtoList;
    }

    public static BucketDto searchBucket(String code) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Bucket WHERE BucketId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, code);

        ResultSet resultSet = pstm.executeQuery();

        BucketDto dto = null;

        if(resultSet.next()) {
            dto = new BucketDto(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        }
        return dto;
    }
}
