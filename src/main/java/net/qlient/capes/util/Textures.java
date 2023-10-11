package net.qlient.capes.util;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class Textures {
    private final MinecraftProfileTexture skin;
    private final MinecraftProfileTexture cape;
    private final MinecraftProfileTexture elytra;
    private final boolean secure;

    public Textures(@Nullable MinecraftProfileTexture skin, @Nullable MinecraftProfileTexture cape, @Nullable MinecraftProfileTexture elytra, boolean secure) {
        this.skin = skin;
        this.cape = cape;
        this.elytra = elytra;
        this.secure = secure;
    }

    @Nullable
    public MinecraftProfileTexture skin() {
        return this.skin;
    }

    @Nullable
    public MinecraftProfileTexture cape() {
        return this.cape;
    }

    @Nullable
    public MinecraftProfileTexture elytra() {
        return this.elytra;
    }

    public boolean secure() {
        return this.secure;
    }

    public static Textures fromMap(Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> textures, boolean secure) {
        return textures.isEmpty() ? MISSING : new Textures(textures.get(MinecraftProfileTexture.Type.SKIN), textures.get(MinecraftProfileTexture.Type.CAPE), textures.get(MinecraftProfileTexture.Type.ELYTRA), secure);
    }

    public static final Textures MISSING = new Textures(null, null, null, true);
}
