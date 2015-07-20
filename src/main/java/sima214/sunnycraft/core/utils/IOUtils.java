package sima214.sunnycraft.core.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import sima214.sunnycraft.core.LogHelper;

public class IOUtils {
	public static ArrayList<String> loadLines(String filename){
		ArrayList<String> s=new ArrayList<String>();
		BufferedReader file=null;
		try {
			file = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(filename)).getInputStream()));
			String curLine;
			while((curLine=file.readLine()) !=null) {
				s.add(curLine);
			}
			file.close();
		} catch (Exception e) {
			LogHelper.fatal("!!!Error occured while loading a file!!!", e);
		}
		return s;
	}
	public static String loadText(String filename){
		return convertArrayString(loadLines(filename));
	}
	public static String convertArrayString(ArrayList<String> s){
		String finalStr = "";//I should have known this from lua(It doesn't convert nil values to strings automatically so it kind of crashes:-)
		for(String curS:s){
			finalStr+=curS+"\n";
		}
		return finalStr;
	}
	public static float[] loadSModel(String filename){
		ArrayList<Float> data=new ArrayList<Float>();
		BufferedReader file=null;
		try {
			file = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(filename)).getInputStream()));
			boolean first=true;
			String curLine;
			while((curLine=file.readLine()) !=null) {
				if(first){
					first=false;
					continue;
				}
				String[] result=curLine.split(" ");
				for(int i=0;i<result.length;i++){
					data.add(Float.parseFloat(result[i]));
				}
			}
			file.close();
		} catch (Exception e) {
			LogHelper.fatal("!!!Error occured while loading a file!!!", e);
		}
		int curIndex=0;
		float[] finalData =new float[data.size()];
		for(float primitive:data){
			finalData[curIndex]=primitive;
			curIndex++;
		}
		//LogHelper.debug(finalData.length/5);
		return finalData;
	}
}
