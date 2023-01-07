package fr.tathan.falloutcraft.common.registries;

import net.minecraft.world.level.GameRules;

public class GameruleRegistry {

    public static final GameRules.Key<GameRules.BooleanValue> ITEMS_RADIATION_DAMAGE = GameRules
            .register("itemsRadiationDamage", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));


}
