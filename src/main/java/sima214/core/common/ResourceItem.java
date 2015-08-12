package sima214.core.common;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ResourceItem extends Item {
	protected String[] tooltip;
	protected String modid;
	protected EnumRarity rarity;
	public ResourceItem(int maxStackSize,EnumRarity rarity,String name,String textureName,String modid,String[] tooltip)
	{
		this.setMaxStackSize(maxStackSize).setTextureName(textureName).setUnlocalizedName(name);
		if(rarity==null){
			rarity=EnumRarity.common;
		}
		this.rarity=rarity;
		this.modid=modid;
		this.tooltip=tooltip;
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
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer,@SuppressWarnings("rawtypes") List infoList, boolean b) {
		addInfoToList(infoList, tooltip);
	}
	public void addInfoToList(List<String> infoList,String[] info){
		for(String curLine:info){
		infoList.add(curLine);
		}
	}
	public void addToOreDict(String oreDict) {
		OreDictionary.registerOre(oreDict, this);
	}
}
