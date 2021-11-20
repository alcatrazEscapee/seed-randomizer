/*
 * Part of the Seed Randomizer mod.
 * Licensed under MIT. See the project LICENSE.txt for details.
 */

package com.alcatrazescapee.seedrandomizer.mixin;

import net.minecraft.world.level.dimension.LevelStem;

import com.alcatrazescapee.seedrandomizer.bridge.LevelStemBridge;
import com.alcatrazescapee.seedrandomizer.bridge.MixinHooks;
import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.*;

/**
 * Rewrite the codec to allow the "fixed_seed" option.
 * Trying to inject a field into the codec itself is VERY brittle, replacing it should have no conflicts outside of just not getting something applied, which in our case, should be fine.
 */
@Mixin(LevelStem.class)
public abstract class LevelStemMixin implements LevelStemBridge
{
    @Shadow @Final @Mutable public static Codec<LevelStem> CODEC;

    static
    {
        CODEC = MixinHooks.makeLevelStemCodec();
    }

    @Unique private boolean seedrandomizer$fixedSeed;

    @Override
    public void seedrandomizer$setFixedSeed(boolean value)
    {
        this.seedrandomizer$fixedSeed = value;
    }

    @Override
    public boolean seedrandomizer$getFixedSeed()
    {
        return seedrandomizer$fixedSeed;
    }
}
