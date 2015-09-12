package sima214.core.common.entities;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;


public class MotionHelper {

	protected final SemiLivingEntityBase host;
	protected Random rand=new Random();
	protected float targetPitch;
	protected float targetYaw;
	protected int timer;
	protected boolean hasBeenHit=false;
	private final static float min_step=1f;
	public MotionHelper(SemiLivingEntityBase semiLivingEntityBase) {
		this.host=semiLivingEntityBase;
		resetDirection();
	}
	public void onUpdate(boolean isRemote){
		if(host.getHitTimer()>1){
			host.motionX*=0.8d;
			host.motionY*=0.8d;
			host.motionZ*=0.8d;
			hasBeenHit=true;
		}
		else if(host.getHitTimer()==1 && (!host.isToDie)){
			reset();
			hasBeenHit=false;
		}
		else
		{
			if(timer<=0 && (!isRemote) && (!host.isToDie)){
				resetDirection();
				host.rotationPitch=this.targetPitch;
				host.rotationYaw=this.targetYaw;
				setHostMotionByRotation();
			}
			else{
				timer--;
			}
		}
		if(hasBeenHit || host.isToDie){
			host.moveEntity(host.motionX, host.motionY, host.motionZ);
		}
		else{
			host.moveEntity(host.getMotionX(), host.getMotionY(), host.getMotionZ());
		}
	}
	public void reset() {
		timer=-1;
	}
	protected void setHostMotionByRotation() {
		Vec3 vec=host.getLookVec();
		host.setMotionX(vec.xCoord*0.1);
		host.setMotionY(vec.yCoord*0.1);
		host.setMotionZ(vec.zCoord*0.1);
	}
	protected void resetDirection() {
		timer=rand.nextInt(100);
		this.targetPitch=MathHelper.wrapAngleTo180_float(rand.nextInt(360));
		this.targetYaw=MathHelper.wrapAngleTo180_float(rand.nextInt(360));
	}
}
