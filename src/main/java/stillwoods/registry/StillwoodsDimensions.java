package stillwoods.registry;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import stillwoods.TheStillwoods;
import stillwoods.rendering.level.StillwoodsSpecialEffects;

import java.util.OptionalLong;

@Mod.EventBusSubscriber(modid = TheStillwoods.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class StillwoodsDimensions {
    public static final ResourceLocation STILLWOODS_EFFECTS = new ResourceLocation(TheStillwoods.MODID, "stillwoods");

    public static final ResourceKey<LevelStem> STILLWOODS_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(TheStillwoods.MODID, "stillwoods"));
    public static final ResourceKey<Level> STILLWOODS_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(TheStillwoods.MODID, "stillwoods"));
    public static final ResourceKey<DimensionType> STILLWOODS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(TheStillwoods.MODID, "stillwoods_type"));


    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(STILLWOODS_TYPE, new DimensionType(
                OptionalLong.empty(),//OptionalLong.of(18000), // fixedTime
                StillwoodsSpecialEffects.RENDER_MOON, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                true, // bedWorks
                true, // respawnAnchorWorks
                0, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                STILLWOODS_EFFECTS, // effectsLocation
                -0.05f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(StillwoodsBiomes.STYGIAN_WOODLANDS)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(STILLWOODS_TYPE), wrappedChunkGenerator);

        context.register(STILLWOODS_KEY, stem);
    }

    @SubscribeEvent
    public static void registerEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(STILLWOODS_EFFECTS, new StillwoodsSpecialEffects());
    }
}
