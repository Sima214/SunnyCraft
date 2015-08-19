package sima214.core.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.config.Configuration;
import sima214.core.SimaCoreMain;
import sima214.core.common.config.IConfigElement;
public class WorldGenSimple implements IConfigElement{
	protected Block ore;
	public int[] validDim;
	public short maxOre;//If you want to put more than 2^15-1 ores in chunk, then that's your problem.
	public short maxTries;
	public short minHeight;
	public short maxHeight;
	public boolean enabled=true;
	public WorldGenSimple(Block ore,int validDim[],short maxOre,short maxTries,short minHeight,short maxHeight) {
		this.ore=ore;
		this.validDim=validDim;
		this.maxOre=maxOre;
		this.maxTries=maxTries;
		this.minHeight=minHeight;
		this.maxHeight=maxHeight;
		SimaCoreMain.registerSimpleOre(this);
	}
	@Override
	public void load(Configuration forgeConfig) {
		String category=ore.getUnlocalizedName();
		validDim=forgeConfig.get(category, "Valid dimensions", validDim).getIntList();
		maxOre=(short) forgeConfig.getInt("Max ore per chunk", category, maxOre, 0, Short.MAX_VALUE, "");
		maxTries=(short) forgeConfig.getInt("Max times per chunk", category, maxTries, 0, Short.MAX_VALUE, "Max amount of times the generator will try to generate an ore before giving up.");
		minHeight=(short) forgeConfig.getInt("Min height",category,minHeight,0,127,"");
		maxHeight=(short) forgeConfig.getInt("Max height",category,maxHeight,0,127,"");
		enabled=forgeConfig.getBoolean("enabled", category, enabled, "Set to false to disable");

	}
	public void generate(Random random, int[] chunkCoords, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
		if(!enabled||!checkDim(world.provider.dimensionId))
			return;
		short lastTries=0;
		for(short i=1;i<=maxTries;i++){
			if(lastTries>maxOre){
				//Logger.debug(i+"#Reached max number of ores: "+lastTries);
				break;
			}
			byte randomHeight=(byte) random.nextInt(127);
			if(randomHeight<=maxHeight&&randomHeight>=minHeight){
				int[] finalCoords={(chunkCoords[0]<<4)+random.nextInt(15),randomHeight,(chunkCoords[1]<<4)+random.nextInt(15)};
				if(canGenerateAt(world, finalCoords)){
					world.setBlock(finalCoords[0], finalCoords[1], finalCoords[2],  ore);
					lastTries++;
					//Logger.debug(i+"#Success at: "+finalCoords[0]+", "+finalCoords[1]+", "+finalCoords[2]);
				}
				//else{Logger.debug(i+"#Location not appopriate: "+finalCoords+", "+world.getBlock(finalCoords[0], finalCoords[1], finalCoords[2]).getUnlocalizedName());}
			}
			//else{Logger.debug(i+"#Not appopriate height: "+randomHeight);}
		}
	}
	private boolean checkDim(int dimensionId) {//Check perfomance
		for(int dim:validDim){
			if(dim==dimensionId)
				return true;
		}
		return false;
	}
	private boolean canGenerateAt(World world,int[] coords){
		Block toReplace =world.getBlock(coords[0], coords[1], coords[2]);
		switch (world.provider.dimensionId) {
		case 1:return toReplace==Blocks.end_stone;//The end
		case -1:return toReplace==Blocks.netherrack||toReplace.isReplaceableOreGen(world,coords[0], coords[1], coords[2], Blocks.netherrack);//The nether
		default:return toReplace.isReplaceableOreGen(world,coords[0], coords[1], coords[2], Blocks.stone);//Everything else
		}
	}
}
