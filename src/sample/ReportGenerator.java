package sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created with Intellij IDEA.
 * Project name: DateTimeCounter.
 * Date: 28.02.2017.
 * Time: 16:42.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class ReportGenerator {
    public static void main(String[] args) {
        String path = new ReportGenerator().getPath();

        readReportFile(path);
    }

    public String getPath() {
        String path = String.valueOf(getClass().getProtectionDomain().getCodeSource().getLocation());
        path += "sample/results/result.html";
        path = path.substring(6);
        return path;
    }

    public static String  readReportFile(String path) {
        StringBuffer buffer = new StringBuffer("");
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(path))) {

            stream.forEach(buffer::append);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}/*D:\Illia\Java\Projects\DateTimeCounter\out\production\DateTimeCounter\src\sample\results
   D:\Illia\Java\Projects\DateTimeCounter\out\production\DateTimeCounter\sample\results*/
