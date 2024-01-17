package com.example.officedemo;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: Yang Hengcan
 * @Date: 2024/1/17 21:19
 * @Description:
 */
public class Test02 {

    public static void main(String[] args) {

        //读取word文档
        XWPFDocument document = null;
        try (InputStream in = Files.newInputStream(Paths.get("E:\\tmp\\任务1.doc"))) {
            document = new XWPFDocument(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将word转成pdf
        PdfOptions options = PdfOptions.create();
        try {
            // 创建临时文件
            File tempFile = File.createTempFile("yhc2023", ".pdf");

            // 将PDF写入临时文件
            try (OutputStream outPdf = Files.newOutputStream(tempFile.toPath())) {
                PdfConverter.getInstance().convert(document, outPdf, options);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            // TODO: 将临时文件上传到服务器

            // 上传完成后，删除临时文件
            tempFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
