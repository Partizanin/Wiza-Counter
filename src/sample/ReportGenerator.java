package sample;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created with Intellij IDEA.
 * Project name: DateTimeCounter.
 * Date: 28.02.2017.
 * Time: 16:42.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class ReportGenerator {


    private String getPath() {
        String path = String.valueOf(getClass().getProtectionDomain().getCodeSource().getLocation());
        path = path.substring(6);
        return path;
    }

    private String readReportFile() {
        StringBuilder buffer = new StringBuilder("");
        //read file into stream, try-with-resources
        String path = getPath() + "sample/results/result.html";
        try (Stream<String> stream = Files.lines(Paths.get(path))) {

            stream.forEach(buffer::append);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    private StringBuilder createNewReport() {
        StringBuilder buffer = new StringBuilder(readReportFile());

        int daysToEndOfWiza = buffer.indexOf("id=\"1\">") + 7;
        buffer.insert(daysToEndOfWiza, "12");

        int daysCanBeAtPoland = buffer.indexOf("id=\"2\">") + 7;
        buffer.insert(daysCanBeAtPoland, "22");

        int numberOfDaysAtHome = buffer.indexOf("id=\"3\">") + 7;
        buffer.insert(numberOfDaysAtHome, "54");

        int numberOfDaysAtPoland = buffer.indexOf("id=\"4\">") + 7;
        buffer.insert(numberOfDaysAtPoland, "99");

        return buffer;
    }

    private void saveAndOpenResultFile() {
        List<String> lines = Collections.singletonList(createNewReport().toString());
        String path = getPath() + "\\sample\\results\\myResult.html";
        Path file = Paths.get(path);
        System.out.println(path);

        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
            Desktop.getDesktop().browse(file.toUri());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}