package sima214.sunnycraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sima214.core.Constants;
import sima214.core.Logger;
import sima214.core.common.ResourceBlock;

public class FernSpawner extends ResourceBlock {
	private static final int color=0xffff5400;
	public FernSpawner() {
		super(new float[]{0.2f,0f,0.2f,0.8f,0.95f,0.8f}, 0.1f, 1f, "fern_spawner", "fern", Block.soundTypeGrass, Constants.SUNCR_ID, Material.plants);
	}
	@Override
	public int getRenderType() {
		return 1;
	}
@Override
public AxisAlignedBB getCollisionBoundingBoxFromPool(World world,int x, int y, int z) {
	return null;
}
@Override
public int getBlockColor() {
	return color;
}
@Override
public int getRenderColor(int i) {
	return color;
}
@Override
public int colorMultiplier(IBlockAccess iBlockAccess, int x,int y, int z) {
	return color|0xffffff;
}
}
