package enviromine.blocks.water;

import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EnviroMineWaterFluid extends Fluid {

    public EnviroMineWaterFluid(String fluidName) {
        super(fluidName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon() {
        return getStillIcon();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getStillIcon() {
        return BlockEnviroMineWater.stillWater;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getFlowingIcon() {
        return BlockEnviroMineWater.flowingWater;
    }
}
