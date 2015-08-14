package sima214.core;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TestEntity extends Entity {

	public TestEntity(World world) {
		super(world);
		this.setSize(0.5f, 0.5f);
	}
	public TestEntity(World world,double x,double y,double z) {
		super(world);
		this.setPosition(x, y, z);
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		// TODO Auto-generated method stub

	}

}
