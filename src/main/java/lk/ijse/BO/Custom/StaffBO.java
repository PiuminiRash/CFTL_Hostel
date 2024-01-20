package lk.ijse.BO.Custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.Entity.Staff;
import lk.ijse.dto.StaffDto;

import java.sql.SQLException;
import java.util.List;

public interface StaffBO extends SuperBO {
    boolean saveStaff(StaffDto dto) throws SQLException, ClassNotFoundException;
    boolean updateStaff(StaffDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteStaff(String id) throws SQLException, ClassNotFoundException;
    List<StaffDto> getAllStaffs() throws SQLException;
    List<StaffDto> getAllStaffType(String type) throws SQLException;
    Staff searchStaff(String id) throws SQLException, ClassNotFoundException;
    List<StaffDto> getAllStaffTypes(String type) throws SQLException;
    List<StaffDto> getAllStaff() throws SQLException;
}
