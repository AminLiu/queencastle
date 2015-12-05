package com.queencastle.dao.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils {
    private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);

    /**
     * 缩放图像（按高度和宽度缩放）
     * 
     * @param imageByte
     * @param height 缩放后的高度
     * @param width 缩放后的宽度
     */
    public final static BufferedImage reSize(byte[] imageByte, int height, int width) {
        double ratio = 0.0; // 缩放比例
        BufferedImage bi = null;

        try {
            bi = ImageIO.read(new ByteArrayInputStream(imageByte));

            Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue() / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op =
                        new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            return toBufferedImage(itemp);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    /**
     * 缩放图像（按高度和宽度缩放）
     * 
     * @param imageByte
     * @param height 缩放后的高度
     * @param width 缩放后的宽度
     */
    public final static byte[] reSize(byte[] imageByte, int height, int width, String suffix) {
        double ratio = 0.0; // 缩放比例
        byte[] newImageByte = null;
        BufferedImage bi = null;

        try {
            bi = ImageIO.read(new ByteArrayInputStream(imageByte));

            Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue() / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op =
                        new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // http://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
            itemp = toBufferedImage(itemp);
            ImageIO.write((BufferedImage) itemp, suffix, out);
            newImageByte = out.toByteArray();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return newImageByte;
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage =
                new BufferedImage(img.getWidth(null), img.getHeight(null),
                        BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public static byte[] fillRectanglePic(BufferedImage smallImg, File bigImg, int leftX,
            int leftY, int width, int height) throws IOException {
        BufferedImage big = ImageIO.read(bigImg);
        Graphics g = big.getGraphics();
        g.drawImage(smallImg, leftX, leftY, width, height, null);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();

        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(big, "jpg", imOut);
        return bs.toByteArray();

    }


}
