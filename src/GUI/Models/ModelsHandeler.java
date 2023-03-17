package GUI.Models;

import BLL.AdminManager;

public class ModelsHandeler {
    private AdminModel adminModel;

    public ModelsHandeler(){
        adminModel = new AdminModel();
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }
}
