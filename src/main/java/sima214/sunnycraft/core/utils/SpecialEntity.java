package sima214.sunnycraft.core.utils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
public abstract class SpecialEntity extends Entity {
	int hitTimer;
	int health;
	int deathTimer;
	public WanderHelper MotionHelper=new WanderHelper(this);
	public boolean isGoingToDie;
	public SpecialEntity(World world) {
		super(world);
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
				this.motionX*=0.5f;
				this.motionY*=0.5f;
				this.motionZ*=0.5f;
			}
			if(!isGoingToDie) {
				this.moveEntity(this.motionX/10.0,this.motionY/10.0,this.motionZ/10.0);
			}
			if(hitTimer<=0){
				MotionHelper.update();
			}
		}
		//LogHelper.info((worldObj.isRemote?"End Client:":"End Server")+getHealth()+" "+getHitTimer()+" "+getDeathTimer());
	}
	private void updateDeathTimer() {
		if(health<=0)
		{
			isGoingToDie=true;
			setDeathTimer(getDeathTimer()-2);
			if(getDeathTimer()<=0f)
			{
				this.setDead();
				worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY, posZ, new ItemStack(Items.blaze_rod,Math.max((int) (Math.random()*10f), 2))));
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
			setHealth(getHealth()+1);
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
	protected void entityInit() {
		dataWatcher.addObject(2, Integer.valueOf(0));
		dataWatcher.addObject(3, Integer.valueOf(0));
		dataWatcher.addObject(4, Integer.valueOf(0));
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
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		if(nbt.hasKey("hit")){
			setHitTimer(nbt.getInteger("hit"));
		}
		if(nbt.hasKey("health")){
			setHealth(nbt.getInteger("health"));
		}
		if(nbt.hasKey("death")){
			setDeathTimer(nbt.getInteger("death"));
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("hit", getHitTimer());
		nbt.setInteger("health", getHealth());
		nbt.setInteger("death", getDeathTimer());
	}
	public boolean canEntityBeSeen(Entity target)
	{
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY, this.posZ), Vec3.createVectorHelper(target.posX, target.posY , target.posZ)) == null;
	}
}