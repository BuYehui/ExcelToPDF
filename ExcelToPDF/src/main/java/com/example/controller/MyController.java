package com.example.controller;

import com.example.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;

public class MyController {
    @FXML
    private TextField pathField;
    @FXML
    private Button selectFile;
    @FXML
    private Button review;
    @FXML
    private Button prePage;
    @FXML
    private Button nextPage;
    @FXML
    private Pane pdfPane;

    @FXML
    private ChoiceBox printDirection;
    @FXML
    private Button print;

    private int index = 1;
    private int totalIndex;
    private String pngPath;

    @FXML
    public void initialize() {
        printDirection.getItems().addAll(
            new Pair<>("01", "纵向"),
            new Pair<>("02", "横向")
        );
        printDirection.setValue("01=纵向");
    }

    @FXML
    private void selectFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("打开Excel目录文件");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel文件", "*.xlsx", "*.xls"));
        File excelFile = fileChooser.showOpenDialog(pdfPane.getScene().getWindow());
        if (excelFile != null) {
            //文件路径反显到界面TextFiled控件中
            pathField.setText(excelFile.toString());
        }
    }

    @FXML
    private void review() throws IOException, InterruptedException {
        System.out.println("Excel转PDF程序启动.....");
        String excelFilePath = pathField.getText();
        File excelFile = new File(excelFilePath);
        String pdfFilePath = excelFile.getParent() + "\\" + excelFile.getName().substring(0 , excelFile.getName().indexOf(".")) + ".pdf";
        File pdfFile = new File(pdfFilePath);
        System.out.println(excelFile + ", " + pdfFilePath);
        //转换
        Utils.fileTransform(excelFile, pdfFile);
        //将PDF渲染成图片
        String result = Utils.pdfTransPic(pdfFile); //返回值：路径 | 总页数
        String[] split = result.split("\\|");
        pngPath = split[0].substring(0, split[0].lastIndexOf("\\") + 1);
        totalIndex = Integer.parseInt(split[1]);
        System.out.println(pngPath);
        page(1);
    }

    @FXML
    private void print() throws IOException {
        Utils.print("");
    }

    /**
     * 上一页
     */
    @FXML
    private void prePage(){
        if(index != 1){
            index--;
            page(index);
        }
    }

    @FXML
    private void nextPage(){
        if(index != totalIndex){
            index++;
            page(index);
        }
    }

    /**
     * 翻到指定页数
     * @param index 页数
     */
    private void page(int index){
        //移除之前的ImageView面板
        pdfPane.getChildren().removeAll();
        String fileName = pngPath + "output_page_" + index + ".png";
        System.out.println("图片路径：" + fileName);
        //图片展示在界面上
        Image image = new Image("file:" + fileName);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(630);
        imageView.setFitHeight(891);
        pdfPane.getChildren().add(imageView);
    }
}
