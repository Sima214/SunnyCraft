package sima214.core.common.entities;

import java.util.Random;

import sima214.sunnycraft.Registry;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;


public class MotionHelper {

	protected final SemiLivingEntityBase host;
	protected Random rand=new Random();
	protected float targetPitch;
	protected float targetYaw;
	protected int timer;
	protected float curStep;
	protected final static float min_step=1f;
	public MotionHelper(SemiLivingEntityBase semiLivingEntityBase) {
		this.host=semiLivingEntityBase;
		resetDirection();
	}
	public void onUpdate(boolean isRemote){
		onCommon();
		if(isRemote){
			onClient();
		}
		else{
			onServer();
		}
	}
	public void onCommon(){
		host.moveEntity(host.getMotionX(), host.getMotionY(), host.getMotionZ());
	}
	public void onClient(){

	}
	public void onServer(){
		if(!host.isToDie() && handleAfterHit()){
			wanderRandomly();
		}
	}
	protected boolean handleAfterHit(){
		if(host.getHitTimer() > (Registry.fireball_hitAfter.value/2)){
			host.setVelocity(0.8f);
			return false;
		}
		else if (host.getHitTimer() == (Registry.fireball_hitAfter.value/2)) {
			timer=-1;
			return false;
		}
		return true;
	}
	protected void wanderRandomly(){
		timer--;
		if(timer<=0){
			resetDirection();
		}
		host.rotationPitch=targetPitch;
		host.rotationYaw=targetYaw;
		setHostMotionByCurRotation();
	}
	protected void resetDirection() {
		timer=rand.nextInt(200);
		curStep=min_step;//TODO
		this.targetPitch=MathHelper.wrapAngleTo180_float(rand.nextInt(360));
		this.targetYaw=MathHelper.wrapAngleTo180_float(rand.nextInt(360));
	}
	protected void setHostMotionByCurRotation() {
		Vec3 vec=host.getLookVec();
		host.setVelocity(vec, 0.1f);
	}
}
