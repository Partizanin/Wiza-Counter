import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sample.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Created with Intellij IDEA.
 * Project name: DateTimeCounter.
 * Date: 28.02.2017.
 * Time: 23:27.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class UtilsTest {
    private Utils utils;
    private DateTimeFormatter formatter;


    @Before
    public void setUp() throws Exception {
        utils = new Utils();
        formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    }

    @Test
    public void parser() throws Exception {
        LocalDate actual = LocalDate.parse("04/11/2017",formatter);
        LocalDate expected = LocalDate.parse("05/11/2017",formatter);

        expected = LocalDate.parse(utils.dateValidation("2017-04-11"));
        Assert.assertTrue(actual.isEqual(expected));
        expected = LocalDate.parse(utils.dateValidation("11-04-2017"));
        Assert.assertTrue(actual.isEqual(expected));
        expected = LocalDate.parse(utils.dateValidation("11.04.2017"));
        Assert.assertTrue(actual.isEqual(expected));
        expected = LocalDate.parse(utils.dateValidation("2017.04.11"));
        Assert.assertTrue(actual.isEqual(expected));
        expected = LocalDate.parse(utils.dateValidation("2017/04/11"));
        Assert.assertTrue(actual.isEqual(expected));
        expected = LocalDate.parse(utils.dateValidation("11/04/2017"));
        Assert.assertTrue(actual.isEqual(expected));

    }

    @Test
    public void daysBetweenDate() throws Exception {
        LocalDate startDate;
        LocalDate endDate;

        startDate = LocalDate.parse("12/22/2016", formatter);
        endDate = LocalDate.parse("12/22/2017", formatter);

        Assert.assertEquals(utils.daysBetweenDate(startDate, endDate), "365");

        startDate = LocalDate.parse("12/02/2017", formatter);
        endDate = LocalDate.parse("12/22/2017", formatter);

        Assert.assertEquals(utils.daysBetweenDate(startDate, endDate), "20");

        startDate = LocalDate.parse("12/23/2017", formatter);
        endDate = LocalDate.parse("12/22/2017", formatter);

        Assert.assertEquals(utils.daysBetweenDate(startDate, endDate), "-1");

        startDate = LocalDate.parse("12/22/2017", formatter);
        endDate = LocalDate.parse("12/22/2017", formatter);

        Assert.assertEquals(utils.daysBetweenDate(startDate, endDate), "0");

        startDate = LocalDate.parse("02/01/2017", formatter);
        endDate = LocalDate.parse("01/01/2017", formatter);

        Assert.assertEquals(utils.daysBetweenDate(startDate, endDate), "-31");

    }

}