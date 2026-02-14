package me.decce.transformingbase.core;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class FakeDate {
    public static final String MODID_UNKNOWN = "<unknown>";
    public static final Set<String> checked = Set.of(
            Calendar.class.getName(),
            Date.class.getName(),
            LocalDate.class.getName(),
            ZonedDateTime.class.getName()
    );
    public static FakeDateConfig config;
    public static FakeDateAccessor accessor;

    public static boolean filter() {
        var mod = accessor.getCallerMod().orElse(MODID_UNKNOWN);
        var fake = config.filter.stream().anyMatch(s -> "*".equals(s) || s.equals(mod));
        if (fake && config.debugFakingDate) {
            LibraryAccessor.warn("Faking date to {}/{}/{} for mod {}", config.fakeYear, config.fakeMonth, config.fakeDayOfMonth, mod, new StacktracePrinter());
        }
        if (!fake && config.debugNonFakingDate) {
            LibraryAccessor.warn("Not faking date for mod {}", mod, new StacktracePrinter());
        }
        return fake;
    }
}
