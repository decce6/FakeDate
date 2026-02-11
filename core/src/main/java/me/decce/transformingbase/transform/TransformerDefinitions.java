package me.decce.transformingbase.transform;

import me.decce.transformingbase.transform.transformers.CalendarTransformer;
import me.decce.transformingbase.transform.transformers.DateTransformer;
import me.decce.transformingbase.transform.transformers.LocalDateTransformer;
import me.decce.transformingbase.transform.transformers.ZonedDateTimeTransformer;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public enum TransformerDefinitions {
    CALENDAR(Calendar.class, CalendarTransformer.class),
    DATE(Date.class, DateTransformer.class),
    LOCAL_DATE(LocalDate.class, LocalDateTransformer.class),
    ZONED_DATE_TIME(ZonedDateTime.class, ZonedDateTimeTransformer.class);

    public final TransformerDefinition definition;

    TransformerDefinitions(Class<?> target, Class<?> transformer) {
        this(new TransformerDefinition(target.getName(), transformer));
    }

    TransformerDefinitions(TransformerDefinition definition) {
        this.definition = definition;
    }
}
