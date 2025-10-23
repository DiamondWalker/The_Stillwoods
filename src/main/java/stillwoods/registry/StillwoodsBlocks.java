package stillwoods.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import stillwoods.TheStillwoods;

public class StillwoodsBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheStillwoods.MODID);

    public static final RegistryObject<Block> SHADOW_DIRT = BLOCKS.register("shadow_dirt", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final RegistryObject<Item> SHADOW_DIRT_ITEM = StillwoodsItems.ITEMS.register("shadow_dirt", () -> new BlockItem(SHADOW_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Block> SHADOW_GRASS_BLOCK = BLOCKS.register("shadow_grass_block", () -> new GrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final RegistryObject<Item> SHADOW_GRASS_BLOCK_ITEM = StillwoodsItems.ITEMS.register("shadow_grass_block", () -> new BlockItem(SHADOW_GRASS_BLOCK.get(), new Item.Properties()));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
