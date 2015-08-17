package sima214.core.common;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import sima214.core.Logger;
import sima214.core.common.config.ConfigHelper;
import cpw.mods.fml.common.IWorldGenerator;
/*
 * NOTE YOU MUST REGISTER STUFF HERE AS EARLY AS CONSTRUCTION(before simacore gets pre-init)
 */
public class OreGenHelper implements IWorldGenerator{
	public ArrayList<WorldGenSimple> simple=new ArrayList<WorldGenSimple>();
	public ConfigHelper simpleCfg=new ConfigHelper("SimpleOreGen");
	public OreGenHelper() {
	}
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		Logger.debug(chunkX+" : "+chunkZ);
		for(WorldGenSimple wgs:simple){
			wgs.generate(random,chunkX,chunkZ,world,chunkGenerator,chunkProvider);
		}
	}
	public void prepareConfig() {
		simpleCfg.addElements((WorldGenSimple[]) simple.toArray());
	}
	public void add(WorldGenSimple reg){
		simple.add(reg);
	}
	public void add(WorldGenSimple[] reg){
		for(WorldGenSimple e:reg){
			simple.add(e);
		}
	}
}
