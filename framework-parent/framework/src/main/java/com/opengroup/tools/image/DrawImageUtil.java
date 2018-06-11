/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.opengroup.tools.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 
 * @author jin
 * @version $Id: DrawImageUtil.java, v 0.1 2016年6月28日 上午11:24:04 jin Exp $
 */
public class DrawImageUtil {
    /**
     * 把手机号跟底图做合并，形成图片 
     * 
     * @param tel
     * @param saveFolder
     * @param bgImg
     * @throws Exception
     */
    public static void drawTel(String tel, String saveFolder, String bgImg) throws Exception {
        tel = tel.replace("/", " ").trim();
        File file = new File(saveFolder, tel);
        if (file.exists()) {
            return;
        }
        int length = tel.length() * 17;
        BufferedImage bi = ImageIO.read(new File(bgImg));
        int imgHeight = bi.getHeight();
        int imgWidth = bi.getWidth();
        int minWidth = imgWidth - length;
        int startX = (int) (Math.random() * minWidth);
        bi = bi.getSubimage(startX, 0, length, imgHeight);
        Graphics2D g = (Graphics2D) bi.getGraphics();
        g.setColor(Color.BLACK);
        g.setFont(new Font("宋体", Font.BOLD, 30));
        for (int i = 0; i < tel.length(); i++) {
            if (i % 2 == 0) {
                g.rotate(Math.toRadians(5));
            } else {
                g.rotate(Math.toRadians(-5));
            }
            g.drawString(String.valueOf(tel.charAt(i)), i * 17, 25);

        }
        ImageIO.write(bi, "png", new File(saveFolder));
    }
}
