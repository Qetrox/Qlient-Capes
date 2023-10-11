package net.qlient.capes.mixin;

import com.mojang.authlib.GameProfile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.texture.PlayerSkinProvider;
import net.minecraft.client.util.SkinTextures;
import static net.qlient.capes.util.CapeUtil.getQlientCapeLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.concurrent.CompletableFuture;




@Environment(EnvType.CLIENT)
@Mixin(PlayerSkinProvider.class)
public class PlayerSkinProviderMixin {

    @Inject(method = "fetchSkinTextures*", at = @At("RETURN"), cancellable = true)
    private void modifyCapeTexture(GameProfile profile, CallbackInfoReturnable<CompletableFuture<SkinTextures>> info) {
        CompletableFuture<SkinTextures> modifiedTextures = info.getReturnValue().thenApplyAsync(originalTextures -> {
            return new SkinTextures(
                    originalTextures.texture(),
                    originalTextures.textureUrl(),
                    getQlientCapeLocation(profile, originalTextures.capeTexture()),
                    getQlientCapeLocation(profile, originalTextures.elytraTexture()),
                    originalTextures.model(),
                    originalTextures.secure()
            );
        });

        info.setReturnValue(modifiedTextures);
    }
}
