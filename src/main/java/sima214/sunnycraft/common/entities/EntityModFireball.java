package sima214.sunnycraft.common.entities;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import sima214.core.Logger;
import sima214.core.common.entities.SemiLivingEntityBase;
import sima214.sunnycraft.Registry;

public class EntityModFireball extends SemiLivingEntityBase {
	public EntityModFireball(World world) {
		super(world);
		this.setSize(1f, 1f);
		setHealth(Registry.fireball_health.value);
		setDeathTimer(100);
	}
	public EntityModFireball(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y, z);
	}
	@Override
	protected void fall(float distance) {}
	@Override
	protected void updateFallState(double d, boolean b) {}
	@Override
	public boolean handleLavaMovement() {
		if(worldObj.isMaterialInBB(this.boundingBox.expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.lava)){
			int curHealth=getHealth();
			if(worldObj.getBlock((int) posX,(int) posY,(int) posZ)==Blocks.lava && curHealth<Registry.fireball_maxHealth.value){
				worldObj.setBlockToAir((int) posX,(int) posY,(int) posZ);
				setHealth(curHealth+1);
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean handleWaterMovement() {
		if (this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.4000000059604645D, 0.0D).contract(0.001D, 0.001D, 0.001D), Material.water, this)) {
			this.setDead();
			if(!worldObj.isRemote){
				this.worldObj.playSoundAtEntity(this,"random.fizz",1f,1f);
				this.worldObj.setBlock((int)posX,(int)posY, (int)posZ, Blocks.obsidian);
			}
		}
		return false;
	}
	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float amount) {
		Logger.info("Attacked for :"+amount);
		if(amount>Registry.fireball_damageRequired.value && getHitTimer()<=Registry.fireball_hitAfter.value && getHealth()>0){
			setHealth(getHealth()-1);
			setHitTimer(Registry.fireball_hit.value);
			try{
				EntityPlayer player=(EntityPlayer) damageSource.getEntity();
				player.addChatMessage(new ChatComponentText("Current health: "+getHealth()));
				Vec3 vec=player.getLookVec();
				motionX=vec.xCoord*0.1;
				motionY=vec.yCoord*0.1;
				motionZ=vec.zCoord*0.1;

			}
			catch(Exception e){
				Logger.exception("Debug", e);
			}
			return true;
		}
		return false;
	}
}
