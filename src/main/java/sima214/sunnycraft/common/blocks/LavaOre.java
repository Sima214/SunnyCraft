package sima214.sunnycraft.common.blocks;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import sima214.core.common.ResourceBlock;
import sima214.sunnycraft.Registry;

public class LavaOre extends ResourceBlock {
	public LavaOre(float[] bounds, float hardness, float resistance, String name, String textureName, SoundType sound, String modid) {
		super(bounds, hardness, resistance, name, textureName, sound, modid, Material.rock);
		setHarvestLevel("pickaxe", 3);
	}
	@Override
	public void onEntityWalking(World world, int x,int y, int z, Entity ent) {
		// TODO set entity on special fire
	}
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		//world.setBlock(x, y, z, Blocks.flowing_lava);TODO make it not annoying.
	}
	@Override
	public boolean canDropFromExplosion(Explosion exp) {
		return false;
	}
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret=new ArrayList<ItemStack>(1);
		ItemStack itemStack = new ItemStack(Registry.lavadust, (int) (1+ (world.rand.nextFloat()*fortune)/2));
		ret.add(itemStack);
		return ret;
	}
	@Override
	protected boolean canSilkHarvest() {
		return true;
		
	}
}
