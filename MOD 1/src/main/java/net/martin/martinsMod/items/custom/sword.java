package net.martin.martinsMod.items.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.damagesource.DamageSource;


public class sword extends SwordItem {

    private final int damage;
    public sword(int damage)
    {
        super(Tiers.DIAMOND,new Properties());
        this.damage = damage;
    }

    @Override
    public boolean hurtEnemy(ItemStack Stack, LivingEntity Target, LivingEntity Attacker)
    {
        if (Attacker instanceof Player)
        {
            DamageSource source = new DamageSource(null);
            Target.hurt(source,this.damage);
        }
        return super.hurtEnemy(Stack, Target, Attacker);
    }
}
