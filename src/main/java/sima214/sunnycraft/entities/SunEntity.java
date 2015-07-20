package sima214.sunnycraft.entities;

import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import sima214.sunnycraft.core.utils.SpecialEntity;

public class SunEntity extends SpecialEntity {
	private boolean isBoss;
	public SunEntity(World world) {
		super(world);
		this.setSize(getSizeByType(),getSizeByType());
		this.width=getSizeByType();
		this.height=getSizeByType();
		setHealth(8);
		setDeathTimer(100);
	}
	public SunEntity(World world, float x, float y, float z) {
		this(world);
		setPosition(x, y, z);
	}
	private float getSizeByType(){
		return isBoss?8f:1f;
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
	};
	@Override
	public boolean attackEntityFrom(DamageSource dmg, float amount) {
		if(amount>7.0f&&getHitTimer()<10&&!isGoingToDie){
			//LogHelper.info("Attacked for :"+amount);
			setHealth(getHealth()-1);
			setHitTimer(20);
			try {
				Vec3 vec = dmg.getEntity().getLookVec();
				this.motionX=vec.xCoord;
				this.motionY=vec.yCoord;
				this.motionZ=vec.zCoord;
			} catch (NullPointerException e) {}
		}
		return true;
	}
}
