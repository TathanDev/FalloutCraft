package fr.tathan.falloutcraft.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {


    public static final ForgeConfigSpec CONFIG;
    private static final ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();

    static {
        init(CONFIG_BUILDER);
        CONFIG = CONFIG_BUILDER.build();
    }

    public static ForgeConfigSpec.ConfigValue<Boolean> radiationRainDamage;
    public static ForgeConfigSpec.ConfigValue<Boolean> usePimpBoy;
    public static ForgeConfigSpec.ConfigValue<Boolean> pimpBoyUtilisation;
    public static ForgeConfigSpec.ConfigValue<Boolean> itemRadiationDamage;



    public static void init(ForgeConfigSpec.Builder builder) {
        radiationRainDamage = builder.comment("Set this to true if you want to make the rain kill the player!").define("radiationRainDamage", true);
        itemRadiationDamage = builder.comment("Set this to true if you want to make the item damage the player if the item has radiation !").define("itemRadiationDamage", true);
        usePimpBoy = builder.comment("Set this to true if you want to use the Pimp Boy mechanic !").define("usePimpBoy", true);
        pimpBoyUtilisation = builder.comment("If you want to force the player to have the Pimp Boy in there hand for opening the inventory, set this to true").define("pimpBoyUtilisation", true);

    }

}
