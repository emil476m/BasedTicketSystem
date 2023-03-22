package GUI.Models;

import java.io.IOException;

public class ModelsHandler {
    private AdminModel adminModel;

    public ModelsHandler() throws IOException {
        adminModel = new AdminModel();
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }
}
