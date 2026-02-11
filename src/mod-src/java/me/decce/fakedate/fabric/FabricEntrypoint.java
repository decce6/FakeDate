package me.decce.fakedate.fabric;

//? if fabric {
import me.decce.fakedate.FakeDateMod;
import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        FakeDateMod.init();
    }
}
//?}
