package GUI.Controllers;

import GUI.Models.ModelsHandeler;

public abstract class BaseController {
    private ModelsHandeler modelsHandler;

    public void setModel(ModelsHandeler modelsHandler) {
        this.modelsHandler = modelsHandler;
    }

    public ModelsHandeler getModelsHandler() {
        return modelsHandler;
    }

    public abstract void setup();
}
