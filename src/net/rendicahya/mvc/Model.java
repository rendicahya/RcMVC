package net.rendicahya.mvc;

import ij.IJ;
import ij.ImagePlus;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;

public class Model<C extends Controller> {

    protected C controller;
    private PropertiesConfiguration properties;

    protected Model(C controller) {
        this.controller = controller;
    }

    public boolean setPropertiesFilePath(String path) {
        try {
            properties = new PropertiesConfiguration(path);
        } catch (ConfigurationException ex) {
            return false;
        }

        properties.setAutoSave(true);
        File file = properties.getFile();

        return properties != null && file != null && file.exists() && file.canRead() && file.canWrite();
    }

    protected String readFile(File file) {
        try {
            return FileUtils.readFileToString(file);
        } catch (IOException ex) {
            System.err.println("Error reading file");
            return null;
        }
    }

    protected String readFile(String path) {
        return readFile(new File(path));
    }

    public BufferedImage loadImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException ex) {
            return null;
        }
    }

    public BufferedImage loadImage(String path) {
        return loadImage(new File(path));
    }

    public ImagePlus loadImagePlus(File file) {
        return loadImagePlus(file.getAbsolutePath());
    }

    public ImagePlus loadImagePlus(String path) {
        return IJ.openImage(path);
    }

    public PropertiesConfiguration getProperties() {
        return properties;
    }
}
