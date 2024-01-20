package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.GaurdianBO;
import lk.ijse.DAO.Custom.GaurdianDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.Gaurdian;
import lk.ijse.dto.GardianDto;

import java.security.Guard;
import java.sql.SQLException;

public class GaurdianBOImpl implements GaurdianBO {
    GaurdianDAO gaurdianDAO = (GaurdianDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.GAURDIAN);
    @Override
    public boolean saveGardian(GardianDto dto) throws SQLException, ClassNotFoundException {
        return gaurdianDAO.save(new Gaurdian(dto.getStudentId(), dto.getGardianName(), dto.getEmail(), dto.getContactNo()));
    }

    @Override
    public boolean updateGardian(GardianDto dto) throws SQLException, ClassNotFoundException {
        return gaurdianDAO.update(new Gaurdian(dto.getStudentId(), dto.getGardianName(), dto.getEmail(), dto.getContactNo()));
    }

    @Override
    public GardianDto searchGuardian(String id) throws SQLException, ClassNotFoundException {
        Gaurdian gaurdian = gaurdianDAO.search(id);
        return new GardianDto(
                gaurdian.getStudentId(),
                gaurdian.getGardianName(),
                gaurdian.getEmail(),
                gaurdian.getContactNo()
        );
    }
}
