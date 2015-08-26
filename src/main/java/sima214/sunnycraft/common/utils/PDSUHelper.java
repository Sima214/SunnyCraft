package sima214.sunnycraft.common.utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class PDSUHelper {
	public static boolean pushContents(ItemStack itemStack, TileEntity tile, int side) {
		boolean sided=false;
		IInventory inventory=(IInventory)tile;
		if(tile instanceof ISidedInventory){
			sided=true;
		}
		for(int i=0;i<inventory.getSizeInventory();i++){
			
		}
		return false;
	}

	public static boolean pullContents(ItemStack itemStack, TileEntity tileEntity, int side) {
		return false;
	}

}
