package enviromine.world.chunk;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenModifiedMinable extends WorldGenerator {

    private Block minableBlock;
    /** The number of blocks to generate. */
    private int numberOfBlocks;
    private Block targetBlock;
    private int targetMeta;
    private int mineableBlockMeta;

    public WorldGenModifiedMinable(Block block, int number) {
        this(block, number, Blocks.stone);
    }

    public WorldGenModifiedMinable(Block block, int number, Block target) {
        this(block, 0, number, target, 0);
    }

    public WorldGenModifiedMinable(Block block, int meta, int number, Block target) {
        this(block, meta, number, target, 0);
    }

    public WorldGenModifiedMinable(Block block, int meta, int number, Block target, int targetMeta) {
        this.minableBlock = block;
        this.mineableBlockMeta = meta;
        this.numberOfBlocks = number;
        this.targetBlock = target;
        this.targetMeta = targetMeta;
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        float f = rand.nextFloat() * (float) Math.PI;
        double d0 = (double) ((float) (x + 8) + MathHelper.sin(f) * (float) this.numberOfBlocks / 8.0F);
        double d1 = (double) ((float) (x + 8) - MathHelper.sin(f) * (float) this.numberOfBlocks / 8.0F);
        double d2 = (double) ((float) (z + 8) + MathHelper.cos(f) * (float) this.numberOfBlocks / 8.0F);
        double d3 = (double) ((float) (z + 8) - MathHelper.cos(f) * (float) this.numberOfBlocks / 8.0F);
        double d4 = (double) (y + rand.nextInt(3) - 2);
        double d5 = (double) (y + rand.nextInt(3) - 2);

        for (int l = 0; l <= this.numberOfBlocks; ++l) {
            double d6 = d0 + (d1 - d0) * (double) l / (double) this.numberOfBlocks;
            double d7 = d4 + (d5 - d4) * (double) l / (double) this.numberOfBlocks;
            double d8 = d2 + (d3 - d2) * (double) l / (double) this.numberOfBlocks;
            double d9 = rand.nextDouble() * (double) this.numberOfBlocks / 16.0D;
            double d10 = (double) (MathHelper.sin((float) l * (float) Math.PI / (float) this.numberOfBlocks) + 1.0F)
                * d9 + 1.0D;
            double d11 = (double) (MathHelper.sin((float) l * (float) Math.PI / (float) this.numberOfBlocks) + 1.0F)
                * d9 + 1.0D;
            int i1 = MathHelper.floor_double(d6 - d10 / 2.0D);
            int j1 = MathHelper.floor_double(d7 - d11 / 2.0D);
            int k1 = MathHelper.floor_double(d8 - d10 / 2.0D);
            int l1 = MathHelper.floor_double(d6 + d10 / 2.0D);
            int i2 = MathHelper.floor_double(d7 + d11 / 2.0D);
            int j2 = MathHelper.floor_double(d8 + d10 / 2.0D);

            for (int k2 = i1; k2 <= l1; ++k2) {
                double d12 = ((double) k2 + 0.5D - d6) / (d10 / 2.0D);

                if (d12 * d12 < 1.0D) {
                    for (int l2 = j1; l2 <= i2; ++l2) {
                        double d13 = ((double) l2 + 0.5D - d7) / (d11 / 2.0D);

                        if (d12 * d12 + d13 * d13 < 1.0D) {
                            for (int i3 = k1; i3 <= j2; ++i3) {
                                double d14 = ((double) i3 + 0.5D - d8) / (d10 / 2.0D);

                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D
                                    && world.getBlock(k2, l2, i3) == targetBlock
                                    && (targetMeta <= -1 || world.getBlockMetadata(k2, l2, i3) == targetMeta)) {
                                    world.setBlock(k2, l2, i3, this.minableBlock, mineableBlockMeta, 2);
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
