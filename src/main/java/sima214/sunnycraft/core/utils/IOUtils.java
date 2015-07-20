package sima214.sunnycraft.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class IOUtils {
	public static String loadSource(ResourceLocation resource) throws IOException{
		InputStream inputStream = Minecraft.getMinecraft().getResourceManager().getResource(resource).getInputStream();
		BufferedReader reader = null;
		String out="";
		try
		{
			reader = new BufferedReader(new InputStreamReader(inputStream));
			String currentLine;
			while ((currentLine = reader.readLine()) != null)
			{
				out+=currentLine+"\n";}
		}
		finally
		{
			try
			{
				reader.close();
			}
			catch (IOException e)
			{
			}
			try
			{
				inputStream.close();
			}
			catch (IOException e)
			{
			}
		}
		return out;
	}

}
