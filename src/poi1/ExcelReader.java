//package poi1;
//
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.*;
//
//
///**
// * Created by csw on 2020/03/07.
// * Description:
// */
//public class ExcelReader {
//
//
//    private static final String XLS = "xls";
//    private static final String XLSX = "xlsx";
//
//    /**
//     * 读取Excel文件内容
//     *
//     * @param fileName 要读取的Excel文件所在路径
//     * @return 读取结果列表，读取失败时返回null
//     */
//    public static void readExcel(String fileName) {
//
//        Workbook workbook = null;
//        FileInputStream inputStream = null;
//        FileOutputStream out = null;
//
//        try {
//            // 获取Excel后缀名
//            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
//            // 获取Excel文件
//            File excelFile = new File(fileName);
//            if (!excelFile.exists()) {
//                System.out.println("指定的Excel文件不存在！");
//            }
//            // 获取Excel工作簿
//            inputStream = new FileInputStream(excelFile);
//            workbook = getWorkbook(inputStream, fileType);
//
//            // 按Sheet读取excel中的数据
//            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
//                Sheet sheet = workbook.getSheetAt(sheetNum);
//                // 校验sheet是否合法
//                if (sheet == null) {
//                    continue;
//                }
//                // 获取第一行数据
//                int firstRowNum = sheet.getFirstRowNum();
//                Row firstRow = sheet.getRow(firstRowNum);
//                if (null == firstRow) {
//                    System.out.println("解析Excel失败，在第一行没有读取到任何数据！");
//                }
//                // 解析每一行的数据，构造数据对象
//                int rowStart = firstRowNum + 1;
//                int rowEnd = sheet.getPhysicalNumberOfRows();
//                for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
//                    Row row = sheet.getRow(rowNum);
//                    if (row != null) {
//                        // 解析每一行数据
//
//                    }
//                }
//            }
//
//            out = new FileOutputStream(new File("E:\\excel\\李小冬1.xls"));
//            workbook.write(out);
//
//        } catch (Exception e) {
//            System.out.println("解析Excel失败，文件名：" + fileName + " 错误信息：" + e.getMessage());
//        } finally {
//            try {
//                if (null != workbook) {
//                    workbook.close();
//                }
//                if (null != inputStream) {
//                    inputStream.close();
//                }
//                if (null != out) {
//                    out.close();
//                }
//            } catch (Exception e) {
//                System.out.println("关闭数据流出错！错误信息：" + e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * 根据文件后缀名类型获取对应的工作簿对象
//     *
//     * @param inputStream 读取文件的输入流
//     * @param fileType    文件后缀名类型（xls或xlsx）
//     * @return 包含文件数据的工作簿对象
//     * @throws IOException
//     */
//    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
//        Workbook workbook = null;
//        if (fileType.equalsIgnoreCase(XLS)) {
//            workbook = new HSSFWorkbook(inputStream);
//        } else if (fileType.equalsIgnoreCase(XLSX)) {
//            workbook = new XSSFWorkbook(inputStream);
//        }
//        return workbook;
//    }
//}
