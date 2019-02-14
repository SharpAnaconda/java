import java.io.*;

public class microScopeResult2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\s138093\\Desktop\\temp\\alldata\\csvall.txt")),"Shift-JIS"));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\s138093\\Desktop\\temp\\text.txt"))));
        String line1 = reader1.readLine();
        String line2 = null;
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\s138093\\Desktop\\unicodetext1.txt")));
        while (null != line1) {
            if (myFunction1(line1)){
                line2 = reader2.readLine();
            }
            myFunction2(line1,line2,bw);
            line1 = reader1.readLine();
        }
        reader1.close();
        reader2.close();
        bw.close();
    }
    private static Boolean myFunction1(String line1){
        if (line1.contains("[ メイン ]")){
            return true;
        }
        return false;
    }
    private static void myFunction2(String line1,String line2,BufferedWriter bw) throws IOException {
        if (line1.contains("2点間") || line1.contains("直径")) {
            bw.write(line1 +  line2);
            bw.newLine();
            System.out.println(line1 + "<" + line2);
        }
    }
}