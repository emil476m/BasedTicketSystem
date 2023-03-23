package GUI.Models;

import java.io.IOException;

public class ModelsHandler {
    private AdminModel adminModel;

    private LoginModel loginModel;

    public ModelsHandler() throws IOException {
        adminModel = new AdminModel();
        loginModel = new LoginModel();
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public LoginModel getLoginModel(){return loginModel;}
}
