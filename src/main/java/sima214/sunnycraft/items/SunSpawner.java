package sima214.sunnycraft.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import sima214.sunnycraft.entities.SunEntity;

public class SunSpawner extends Item {
	public SunSpawner()
	{
		super();
		this.setUnlocalizedName("sunspawner");
	}
	@Override
	public boolean onItemUse(ItemStack item_stack, EntityPlayer player, World world, int x, int y, int z, int side, float fx, float fy, float fz)
	{
		if (!world.isRemote){
			AxisAlignedBB bound =AxisAlignedBB.getBoundingBox(x-256, y-256, z-256, x+256, y+256, z+256);
			@SuppressWarnings("unchecked")
			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player,bound);
			for(Entity cur : list)
			{
				cur.setDead();
			};
			if (side == 0)
			{
				--y;
			}

			if (side == 1)
			{
				++y;
			}

			if (side == 2)
			{
				--z;
			}

			if (side == 3)
			{
				++z;
			}

			if (side == 4)
			{
				--x;
			}

			if (side == 5)
			{
				++x;
			}
			SunEntity entity=new SunEntity(world, x+fx, y+1f, z+fz);
			world.spawnEntityInWorld(entity);
		};
		return true;
	}
}
