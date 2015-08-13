package sima214.core.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ResourceBlock extends Block {
	String modid;
	protected boolean nonCube;
	protected ResourceBlock(Material mat) {
		super(mat);
	}
	public ResourceBlock(){
		this(Material.sponge);
	}
	public ResourceBlock(float[] bounds,float hardness,float resistance,String name,String textureName,SoundType sound,String modid){
		this();
		this.modid=modid;
		this.setResistance(resistance).setHardness(hardness).setBlockName(name).setBlockTextureName(textureName).setStepSound(sound);
		if(bounds!=null){
			this.setBlockBounds(bounds[0],bounds[1],bounds[2],bounds[3],bounds[4],bounds[5]);
			nonCube=true;
			setLightOpacity(0);
		}
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon=iconRegister.registerIcon(modid+":"+this.textureName);
	}
	@Override
	public boolean renderAsNormalBlock() {
		return !nonCube;
	}
	@Override
	public boolean isOpaqueCube() {
		return !nonCube;
	}
	public void registerToOreDict(String oreDict){
		OreDictionary.registerOre(oreDict, this);
	}
}
