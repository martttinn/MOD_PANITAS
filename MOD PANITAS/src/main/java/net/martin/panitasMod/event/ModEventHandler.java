package net.martin.panitasMod.event;
import net.martin.panitasMod.items.ModItems;
import net.martin.panitasMod.panitasMod;
import net.martin.panitasMod.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = panitasMod.MOD_ID, value = Dist.CLIENT)
public class ModEventHandler
{
    private static final Random RANDOM = new Random();

    // METODO QUE SE EJECUTA CADA VEZ QUE SE ATACA A UNA ENTIDAD
    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event)
    {
        // OBTENEMOS EL JUGADOR
        Player player = (Player)event.getEntity();

        //OBTENEMOS EL MUNDO(NIVEL) DEL JUGADOR
        Level world = player.level();

        // OBTENEMOS EL ITEM SOSTENIDO EN LA MANO PRINCIPAL
        ItemStack mainHandItem = player.getMainHandItem();

        // COMPROBAMOS QUE EL ITEM SOSTENIDO SEA EL DESEADO
        if (mainHandItem.getItem() == ModItems.ESPADA_DILDO.get())
        {
            // REPRODUCIMOS EL SONIDO DESEADO
            world.playSound(player, player.getX(), player.getY(), player.getZ(), ModSounds.GEMIDO_HENTAI.get(), player.getSoundSource(), 3.0f, 1.0f);

            // SPAWNEAMOS LA CANTIDAD DE PARTICULAS EQUIVALENTE AL NUMERO DE ITERACIONES
            for (int i = 0; i < 100; i++)
            {
                // SPAWNEAMOS LA PARTICULA EN LA POSICION DEL ENEMIGO CON CIERTO OFFSET ALEATORIO PARA CADA PARTICULA
                world.addParticle(ParticleTypes.END_ROD,
                        event.getTarget().getX() + world.random.nextGaussian() * 0.6,
                        event.getTarget().getY() + world.random.nextGaussian() * 0.6 + 2.0,
                        event.getTarget().getZ() + world.random.nextGaussian() * 0.6,
                        0.0D, 0.0D, 0.0D);
            }
        }
    }

    // METODO QUE SE EJECUTA CADA VEZ QUE SE UTILIZA UN ITEM
    @SubscribeEvent
    public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event)
    {
        // OBTENEMOS LA ENTIDAD QUE HA USADO EL ITEM
        LivingEntity entity = event.getEntity();

        // OBTENEMOS EL MUNDO(LEVEL) DE LA ENTIDAD
        Level world = entity.level();

        // OBTENEMOS EL ITEM USADO POR LA ENTIDAD
        ItemStack usedItem = event.getItem();

        if (usedItem.getItem() == ModItems.LIT_BLUNT.get())
        {
            ServerLevel serverWorld = (ServerLevel) world;

            for (int i = 0 ; i < 100 ; i++)
            {
                double offsetX = entity.getX() + (world.random.nextDouble() - 0.5) * 2.0;
                double offsetY = entity.getY() + 1.0 + world.random.nextDouble();
                double offsetZ = entity.getZ() + (world.random.nextDouble() - 0.5) * 2.0;

                serverWorld.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, offsetX, offsetY, offsetZ, 1, 0.0, 0.0, 0.0, 0.01);
            }
        }
    }

    // METODO QUE SE EJECUTA CADA VEZ QUE MUERTE UNA ENTIDAD, ACTUA EN EL MOMENTO JUSTO ANTERIOR A QUE APAREZCAN LOS ITEMS DROPEADOS DE LA ENTIDAD
    @SubscribeEvent
    public static void onPlayerDeath(LivingDropsEvent event) {
        if (event.getEntity() instanceof Player player) {
            player.getInventory().items.forEach(stack -> {
                if (stack.getItem() == ModItems.PIGGY_BANK.get()) {
                    event.getDrops().removeIf(itemEntity -> itemEntity.getItem().equals(stack));
                }
            });
        }
    }

    // METODO QUE SE EJECUTA CUANDO SE RESPAWNEA UN JUGADOR
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            ItemStack piggyBank = ItemStack.EMPTY;
            for (ItemStack stack : event.getOriginal().getInventory().items) {
                if (stack.getItem() == ModItems.PIGGY_BANK.get()) {
                    piggyBank = stack.copy();
                    break;
                }
            }

            if (!piggyBank.isEmpty()) {
                event.getEntity().getInventory().add(piggyBank);
            }
        }
    }

}
