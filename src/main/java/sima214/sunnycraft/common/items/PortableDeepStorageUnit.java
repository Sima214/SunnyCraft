package sima214.sunnycraft.common.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import sima214.core.Constants;
import sima214.core.Logger;
import sima214.core.common.ResourceItem;
import sima214.sunnycraft.Registry;

public class PortableDeepStorageUnit extends ResourceItem {
	private final static String ITEM_TAG="storedItem";
	private final static String AMOUNT_TAG="storedAmount";
	public PortableDeepStorageUnit() {
		super(1, null, "portdsu", "port_dsu", Constants.SUNCR_ID, null);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List infoList, boolean b) {
		if(itemStack.stackTagCompound.hasKey(ITEM_TAG)){
			ItemStack stack=ItemStack.loadItemStackFromNBT(itemStack.stackTagCompound.getCompoundTag(ITEM_TAG));
			Item item=stack.getItem();
			addInfoToList(infoList, new String[]{EnumChatFormatting.AQUA+"Holds "+itemStack.stackTagCompound.getInteger(AMOUNT_TAG)+" of "+item.getItemStackDisplayName(stack)});
		}
	}
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		if(itemStack.stackTagCompound.hasKey(ITEM_TAG)&&itemStack.stackTagCompound.getInteger(AMOUNT_TAG)>0){
			try {
				ItemStack storedItem=ItemStack.loadItemStackFromNBT(itemStack.stackTagCompound.getCompoundTag(ITEM_TAG));
				Item item=storedItem.getItem();
				if(item.onItemUse(storedItem,player,world,x,y,z,side,p_77648_8_,p_77648_9_,p_77648_10_)){
					changeStoredAmount(itemStack, -1);
				} else
					return false;
			} catch (Exception e) {
				Logger.exception("An error occured while placing a block", e);
			}
		}
		return false;
	}
	public void changeStoredAmount(ItemStack itemStack, int amount){
		int finalValue = Math.min(itemStack.stackTagCompound.getInteger(AMOUNT_TAG)+amount,Registry.portDsu_size.value);
		itemStack.stackTagCompound.setInteger(AMOUNT_TAG, finalValue);
	}
}
