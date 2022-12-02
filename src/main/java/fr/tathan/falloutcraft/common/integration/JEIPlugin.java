package fr.tathan.falloutcraft.common.integration;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.recipe.NukaColaMachineRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;
@JeiPlugin
public class JEIPlugin implements IModPlugin {

    public static RecipeType<NukaColaMachineRecipe> INFUSION_TYPE =
            new RecipeType<>(NukaColaMachineRecipeCategory.UID, NukaColaMachineRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(FalloutCraft.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                NukaColaMachineRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<NukaColaMachineRecipe> recipesInfusing = rm.getAllRecipesFor(NukaColaMachineRecipe.Type.INSTANCE);
        registration.addRecipes(INFUSION_TYPE, recipesInfusing);
    }

}
