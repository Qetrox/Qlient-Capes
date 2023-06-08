package net.qlient.capes.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.qlient.capes.util.CapeUtil;
import net.qlient.capes.util.VersionChecker;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Mixin(TitleScreen.class)
public abstract class MenuButtonMixin extends Screen {

    @Shadow @Final private boolean doBackgroundFade;

    protected MenuButtonMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "initWidgetsNormal")
    private void addCustomButton(int y, int spacingY, CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.of("Reload Capes"), (button) -> {
            CapeUtil.DownloadCapes();
            this.client.setScreen(new TitleScreen(this.doBackgroundFade));
        }).dimensions(this.width / 2 - 100, y + 108, 98, 20).build());

        if (!VersionChecker.isLatest()) {
            this.addDrawableChild(ButtonWidget.builder(Text.of("Outdated Version!"), (button) -> {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.curseforge.com/minecraft/mc-mods/qlient-capes"));
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }).dimensions(this.width / 2 + 2, y + 108, 98, 20).build());
        }
    }
}
