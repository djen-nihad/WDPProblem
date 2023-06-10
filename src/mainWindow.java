import Interface.Welcome;

import javax.swing.*;
import java.io.IOException;

public class mainWindow {

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            try {
                new Welcome();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    
}
