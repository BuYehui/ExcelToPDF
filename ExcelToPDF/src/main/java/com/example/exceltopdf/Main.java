package com.example.exceltopdf;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Excel转PDF程序启动.....");
        String excelFilePath = "E:\\IDEAWorkspace\\catagoryPrint\\testFile\\干部人事档案目录.xlsx";
        String pdfFilePath = "E:\\IDEAWorkspace\\catagoryPrint\\testFile\\temp.pdf";
//        File excelFile = new File(excelFilePath);
//        File pdfFile = new File(pdfFilePath);
        fileTransform(excelFilePath, pdfFilePath);
    }

    /**
     * 转换excelFile为pdfFile
     * @param excelFile excel文件
     * @param pdfFile 生成的pdf文件
     */
    private static void fileTransform(String excelFile, String pdfFile) throws IOException, InterruptedException {
        // 调用 LibreOffice 命令
        System.out.println("ex:" + excelFile);
        System.out.println("pdf:" + pdfFile);
        String[] cmd = {
                "soffice",
                "--headless",
                "--convert-to", "pdf",
                "--outdir", pdfFile,
                excelFile
        };

        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();

        System.out.println("PDF生成完成");
    }
}
