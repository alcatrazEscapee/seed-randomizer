/*
 * Part of the Seed Randomizer mod.
 * Licensed under MIT. See the project LICENSE.txt for details.
 */

package com.alcatrazescapee.seedrandomizer.mixin;

import net.minecraft.world.level.levelgen.WorldGenSettings;

import com.alcatrazescapee.seedrandomizer.bridge.MixinHooks;
import com.mojang.serialization.DataResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldGenSettings.class)
public abstract class WorldGenSettingsMixin
{
    /**
     * Inject the seed from the world gen settings, which is ultimately populated by the server seed / client options, into each dimension's chunk generator.
     */
    @Inject(method = "guardExperimental", at = @At("HEAD"))
    private void injectSeedIntoDimensions(CallbackInfoReturnable<DataResult<WorldGenSettings>> cir)
    {
        MixinHooks.injectSeedIntoDimensions(MixinHooks.cast(this));
    }
}
