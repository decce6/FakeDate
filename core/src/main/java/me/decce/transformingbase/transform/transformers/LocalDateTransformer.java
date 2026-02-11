package me.decce.transformingbase.transform.transformers;

import me.decce.transformingbase.core.FakeDate;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CInline;
import net.lenni0451.classtransform.annotations.CReplaceCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;

import java.time.LocalDate;

@CReplaceCallback
@CTransformer(LocalDate.class)
public abstract class LocalDateTransformer {
    @CInline
    @CInject(method = "now(Ljava/time/Clock;)Ljava/time/LocalDate;", target = @CTarget("RETURN"), cancellable = true)
    private static void fakedate$modifyNow(InjectionCallback ci) {
        var config = FakeDate.config;
        LocalDate originalDate = ci.castReturnValue();
        if (originalDate != null && FakeDate.filter()) {
            ci.setReturnValue(LocalDate.of(
                    config.fakeYear == 0 ? originalDate.getYear() : config.fakeYear,
                    config.fakeMonth == 0 ? originalDate.getMonthValue() : config.fakeMonth,
                    config.fakeDayOfMonth == 0 ? originalDate.getDayOfMonth() : config.fakeDayOfMonth));
        }
    }
}
