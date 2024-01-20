package lk.ijse.dto;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.BucketDto;
import lk.ijse.dto.SectionsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectionModel {
    public static List<SectionsDto> getAllSections() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Section";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<SectionsDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new SectionsDto(
                            resultSet.getString(1)
                    )
            );
        }
        return dtoList;
    }

    public static SectionsDto searchSection(String name) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Section WHERE SectionName = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, name);

        ResultSet resultSet = pstm.executeQuery();

        SectionsDto dto = null;

        if(resultSet.next()) {
            dto = new SectionsDto(
                    resultSet.getString(1)
            );
        }
        return dto;
    }
}
