package lk.ijse.BO;

import lk.ijse.BO.Custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBOFactory(){
        return (boFactory==null)?boFactory=new BOFactory():boFactory;
    }
    public enum BOTypes{
        STUDENT,STAFF,ROOM,GAURDIAN,SECTION,SUBJECT,SECTIONDETAILS,SUBJECTDETAILS,ATTENDANCE,INCOME,EXPENDITURE,PAYMENT
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case STUDENT:
                return new StudentBOImpl();
            case STAFF:
                return new StaffBOImpl();
            case ROOM:
               return new RoomBOImpl();
            case GAURDIAN:
                return new GaurdianBOImpl();
            case SECTION:
                return new SectionBOImpl();
            case SUBJECT:
                return new SubjectBOImpl();
            case SECTIONDETAILS:
                return new SectionDetailsBOImpl();
            case SUBJECTDETAILS:
                return new SubjectDetailsBOImpl();
            case ATTENDANCE:
                return new AttendanceBOImpl();
            case INCOME:
                return new IncomeBOImpl();
            case EXPENDITURE:
                return new ExpenditureBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            default:
                return null;
        }
    }
}
