package sima214.sunnycraft.common.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import sima214.core.Constants;
import sima214.core.Logger;
import sima214.core.common.ResourceItem;
import sima214.sunnycraft.Registry;
import sima214.sunnycraft.common.utils.PDSUHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PortableDeepStorageUnit extends ResourceItem {
	private final static String AMOUNT_TAG="SC_storedAmount";
	public PortableDeepStorageUnit() {
		super(1, null, "portdsu", "white", Constants.SUNCR_ID, null);
		MinecraftForge.EVENT_BUS.register(this);
	}
	private final static String[] modes=new String[]{
		"normal",
		"locked",
		"output to inventory(locked)",
		"pull from inventory(locked)"
	};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List infoList, boolean b) {
		try{
			if(itemStack.stackTagCompound.hasKey(AMOUNT_TAG)){
				ItemStack storedStack=PortableDeepStorageUnit.getStack(itemStack);
				addInfoToList(infoList, new String[]{EnumChatFormatting.AQUA+"Holds "+itemStack.stackTagCompound.getInteger(AMOUNT_TAG)+" of "+storedStack.getItem().getItemStackDisplayName(storedStack),"Current mode: "+modes[itemStack.getItemDamage()]});
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
				int damage = itemStack.getItemDamage();
				if((damage==2||damage==3)){
					TileEntity tile=world.getTileEntity(x, y, z);
					if(tile!=null && tile instanceof IInventory){
						if(itemStack.getItemDamage()==2)
							return PDSUHelper.pushContents(itemStack,(IInventory) tile,side);
						else if(itemStack.getItemDamage()==3)
							return PDSUHelper.pullContents(itemStack,(IInventory) tile,side);
					}
				}
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
				ItemStack containedStack=PortableDeepStorageUnit.getStack(curItem);
				if(containedStack!=null){
					if(tryAddStack(curItem, containedStack, toPickup)){
						event.item.setDead();
						event.setCanceled(true);
					}
				}
			}
		}
	}
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if(!world.isRemote && Registry.portDsu_tickrate.value !=0 && world.getTotalWorldTime() % Registry.portDsu_tickrate.value==0){
			ItemStack storedStack = PortableDeepStorageUnit.getStack(itemStack);
			if(storedStack!=null){
				EntityPlayer player=(EntityPlayer) entity;
				for(int i=0;i<player.inventory.getSizeInventory();i++){//Stays int because some people think bags aren't enough
					ItemStack curStack=player.inventory.getStackInSlot(i);
					if(curStack!=null && tryAddStack(itemStack,storedStack, curStack)){
						player.inventory.setInventorySlotContents(i, null);
					}
				}
			}
		}
	}
	//@Override
	//public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
	//	player.addChatMessage(new ChatComponentText("What are you doing?"));
	//	return false;
	//}TODO
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return false;
	}
	/*
	 * @return returns the actual amount that went into the stack.
	 */
	public static int addToStoredAmount(ItemStack itemStack, int amount){
		int previous=getStoredAmount(itemStack);
		int finalValue = Math.min(previous+amount,Registry.portDsu_size.value);
		itemStack.stackTagCompound.setInteger(AMOUNT_TAG, finalValue);
		if(finalValue<=0){
			clearStoredStack(itemStack);
		}
		return finalValue-previous;
	}
	public static void clearStoredStack(ItemStack itemStack) {
		if(itemStack.getItemDamage()==0){
			itemStack.stackTagCompound.removeTag("id");
			itemStack.stackTagCompound.removeTag("Count");
			itemStack.stackTagCompound.removeTag("Damage");
			itemStack.stackTagCompound.removeTag("tag");
		}
	}
	public static int changeStoredAmount(ItemStack itemStack, int newAmount){
		int finalValue = Math.min(newAmount,Registry.portDsu_size.value);
		itemStack.stackTagCompound.setInteger(AMOUNT_TAG, finalValue);
		return finalValue;
	}
	public static void setStack(ItemStack portDSU,ItemStack itemStack){
		initializeTag(portDSU);
		itemStack=itemStack.copy();
		itemStack.stackSize=1;
		itemStack.writeToNBT(portDSU.stackTagCompound);
	}
	public static ItemBlock getItem(ItemStack itemStack){
		try{
			return (ItemBlock) PortableDeepStorageUnit.getStack(itemStack).getItem();
		}
		catch(Exception e){//NullPointers and ClassCast just to be safe.
			return null;
		}
	}
	public static ItemStack getStack(ItemStack itemStack){
		try{
			return ItemStack.loadItemStackFromNBT(itemStack.stackTagCompound);
		}
		catch(NullPointerException e){
			return null;
		}
	}
	public static int getStoredAmount(ItemStack itemStack){
		try{
			return itemStack.stackTagCompound.getInteger(AMOUNT_TAG);
		}
		catch(NullPointerException e){
			return 0;
		}
	}
	/*
	 * @Returns true if you need to clean up the stack
	 */
	public static boolean tryAddStack(ItemStack mainStack,ItemStack containedStack,ItemStack toAdd){
		if(isItemValid(containedStack, toAdd)){
			int transferedAmount=addToStoredAmount(mainStack, toAdd.stackSize);
			toAdd.stackSize-=transferedAmount;
			return toAdd.stackSize==0;
		}
		return false;
	}
	public static boolean isItemValid(ItemStack contained,ItemStack iS){
		return iS.isItemEqual(contained) && (!iS.hasTagCompound());
	}
	public static ItemStack changeMode(ItemStack pdsu){
		ItemStack result=pdsu.copy();
		int newDamage=result.getItemDamage()+1;
		if(newDamage > (modes.length-1)){
			newDamage=0;
		}
		result.setItemDamage(newDamage);
		return result;
	}
	private static void initializeTag(ItemStack itemStack){
		if(itemStack.stackTagCompound==null){
			itemStack.stackTagCompound=new NBTTagCompound();
		}
	}
}
