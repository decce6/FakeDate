package me.decce.transformingbase.service;

import me.decce.transformingbase.core.FakeDate;
import me.decce.transformingbase.core.FakeDateAccessor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeDateAccessorImpl implements FakeDateAccessor {
    //? if fabric {
    private static final Map<Path, String> pathToModId = new HashMap<>();

    private static List<Path> determineLocation(String modid) {
        var container = net.fabricmc.loader.api.FabricLoader.getInstance().getModContainer(modid).orElse(null);
        if (container == null) {
            return List.of();
        }
        var origin = container.getOrigin();
        if (origin.getKind() == net.fabricmc.loader.api.metadata.ModOrigin.Kind.PATH) {
            return origin.getPaths();
        }
        else if (origin.getKind() == net.fabricmc.loader.api.metadata.ModOrigin.Kind.NESTED) {
            return determineLocation(origin.getParentModId());
        }
        return List.of();
    }

    //?}
    public FakeDateAccessorImpl() {
        //? if fabric {
        for (var mod : net.fabricmc.loader.api.FabricLoader.getInstance().getAllMods()) {
            var modid = mod.getMetadata().getId();
            var paths = determineLocation(modid);
            for (var path : paths) {
                pathToModId.put(path, modid);
            }
        }
        //?}
    }

    // CallerSensitive
    @Override
    public Optional<String> getCallerMod() {
        var walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        //? if fabric {
        var ret = walker.walk(s -> s
                .skip(2) // Skip this class and the caller class (us)
                .dropWhile(f -> FakeDate.checked.contains(f.getClassName()))
                .findFirst()
                .map(f -> f.getDeclaringClass().getProtectionDomain().getCodeSource()))
                .map(cs -> {
                    try {
                        return pathToModId.get(Paths.get(cs.getLocation().toURI()));
                    } catch (Throwable t) {
                        return null;
                    }
                });
        return ret;
        //?} else {
        /*var ret = walker.walk(s -> s
                .skip(2) // Skip this class and the caller class (us)
                .dropWhile(f -> FakeDate.checked.contains(f.getClassName()))
                .findFirst()
                .map(f -> {
                    var n = f.getDeclaringClass().getModule().getName();
                    if (n != null) return n;
                    else return null;
                })
        );
        return ret;
        *///?}
    }
}
