package reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by csw on 2018/6/24.
 * Description:
 */
public class ProcessFile {

    public static void main(String[] args) {
        String pathIn = "E:/test/RF_Disp.rpt"; //输入文件路径及文件名
        String pathOut = "E:/test/result.txt"; //输出文件路径及文件名
        //处理方法
        process(pathIn, pathOut);
    }

    public static void process(String pathIn, String pathOut) {
        List<String> strList = new ArrayList<>();
        BufferedReader reader;
        try {
            //一次性读完文件，将记录逐行保存在内存List中
            reader = new BufferedReader(new FileReader(new File(pathIn)));
            String line;
            while ((line = reader.readLine()) != null) {
                strList.add(line);
            }
            //遍历每行数据
            StringBuilder stringBuilder = new StringBuilder();
            for (String str : strList) {
                if (!str.trim().equals("")) {
                    String[] strS = str.split(" {8}"); //按分隔符分割字符串
                    for (String str2 : strS) {
                        if (!"".equals(str2.trim())) { //去掉为空的字符串
                            stringBuilder.append(str2.trim()).append(" ");
                        }
                    }
//                    System.out.println(stringBuilder);
                    //重新分割字符，判断第二个是否为"0."
                    if ("0.".equals(stringBuilder.toString().split(" ")[1])) {
                        //将结果逐行写到文件中去
                        String str1 = stringBuilder.toString().split(" ")[0];
                        if (!"-0.".equals(str1)) { //第一个字符不能是"-0."
                            System.out.println(str1);
                            writeToFile(pathOut, str1);
                            break; //找到第一个跳出循环
                        }
                    }
                    stringBuilder.setLength(0);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String fileName, String content) {
        File file = new File(fileName);
        if(!file.exists()){//判断文件路径是否存在
            File filepath = new File(file.getParent());
            filepath.mkdir();
        }
        if(!file.isDirectory()){//判断文件是否存在
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
