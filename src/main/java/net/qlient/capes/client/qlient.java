package net.qlient.capes.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.qlient.capes.util.CapeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class qlient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Qlient Capes");

	@Override
	public void onInitializeClient() {
		LOGGER.info("Downloading Cape Data...");
		CapeUtil.DownloadCapes();
		LOGGER.info("Qlient Capes Loaded");
	}
}
