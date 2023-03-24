package GUI.Models;

import java.io.IOException;

public class ModelsHandler {
    private AdminModel adminModel;
    private EventsModel eventsModel;
    private LoginModel loginModel;

    public ModelsHandler() throws IOException {
        adminModel = new AdminModel();
        loginModel = new LoginModel();
        eventsModel = new EventsModel();
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public LoginModel getLoginModel(){return loginModel;}

    public EventsModel getEventsModel() {
        return eventsModel;
    }
}
