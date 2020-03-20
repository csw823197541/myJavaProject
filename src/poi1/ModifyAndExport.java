package poi1;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by csw on 2020/03/07.
 * Description:
 */
public class ModifyAndExport {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    private static final String birthdayDateError = "_出生日期错误"; // 出生日期错误

    private static final String workError = "_工作单位错误"; // 工作单位错误

    private static final String cError = "_处置方式错误"; // 处置方式错误

    public static void main(String[] args) {
        int year = 2020;
        int month = 3;

        String source = "E:/excel"; // 设定Excel文件所在路径
        String target = "E:/excel/result/"; // 设定结果文件保存路径

        readFileFromDirection(source, target, year, month);
    }

    private static void readFileFromDirection(String source, String target, int year, int month) {
        File file = new File(source);
        File[] files = file.listFiles();
        if (files == null) {
            System.out.println(source + " 目录不存在或它不是一个目录");
        } else {
            for (File file1 : files) {
                if (!file1.isDirectory()) {
                    modifyAndExport(file1, target, year, month);
                }
            }
        }
    }

    /**
     * poi实现excel修改，读取excel并且修改部分内容并输出
     *
     * @param file
     * @param target
     * @param year
     * @param month
     */
    private static void modifyAndExport(File file, String target, int year, int month) {

        int startYear = year; // 指定随机日期年份
        int startMonth = month - 1;
        if (month == 1) {
            startYear = year - 1;
            startMonth = 12;
        }

        String error = "_";
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        HSSFWorkbook workbook = null;
        try {
            // 获取Excel文件
            if (!file.exists()) {
                System.out.println("指定的Excel文件不存在！");
            }
            inputStream = new FileInputStream(file);
            workbook = new HSSFWorkbook(inputStream);
            // 获取第一个sheet进行修改记录
            HSSFSheet sheet = workbook.getSheetAt(0);

            // A,B,C,D,E,F,G,H,I,J, K, L, M, N, O
            // 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14

            // 1、填报单位、填报日期、线索编码、人员编号置为空
            HSSFRow row_1 = sheet.getRow(1);
            Cell cell_1_3 = row_1.getCell(3);
            cell_1_3.setCellValue(""); // 填报单位
            Cell cell_1_11 = row_1.getCell(11);
            cell_1_11.setCellValue(""); // 填报日期
            HSSFRow row_2 = sheet.getRow(2);
            Cell cell_2_3 = row_2.getCell(3);
            cell_2_3.setCellValue(""); // 线索编码
            Cell cell_2_10 = row_2.getCell(10);
            cell_2_10.setCellValue(""); // 人员编号

            // 2、单位或事件事故置为否
            Cell cell_2_13 = row_2.getCell(13);
            cell_2_13.setCellValue("否");

            // 3、出生年月时间格式1980/8/1
            HSSFRow row_3 = sheet.getRow(3);
            Cell cell_3_10 = row_3.getCell(10);
            try {
                if (cell_3_10.getDateCellValue() != null) {
                    cell_3_10.setCellValue(sdf.format(cell_3_10.getDateCellValue()));
                }
            } catch (Exception e) {
                error = birthdayDateError;
                e.printStackTrace();
            }

            // 4、"是否中共党员"如果为：否，则"入党时间"为：""
            HSSFRow row_4 = sheet.getRow(4);
            Cell cell_4_4 = row_4.getCell(4);
            if ("否".equals(getCellValue(cell_4_4).trim())) {
                Cell cell_4_11 = row_4.getCell(11);
                cell_4_11.setCellValue("");
            }

            // 5、工作单位及职务，开头一定是"江苏省宿迁市泗洪县"
            HSSFRow row_6 = sheet.getRow(6);
            Cell cell_6_4 = row_6.getCell(4);
            try {
                if (cell_6_4.getStringCellValue() != null) {
                    String value = getConvertValue(cell_6_4.getStringCellValue());
                    cell_6_4.setCellValue(value);
                }
            } catch (Exception e) {
                error = workError;
                e.printStackTrace();
            }

            Map<Integer, Cell> cellMap = new LinkedHashMap<>();
            boolean flag = false;
            for (Row row : sheet) {
                if (row.getLastCellNum() >= 14) { // 有14列
                    if ("处置时间".equals(getCellValue(row.getCell(0)).trim())) {
                        if (getCellValue(row.getCell(9)).trim().length() > 0) { // 有处置方式，才能改变时间
                            cellMap.put(row.getRowNum(), row.getCell(2));
                            flag = true;
                        }
                    } else {
                        if (flag) {
                            break;
                        }
                    }
                }
            }

            // 6、如果组织措施是诫勉，则不修改处置时间和受理时间
            int zuZhiCuoShiRowNum = getZuZhiCuoShiRowNum(sheet);
            Cell cell_z_1 = sheet.getRow(zuZhiCuoShiRowNum).getCell(1);
            if (!"诫勉".equals(getCellValue(cell_z_1))) {
                // 7、处置时间设为参数，如：为3月份，则处置时间设为2月26到3月18（随机）；受理时间在处置时间前2-10天（随机）
                if (cellMap.size() > 0) {
                    // 设置处置时间
                    int n = 22 / cellMap.size();
                    int s = 26;
                    for (Map.Entry<Integer, Cell> entry : cellMap.entrySet()) {
                        int e = s + n;
                        e = e > 28 ? e - 28 : e;
                        entry.getValue().setCellValue(sdf.format(getRandomDate(startYear, year, startMonth, month, s, e)));
                        s = e;
                    }
                    // 设置受理时间
                    for (Row row : sheet) {
                        if (row.getLastCellNum() >= 14) {
                            if ("受理时间".equals(getCellValue(row.getCell(10)).trim())) {
                                row.getCell(11).setCellValue(sdf.format(getRandomDate(year, year, startMonth, startMonth, 18, 26)));
                                break;
                            }
                        }
                    }
                }
            }

            // 9、"处置方式"结尾应该是：了结-适当处理，如果不是则在保存的文件名中提示出来
            int i = 0;
            for (Map.Entry<Integer, Cell> entry : cellMap.entrySet()) {
                // 8、只要"处置方式"含有"初步核实"，"是否与本人核实"只能填"是"
                if ("初步核实".equals(getCellValue(sheet.getRow(entry.getKey()).getCell(9)).trim())) {
                    sheet.getRow(zuZhiCuoShiRowNum).getCell(11).setCellValue("是");
                }
                if (i == cellMap.size() - 1) {
                    if (!"了结".equals(getCellValue(sheet.getRow(entry.getKey()).getCell(9)).trim())) {
                        error = cError;
                    }
                }
                i = i + 1;
            }

            // 10、是否主动投案、是否主动交代问题、是否属于向上级纪委监委报告范围都是填否
            for (Row row : sheet) {
                if (row.getLastCellNum() >= 14) { // 有14列
                    if ("是否主动投案".equals(getCellValue(row.getCell(12)).trim())) {
                        row.getCell(14).setCellValue("否");
                    }
                    if ("是否主动交代问题".equals(getCellValue(row.getCell(0)).trim())) {
                        row.getCell(4).setCellValue("否");
                    }
                    if ("是否属于向上级纪委监委报告范围".equals(getCellValue(row.getCell(6)).trim())) {
                        row.getCell(14).setCellValue("否");
                    }
                }
            }

            // 将excel文件重新保存
            String exportFileName = target + file.getName().substring(0, file.getName().lastIndexOf(".")) + error + file.getName().substring(file.getName().lastIndexOf("."));
            File fileTarget = new File(exportFileName);
            if (!fileTarget.getParentFile().exists()) {
                fileTarget.getParentFile().mkdirs();
            }
            fileOutputStream = new FileOutputStream(fileTarget);
            workbook.write(fileOutputStream);
            workbook.close();
        } catch (Exception e) {
            System.out.println("解析Excel失败，文件名：" + file.getName() + " 错误信息：" + e.getMessage());
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

    private static int getZuZhiCuoShiRowNum(HSSFSheet sheet) {
        int rowNum = -1;
        for (Row row : sheet) {
            if (row.getLastCellNum() >= 2) {
                if ("组织措施".equals(getCellValue(row.getCell(0)).trim())) {
                    return row.getRowNum();
                }
            }
        }
        return rowNum;
    }


    private static String getConvertValue(String value) {
        String str = "江苏省宿迁市泗洪县";
        int i = str.indexOf(value.substring(0, 1));
        if (i < 0) {
            return str + value;
        }
        return str.substring(0, i) + value;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellTypeEnum() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellTypeEnum() == CellType.FORMULA) {
            return cell.getCellFormula();
        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return "";
    }

    private static Date getRandomDate(int sY, int eY, int sM, int eM, int sD, int eD) throws ParseException {
        long start = sdf.parse(sY + "/" + sM + "/" + sD).getTime();
        long end = sdf.parse(eY + "/" + eM + "/" + eD).getTime();
        long ms = (long) ((end - start) * Math.random() + start); // 获得了符合条件的13位毫秒数
        return new Date(ms);
    }

}
