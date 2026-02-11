package me.decce.transformingbase.transform.transformers;

import me.decce.transformingbase.core.FakeDate;
import net.lenni0451.classtransform.annotations.CInline;
import net.lenni0451.classtransform.annotations.CReplaceCallback;
import net.lenni0451.classtransform.annotations.CShadow;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;

import java.util.Date;

@CReplaceCallback
@CTransformer(Date.class)
public abstract class DateTransformer {
    @CShadow
    public abstract void setDate(int date);
    @CShadow
    public abstract void setMonth(int month);
    @CShadow
    public abstract void setYear(int year);

    @CInline
    @CInject(method = "<init>()V", target = @CTarget("RETURN"))
    private void fakedate$init() {
        var config = FakeDate.config;
        if (!FakeDate.filter()) return;
        if (config.fakeYear != 0) this.setYear(config.fakeYear - 1900);
        if (config.fakeMonth != 0) this.setMonth(config.fakeMonth - 1);
        if (config.fakeDayOfMonth != 0) this.setDate(config.fakeDayOfMonth);
    }
}
