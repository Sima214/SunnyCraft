package sima214.core.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class IOUtils {
	public static BufferedReader getReaderByResource(ResourceLocation rLocation) throws IOException{
		return new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(rLocation).getInputStream()));
	}
}
