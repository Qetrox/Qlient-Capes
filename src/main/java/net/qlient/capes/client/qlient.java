package net.qlient.capes.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.qlient.capes.util.CapeUtil;
import net.qlient.capes.util.VersionChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Environment(EnvType.CLIENT)
public class qlient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Qlient Capes");

	public static String VERSION = "1.1.0";

	@Override
	public void onInitializeClient() {
		LOGGER.info("Downloading Cape Data...");
		CapeUtil.DownloadCapes();
		VersionChecker.getOnlineVersion();
		LOGGER.info("Qlient Capes Loaded");
	}
}
