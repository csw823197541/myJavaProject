package poi1;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

/**
 * Created by csw on 2020/03/07.
 * Description:
 */
public class ModifyAndExport {

    public static void main(String[] args) {
        // 设定Excel文件所在路径
        String excelFileName = "E:/excel/李小冬.xls";

        modifyAndExport(excelFileName);
    }

    /**
     * poi实现excel修改
     * 读取excel并且修改部分内容并输出
     *
     * @param fileName
     */
    public static void modifyAndExport(String fileName) {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        HSSFWorkbook workbook = null;
        try {
            // 获取Excel文件
            File excelFile = new File(fileName);
            if (!excelFile.exists()) {
                System.out.println("指定的Excel文件不存在！");
            }
            inputStream = new FileInputStream(excelFile);
            workbook = new HSSFWorkbook(inputStream);
            // 获取第一个sheet进行修改记录
            HSSFSheet sheet = workbook.getSheetAt(0);

            // 修改逻辑
            // 填报单位、填报时间、线索编码、人员编号置为空
            // 单位或事件事故置为否
            // 出生年月时间格式1980/8/1
            // "是否中共党员"如果为：否，则"入党时间"为：""
            // 工作单位及职务，开头一定是"江苏省宿迁市泗洪县"
            // "处置方式"结尾应该是：了结-适当处理，如果不是则在保存的文件名中提示出来
            // 处置时间可参数设置为3月份，则处置时间设为2月26到3月18；受理时间在处置时间2-10天
            // 如果组织措施是诫勉，则不修改处置时间和受理时间
            // 只要处置方式为"初步核实"，"是否与本人核实"只能填是
            // 是否主动投案、是否主动交代问题、是否属于向上级纪委监委报告范围都是填否
            HSSFRow row = sheet.getRow(0);
            HSSFCell cell = row.getCell(0);
            cell.setCellValue("联系人姓名");

            // 将excel文件重新保存
            String exportFileName = fileName.substring(0, fileName.lastIndexOf(".")) + "error" + fileName.substring(fileName.lastIndexOf("."));
            fileOutputStream = new FileOutputStream(new File(exportFileName));
            workbook.write(fileOutputStream);
            workbook.close();
        } catch (Exception e) {
            System.out.println("解析Excel失败，文件名：" + fileName + " 错误信息：" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                System.out.println("关闭数据流出错！错误信息：" + e.getMessage());
            }
        }
    }

}
