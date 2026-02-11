package me.decce.transformingbase.core;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class FakeDate {
    public static final Set<String> checked = Set.of(
            Calendar.class.getName(),
            Date.class.getName(),
            LocalDate.class.getName(),
            ZonedDateTime.class.getName()
    );
    public static FakeDateConfig config;
    public static FakeDateAccessor accessor;

    public static boolean filter() {
        var mod = accessor.getCallerMod();
        var allowed = config.filter;
        return allowed.stream().anyMatch(s -> "*".equals(s) || s.equals(mod.orElse(null)));
    }
}
