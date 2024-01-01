package fr.tathan.falloutcraft.common.integration;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.common.recipe.NukaColaMachineRecipe;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class NukaColaMachineRecipeCategory implements IRecipeCategory<NukaColaMachineRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(FalloutCraft.MODID, "nuka_cola_machine");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(FalloutCraft.MODID, "textures/gui/nuka_cola_machine_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public NukaColaMachineRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 140, 100);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlocksRegistry.NUKA_COLA_MACHINE.get()));
    }

    @Override
    public RecipeType<NukaColaMachineRecipe> getRecipeType() {
        return JEIPlugin.INFUSION_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Nuka Cola Machine");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, NukaColaMachineRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 15).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.INPUT, 55, 15)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(recipe.getFluid()))
                .setFluidRenderer(64000, false, 16, 61);


        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 60).addItemStack(recipe.getResult());
    }


}
