package print;

/**
 * Created by csw on 2019/04/01.
 * Description:
 */
public class Printer {

    public static void main(String[] args) {
        Printer.println("默认");
        Printer.println("黑色", Printer.BLACK);
        Printer.println("白色", Printer.WHITE);
        Printer.println("红色", Printer.RED);
        Printer.println("绿色", Printer.GREEN);
        Printer.println("黄色", Printer.YELLOW);
        Printer.println("蓝色", Printer.BLUE);
        Printer.println("品红", Printer.MAGENTA);
        Printer.println("蓝绿", Printer.CYAN);
        Printer.println("黑底白字", Printer.WHITE, Printer.BLACK_BACKGROUND);
        Printer.println("白底黑字", Printer.BLACK, Printer.WHITE_BACKGROUND);
        Printer.println("蓝底红字", Printer.RED, Printer.BLUE_BACKGROUND);
        Printer.println("加粗", Printer.BOLD);
        Printer.println("加粗倾斜", Printer.BOLD, Printer.ITATIC);
        Printer.println("黄底白字下划线", Printer.WHITE, Printer.YELLOW_BACKGROUND, Printer.UNDERLINE);
        Printer.println("红字颜色反转", Printer.RED, Printer.REVERSE);
    }

    public static final int WHITE = 30;             // 白色
    public static final int WHITE_BACKGROUND = 40;  // 白色背景
    public static final int RED = 31;               // 红色
    public static final int RED_BACKGROUND = 41;    // 红色背景
    public static final int GREEN = 32;             // 绿色
    public static final int GREEN_BACKGROUND = 42;  // 绿色背景
    public static final int YELLOW = 33;            // 黄色
    public static final int YELLOW_BACKGROUND = 43; // 黄色背景
    public static final int BLUE = 34;              // 蓝色
    public static final int BLUE_BACKGROUND = 44;   // 蓝色背景
    public static final int MAGENTA = 35;           // 品红（洋红）
    public static final int MAGENTA_BACKGROUND = 45;// 品红背景
    public static final int CYAN = 36;              // 蓝绿
    public static final int CYAN_BACKGROUND = 46;   // 蓝绿背景
    public static final int BLACK = 37;             // 黑色
    public static final int BLACK_BACKGROUND = 47;  // 黑色背景

    public static final int BOLD = 1;       // 粗体
    public static final int ITATIC = 3;     // 斜体
    public static final int UNDERLINE = 4;  // 下划线
    public static final int REVERSE = 7;    // 反转

    private static String format(String txt, int... codes) {
        StringBuilder sb = new StringBuilder();
        for (int code : codes) {
            sb.append(code).append(";");
        }
        String _code = sb.toString();
        if (_code.endsWith(";")) {
            _code = _code.substring(0, _code.length() - 1);
        }
        return (char) 27 + "[" + _code + "m" + txt + (char) 27 + "[0m";
    }

    /**
     * 打印不换行
     */
    public static void print(String txt, int... codes) {
        System.out.print(format(txt, codes));
    }

    /**
     * 打印并换行
     */
    public static void println(String txt, int... codes) {
        System.out.println(format(txt, codes));
    }

    /**
     * 默认打印红色文字
     */
    public static void println(String txt) {
        System.out.println("默认");
        System.out.println(format(txt));
    }
}
