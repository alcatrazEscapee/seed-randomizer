/*
 * Part of the Seed Randomizer mod.
 * Licensed under MIT. See the project LICENSE.txt for details.
 */

package com.alcatrazescapee.seedrandomizer.mixin.accessor;

import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LevelStem.class)
public interface LevelStemAccessor
{
    @Mutable
    @Accessor("generator")
    void seedrandomizer$setGenerator(ChunkGenerator generator);
}
