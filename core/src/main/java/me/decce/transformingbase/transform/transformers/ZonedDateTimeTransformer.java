package me.decce.transformingbase.transform.transformers;

import me.decce.transformingbase.core.FakeDate;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CInline;
import net.lenni0451.classtransform.annotations.CReplaceCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@CReplaceCallback
@CTransformer(ZonedDateTime.class)
public abstract class ZonedDateTimeTransformer {
    @CInline
    @CInject(method = "now(Ljava/time/Clock;)Ljava/time/ZonedDateTime;", target = @CTarget("RETURN"), cancellable = true)
    private static void fakedate$modifyNow(InjectionCallback ci) {
        var config = FakeDate.config;
        ZonedDateTime originalDate = ci.castReturnValue();
        if (originalDate != null && FakeDate.filter()) {
            ci.setReturnValue(ZonedDateTime.of(LocalDate.of(
                    config.fakeYear == 0 ? originalDate.getYear() : config.fakeYear,
                    config.fakeMonth == 0 ? originalDate.getMonthValue() : config.fakeMonth,
                    config.fakeDayOfMonth == 0 ? originalDate.getDayOfMonth() : config.fakeDayOfMonth),
                    originalDate.toLocalTime(),
                    originalDate.getZone()));
        }
    }
}
