package sima214.sunnycraft.entities;

import sima214.sunnycraft.core.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class SunEntity extends Entity {
	private int hitTimer;
	private int health;
	private int deathTimer;
	private boolean isBoss;
	public SunEntity(World world) {
		super(world);
		this.setSize(getSizeByType(),getSizeByType());
		this.width=getSizeByType();
		this.height=getSizeByType();
		setHealth(20);
		setDeathTimer(100);
	}
	public SunEntity(World world, float x, float y, float z) {
		this(world);
		setPosition(x, y, z);
	}
	private float getSizeByType(){
		return isBoss?8f:1f;
	}
	@Override
	public void onUpdate() {
		//LogHelper.info((worldObj.isRemote?"Client:":"Server:")+getHealth()+" "+getHitTimer()+" "+getDeathTimer());
		super.onUpdate();
		if (!worldObj.isRemote){
			updateDeathTimer();
			if(getHitTimer()>0)
			{
				setHitTimer(getHitTimer()-1);
			}
		}
		//LogHelper.info((worldObj.isRemote?"End Client:":"End Server")+getHealth()+" "+getHitTimer()+" "+getDeathTimer());
	}
	private void updateDeathTimer() {
		if(health<=0)
		{
			setDeathTimer(getDeathTimer()-1);
			if(getDeathTimer()<=0f)
			{
				this.setDead();
			}
		}
	}
	@Override
	public boolean canBeCollidedWith() {
		return true;

	}
	@Override
	protected void fall(float distance) {}
	@Override
	protected void updateFallState(double p_70064_1_, boolean p_70064_3_) {}
	@Override
	public boolean handleLavaMovement() {
		if(worldObj.isMaterialInBB(this.boundingBox.expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.lava)){

		}
		return false;
	}
	@Override
	public boolean handleWaterMovement() {
		if (this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.4000000059604645D, 0.0D).contract(0.001D, 0.001D, 0.001D), Material.water, this)) {
			this.setDead();
			if(!worldObj.isRemote){
				this.worldObj.playSoundAtEntity(this,"random.fizz",1f,1f);
				this.worldObj.setBlock((int)posX,(int)posY, (int)posZ, Block.getBlockFromName("obsidian"));
			}
		}
		return false;
	}
	@Override
	public boolean attackEntityFrom(DamageSource p_70097_1_, float amount) {
			if(amount>5.0f&&getHitTimer()<10){
			LogHelper.info("Attacked for :"+amount);
			setHealth(getHealth()-1);
			setHitTimer(30);}
			return true;
	}
	@Override
	protected void entityInit() {
		dataWatcher.addObject(2, Integer.valueOf(0));
		dataWatcher.addObject(3, Integer.valueOf(0));
		dataWatcher.addObject(4, Integer.valueOf(0));
	}
	public int getHealth(){//TODO Reduce usage of data watcher
		return worldObj.isRemote?dataWatcher.getWatchableObjectInt(2):health;
	}
	public void setHealth(int amount) {
		if(!worldObj.isRemote){
			dataWatcher.updateObject(2, Integer.valueOf(amount));
			this.health=amount;
		}
	}
	public void setHitTimer(int amount) {
		if(!worldObj.isRemote){
			dataWatcher.updateObject(3, Integer.valueOf(amount));
			this.hitTimer=amount;
		}
	}
	public int getHitTimer() {
		return worldObj.isRemote?dataWatcher.getWatchableObjectInt(3):hitTimer;
	}
	public void setDeathTimer(int amount) {
		if(!worldObj.isRemote){
			dataWatcher.updateObject(4, Integer.valueOf(amount));
			this.deathTimer=amount;
		}
	}
	public int getDeathTimer() {
		return worldObj.isRemote?dataWatcher.getWatchableObjectInt(4):deathTimer;
	}
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
	}
}