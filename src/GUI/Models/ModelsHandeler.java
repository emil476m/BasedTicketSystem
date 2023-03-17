package GUI.Models;

import BLL.AdminManager;

import java.io.IOException;

public class ModelsHandeler {
    private AdminModel adminModel;

    public ModelsHandeler() throws IOException {
        adminModel = new AdminModel();
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }
}
