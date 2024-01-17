package com.example.officedemo;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @Author: Yang Hengcan
 * @Date: 2024/1/17 21:05
 * @Description:
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("E:\\tmp\\任务1.doc");
        XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
// 将word中字体转化为宋体，防止其他字体无法正常显示
        setFontType(xwpfDocument);
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\tmp\\任务2.pdf");
        PdfOptions pdfOptions = PdfOptions.create();
        PdfConverter.getInstance().convert(xwpfDocument, fileOutputStream, pdfOptions);
        fileInputStream.close();
        fileOutputStream.close();
    }

    /***
     * 将文档的文字设置为宋体 防止其他字体转pdf不显示
     * @Description:
     * @param xwpfDocument
     * @return:
     */
    private static void setFontType(XWPFDocument xwpfDocument) {
        //转换文档中文字字体
        List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();
        if (paragraphs != null && paragraphs.size() > 0) {
            for (XWPFParagraph paragraph : paragraphs) {
                List<XWPFRun> runs = paragraph.getRuns();
                if (runs != null && runs.size() > 0) {
                    for (XWPFRun run : runs) {
                        run.setFontFamily("宋体");
                    }
                }
            }
        }
        //转换表格里的字体 我也不想俄罗斯套娃但是不套真不能设置字体
        List<XWPFTable> tables = xwpfDocument.getTables();
        for (XWPFTable table : tables) {
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> tableCells = row.getTableCells();
                for (XWPFTableCell tableCell : tableCells) {
                    List<XWPFParagraph> paragraphs1 = tableCell.getParagraphs();
                    for (XWPFParagraph xwpfParagraph : paragraphs1) {
                        List<XWPFRun> runs = xwpfParagraph.getRuns();
                        for (XWPFRun run : runs) {
                            run.setFontFamily("宋体");
                        }
                    }
                }
            }
        }
    }
}