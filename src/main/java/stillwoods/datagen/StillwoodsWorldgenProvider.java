package stillwoods.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import stillwoods.TheStillwoods;
import stillwoods.registry.StillwoodsBiomes;
import stillwoods.registry.StillwoodsDimensions;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class StillwoodsWorldgenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, StillwoodsDimensions::bootstrapType)
            .add(Registries.LEVEL_STEM, StillwoodsDimensions::bootstrapStem)
            .add(Registries.BIOME, StillwoodsBiomes::boostrap);


    public StillwoodsWorldgenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(TheStillwoods.MODID));
    }
}
