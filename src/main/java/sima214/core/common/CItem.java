package sima214.core.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sima214.core.TestEntity;

public class CItem extends ResourceItem {

	public CItem(int maxStackSize, EnumRarity rarity, String name,String textureName, String modid, String[] tooltip) {
		super(maxStackSize, rarity, name, textureName, modid, tooltip);
	}
	@Override
	public boolean onItemUse(ItemStack iStack, EntityPlayer entityPlayer,World world, int x, int y, int z,int p_77648_7_, float p_77648_8_, float p_77648_9_,float p_77648_10_) {
		world.spawnEntityInWorld(new TestEntity(world, x, y+1, z));
		return true;
	}
}
