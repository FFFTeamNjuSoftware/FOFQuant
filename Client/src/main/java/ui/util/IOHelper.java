package ui.util;

/**
 * Created by tj on 2016/8/18.
 */
public class IOHelper {
    private static String fileName = "Client/src/main/java/ui/util/temp.txt";
    public static String name;

    public static void writeName(String str) {
//        try {
//            FileWriter writer = new FileWriter(fileName);
//            writer.write(str);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        name = str;

    }

    public static String readName() {
//        try {
//            BufferedReader in = new BufferedReader(new FileReader(fileName));
//            String line = in.readLine();
//            return line;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
        return name;
    }

}
