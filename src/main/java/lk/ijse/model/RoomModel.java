package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.BranchDto;
import lk.ijse.dto.RoomDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomModel {
    public static String generateNextRoomNo() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT RoomNo FROM Room ORDER BY RoomNo DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitRoomNo(resultSet.getString(1));
        }
        return splitRoomNo(null);
    }

    private static String splitRoomNo(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("R");

            int id = Integer.parseInt(split[1]);
            id++;
            return "R00" + id;
        } else {
            return "R001";
        }
    }

    public boolean saveRoom(RoomDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO room VALUES(?,?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getRoomNo());
        pstm.setString(2, dto.getRoomName());
        pstm.setInt(3, dto.getNoOfBed());
        pstm.setInt(4, dto.getStudentCount());

        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;
    }

    public boolean updateRoom(RoomDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Room SET RoomName = ?, NoOfBeds = ?, StudentCount = ? WHERE RoomNo = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getRoomName());
        pstm.setInt(2, dto.getNoOfBed());
        pstm.setInt(3, dto.getStudentCount());
        pstm.setString(4, dto.getRoomNo());

        return pstm.executeUpdate() > 0;
    }


    public static boolean deleteRoom(String RoomNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM Room WHERE RoomNo = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, RoomNo);

        return pstm.executeUpdate() > 0;
    }

    public static List<RoomDto> getAllRoom() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Room";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<RoomDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new RoomDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getInt(4)
                    )
            );
        }
        return dtoList;
    }

    public static List<RoomDto> getIncomplete() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Room WHERE NoOfBeds <> StudentCount;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<RoomDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new RoomDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getInt(4)
                    )
            );
        }
        return dtoList;
    }
}
