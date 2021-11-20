# Seed Randomizer

A mod which fixes [MC-195717](https://bugs.mojang.com/browse/MC-195717): Custom datapack dimensions, or overrides of existing dimensions, must have a fixed seed in the JSON. This mod adds an additional step which replaces whatever seed was defined in the dimension JSON with the seed chosen either in `server.properties`, or in the client's "Seed" input box.

N.B. This mod **does not** remove the requirement for the `seed` field to be present in chunk generators, or biome sources. However, this mod **does** override that seed if so desired. The overriden seed will be saved to the `level.dat` for all dimensions which an override is specified (all by default).

If a dimension is desired to have a fixed seed, this override can be disabled on a per-dimension basis by adding an additional parameter to the dimension JSON:

```json5
// In data/<namespace>/dimension/<dimension>.json
{
  "type": "minecraft:overworld", // The dimension type
  "generator": {
    // ... other chunk generator fields ...
    "seed": 1234
  },
  "fixed_seed": true // This dimension will use the seed "1234" always
}
```

