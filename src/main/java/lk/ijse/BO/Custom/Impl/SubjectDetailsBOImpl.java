package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.SubjectDetailsBO;
import lk.ijse.DAO.Custom.SubjectDetailsDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.SubjectDetails;
import lk.ijse.dto.SubjectDetailsDto;

import java.sql.SQLException;
import java.util.List;

public class SubjectDetailsBOImpl implements SubjectDetailsBO {
    SubjectDetailsDAO subjectDetailsDAO = (SubjectDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUBJECTDETAILS);
    @Override
    public boolean saveSubjectDetails(List<SubjectDetailsDto> subjectDetailsDtoList) throws SQLException, ClassNotFoundException {
        for (SubjectDetailsDto subjectDetailsDto : subjectDetailsDtoList) {
            SubjectDetails subjectDetails = new SubjectDetails();
            subjectDetails.setSubjectCode(subjectDetailsDto.getSubjectCode());
            subjectDetails.setStaffId(subjectDetailsDto.getStaffId());

            if (!subjectDetailsDAO.save(subjectDetails)) {
                return false;
            }
        }
        return true;
    }
}
