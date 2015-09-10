package sima214.core.common.entities;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;


public class MotionHelper {

	protected final SemiLivingEntityBase host;
	protected Random rand=new Random();
	protected float targetPitch;
	protected float targetYaw;
	public int timer;
	public MotionHelper(SemiLivingEntityBase semiLivingEntityBase) {
		this.host=semiLivingEntityBase;
		resetDirection();
	}
	public void onUpdate(boolean isRemote){
		if(host.getHitTimer()>1){
			host.motionX*=0.6d;
			host.motionY*=0.6d;
			host.motionZ*=0.6d;
		}
		else if(host.getHitTimer()==1){
			resetDirection();
		}
		else
		{
			if(timer<=0){
				resetDirection();
				host.rotationPitch=this.targetPitch;
				host.rotationYaw=this.targetYaw;
				setHostMotionByRotation();
			}
			else{
				timer--;
			}
		}
		host.moveEntity(host.motionX, host.motionY, host.motionZ);
	}
	protected void setHostMotionByRotation() {
		Vec3 vec=host.getLookVec();
		host.motionX=vec.xCoord*0.2;
		host.motionY=vec.yCoord*0.2;
		host.motionZ=vec.zCoord*0.2;
	}
	protected void resetDirection() {
		timer=rand.nextInt(100);
		this.targetPitch=MathHelper.wrapAngleTo180_float(rand.nextInt(360));
		this.targetYaw=MathHelper.wrapAngleTo180_float(rand.nextInt(360));
	}
}
