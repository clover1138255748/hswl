package com.opengroup.tools.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.opengroup.exception.BaseFrameworkException;
import com.opengroup.tools.date.DateFormatterEnum;
import com.opengroup.tools.date.DateUtil;
import com.opengroup.tools.io.IOUtil;
import com.opengroup.tools.random.UniqueIdGenerator;

/**
 * http协议下的文件下载类
 * @author ruimin.jrm
 *
 */
public class FileDownloadUtil {

    private static Log logger = LogFactory.getLog(FileDownloadUtil.class);

    /**
     * 文件下载
     * 
     * @param file
     * @param response
     * @param destName
     * @throws IOException
     */
    public static void downloadFile(File file, HttpServletResponse response, String destName)
                                                                                             throws IOException {
        if (file.exists()) {
            FileInputStream fin = null;
            try {
                fin = new FileInputStream(file);
                int size = fin.available();
                response.setContentType("application/x-msdownload");
                response.setContentLength(size);
                response.setHeader("Content-Disposition", "attachment;filename=" + destName);
                byte[] buffer = new byte[size];
                fin.read(buffer);
                response.getOutputStream().write(buffer);
            } catch (Exception e) {
                logger.error("error", e);
            } finally {
                if (fin != null)
                    fin.close();
            }
        }
    }

    /**
     * 文件下载
     * 
     * @param filePath
     * @param response
     * @param destName
     * @throws IOException
     */
    public static void downloadFile(String filePath, HttpServletResponse response, String destName)
                                                                                                   throws IOException {
        File file = new File(filePath);
        downloadFile(file, response, destName);
    }

    public static String uploadFileBytes(byte[] fileBytes, File destFolder, String format) {
        try {
            String fileName = UniqueIdGenerator.generateRandomStr(20) + "." + format;
            File destFile = new File(destFolder, fileName);
            if (!destFolder.exists()) {
                destFolder.mkdirs();
            }
            IOUtil.writeBytesToFile(fileBytes, destFile);
            return fileName;
        } catch (IOException e) {
            throw new BaseFrameworkException(e);
        }
    }

    /**
     * 文传上传并保存
     * 
     * @param file
     * @param destFolder
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, File destFolder) throws IOException {
        String originalName = file.getOriginalFilename();
        int idx = originalName.lastIndexOf(".");
        String format = originalName.substring(idx + 1);
        String fileName = UniqueIdGenerator.generateRandomStr(20) + "." + format;
        File destFile = new File(destFolder, fileName);
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }
        file.transferTo(destFile);
        return fileName;
    }

    /**
     * 文传上传并保存
     * 
     * @param file
     * @param destFolder
     * @throws IOException
     */
    public static String uploadFileWithoutChangeName(MultipartFile file, File destFolder)
                                                                                         throws IOException {
        String originalName = file.getOriginalFilename();
        //        int idx = originalName.lastIndexOf(".");
        //        String format = originalName.substring(idx + 1);
        String fileName = originalName;
        File destFile = new File(destFolder, fileName);
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }
        file.transferTo(destFile);
        return fileName;
    }

    /**
     *  取得日期格式的文件路径
     * 
     * @param rootPath
     * @return
     */
    public static File getDateFolder(String rootPath) {
        String folderName = DateUtil.format(new Date(),
            DateFormatterEnum.SIMPLE_WITHOUT_SEP.getCode());
        File destFolder = new File(rootPath, folderName);
        return destFolder;
    }
}
