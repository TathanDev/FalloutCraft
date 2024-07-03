package fr.tathan.falloutcraft.mixin;

import fr.tathan.falloutcraft.common.fluid.ModFluidTypes;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin (ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements TraceableEntity {

    @Shadow public abstract ItemStack getItem();
    @Shadow public abstract void setItem(ItemStack pStack);

    protected ItemEntityMixin(EntityType<? extends ItemEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(at=@At(value = "HEAD"), method ="tick")
    public void tick(CallbackInfo ci) {
        if (this.isInFluidType(ModFluidTypes.RADIATED_WATER_FLUID_TYPE.get())) {
            ItemStack item = getItem();
            ItemRadiation radiation = item.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
            radiation.loadNBTData(item.getOrCreateTagElement("radiation"));
            radiation.setRadiation(10.0);
            radiation.saveNBTData(item.getOrCreateTagElement("radiation"));

        }
    }
}
