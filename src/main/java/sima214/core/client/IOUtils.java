package sima214.core.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import sima214.core.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class IOUtils {
	public static BufferedReader getReaderByResource(ResourceLocation rLocation) throws IOException{
		return new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(rLocation).getInputStream()));
	}
	public static ArrayList<String> loadLines(ResourceLocation rLocation){//Taken from another project of mine
		ArrayList<String> s=new ArrayList<String>();
		BufferedReader file=null;
		try {
			file = getReaderByResource(rLocation);
			String curLine;
			while((curLine=file.readLine()) !=null) {
				s.add(curLine);
			}
			file.close();
		} catch (Exception e) {
			Logger.exception("Error occured while loading a file. "+rLocation.toString(), e);
		}
		return s;
	}
	public static String loadText(ResourceLocation rLocation){
		return convertArrayString(loadLines(rLocation));
	}
	public static String convertArrayString(ArrayList<String> s){
		String finalStr = "";
		for(String curS:s){
			finalStr+=curS+"\n";
		}
		return finalStr;
	}
}
