package enviromine.client.renderer.tileentity;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enviromine.blocks.tiles.TileEntityEsky;

@SideOnly(Side.CLIENT)
public class TileEntityEskyRenderer extends TileEntitySpecialRenderer {

    private static final ResourceLocation field_147520_b = new ResourceLocation(
        "enviromine",
        "textures/models/blocks/esky_model.png");
    private ModelChest field_147521_c = new ModelChest();

    public void renderTileEntityAt(TileEntityEsky p_147500_1_, double p_147500_2_, double p_147500_4_,
        double p_147500_6_, float p_147500_8_) {
        int i = 0;

        if (p_147500_1_.hasWorldObj()) {
            i = p_147500_1_.getBlockMetadata();
        }

        this.bindTexture(field_147520_b);
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) p_147500_2_, (float) p_147500_4_ + 1.0F, (float) p_147500_6_ + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        short short1 = 0;

        if (i == 2) {
            short1 = 180;
        }

        if (i == 3) {
            short1 = 0;
        }

        if (i == 4) {
            short1 = 90;
        }

        if (i == 5) {
            short1 = -90;
        }

        GL11.glRotatef((float) short1, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float f1 = p_147500_1_.field_145975_i + (p_147500_1_.field_145972_a - p_147500_1_.field_145975_i) * p_147500_8_;
        f1 = 1.0F - f1;
        f1 = 1.0F - f1 * f1 * f1;
        this.field_147521_c.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
        this.field_147521_c.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_,
        float p_147500_8_) {
        this.renderTileEntityAt((TileEntityEsky) p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
    }
}
