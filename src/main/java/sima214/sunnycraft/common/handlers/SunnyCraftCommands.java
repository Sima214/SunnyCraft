package sima214.sunnycraft.common.handlers;

import cpw.mods.fml.common.Loader;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

public class SunnyCraftCommands extends CommandBase {

	public SunnyCraftCommands() {
	}
	@Override
	public String getCommandName() {
		return "sunnycraft";
	}
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/sunnycraft <info>";
	}
	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length==0){
			sender.addChatMessage(new ChatComponentText("Not enough arguments."));
			return;
		}
		if(args[0].equals("info")){
			getInfo(sender,args);
		}
		else
		{
			sender.addChatMessage(new ChatComponentText("Invalid arguments: "+args[0]));
		}
	}
	private void getInfo(ICommandSender sender, String[] args) {
		if(sender instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) sender;
			ItemStack heldStack = player.inventory.getCurrentItem();
			if(heldStack!=null){
				sender.addChatMessage(new ChatComponentText("Unlocalized name: "+heldStack.getUnlocalizedName()));
				sender.addChatMessage(new ChatComponentText("Class is: "+heldStack.getItem().getClass().toString()));
				if(Loader.isModLoaded("Botania") && heldStack.getItem() instanceof ItemFood){
					ItemFood food=(ItemFood) heldStack.getItem();
					int val =food.func_150905_g(heldStack);
					sender.addChatMessage(new ChatComponentText("Provided mana from this food: "+(val * val * 64)));
				}
			}else {
				sender.addChatMessage(new ChatComponentText("You are not holding an item."));
			}
		}else{
			sender.addChatMessage(new ChatComponentText("You are not a player."));
		}
	}
}
