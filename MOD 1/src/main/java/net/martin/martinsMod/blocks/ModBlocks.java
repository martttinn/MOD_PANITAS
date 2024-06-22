package net.martin.martinsMod.blocks;

import net.martin.martinsMod.items.ModItems;
import net.martin.martinsMod.martinsMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, martinsMod.MODID);

    //BLOQUE 1
    public static final RegistryObject<Block> BLOQUE_PREPUCIO = registerBlock("bloque_prepucio", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK)));

    //BLOQUE 2
    public static final RegistryObject<Block> BLOQUE_VIRGEN = registerBlock("bloque_virgen", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name,block); // ---> REGISTRA EL BLOQUE
        registerBlockItem(name,toReturn); // ---> REGISTRA EL ITEM DEL BLOQUE
        return  toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block)
    {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
