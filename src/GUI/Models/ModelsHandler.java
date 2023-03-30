package GUI.Models;

import java.io.IOException;

public class ModelsHandler {
    private AdminModel adminModel;
    private EventCoordinatorModel eventCoordinatorModel;
    private LoginModel loginModel;

    public ModelsHandler() throws IOException {
        adminModel = new AdminModel();
        loginModel = new LoginModel();
        eventCoordinatorModel = new EventCoordinatorModel();
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public LoginModel getLoginModel(){return loginModel;}

    public EventCoordinatorModel getEventCoordinatorModel() {
        return eventCoordinatorModel;
    }
}
