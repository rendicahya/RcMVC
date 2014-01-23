package net.rendicahya.mvc;

public class FrontLoader {

    protected static void startApp(final Controller controller) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                controller.loadView();
            }
        });
    }
}
