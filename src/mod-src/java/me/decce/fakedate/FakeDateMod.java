package me.decce.fakedate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class FakeDateMod {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {

        /*
        var c = Calendar.getInstance();
        LOGGER.info("Calendar: year={},month={},day={}\n", c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        var l = LocalDate.now();
        LOGGER.info("LocalDate: year={},month={},day={}\n", l.getYear(), l.getMonth(), l.getDayOfMonth());
        var z = ZonedDateTime.now();
        LOGGER.info("ZonedDateTime: year={},month={},day={}\n", z.getYear(), z.getMonth(), z.getDayOfMonth());
        var d = new Date();
        LOGGER.info("Date: year={},month={},day={}\n", d.getYear(), d.getMonth(), d.getDate());
        */

    }
}
