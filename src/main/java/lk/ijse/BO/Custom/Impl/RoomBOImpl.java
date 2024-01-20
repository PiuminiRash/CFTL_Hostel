package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.RoomBO;
import lk.ijse.DAO.Custom.RoomDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.Room;
import lk.ijse.dto.RoomDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    @Override
    public boolean saveRoom(RoomDto dto) throws SQLException, ClassNotFoundException {
        return roomDAO.save(new Room(dto.getRoomNo(), dto.getRoomName(), dto.getNoOfBed()));
    }

    @Override
    public boolean updateRoom(RoomDto dto) throws SQLException, ClassNotFoundException {
        return roomDAO.update(new Room(dto.getRoomNo(), dto.getRoomName(), dto.getNoOfBed()));
    }

    @Override
    public RoomDto searchRoom(String id) throws SQLException, ClassNotFoundException {
        Room room = roomDAO.search(id);
        return new RoomDto(
                room.getRoomNo(),
                room.getRoomName(),
                room.getNoOfBed()
        );
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.delete(id);
    }

    @Override
    public List<RoomDto> getAllRoom() throws SQLException {
        ArrayList<RoomDto> roomDtos = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : rooms) {
            roomDtos.add(new RoomDto(room.getRoomNo(),room.getRoomName(),room.getNoOfBed()));
        }
        return roomDtos;
    }

    @Override
    public List<RoomDto> getIncomplete() throws SQLException {
        return null;
    }
}
