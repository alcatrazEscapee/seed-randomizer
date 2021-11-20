/*
 * Part of the Seed Randomizer mod.
 * Licensed under MIT. See the project LICENSE.txt for details.
 */

package com.alcatrazescapee.seedrandomizer;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SeedRandomizer.MOD_ID)
public final class SeedRandomizer
{
    public static final String MOD_ID = "seedrandomizer";
    private static final Logger LOGGER = LogManager.getLogger();

    public SeedRandomizer()
	{
        LOGGER.info("I'm a randomizer!");
    }
}
