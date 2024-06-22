package net.martin.martinsMod.items;

import net.martin.martinsMod.items.custom.sword;
import net.martin.martinsMod.martinsMod;
import net.minecraft.core.Holder;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, martinsMod.MODID);

    // ITEM 1
    public static final RegistryObject<SwordItem> SWORD = ITEMS.register("sword", () -> new SwordItem(Tiers.DIAMOND, new Item.Properties()));

    //ITEM 2
    public static final RegistryObject<Item> PENE = ITEMS.register("pene", () -> new Item(new Item.Properties()));

    //ITEM 3
    public static final RegistryObject<Item> KARAMBIT = ITEMS.register("karambit", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }

}

