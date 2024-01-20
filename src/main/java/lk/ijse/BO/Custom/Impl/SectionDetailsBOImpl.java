package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.SectionDetailsBO;
import lk.ijse.DAO.Custom.SectionDetailsDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.SectionDetails;
import lk.ijse.dto.SectionDetailsDto;

import java.sql.SQLException;
import java.util.List;

public class SectionDetailsBOImpl implements SectionDetailsBO {
    SectionDetailsDAO sectionDetailsDAO = (SectionDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SECTIONDETAILS);
    @Override
    public boolean saveSectionDetails(List<SectionDetailsDto> sectionDetailsDtoList) throws SQLException, ClassNotFoundException {
        for (SectionDetailsDto sectionDetailsDto : sectionDetailsDtoList) {
            SectionDetails sectionDetails = new SectionDetails();
            sectionDetails.setSectionName(sectionDetailsDto.getSectionName());
            sectionDetails.setSubjectCode(sectionDetailsDto.getSubjectCode());

            if (!sectionDetailsDAO.save(sectionDetails)) {
                return false;
            }
        }
        return true;
    }
}
