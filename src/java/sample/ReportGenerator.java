package sample;

import java.awt.*;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

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
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    ReportGenerator() {
    }

    ReportGenerator(DateObject dateObject) {

        this.dateObject = dateObject;
    }

    private String getPath() {
        String path = String.valueOf(getClass().getProtectionDomain().getCodeSource().getLocation());

        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        path = path.substring(6);
        int indexOf1 = path.lastIndexOf("/") + 1;
        path = path.substring(0, indexOf1);
        return path;
    }

    private String readDefaultReportFile() {
        StringBuilder buffer = new StringBuilder("");
        String path = getPath() + "result.html";

        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(path), "UTF8"));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }

        String str;

        try {
            assert in != null;
            while ((str = in.readLine()) != null) {

                buffer.append(str);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return buffer.toString();
    }

    private StringBuilder createNewReport() {
        System.out.println("Generate Report");
        StringBuilder buffer = new StringBuilder(readDefaultReportFile());

        int daysToEndOfWiza = buffer.indexOf("id=\"1\">") + 7;
        buffer.insert(daysToEndOfWiza, getDaysToEndOfWizaValue());

        int daysCanBeAtPoland = buffer.indexOf("id=\"2\">") + 7;
        buffer.insert(daysCanBeAtPoland, getDaysCanBeAtPolandValue());

        int numberOfDaysAtHome = buffer.indexOf("id=\"3\">") + 7;
        buffer.insert(numberOfDaysAtHome, getNumberOfDaysAtHomeValue());

        int numberOfDaysAtPoland = buffer.indexOf("id=\"4\">") + 7;
        buffer.insert(numberOfDaysAtPoland, getNumberOfDaysAtPolandValue());

        int lastDayCanBeAtPoland = buffer.indexOf("id=\"5\">") + 7;
        buffer.insert(lastDayCanBeAtPoland, getLastDayCanBeAtPoland());

        int wizaFromDate = buffer.indexOf("id=\"6\">") + 7;
        buffer.insert(wizaFromDate, dateObject.getStartDate());

        int wizaToDate = buffer.indexOf("id=\"7\">") + 7;
        buffer.insert(wizaToDate, dateObject.getEndDate());

        int period = buffer.indexOf("id=\"8\">") + 7;
        buffer.insert(period, dateObject.getStayPeriod());

        int enterExitDate = buffer.indexOf("id=\"9\">") + 7;
        String result = getEnterExitDateReport();
        buffer.insert(enterExitDate, result);

        return buffer;
    }

    void saveAndOpenResultFile() {
        List<String> lines = Collections.singletonList(createNewReport().toString());
        String path = getPath() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH-mm-ss")) + ".html";
        Path file = Paths.get(path);
        System.out.println("Save Report File");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
            System.out.println("Open File");
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

    private String getLastDayCanBeAtPoland() {

        String result = "0";

        result = LocalDate.now().plusDays(Long.parseLong(getDaysCanBeAtPolandValue())).format(formatter);

        return result;
    }

    private String getEnterExitDateReport() {
        StringBuilder sb = new StringBuilder("");

        /*daysInPoland = exitDate - enterDate*/
        /*daysAtUkraine = enterDate2 - 1exitDate*/
        LocalDate exitDate1 = null;
        for (LocalDate[] dates : dateObject.getEnterExitDate()) {
            LocalDate enterDate = dates[0];
            LocalDate exitDate = dates[1];

            int daysInPoland = 0;
            int daysAtUkraine = 0;

            if (exitDate1 != null) {
                daysAtUkraine = Integer.parseInt(utils.daysBetweenDate(exitDate1, enterDate));
            }

            daysInPoland = Integer.parseInt(utils.daysBetweenDate(enterDate, exitDate));

            sb.append("<td>").append(enterDate.format(formatter)).append("</td>");
            sb.append("<td>").append(exitDate.format(formatter)).append("</td>");
            sb.append("<td>").append(daysInPoland).append("</td>");
            sb.append("<td>").append(daysAtUkraine).append("</td>");
            sb.append("<tr></tr>");
            exitDate1 = exitDate;
        }

        return sb.toString();
    }
}