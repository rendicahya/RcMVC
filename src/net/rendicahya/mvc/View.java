package net.rendicahya.mvc;

import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import static javax.swing.JOptionPane.*;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import net.infonode.gui.laf.InfoNodeLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceAutumnLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceChallengerDeepLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceDustCoffeeLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceEmeraldDuskLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceModerateLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlack2007LookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeSilver2007LookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceTwilightLookAndFeel;

public abstract class View<C extends Controller> extends JFrame {

    protected C controller;
    private final HashMap<String, LookAndFeel> lafs = new HashMap<>();

    {
        lafs.put("Nimbus", new NimbusLookAndFeel());
        lafs.put("Windows", new WindowsLookAndFeel());
        lafs.put("Windows Classic", new WindowsClassicLookAndFeel());
        lafs.put("Info Node", new InfoNodeLookAndFeel());
        lafs.put("Autumn", new SubstanceAutumnLookAndFeel());
        lafs.put("Business Black Steel", new SubstanceBusinessBlackSteelLookAndFeel());
        lafs.put("Business Blue Steel", new SubstanceBusinessBlueSteelLookAndFeel());
        lafs.put("Business", new SubstanceBusinessLookAndFeel());
        lafs.put("Challenger Deep", new SubstanceChallengerDeepLookAndFeel());
        lafs.put("Creme", new SubstanceCremeLookAndFeel());
        lafs.put("Creme Coffee", new SubstanceCremeCoffeeLookAndFeel());
        lafs.put("Dust", new SubstanceDustLookAndFeel());
        lafs.put("Dust Coffee", new SubstanceDustCoffeeLookAndFeel());
        lafs.put("Emerald Dusk", new SubstanceEmeraldDuskLookAndFeel());
        lafs.put("Gemini", new SubstanceGeminiLookAndFeel());
        lafs.put("Graphite", new SubstanceGraphiteLookAndFeel());
        lafs.put("Graphite Aqua", new SubstanceGraphiteAquaLookAndFeel());
        lafs.put("Graphite Glass", new SubstanceGraphiteGlassLookAndFeel());
        lafs.put("Magellan", new SubstanceMagellanLookAndFeel());
        lafs.put("Mariner", new SubstanceMarinerLookAndFeel());
        lafs.put("Mist Aqua", new SubstanceMistAquaLookAndFeel());
        lafs.put("Mist Silver", new SubstanceMistSilverLookAndFeel());
        lafs.put("Moderate", new SubstanceModerateLookAndFeel());
        lafs.put("Nebula", new SubstanceNebulaLookAndFeel());
        lafs.put("Nebula Brick Wall", new SubstanceNebulaBrickWallLookAndFeel());
        lafs.put("Office Black 2007", new SubstanceOfficeBlack2007LookAndFeel());
        lafs.put("Office Blue 2007", new SubstanceOfficeBlue2007LookAndFeel());
        lafs.put("Office Silver 2007", new SubstanceOfficeSilver2007LookAndFeel());
        lafs.put("Raven", new SubstanceRavenLookAndFeel());
        lafs.put("Sahara", new SubstanceSaharaLookAndFeel());
        lafs.put("Twilight", new SubstanceTwilightLookAndFeel());
    }

    protected View(final C controller) {
        this.controller = controller;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.quitApp();
            }
        });
    }

    protected void doInBackground(final Runnable runnable) {
        new SwingWorker() {
            @Override
            protected Object doInBackground() {
                runnable.run();

                return null;
            }
        }.execute();
    }

    /**
     * Should be called after initComponents()
     */
    protected void centerFrame() {
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width - getSize().width) / 2, (dim.height - getSize().height) / 2 - 10);
    }

    protected void maximizeFrame() {
        setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * Should be called before initComponents()
     *
     * @param lafName
     */
    protected void setLaf(String lafName) {
        try {
            UIManager.setLookAndFeel(lafs.get(lafName));
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException e) {
        }
    }

    protected void setAppIcon(String iconPath) {
        try {
            setIconImage(ImageIO.read(getClass().getResourceAsStream(iconPath)));
        } catch (IOException e) {
        }
    }

    public void alert(String message) {
        showMessageDialog(getContentPane(), message, getTitle(), INFORMATION_MESSAGE);
    }

    public boolean confirm(String message) {
        return showConfirmDialog(getContentPane(), message, getTitle(), YES_NO_OPTION) == OK_OPTION;
    }

    public String prompt(String message) {
        return showInputDialog(getContentPane(), message, getTitle(), QUESTION_MESSAGE);
    }
}
