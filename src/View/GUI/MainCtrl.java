package View.GUI;

import javafx.scene.control.Button;

import java.io.IOException;

public class MainCtrl {

    public Button choosePrgButton;

    public void handleChoosePrgButtonClick() throws IOException {
        PrgSelectionCtrl.display();
    }

}
