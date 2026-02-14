package me.decce.transformingbase.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "unused"})
public class FakeDateConfig {
    @Comment("Specifies the year to fake (0 = disable)")
    public int fakeYear = 0;
    @Comment("Specifies the month to fake (0 = disable, 1 = January, 2 = February, ...)")
    public int fakeMonth = 0;
    @Comment("Specifies the day to fake (0 = disable)")
    public int fakeDayOfMonth = 0;
    @Comment("Only fake date when queried by specified mods\nUse * to allow any (not recommended)")
    public List<String> filter = List.of("enter_modid_here");
    @Comment("When enabled, prints verbose information when faking date")
    public boolean debugFakingDate;
    @Comment("When enabled, prints verbose information when not faking date (mod not in filter)")
    public boolean debugNonFakingDate;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Comment {
        String value();
    }
}
