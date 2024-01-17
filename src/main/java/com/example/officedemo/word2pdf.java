package com.example.officedemo;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: Yang Hengcan
 * @Date: 2024/1/17 21:00
 * @Description:
 */
public class word2pdf {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\tmp\\任务.docx");
        XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
        PdfOptions pdfOptions = PdfOptions.create();
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\tmp\\任务1.pdf");
        PdfConverter.getInstance().convert(xwpfDocument,fileOutputStream,pdfOptions);
        fileInputStream.close();
        fileOutputStream.close();
    }
}
