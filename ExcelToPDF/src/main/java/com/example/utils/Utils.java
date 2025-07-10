package com.example.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utils {

    public static String pdfTransPic(File pdfFile) {
        // 加载 PDF 文件
        PDDocument document = null;
        int numberOfPages = 0;
        try {
            document = PDDocument.load(pdfFile); // PDF渲染器
            PDFRenderer renderer = new PDFRenderer(document);

            // 渲染每一页为图像，保存为 PNG
            numberOfPages = document.getNumberOfPages();
            for (int page = 0; page < numberOfPages; page++) {
                // 渲染第 page 页为图片，150 DPI（默认）
                BufferedImage image = renderer.renderImageWithDPI(page, 150); // 150 DPI

                // 保存为 PNG 图片
                ImageIO.write(image, "PNG", new File("output_page_" + (page + 1) + ".png"));
            }

            document.close();
            System.out.println("PDF 渲染为图片完成");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File("output_page_1.png").getAbsolutePath() + "|" + numberOfPages;
    }

    /**
     * 转换excelFile为pdfFile
     * @param excelFile excel文件
     * @param pdfFile 生成的pdf文件
     */
    public static void fileTransform(File excelFile, File pdfFile) throws IOException, InterruptedException {
        // 调用 LibreOffice 命令
        System.out.println("ex:" + excelFile.getAbsolutePath());
        System.out.println("pdf:" + pdfFile.getParent());
        //通过命令行将Excel转为PDF文件
        String[] cmd = {
                "soffice",
                "--headless",
                "infilter=\"MS Excel 2007 XML\"",  // 指定过滤器
                "macro:///Standard.Module1.AdjustPageSettings()", // 可选：调用宏调整页面设置
                "--convert-to", "pdf:calc_pdf_Export",
                "--outdir", pdfFile.getParent(),
                excelFile.getAbsolutePath()
        };

        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();

        System.out.println("PDF生成完成");
    }

}
