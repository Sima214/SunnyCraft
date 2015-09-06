package sima214.core.common.entities;


public class MotionHelper {

	private final SemiLivingEntityBase host;

	public MotionHelper(SemiLivingEntityBase semiLivingEntityBase) {
		this.host=semiLivingEntityBase;
	}
	public void onUpdate(boolean isRemote){
		if(host.getHitTimer()>1){
		host.motionX*=0.8d;
		host.motionY*=0.8d;
		host.motionZ*=0.8d;
		}
		else if(host.getHitTimer()==1){
			reset();
		}
		host.moveEntity(host.motionX, host.motionY, host.motionZ);
	}
	public void reset() {
		host.motionX=host.motionY=host.motionZ=0d;
	}
}
