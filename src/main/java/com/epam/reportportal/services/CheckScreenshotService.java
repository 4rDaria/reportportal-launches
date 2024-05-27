package com.epam.reportportal.services;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CheckScreenshotService {

    public static Boolean imagesAreEqual(String expectedImagePath, String actualImagePath) throws IOException {
        Boolean samePixelColors = true;
        File expectedImage = new File(expectedImagePath);
        File actualImage = new File(actualImagePath);

        BufferedImage source = ImageIO.read(actualImage);
        BufferedImage target = ImageIO.read(expectedImage);

        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        for (int x = 0; x < sourceWidth; x++) {
            for (int y = 0; y < sourceHeight; y++) {
                int sourceRgb = source.getRGB(x, y);
                int targetRgb = target.getRGB(x, y);
                if (sourceRgb != targetRgb) {
                    samePixelColors = false;
                    break;
                }
            }
        }
        if (samePixelColors) {
            return true;
        }
        else {
            return false;
        }
    }
}
