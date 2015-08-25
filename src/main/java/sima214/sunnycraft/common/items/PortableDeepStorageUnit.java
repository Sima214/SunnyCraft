package sima214.sunnycraft.common.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import sima214.core.Constants;
import sima214.core.Logger;
import sima214.core.common.ResourceItem;
import sima214.sunnycraft.Registry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PortableDeepStorageUnit extends ResourceItem {
	private final static String AMOUNT_TAG="SC_storedAmount";
	public PortableDeepStorageUnit() {
		super(1, null, "portdsu", "port_dsu", Constants.SUNCR_ID, null);
		MinecraftForge.EVENT_BUS.register(this);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List infoList, boolean b) {
		try{
			if(itemStack.stackTagCompound.hasKey(AMOUNT_TAG)){
				ItemStack storedStack=this.getStack(itemStack);
				addInfoToList(infoList, new String[]{EnumChatFormatting.AQUA+"Holds "+itemStack.stackTagCompound.getInteger(AMOUNT_TAG)+" of "+storedStack.getItem().getItemStackDisplayName(storedStack)});
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
			if(itemStack.stackTagCompound.hasKey(AMOUNT_TAG) && getStoredAmount(itemStack)>0){
				//TileEntity tile=world.getTileEntity(x, y, z);
				//if(tile!=null && ()){
				//
				//}
				//else{
				try {
					ItemStack storedStack=getStack(itemStack);
					if(storedStack.getItem().onItemUse(storedStack,player,world,x,y,z,side,p_77648_8_,p_77648_9_,p_77648_10_)&& (!player.capabilities.isCreativeMode)){
						addToStoredAmount(itemStack, -1);
					} else
						return false;
				} catch (Exception e) {
					Logger.exception("An error occured while placing a block", e);
				}
			}
			//}
			return false;
		}
		catch(NullPointerException e){
			return false;
		}
	}
	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event){//Why does the other ItemPickupEvent event exist?
		ItemStack toPickup=event.item.getEntityItem();
		for(int i=0;i<event.entityPlayer.inventory.getSizeInventory();i++){
			ItemStack curItem=event.entityPlayer.inventory.getStackInSlot(i);
			if(curItem==null){
				continue;
			}
			Item item=curItem.getItem();
			if(item instanceof PortableDeepStorageUnit){
				PortableDeepStorageUnit pdsu=(PortableDeepStorageUnit) item;
				ItemStack containedStack=pdsu.getStack(curItem);
				if(containedStack!=null && toPickup.isItemEqual(containedStack)){
					int transferedAmount=pdsu.addToStoredAmount(curItem, toPickup.stackSize);
					if(transferedAmount==toPickup.stackSize){
						event.item.setDead();
						event.setCanceled(true);
					}
					else
					{
						toPickup.stackSize-=transferedAmount;
					}
				}
			}
		}
	}
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if(!world.isRemote && Registry.portDsu_tickrate.value !=0 && world.getTotalWorldTime() % Registry.portDsu_tickrate.value==0){
			ItemStack storedStack = this.getStack(itemStack);
			if(storedStack!=null){
				EntityPlayer player=(EntityPlayer) entity;
				for(int i=0;i<player.inventory.getSizeInventory();i++){//Stays int because some people think bags aren't enough
					ItemStack curStack=player.inventory.getStackInSlot(i);
					if(curStack!=null && curStack.isItemEqual(storedStack)){
						curStack.stackSize-=this.addToStoredAmount(itemStack, curStack.stackSize);
						if(curStack.stackSize==0){
							player.inventory.setInventorySlotContents(i, null);
						}
					}
				}
			}
		}
	}
	/*
	 * @return returns the actual amount that went into the stack.
	 */
	public int addToStoredAmount(ItemStack itemStack, int amount){
		int previous=getStoredAmount(itemStack);
		int finalValue = Math.min(previous+amount,Registry.portDsu_size.value);
		itemStack.stackTagCompound.setInteger(AMOUNT_TAG, finalValue);
		if(finalValue<=0){
			clearStoredStack(itemStack);
		}
		return finalValue-previous;
	}
	public void clearStoredStack(ItemStack itemStack) {
		itemStack.stackTagCompound.removeTag("id");
		itemStack.stackTagCompound.removeTag("Count");
		itemStack.stackTagCompound.removeTag("Damage");
		itemStack.stackTagCompound.removeTag("tag");
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
	public ItemBlock getItem(ItemStack itemStack){
		try{
			return (ItemBlock) this.getStack(itemStack).getItem();
		}
		catch(Exception e){//NullPointers and ClassCast just to be safe.
			return null;
		}
	}
	public ItemStack getStack(ItemStack itemStack){
		try{
			return ItemStack.loadItemStackFromNBT(itemStack.stackTagCompound);
		}
		catch(NullPointerException e){
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
