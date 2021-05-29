package draylar.budgetdfu.mixin;

import draylar.budgetdfu.BudgetDFU;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DefaultedRegistry.class)
public class DefaultedRegistryDFUMixin {

    @Shadow @Final private Identifier defaultId;

    @ModifyVariable(method = "get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;", at = @At("HEAD"), index = 1)
    private Identifier remapRegistryRetrieval(Identifier id) {
        // If the ID passed in is null, return the default value of this registry.
        if(id == null) {
            return defaultId;
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
