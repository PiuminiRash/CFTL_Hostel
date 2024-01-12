package lk.ijse.model;

import lk.ijse.DB.DbConnection;
import lk.ijse.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceSubjectModel {
    private SubjectModel subjectModel = new SubjectModel();
    private SectionDetailsModel sectionDetailsModel = new SectionDetailsModel();
    private StaffModel staffModel = new StaffModel();
    private SectionModel sectionModel = new SectionModel();
    private SubjectDetailsModel subjectDetailsModel = new SubjectDetailsModel();

    public boolean placeSubject(PlaceSubjectDto placeSubjectDto) throws SQLException {
//        System.out.println(placeSubjectDto);
//
//        String subjectCode = placeSubjectDto.getSubjectCode();
//        String subjectName = placeSubjectDto.getSubjectName();
//        String bucket = placeSubjectDto.getBucket();
//
//        Connection connection = null;
//        try {
//            connection = DbConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            SubjectDto subjectDto = new SubjectDto(placeSubjectDto.getSubjectCode(), placeSubjectDto.getSubjectName(), placeSubjectDto.getBucket());
//            boolean isSubjectSaved = subjectModel.saveSubject(subjectDto);
//            if (isSubjectSaved) {
//                boolean isSectionUpdate = sectionModel.updateSectionDetails(placeSubjectDto.getSectionTmList());
//                if (isSectionUpdate) {
//                    boolean isStaffUpdated = staffModel.updateStaffDetails(placeSubjectDto.getStaffTmList());
//                    SectionDetailsDto sectionDetailsDto = new SectionDetailsDto(placeSubjectDto.getSubjectCode(), placeSubjectDto.getSectionTmList());
//                    if (isStaffUpdated) {
//                        boolean isSectionDetailsSave = sectionDetailsModel.saveSectionDetails(sectionDetailsDto);
//                        SubjectDetailsDto subjectDetailsDto = new SubjectDetailsDto(placeSubjectDto.getSubjectCode(), placeSubjectDto.getStaffTmList());
//                        if (isSectionDetailsSave) {
//                            boolean isSubjectDetailsSave = subjectDetailsModel.saveSubjectDetails(subjectDetailsDto);
//                            if (isSubjectDetailsSave) {
//                                connection.commit();
//                            }
//                        }
//                    }
//                }
//            }
//            connection.rollback();
//        } finally {
//            connection.setAutoCommit(true);
//        }
//        return true;
//            System.out.println(placeSubjectDto);
//
//            Connection connection = null;
//            try {
//                connection = DbConnection.getInstance().getConnection();
//                connection.setAutoCommit(false);
//
//                String subjectCode = placeSubjectDto.getSubjectCode();
//                String subjectName = placeSubjectDto.getSubjectName();
//                String bucket = placeSubjectDto.getBucket();
//
//                SubjectDto subjectDto = new SubjectDto(subjectCode, subjectName, bucket);
//                boolean isSubjectSaved = subjectModel.saveSubject(subjectDto);
//
//                if (isSubjectSaved) {
//                    boolean isSectionUpdate = sectionModel.updateSectionDetails(placeSubjectDto.getSectionTmList());
//
//                    if (isSectionUpdate) {
//                        boolean isStaffUpdated = staffModel.updateStaffDetails(placeSubjectDto.getStaffTmList());
//                        SectionDetailsDto sectionDetailsDto = new SectionDetailsDto(subjectCode, placeSubjectDto.getSectionTmList());
//
//                        if (isStaffUpdated) {
//                            boolean isSectionDetailsSave = sectionDetailsModel.saveSectionDetails(sectionDetailsDto);
//                            SubjectDetailsDto subjectDetailsDto = new SubjectDetailsDto(subjectCode, placeSubjectDto.getStaffTmList());
//
//                            if (isSectionDetailsSave) {
//                                boolean isSubjectDetailsSave = subjectDetailsModel.saveSubjectDetails(subjectDetailsDto);
//
//                                if (isSubjectDetailsSave) {
//                                    connection.commit();
//                                } else {
//                                    connection.rollback();
//                                }
//                            } else {
//                                connection.rollback();
//                            }
//                        } else {
//                            connection.rollback();
//                        }
//                    } else {
//                        connection.rollback();
//                    }
//                } else {
//                    connection.rollback();
//                }
//            } finally {
//                if (connection != null) {
//                    connection.setAutoCommit(true);
//                    connection.close();
//                }
//            }
            return true;


    }
}
