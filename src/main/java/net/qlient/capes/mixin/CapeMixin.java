package net.qlient.capes.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.Identifier;
import net.qlient.capes.util.CapeUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(AbstractClientPlayerEntity.class)
public class CapeMixin {
    @Inject(method = "getCapeTexture", at = @At("RETURN"), cancellable = true)
    private void getCapeTexture(CallbackInfoReturnable<Identifier> ci) {
        Identifier originalCape = ci.getReturnValue();
        String uuid = ((AbstractClientPlayerEntity) (Object) this).getGameProfile().getId().toString();

        String CapeCode = CapeUtil.CheckCape(uuid);
        String NoneCode = "NONE";

        if(CapeCode.equals(NoneCode)) {
            ci.setReturnValue(originalCape);
            return;
        }

        Identifier newCape = new Identifier("qlientcapes:capes/" + CapeCode.toLowerCase() + ".png");
        ci.setReturnValue(newCape);
    }
}
