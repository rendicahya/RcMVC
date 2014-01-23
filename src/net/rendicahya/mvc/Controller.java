package net.rendicahya.mvc;

public class Controller<V extends View, M extends Model> {

    public static final String SUN_JAVA_COMMAND = "sun.java.command";
    protected V view;
    protected M model;
    private boolean confirmOnExit;
    private Runnable beforeQuit;

    public void loadView() {
        view.setVisible(true);
    }

    public void confirmOnQuit(boolean confirmOnExit) {
        this.confirmOnExit = confirmOnExit;
    }

    public void beforeQuit(Runnable action) {
        beforeQuit = action;
    }

    public void quitApp() {
        if (confirmOnExit && !view.confirm("Quit?")) {
            return;
        }

        if (beforeQuit != null) {
            beforeQuit.run();
        }

        System.exit(0);
    }
}