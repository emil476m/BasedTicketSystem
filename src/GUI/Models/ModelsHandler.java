package GUI.Models;

import BE.UserType;

import java.util.List;

public class ModelsHandler {
    private AdminModel adminModel;
    private EventCoordinatorModel eventCoordinatorModel;
    private LoginModel loginModel;
    private TicketModel ticketModel;

    private List<UserType> userTypeList;

    public ModelsHandler() throws Exception {
        adminModel = new AdminModel();
        loginModel = new LoginModel();
        eventCoordinatorModel = new EventCoordinatorModel();
        ticketModel = new TicketModel();
        userTypeList = adminModel.getAllUserTypes();
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public LoginModel getLoginModel(){return loginModel;}

    public EventCoordinatorModel getEventCoordinatorModel() {
        return eventCoordinatorModel;
    }

    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    public TicketModel getTicketModel() {
        return ticketModel;
    }
}
