package poi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author lyt
 * 2017年7月19日
 */
public class poiexe {

    public static void main(String[] args) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("导出单");
        // 为文件添加密码，设置文件只读
//        sheet.protectSheet(UUID.randomUUID().toString());
        // 标题，在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        // 设置单元格内容
        cell.setCellValue("E00000001--物料导出表--李三");
        // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
        // 标题字体
        HSSFFont titleFont = wb.createFont();
        titleFont.setFontName("宋体");
        titleFont.setBold(true); // 粗体显示
        titleFont.setFontHeightInPoints((short) 16);
        // 标题样式
        HSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFont(titleFont); // 调用字体样式对象
        titleStyle.setWrapText(false);
        // 设置样式
        cell.setCellStyle(titleStyle);

        // 表头，在sheet里创建第二行
        HSSFRow row2 = sheet.createRow(1);
        // 创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("库房名称");
        row2.createCell(1).setCellValue("物料名称");
        row2.createCell(2).setCellValue("规格/品牌/型号");
        row2.createCell(3).setCellValue("单位");
        row2.createCell(4).setCellValue("单价");
        row2.createCell(5).setCellValue("数量");
        row2.createCell(6).setCellValue("价格总计");
        row2.createCell(7).setCellValue("采购人");
        row2.createCell(8).setCellValue("导出日期");
        // 表头字体
        HSSFFont headFont = wb.createFont();
        headFont.setFontName("宋体");
        headFont.setBold(true); // 粗体显示
        headFont.setFontHeightInPoints((short) 12);
        // 标题样式
        HSSFCellStyle headStyle = wb.createCellStyle();
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headStyle.setFont(headFont); // 调用字体样式对象
        headStyle.setWrapText(false);
        headStyle.setBorderBottom(BorderStyle.THIN); //下边框
        headStyle.setBorderLeft(BorderStyle.THIN);//左边框
        headStyle.setBorderTop(BorderStyle.THIN);//上边框
        headStyle.setBorderRight(BorderStyle.THIN);//右边框
        // 设置样式
        Integer[] width = new Integer[]{12, 12, 17, 5, 5, 5, 11, 8, 11};
        for (int i = 0; i < 9; i++) {
            row2.getCell(i).setCellStyle(headStyle);
            // 设置列宽
            sheet.setColumnWidth(i, width[i] * 256);
            row2.getCell(i).setCellStyle(headStyle);
        }

        // 在sheet里创建第三行
        int n = 2 + 10;
        for (int i = 2; i < n; i++) {
            HSSFRow row3 = sheet.createRow(i);
            row3.createCell(0).setCellValue("钳工");
            row3.createCell(1).setCellValue("虎口钳");
            row3.createCell(2).setCellValue(87);
            row3.createCell(3).setCellValue(87);
            row3.createCell(4).setCellValue(87);
            row3.createCell(5).setCellValue(87);
            row3.createCell(6).setCellValue(87);
            row3.createCell(7).setCellValue(87);
            row3.createCell(8).setCellValue("2018/11/8");
        }
        // 合计行
        HSSFRow row4 = sheet.createRow(n);
        HSSFCell cel41 = row4.createCell(0);
        cel41.setCellValue("合计");
        HSSFCell cel42 = row4.createCell(6);
        cel42.setCellValue("295");
        row4.createCell(1);
        row4.createCell(2);
        row4.createCell(3);
        row4.createCell(4);
        row4.createCell(5);
        row4.createCell(7);
        row4.createCell(8);
        sheet.addMergedRegion(new CellRangeAddress(n, n, 0, 5));
        // 设置内容样式
        HSSFFont cellFont = wb.createFont();
        cellFont.setFontName("宋体");
        cellFont.setBold(false); // 粗体显示
        cellFont.setFontHeightInPoints((short) 12);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(false);
        cellStyle.setFont(cellFont);
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 9; j++) {
                sheet.getRow(i).getCell(j).setCellStyle(cellStyle);
            }
        }

        // 签字第一行
        n++;
        sheet.createRow(n);
        n++;
        HSSFRow row5 = sheet.createRow(n);
        row5.createCell(0).setCellValue("管理员签字：");
        sheet.addMergedRegion(new CellRangeAddress(n, n, 0, 1));
        row5.createCell(3).setCellValue("采购部门主任签字：");
        sheet.addMergedRegion(new CellRangeAddress(n, n, 3, 6));
        // 签字第二行
        n++;
        sheet.createRow(n);
        n++;
        HSSFRow row6 = sheet.createRow(n);
        row6.createCell(0).setCellValue("实训中心主任签字：");
        sheet.addMergedRegion(new CellRangeAddress(n, n, 0, 1));
        row6.createCell(3).setCellValue("分管校长签字：");
        sheet.addMergedRegion(new CellRangeAddress(n, n, 3, 6));
        // 设置内容样式
        HSSFFont cellFont1 = wb.createFont();
        cellFont1.setFontName("宋体");
        cellFont1.setBold(false); // 粗体显示
        cellFont1.setFontHeightInPoints((short) 14);
        HSSFCellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle1.setWrapText(false);
        cellStyle1.setFont(cellFont1);
        row5.getCell(0).setCellStyle(cellStyle1);
        row5.getCell(3).setCellStyle(cellStyle1);
        row6.getCell(0).setCellStyle(cellStyle1);
        row6.getCell(3).setCellStyle(cellStyle1);

        // 设置行高
        for (int i = 1; i < n; i++) {
            sheet.getRow(i).setHeightInPoints(15);
        }


        String imgPath = "D://水印.png";
        ImageUtils.createWaterMark("山东省民族中等专业学校", imgPath);

        ExcelWaterRemarkUtils.putWaterRemarkToExcel(wb, sheet, imgPath, 0, 0, 0, 12, 1, 2, 0, 0);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        wb.close();
        byte[] content = os.toByteArray();

        File file1 = new File("D://导出单.xls");// Excel文件生成后存储的位置。
        OutputStream fos;
        try {
            fos = new FileOutputStream(file1);
            fos.write(content);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
