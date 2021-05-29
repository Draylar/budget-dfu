package draylar.budgetdfu;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class BudgetDFU {

    private static final Map<Registry<?>, Map<Identifier, Identifier>> ID_REMAPS = new HashMap<>();
    private static final Map<Registry<?>, Map<String, String>> NAMESPACE_REMAPS = new HashMap<>();

    public static void registerDirectRemap(Registry<?> registry, Identifier original, Identifier replacement) {
        if(ID_REMAPS.containsKey(registry)) {
            ID_REMAPS.get(registry).put(original, replacement);
        } else {
            HashMap<Identifier, Identifier> map = new HashMap<>();
            map.put(original, replacement);
            ID_REMAPS.put(registry, map);
        }
    }

    public static void registerNamespaceRemap(Registry<?> registry, String original, String replacement) {
        if(NAMESPACE_REMAPS.containsKey(registry)) {
            NAMESPACE_REMAPS.get(registry).put(original, replacement);
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put(original, replacement);
            NAMESPACE_REMAPS.put(registry, map);
        }
    }

    @Nullable
    public static Identifier remapDirect(Registry<?> registry, Identifier id) {
        if(ID_REMAPS.containsKey(registry)) {
            return ID_REMAPS.get(registry).get(id);
        } else {
            return null;
        }
    }

    @Nullable
    public static String remapNamespace(Registry<?> registry, String id) {
        if(NAMESPACE_REMAPS.containsKey(registry)) {
            return NAMESPACE_REMAPS.get(registry).get(id);
        } else {
            return null;
        }
    }

    private BudgetDFU() {
        // NO-OPERATION
    }
}
