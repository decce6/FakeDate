package me.decce.transformingbase.transform.transformers;

import me.decce.transformingbase.core.FakeDate;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CInline;
import net.lenni0451.classtransform.annotations.CReplaceCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;

import java.util.Calendar;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

@CReplaceCallback
@CTransformer(Calendar.class)
public abstract class CalendarTransformer {
    @CInline
    @CInject(method = "createCalendar", target = @CTarget("RETURN"))
    private static void fakedate$postCreateCalendar(InjectionCallback ci) {
        var config = FakeDate.config;
        Calendar calendar = ci.castReturnValue();
        if (calendar != null && FakeDate.filter()) {
            var year = config.fakeYear == 0 ? calendar.get(YEAR) : config.fakeYear;
            var month = config.fakeMonth == 0 ? calendar.get(MONTH) : config.fakeMonth - 1;
            var day = config.fakeDayOfMonth == 0 ? calendar.get(DAY_OF_MONTH) : config.fakeDayOfMonth;
            calendar.set(year, month, day);
        }
    }
}
