package lk.ijse.dto;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.StudentDto;
import lk.ijse.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    public static boolean saveUser(UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO users VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getUserName());
        pstm.setString(2, dto.getPassword());
        pstm.setString(3, dto.getEmail());

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateUser(UserDto userDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE users SET password = ?, email = ? WHERE userName = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, userDto.getPassword());
        pstm.setString(2, userDto.getEmail());
        pstm.setString(3, userDto.getUserName());

        return pstm.executeUpdate() > 0;
    }


    public static List<UserDto> getAllUser() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM users";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<UserDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new UserDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    )
            );
        }
        return dtoList;
    }

    public static List<UserDto> loginUser() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM users ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        UserDto userDto = new UserDto();
        ResultSet resultSet = preparedStatement.executeQuery();
        List<UserDto> userDtoList = new ArrayList<>();
        while (resultSet.next()) {
            userDtoList.add(
                    new UserDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    )
            );
        }

        return userDtoList;
    }

}
