package lk.ijse.DAO;

import lk.ijse.DAO.Custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        STUDENT,STAFF,ROOM,GAURDIAN,SECTION,SUBJECT,SECTIONDETAILS,SUBJECTDETAILS,ATTENDANCE,INCOME,EXPENDITURE,PAYMENT,QUERY
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case STUDENT:
                return new StudentDAOImpl();
            case STAFF:
                return new StaffDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case GAURDIAN:
                return new GaurdianDAOImpl();
            case SECTION:
                return new SectionDAOImpl();
            case SUBJECT:
                return new SubjectDAOImpl();
            case SECTIONDETAILS:
                return new SectionDetailsDAOImpl();
            case SUBJECTDETAILS:
                return new SubjectDetailsDAOImpl();
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case INCOME:
                return new IncomeDAOImpl();
            case EXPENDITURE:
                return new ExpenditureDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case QUERY:
                //return new ();
            default:
                return null;
        }
    }
}
