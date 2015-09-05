package sima214.sunnycraft.common.entities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import sima214.core.Logger;
import sima214.core.common.entities.SemiLivingEntityBase;

public class EntityModFireball extends SemiLivingEntityBase {
	public EntityModFireball(World world) {
		super(world);
		this.setSize(1f, 1f);
		setHealth(20);//TODO handle coniguration
		setDeathTimer(100);
	}
	public EntityModFireball(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y, z);
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
	public boolean attackEntityFrom(DamageSource damageSource, float amount) {
		Logger.info("Attacked for :"+amount);
		if(amount>5.0f && getHitTimer()<10){
			setHealth(getHealth()-1);
			setHitTimer(30);
		}
		return true;
	}
}
