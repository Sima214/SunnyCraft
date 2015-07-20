package sima214.sunnycraft.core.utils;

import java.util.Random;

import sima214.sunnycraft.core.LogHelper;
import net.minecraft.entity.Entity;

public class WanderHelper {
	SpecialEntity host;
	Entity provoker;
	int provokeCooldown=-1;
	int changeCourseCooldown;
	Random rng=new Random();
	public static int maxCool=100;
	WanderHelper(SpecialEntity host){
		this.host=host;
		changeCourse();
	}
	public void changeCourse(){
		host.motionX=rng.nextGaussian();
		host.motionY=rng.nextGaussian();
		host.motionZ=rng.nextGaussian();
		changeCourseCooldown=rng.nextInt(maxCool);
	}
	public void update(){
		if(provokeCooldown==-1){
			if(changeCourseCooldown==-1){
				changeCourse();
			}
			else
			{
				if(host.isCollided) {
					changeCourse();
				}
				changeCourseCooldown--;
			}
		}
		else
		{
			provokeCooldown--;
			float addedMotion=(float) (provoker.motionX+provoker.motionY+provoker.motionZ);
			float dist=host.getDistanceToEntity(provoker);
			float[] look={(float) ((provoker.posX-host.posX)/dist),(float) ((provoker.posY-host.posY)/dist),(float) ((provoker.posZ-host.posZ)/dist)};
			host.motionX=look[0]*addedMotion;
			host.motionY=look[1]*addedMotion;
			host.motionZ=look[2]*addedMotion;//And yes, I am evil.
			LogHelper.debug(host.motionX+" "+host.motionY+" "+host.motionZ);
			if(provokeCooldown==-1){
				provoker=null;
			}
		}
	}
	public void provoke(Entity tar,int cooldown){
		this.provoker=tar;
		this.provokeCooldown=cooldown;
	}

}
