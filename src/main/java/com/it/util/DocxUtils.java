package com.it.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DocxUtils {
    public static void main(String[] args) {
        String filePath = "E:\\idea\\ArticleManage\\src\\main\\webapp\\docx\\白衣素爱.docx";
        String suffix = filePath.substring(filePath.lastIndexOf("."));
        if (".docx".equals(suffix)) {
            System.out.println("文件类型是.docx");
            System.out.println(dealDocXFile(filePath));
        }
    }

    public static String dealDocXFile(String filePath) {

        InputStream input = null;
        String text="";
        try {
            //实例化解析docx文档的对象
            input = new FileInputStream(filePath);
            XWPFDocument xd = new XWPFDocument(input);

            //1   getAllPackagePictures()此包装中的所有图片
            List<XWPFPictureData> xwpfPictureDataList2 = xd.getAllPictures();
            if (CollUtil.isNotEmpty(xwpfPictureDataList2)) {
                for (XWPFPictureData xwpfPictureData : xwpfPictureDataList2) {
                    //图像的文件名
                    System.out.println("图片名称：" + xwpfPictureData.getFileName());
                }
            }

            //文本的段落
            List<XWPFParagraph> xwpfParagraphList = xd.getParagraphs();
            text+=dealParagraph(xwpfParagraphList);


                input.close();
            } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return text;
    }


    public static String dealParagraph(List<XWPFParagraph> xwpfParagraphListList) {
        String text = "";
        if (CollUtil.isNotEmpty(xwpfParagraphListList)) {
            for (XWPFParagraph xwpfParagraph : xwpfParagraphListList) {
                if (ObjectUtil.isNull(xwpfParagraph) || StrUtil.isEmpty(xwpfParagraph.getText())) {
                    continue;
                }
                text += xwpfParagraph.getParagraphText();
            }


        }
        return text;
    }

    public static void dealTable(List<XWPFTable> xwpfTableList) {
        if (CollUtil.isNotEmpty(xwpfTableList)) {
            for (XWPFTable xwpfTable : xwpfTableList) {
                //遍历行
                for (XWPFTableRow xwpfTableRow : xwpfTable.getRows()) {
                    //遍历单元格
                    for (XWPFTableCell xwpfTableCell : xwpfTableRow.getTableCells()) {
                        //处理段落
                        dealParagraph(xwpfTableCell.getParagraphs());
                        dealTable(xwpfTableCell.getTables());
                    }
                }
            }
        }
    }

}
