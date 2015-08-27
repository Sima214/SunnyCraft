package sima214.sunnycraft.client.item;

import org.lwjgl.opengl.GL11;

import sima214.sunnycraft.common.items.PortableDeepStorageUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
public class PDSUItemRenderer implements IItemRenderer {
	private static RenderItem itemRenderer=new RenderItem();
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack itemStack, ItemRendererHelper helper) {
		if(PortableDeepStorageUnit.getStack(itemStack)==null)
			return false;
		else return type != ItemRenderType.INVENTORY;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data) {
		Tessellator tes=Tessellator.instance;//TODO Create a faster tessellator and also a better render/overlay.
		GL11.glPushMatrix();
		IIcon icon=itemStack.getIconIndex();
		ItemStack contStack=PortableDeepStorageUnit.getStack(itemStack);
		if(contStack!=null){
			if(type==ItemRenderType.INVENTORY){
				renderOverlay(icon, 0.5f);
				GL11.glTranslatef(-1f, -1f, 0.1f);
				Minecraft mc = Minecraft.getMinecraft();
				TextureManager textureManager = mc.getTextureManager();
				FontRenderer fontRenderer = RenderManager.instance.getFontRenderer();
				RenderHelper.enableGUIStandardItemLighting();
				itemRenderer.renderItemAndEffectIntoGUI(fontRenderer, textureManager, contStack, 1, 1);
			}
			else{
				GL11.glScalef(0.8f, 0.8f, 0.8f);
				Minecraft mc = Minecraft.getMinecraft();
				RenderManager.instance.itemRenderer.renderItem(mc.thePlayer, contStack, 0, type);
			}
		}
		else{
			if(type==ItemRenderType.INVENTORY){
				renderOverlay(icon, 0.75f);
			}
			else{
				GL11.glTranslatef(-0.5f, -0.25f, 0f);
				GL11.glColor4f(0, 1f, 1f, 0.5f);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				ItemRenderer.renderItemIn2D(tes, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625f);
				GL11.glDisable(GL11.GL_BLEND);
			}
		}
		GL11.glPopMatrix();
	}
	private void renderOverlay(IIcon icon,float alpha){
		GL11.glColor4f(0, 1f, 1f, alpha);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		itemRenderer.renderIcon(0, 0, icon, icon.getIconWidth(), icon.getIconHeight());
		GL11.glDisable(GL11.GL_BLEND);
	}
}
