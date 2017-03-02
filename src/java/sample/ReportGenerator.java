package sample;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
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
class ReportGenerator {
    private Utils utils = new Utils();
    private DateObject dateObject;

    ReportGenerator() {
    }

    ReportGenerator(DateObject dateObject) {

        this.dateObject = dateObject;
    }

    private String getPath() {
        String path = String.valueOf(getClass().getProtectionDomain().getCodeSource().getLocation());
        path = path.substring(6);
        return path;
    }

    private String readDefaultReportFile() {
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
        StringBuilder buffer = new StringBuilder(readDefaultReportFile());

        int daysToEndOfWiza = buffer.indexOf("id=\"1\">") + 7;
        buffer.insert(daysToEndOfWiza, getDaysToEndOfWizaValue());

        int daysCanBeAtPoland = buffer.indexOf("id=\"2\">") + 7;
        buffer.insert(daysCanBeAtPoland, getDaysCanBeAtPolandValue());

        int numberOfDaysAtHome = buffer.indexOf("id=\"3\">") + 7;
        buffer.insert(numberOfDaysAtHome, getNumberOfDaysAtHomeValue());

        int numberOfDaysAtPoland = buffer.indexOf("id=\"4\">") + 7;
        buffer.insert(numberOfDaysAtPoland, getNumberOfDaysAtPolandValue());

        return buffer;
    }

    void saveAndOpenResultFile() {
        List<String> lines = Collections.singletonList(createNewReport().toString());
        String path = getPath() + "/sample/results/myResult.html";
        Path file = Paths.get(path);
        System.out.println(path);

        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
            Desktop.getDesktop().browse(file.toUri());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getNumberOfDaysAtHomeValue() {
        String result = "0";
        int tempResult = 0;

        if (dateObject.getEnterExitDate().size() > 1) {

            for (int i = 0; i < dateObject.getEnterExitDate().size() - 1; i++) {
                LocalDate exitDateToHome = dateObject.getEnterExitDate().get(i)[1];
                LocalDate enterDateToPolandAfterHome = dateObject.getEnterExitDate().get(i + 1)[1];

                tempResult += Integer.parseInt(utils.daysBetweenDate(exitDateToHome, enterDateToPolandAfterHome));

            }

        } else if (dateObject.getEnterExitDate().size() == 1) {

            LocalDate exitDateToHome = dateObject.getEnterExitDate().get(0)[1];

            tempResult = Integer.parseInt(utils.daysBetweenDate(exitDateToHome, LocalDate.now()));
        }

        result = String.valueOf(tempResult);
        return result;
    }

    private String getDaysToEndOfWizaValue() {
        String result = "0";

        result = utils.daysBetweenDate(LocalDate.now(), dateObject.getEndDate());

        return result;
    }

    private String getDaysCanBeAtPolandValue() {
        String result = "";

        result = String.valueOf(dateObject.getStayPeriod() - Integer.parseInt(getNumberOfDaysAtPolandValue()));

        return result;
    }

    private String getNumberOfDaysAtPolandValue() {
        String result = "";
        int tempResult = 0;

        for (int i = 0; i < dateObject.getEnterExitDate().size(); i++) {
            LocalDate enterDate = dateObject.getEnterExitDate().get(i)[0];
            LocalDate exitDate = dateObject.getEnterExitDate().get(i)[1];

            tempResult += Integer.parseInt(utils.daysBetweenDate(enterDate, exitDate));
        }

        result = String.valueOf(tempResult);
        return result;
    }
}