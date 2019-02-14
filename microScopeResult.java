import java.io.*;

public class microScopeResult {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\s138093\\Desktop\\共有サーバー\\0.txt")), "Shift-JIS"));
        String z = null;
        String line = reader.readLine();
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\s138093\\Desktop\\共有テキスト\\0.txt")));
        while (null != line) {
            z = myFunction(line,z,bw);
            line = reader.readLine();
        }
        reader.close();
        bw.close();
    }
    private static String myFunction(String line,String z,BufferedWriter bw) throws IOException {
        if (line.contains("Z:\\")) {
            z = line;
            return z;
        }
        if ((line.contains("WIN") || line.contains("BUILTIN\\Administrators")) && !line.contains("<DIR>")) {
            bw.write(line + "<" + z);
            bw.newLine();
            System.out.println(line + "<" + z);
        }
        return z;
    }
}