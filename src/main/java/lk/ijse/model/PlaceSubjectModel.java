package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.PlaceSubjectDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceSubjectModel {
    private SubjectModel subjectModel = new SubjectModel();
    private TeacherModel teacherModel = new TeacherModel();
    private SubjectDetailsModel subjectDetailsModel = new SubjectDetailsModel();

    public boolean placeSubject(PlaceSubjectDto placeSubjectDto) throws SQLException {
        System.out.println(placeSubjectDto);

        String sectionName = placeSubjectDto.getSectionName();
        String bucket = placeSubjectDto.getBucket();
        String subjectCode = placeSubjectDto.getSubjectCode();
        String subjectName = placeSubjectDto.getSubjectName();

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isSubjectSaved = subjectModel.saveSubject(sectionName,bucket,subjectCode,subjectName);
            if (isSubjectSaved) {
                boolean isUpdated = teacherModel.updateTeacher(placeSubjectDto.getAddTeacherTmList());
                if (isUpdated) {
                    boolean isOrderDetailSaved = subjectDetailsModel.saveSubjectDetails(placeSubjectDto.getSubjectCode(),placeSubjectDto.getAddTeacherTmList());
                    if (isOrderDetailSaved) {
                        connection.commit();
                    }
                }
            }
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }
}
