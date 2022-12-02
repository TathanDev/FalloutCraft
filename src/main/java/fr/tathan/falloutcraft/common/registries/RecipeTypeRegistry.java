package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.recipe.NukaColaMachineRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeTypeRegistry {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FalloutCraft.MODID);

    public static final RegistryObject<RecipeSerializer<NukaColaMachineRecipe>> NUKA_COLA_MACHINE_SERIALIZER =
            SERIALIZERS.register("nuka_cola_machine", () -> NukaColaMachineRecipe.Serializer.INSTANCE);
}
