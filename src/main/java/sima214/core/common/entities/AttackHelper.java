package sima214.core.common.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

public class AttackHelper {

	private SemiLivingEntityBase host;
	private EntityPlayer target;

	public AttackHelper(SemiLivingEntityBase semiLivingEntityBase) {
		this.host=semiLivingEntityBase;
	}

	public void onUpdate(boolean isRemote) {
		if(target==null)return;
		if((host.worldObj.getTotalWorldTime()%10)==0){
			target.attackEntityFrom(DamageSource.lava, 10f);
		}
	}

	public void setTarget(EntityPlayer player) {
		this.target=player;
	}
	public EntityPlayer getTarget() {
		return target;
	}

}
