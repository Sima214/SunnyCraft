package sima214.sunnycraft.common.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import sima214.core.Constants;
import sima214.core.Logger;
import sima214.core.common.ResourceItem;
import sima214.sunnycraft.Registry;

public class PortableDeepStorageUnit extends ResourceItem {
	private final static String AMOUNT_TAG="SC_storedAmount";
	public PortableDeepStorageUnit() {
		super(1, null, "portdsu", "port_dsu", Constants.SUNCR_ID, null);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List infoList, boolean b) {
		try{
			if(itemStack.stackTagCompound.hasKey(AMOUNT_TAG)){
				Item item=getStack(itemStack);
				addInfoToList(infoList, new String[]{EnumChatFormatting.AQUA+"Holds "+itemStack.stackTagCompound.getInteger(AMOUNT_TAG)+" of "+item.getItemStackDisplayName(new ItemStack(item))});
			}
		}
		catch(NullPointerException e){
			//No-op
		}
	}
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		try{
			if(itemStack.stackTagCompound.hasKey(AMOUNT_TAG)&&getStoredAmount(itemStack)>0){
				try {
					ItemBlock item=getStack(itemStack);
					if(item.onItemUse(new ItemStack(item),player,world,x,y,z,side,p_77648_8_,p_77648_9_,p_77648_10_)&& (!player.capabilities.isCreativeMode)){
						addToStoredAmount(itemStack, -1);
					} else
						return false;
				} catch (Exception e) {
					Logger.exception("An error occured while placing a block", e);
				}
			}
			return false;
			}
			catch(NullPointerException e){
				return false;
			}
	}
	/*
	 * @return returns the actual amount that went into the stack.
	 */
	public int addToStoredAmount(ItemStack itemStack, int amount){//TODO don't steal when you are out of space
		int previous=getStoredAmount(itemStack);
		int finalValue = Math.min(previous+amount,Registry.portDsu_size.value);
		itemStack.stackTagCompound.setInteger(AMOUNT_TAG, finalValue);
		return finalValue-previous;
	}
	public int changeStoredAmount(ItemStack itemStack, int newAmount){
		int finalValue = Math.min(newAmount,Registry.portDsu_size.value);
		itemStack.stackTagCompound.setInteger(AMOUNT_TAG, finalValue);
		return finalValue;
	}
	public void setStack(ItemStack portDSU,ItemStack itemStack){
		initializeTag(portDSU);
		itemStack=itemStack.copy();
		itemStack.stackSize=1;
		itemStack.writeToNBT(portDSU.stackTagCompound);
	}
	public ItemBlock getStack(ItemStack itemStack){
		try{
			return (ItemBlock) ItemStack.loadItemStackFromNBT(itemStack.stackTagCompound).getItem();
		}
		catch(Exception e){//NullPointers and ClassCast just to be safe.
			return null;
		}
	}
	public int getStoredAmount(ItemStack itemStack){
		try{
			return itemStack.stackTagCompound.getInteger(AMOUNT_TAG);
		}
		catch(NullPointerException e){
			return 0;
		}
	}
	private void initializeTag(ItemStack itemStack){
		if(itemStack.stackTagCompound==null){
			itemStack.stackTagCompound=new NBTTagCompound();
		}
	}
}
