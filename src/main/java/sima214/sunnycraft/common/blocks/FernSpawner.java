package sima214.sunnycraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sima214.core.Constants;
import sima214.core.common.ResourceBlock;
import sima214.sunnycraft.common.entities.EntityModFireball;

public class FernSpawner extends ResourceBlock {
	private static final int color=0xffff5400;
	public FernSpawner() {
		super(new float[]{0.2f,0f,0.2f,0.8f,0.95f,0.8f}, 0.1f, 1f, "fern_spawner", "fern", Block.soundTypeGrass, Constants.SUNCR_ID, Material.plants);
	}
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world,int x, int y, int z) {
		return null;
	}
	@Override
	public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
		return false;
	}
	public boolean canBePlacedOn(Block block){
		return block==Blocks.netherrack || block==Blocks.soul_sand;
	}
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return canBePlacedOn(world.getBlock(x, y-1, z));//TODO add only at the nether
	}
	@Override
	public int getRenderType() {
		return 1;
	}
	@Override
	public boolean isOpaqueCube() {
		return false;

	}
	@Override
	public boolean renderAsNormalBlock() {
		return false;
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
	@Override
	public boolean onBlockActivated(World world, int x,int y, int z, EntityPlayer player,int side, float localX, float localY,float localZ) {
		int index=player.inventory.currentItem;
		byte curMeta=(byte) world.getBlockMetadata(x, y, z);
		ItemStack curStack=player.inventory.getStackInSlot(index);
		if(curStack!=null && curStack.getItem()==Items.bone) {
			;
		}
		{
			if(curMeta==15){
				//TODO spawn the thing
				if(world.isRemote){
					player.addChatMessage(new ChatComponentText("You feel like something is coming..."));//Temporary message used to debug.
				}
				world.spawnEntityInWorld(new EntityModFireball(world, x, y+1, z));
				curMeta=-1;
			}
			world.setBlockMetadataWithNotify(x, y, z, curMeta+1, 3);
			if(!player.capabilities.isCreativeMode){
				player.inventory.decrStackSize(index, 1);
			}
			return true;
		}
	}
}
