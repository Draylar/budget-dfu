package draylar.budgetdfu.mixin;

import draylar.budgetdfu.BudgetDFU;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Registry.class)
public class RegistryDFUMixin {

    @ModifyVariable(method = "get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;", at = @At("HEAD"), index = 1)
    private Identifier remapRegistryRetrieval(Identifier id) {
        // If the ID passed in is null, return null.
        if(id == null) {
            return null;
        }

        @Nullable Identifier direct = BudgetDFU.remapDirect((DefaultedRegistry) (Object) this, id);

        // If a direct Identifier remap was found, apply it now & return.
        if(direct != null) {
            return direct;
        }

        // If a namespace remap is applicable, apply now & return.
        String updatedNamespace = BudgetDFU.remapNamespace((DefaultedRegistry) (Object) this, id.getNamespace());
        if(updatedNamespace != null) {
            return new Identifier(updatedNamespace, id.getPath());
        }

        return id;
    }

    @ModifyVariable(method = "getOrEmpty(Lnet/minecraft/util/Identifier;)Ljava/util/Optional;", at = @At("HEAD"), index = 1)
    private Identifier remapOptionalRegistryRetrieval(Identifier id) {
        // If the ID passed in is null, return null.
        if(id == null) {
            return null;
        }

        @Nullable Identifier direct = BudgetDFU.remapDirect((DefaultedRegistry) (Object) this, id);

        // If a direct Identifier remap was found, apply it now & return.
        if(direct != null) {
            return direct;
        }

        // If a namespace remap is applicable, apply now & return.
        String updatedNamespace = BudgetDFU.remapNamespace((DefaultedRegistry) (Object) this, id.getNamespace());
        if(updatedNamespace != null) {
            return new Identifier(updatedNamespace, id.getPath());
        }

        return id;
    }
}
