package sima214.core.common;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ResourceItem extends Item {

	protected String modid;
	protected EnumRarity rarity;
	public ResourceItem(int maxStackSize,EnumRarity rarity,String name,String textureName,String oreDict,String modid)
	{
		this.setMaxStackSize(maxStackSize).setTextureName(textureName).setUnlocalizedName(name);
		OreDictionary.registerOre(oreDict, this);
		if(rarity==null){
			rarity=EnumRarity.common;
		}
		this.rarity=rarity;
		this.modid=modid;
	}
	@Override
    @SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon=iconRegister.registerIcon(modid+":"+this.iconString);
	}
	@Override
	public EnumRarity getRarity(ItemStack itemStack) {
		return rarity;
	}
}
