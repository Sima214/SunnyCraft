package sima214.core.common;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
/*
 * A lighter version of oregen.
 * Only good if you want to generate one-ore patches distributed randomly accross a chunk.
 */
public class WorldGenLight implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
	}

}
