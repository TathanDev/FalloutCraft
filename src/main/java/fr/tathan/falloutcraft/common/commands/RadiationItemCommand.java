package fr.tathan.falloutcraft.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class RadiationItemCommand {

        public RadiationItemCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

            dispatcher.register(Commands.literal("falloutcraft").then(Commands.literal("addRadiation").requires(c -> c.hasPermission(2)).executes((Command) -> {

                return addRadiation(Command.getSource());
            })));

            dispatcher.register(Commands.literal("falloutcraft").then(Commands.literal("subRadiation").requires(c -> c.hasPermission(2)).executes((Command) -> {

                return subRadiation(Command.getSource());
            })));


        }

    private int addRadiation(CommandSourceStack source)  throws CommandSyntaxException {



           Player player = source.getPlayer();

           ItemStack item = player.getItemInHand(InteractionHand.MAIN_HAND);

        ItemRadiation radiation = item.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElseThrow(() -> new IllegalStateException("Damn! An Error ?! This is Spooky !!"));


        if (radiation.getRadiation() == 10) {
            player.sendSystemMessage(Component.translatable("commands.falloutcraft.addRadiation.failure", item.getDisplayName()));
        } else {
            radiation.addRadiation(1);
            player.sendSystemMessage(Component.translatable("commands.falloutcraft.addRadiation.success", item.getDisplayName(), radiation.getRadiation()));
        }

        return 0;

    }

    private int subRadiation(CommandSourceStack source)  throws CommandSyntaxException {

        Player player = source.getPlayer();

        ItemStack item = player.getItemInHand(InteractionHand.MAIN_HAND);

        ItemRadiation radiation = item.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElseThrow(() -> new IllegalStateException("Damn! An Error ?! This is Spooky !!"));


        if (radiation.getRadiation() == 0) {
            player.sendSystemMessage(Component.translatable("commands.falloutcraft.addRadiation.failure", item.getDisplayName()));
        } else {
            radiation.subRadiation(1);

            player.sendSystemMessage(Component.translatable("commands.falloutcraft.addRadiation.success", item.getDisplayName(), radiation.getRadiation()));
        }

        return 1;

    }

}
