package sima214.core.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class SemiLivingEntityBase extends Entity {
	protected final String HEALTH_TAG="health";
	protected final String DEATH_TAG="death";
	protected final String HIT_TAG="hit";
	private int hitTimer;//TODO optimize their size
	private int health;
	private int deathTimer;
	protected MotionHelper moveHelper=new MotionHelper(this);
	protected AttackHelper attackHelper=new AttackHelper(this);
	public boolean isToDie;
	public SemiLivingEntityBase(World world) {
		super(world);
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!worldObj.isRemote){
			if(getHitTimer()>0)
			{
				setHitTimer(getHitTimer()-1);
			}
			if(getHealth()<=0)
			{
				isToDie=true;
				setDeathTimer(getDeathTimer()-1);
				if(getDeathTimer() <= 0)
				{
					this.setDead();
				}
			}
		}
		moveHelper.onUpdate(worldObj.isRemote);
		attackHelper.onUpdate(worldObj.isRemote);
	}
	//Handling for values
	//Client syncing, persistency
	@Override
	protected void entityInit() {
		dataWatcher.addObject(2, Integer.valueOf(0));
		dataWatcher.addObject(3, Integer.valueOf(0));
		dataWatcher.addObject(4, Integer.valueOf(0));
		dataWatcher.addObject(5, Float.valueOf(0));
		dataWatcher.addObject(6, Float.valueOf(0));
		dataWatcher.addObject(7, Float.valueOf(0));
	}
	public int getHealth(){
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
	//Motion
	public void setMotionX(double newValue){
		if(!worldObj.isRemote){
			dataWatcher.updateObject(5, Float.valueOf((float)newValue));
			this.motionX=newValue;
		}
	}
	public double getMotionX(){
		return worldObj.isRemote ? dataWatcher.getWatchableObjectFloat(5) : motionX;
	}
	public void setMotionY(double newValue){
		if(!worldObj.isRemote){
			dataWatcher.updateObject(6, Float.valueOf((float)newValue));
			this.motionY=newValue;
		}
	}
	public double getMotionY(){
		return worldObj.isRemote ? dataWatcher.getWatchableObjectFloat(6) : motionY;
	}
	public void setMotionZ(double newValue){
		if(!worldObj.isRemote){
			dataWatcher.updateObject(7, Float.valueOf((float)newValue));
			this.motionZ=newValue;
		}
	}
	public double getMotionZ(){
		return worldObj.isRemote ? dataWatcher.getWatchableObjectFloat(7) : motionZ;
	}
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		if(nbt.hasKey(HEALTH_TAG)){
			setHealth(nbt.getInteger(HEALTH_TAG));
		}
		if(nbt.hasKey(DEATH_TAG)){
			setDeathTimer(nbt.getInteger(DEATH_TAG));
		}
		if(nbt.hasKey(HIT_TAG)){
			setHitTimer(nbt.getInteger(HIT_TAG));
		}
	}
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger(HEALTH_TAG, getHealth());
		nbt.setInteger(DEATH_TAG, getDeathTimer());
		nbt.setInteger(HIT_TAG, getHitTimer());
	}
	@Override
	public boolean canBeCollidedWith() {
		return true;
	}
	@Override
	public Vec3 getLookVec() {
		float f1 = MathHelper.cos(-this.rotationYaw * 0.017453292F - (float)Math.PI);
		float f2 = MathHelper.sin(-this.rotationYaw * 0.017453292F - (float)Math.PI);
		float f3 = -MathHelper.cos(-this.rotationPitch * 0.017453292F);
		float f4 = MathHelper.sin(-this.rotationPitch * 0.017453292F);
		return Vec3.createVectorHelper(f2 * f3, f4, f1 * f3);
	}
}
