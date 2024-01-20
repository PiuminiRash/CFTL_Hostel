package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.SubjectBO;
import lk.ijse.DAO.Custom.SubjectDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.Subject;
import lk.ijse.dto.SubjectDto;

import java.sql.SQLException;
import java.util.List;

public class SubjectBOImpl implements SubjectBO {
    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUBJECT);
    @Override
    public boolean saveSubject(SubjectDto dto) throws SQLException, ClassNotFoundException {
        return subjectDAO.save(new Subject(dto.getSubjectCode(),dto.getSubjectName(),dto.getBucket()));
    }

    @Override
    public String generateNextSubjectCode() throws SQLException {
        return null;
    }

    @Override
    public String splitSubjectCode(String currentOrderId) {
        return null;
    }

    @Override
    public List<SubjectDto> getAllSubject() throws SQLException {
        return null;
    }

    @Override
    public List<SubjectDto> bucketSub(String section, String bucket) throws SQLException {
        return null;
    }

    @Override
    public List<SubjectDto> SearchSub(String section) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteSubject(String SubjectCode) throws SQLException {
        return false;
    }
}
