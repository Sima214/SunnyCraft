package sima214.core.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.config.Configuration;
import sima214.core.common.config.IConfigElement;
public class WorldGenSimple implements IConfigElement{
	protected Block ore;
	public int[] validDim;
	public short maxOre;//If you want to put more than 2^15-1 ores in chunk, then that's yours problem.
	public short maxTries;
	public WorldGenSimple(Block ore,int validDim[],short maxOre,short maxTries) {
		this.ore=ore;
		this.validDim=validDim;
		this.maxOre=maxOre;
		this.maxTries=maxTries;
	}
	@Override
	public void load(Configuration forgeConfig) {
		
	}
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
		
	}
}
