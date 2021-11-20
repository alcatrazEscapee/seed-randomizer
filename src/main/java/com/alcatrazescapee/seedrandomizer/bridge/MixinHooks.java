/*
 * Part of the Seed Randomizer mod.
 * Licensed under MIT. See the project LICENSE.txt for details.
 */

package com.alcatrazescapee.seedrandomizer.bridge;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;

import com.alcatrazescapee.seedrandomizer.mixin.accessor.LevelStemAccessor;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public final class MixinHooks
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static Codec<LevelStem> makeLevelStemCodec()
    {
        return RecordCodecBuilder.create(instance -> instance.group(
            DimensionType.CODEC.fieldOf("type")
                .flatXmap(ExtraCodecs.nonNullSupplierCheck(), ExtraCodecs.nonNullSupplierCheck())
                .forGetter(LevelStem::typeSupplier),
            ChunkGenerator.CODEC.fieldOf("generator").forGetter(LevelStem::generator),
            Codec.BOOL.optionalFieldOf("fixed_seed", false).forGetter(c -> MixinHooks.<LevelStemBridge>cast(c).seedrandomizer$getFixedSeed())
        ).apply(instance, (type, generator, fixedSeed) -> {
            final LevelStem dimension = new LevelStem(type, generator);
            MixinHooks.<LevelStemBridge>cast(dimension).seedrandomizer$setFixedSeed(fixedSeed);
            return dimension;
        }));
    }

    public static void injectSeedIntoDimensions(WorldGenSettings settings)
    {
        LOGGER.info("Seed Randomizer: Injecting seeds");
        final long seed = settings.seed();
        for (Map.Entry<ResourceKey<LevelStem>, LevelStem> entry : settings.dimensions().entrySet())
        {
            final LevelStem dimension = entry.getValue();
            if (!MixinHooks.<LevelStemBridge>cast(dimension).seedrandomizer$getFixedSeed())
            {
                LOGGER.info("Injecting seed {} into dimension {}", seed, entry.getKey().location());
                MixinHooks.<LevelStemAccessor>cast(dimension).seedrandomizer$setGenerator(dimension.generator().withSeed(seed));
            }
            else
            {
                LOGGER.info("Skipping dimension {}, using fixed seed", entry.getKey().location());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object o)
    {
        return (T) o;
    }
}
