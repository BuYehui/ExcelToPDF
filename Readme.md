# 介绍

尝试将Excel文件转为PDF文件，带样式。

# JavaFX打印

```java
// 获取默认的打印机
Printer printer = Printer.getDefaultPrinter();

// 创建一个打印作业
PrinterJob printerJob = PrinterJob.createPrinterJob(printer);
// 创建打印页面的布局，确保纸张为 A4 并设置适当的边距
/**
Printer.MarginType.
DEFAULT: 默认的边距
HARDWARE_MINIMUM: 打印机硬件支持的最小边距
EQUAL: 上下左右边距相等
EQUAL_OPPOSITES: 上下边距相等，左右边距相等

PageOrientation.
PORTRAIT（纵向方向）
LANDSCAPE（横向方向）


*/
PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
// 设置打印的内容，这里我们将文本显示在页面上
Text text = new Text("这是打印的内容\n这是第二行\n这是第三行");
// 设置字体大小，确保内容在纸张上显示完整
text.setStyle("-fx-font-size: 14;");
// 打印页面内容
boolean success = printerJob.printPage(pageLayout, text);
// 如果成功打印，则结束作业
if (success) {
    printerJob.endJob();
}

```

