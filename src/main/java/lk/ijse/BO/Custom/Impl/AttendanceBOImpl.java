package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.AttendanceBO;
import lk.ijse.DAO.Custom.AttendanceDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.Attendance;
import lk.ijse.dto.AttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceBOImpl implements AttendanceBO {
    AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDANCE);
    @Override
    public boolean saveAttendance(List<Attendance> attendanceDtoList) throws SQLException, ClassNotFoundException {
        ArrayList<AttendanceDto> attendanceDtos = new ArrayList<>();
                for ( Attendance attendance : attendanceDtoList) {
                    attendanceDtos.add(new AttendanceDto(attendance.getDate(), attendance.getTeacherId(), attendance.isPresent()));
                }
        return attendanceDAO.save((Attendance) attendanceDtoList);
    }

    @Override
    public List<AttendanceDto> getAttendance(String date) throws SQLException {
        return null;
    }
}
