package com.opengroup.tools.image;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.mortennobel.imagescaling.ResampleOp;

public class ImageUtil {
    /**
     * 对图片进行缩放，并保存到目标文件
     * @param imagePath
     * @param savePath
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public static boolean zoomImage(String imagePath, String savePath, int width, int height)
                                                                                             throws Exception {
        File f = new File(imagePath);
        int idx = imagePath.lastIndexOf(".");
        String format = imagePath.substring(idx + 1);
        BufferedImage bimg = ImageIO.read(f);
        int oheight = bimg.getHeight();
        int owidth = bimg.getWidth();
        double rateH = height * 1.0 / oheight;
        double rateW = width * 1.0 / owidth;
        double rate = rateH > rateW ? rateW : rateH;

        int heightDest = (int) (rate * oheight);
        int widthDest = (int) (rate * owidth);

        ResampleOp resampleOp = new ResampleOp(widthDest, heightDest);
        BufferedImage rescaledTomato = resampleOp.filter(bimg, null);

        return ImageIO.write(rescaledTomato, format, new File(savePath));
    }

    /**
     * 如果缩放之后的图片文件不存在，对图片进行缩放，并保存。
     * @param folder
     * @param imageName
     * @param width
     * @param height
     * @param format
     * @throws Exception
     */
    public static File createImageNotExisted(String folder, String imageName, int width,
                                             int height, String format) throws Exception {
        String destName = imageName + "." + format + "." + width + "X" + height + "." + format;
        File destFile = new File(folder, destName);
        if (!destFile.exists()) {
            String originalFileName = imageName + "." + format;
            if (format.equalsIgnoreCase("jpg") || format.equalsIgnoreCase("png")|| format.equalsIgnoreCase("gif")) {
                File originalFile = new File(folder, originalFileName);
                zoomImage(originalFile.getAbsolutePath(), destFile.getAbsolutePath(), width, height);
            }
        }
        return destFile;
    }
}
