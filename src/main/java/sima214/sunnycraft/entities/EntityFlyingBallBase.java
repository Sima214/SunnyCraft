package sima214.sunnycraft.entities;

import java.util.Collection;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public abstract class EntityFlyingBallBase extends EntityLivingBase{//Extends living for compact

	public EntityFlyingBallBase(World world) {
		super(world);
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
		updateCommon();
		if(worldObj.isRemote){
			updateClient();}
		else{
			updateServer();}
	}
	protected abstract void updateCommon();
	protected abstract void updateClient();
	protected abstract void updateServer();
	protected abstract void onCollideWith(Entity entity);
	protected abstract void onWater();
	protected abstract boolean onAttackFrom(DamageSource type, float amount);
	protected abstract void onLava();
	protected abstract void save(NBTTagCompound nbt);
	protected abstract void load(NBTTagCompound nbt);
	@Override
	public abstract void onCollideWithPlayer(EntityPlayer player);
	@Override
	protected void updateFallState(double p_70064_1_, boolean p_70064_3_) {
		//No fall damage,we fly!...
	}
	@Override
	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}
	@Override
	public AxisAlignedBB getCollisionBox(Entity entity) {
		return boundingBox;
	}
	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}
	@Override
	public boolean canBePushed() {
		return true;
	}
	@Override
	public boolean handleWaterMovement() {
		if(super.handleWaterMovement()){
			onWater();
			return true;
		}
		return false;
	}
	@Override
	public boolean handleLavaMovement() {
		if(super.handleLavaMovement()){
			onLava();
			return true;
		}
		return false;
	}
	@Override
	protected void setBeenAttacked() {}
	@Override
	public boolean attackEntityFrom(DamageSource type, float amount) {
		if (ForgeHooks.onLivingAttack(this, type, amount)) {
			return false;
		}
		return onAttackFrom(type,amount);
	}
	@Override
	protected void applyEntityAttributes() {
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.maxHealth);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4000);
	}
	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}
	@Override
	protected int decreaseAirSupply(int what) {
		return what;}
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setFloat("HealF", this.getHealth());
		nbt.setInteger("HurtTime", this.hurtTime);
		nbt.setInteger("DeathTime", this.deathTime);
		save(nbt);
	}
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		setHealth(nbt.getFloat("HealF"));
		hurtTime=nbt.getInteger("HurtTime");
		deathTime=nbt.getInteger("DeathTime");
		load(nbt);
	}
	@Override
	protected void updatePotionEffects() {}//No potions
	@Override
	public void clearActivePotions() {}
	@SuppressWarnings("rawtypes")
	@Override
	public Collection getActivePotionEffects() {
		return null;//Hope for null checks
	}
	@Override
	public boolean isPotionActive(int p_82165_1_) {
		return false;
	}
	@Override
	public boolean isPotionActive(Potion p_70644_1_) {
		return false;
	}
	@Override
	public PotionEffect getActivePotionEffect(Potion p_70660_1_) {
		return null;
	}
	@Override
	public void addPotionEffect(PotionEffect p_70690_1_) {}
	@Override
	public boolean isPotionApplicable(PotionEffect p_70687_1_) {
		return false;
	}
	@Override
	public boolean isEntityUndead() {
		return false;
	}
	@Override
	public void removePotionEffect(int p_82170_1_) {}
	@Override
	public void removePotionEffectClient(int p_70618_1_) {}
	@Override
	protected void onNewPotionEffect(PotionEffect p_70670_1_) {}
	@Override
	protected void onChangedPotionEffect(PotionEffect p_70695_1_, boolean p_70695_2_) {}
	@Override
	protected void onFinishedPotionEffect(PotionEffect p_70688_1_) {}
	@Override
	public void heal(float p_70691_1_) {}
	@Override
	protected void fall(float p_70069_1_) {}
	@Override
	protected float applyArmorCalculations(DamageSource p_70655_1_, float p_70655_2_) {
		return p_70655_2_;
	}
	@Override
	protected float applyPotionDamageCalculations(DamageSource p_70672_1_, float p_70672_2_) {
		return p_70672_2_;
	}
	@Override
	protected void damageEntity(DamageSource p_70665_1_, float p_70665_2_) {
	}
	@Override
	protected void kill() {//Let's be quick about this
		this.setDead();
	}
	@Override
	public void setSprinting(boolean p_70031_1_) {}
	@Override
	public void setSneaking(boolean p_70095_1_) {}
	@Override
	protected void jump() {}
	@Override
	public void onLivingUpdate() {//Why extra updates?
	}
	@Override
	protected void collideWithEntity(Entity entity) {
		onCollideWith(entity);
	}
	@Override
	public void onItemPickup(Entity p_71001_1_, int p_71001_2_) {}
	@Override
	public void curePotionEffects(ItemStack curativeItem) {}








	@Override
	public ItemStack getHeldItem() {
		return null;
	}
	@Override
	public ItemStack getEquipmentInSlot(int p_71124_1_) {
		return null;
	}
	@Override
	public void setCurrentItemOrArmor(int p_70062_1_, ItemStack p_70062_2_) {

	}
	@Override
	public ItemStack[] getLastActiveItems() {
		return null;
	}

}
