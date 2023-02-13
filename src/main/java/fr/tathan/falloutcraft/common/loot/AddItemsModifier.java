package fr.tathan.falloutcraft.common.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Supplier;

public class AddItemsModifier extends LootModifier {
    public static final Supplier<Codec<AddItemsModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(m -> m.item))
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("item2").forGetter(m -> m.item2))
            .and(Codec.INT.fieldOf("multiplier").forGetter(m -> m.multiplier))
            .apply(inst, AddItemsModifier::new)));
    private final Item item;
    private final Item item2;
    private final int multiplier;


    protected AddItemsModifier(LootItemCondition[] conditionsIn, Item item, Item item2, int multiplier) {
        super(conditionsIn);
        this.item = item;
        this.item2 = item2;
        this.multiplier = multiplier;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        Random random = new Random();
        int multiplier1 = random.nextInt(1, this.multiplier);

        for (int i = 0; i < multiplier1; i++) {
            generatedLoot.add(new ItemStack(item));
        }

        int multiplier2 = random.nextInt(1, this.multiplier);

        for (int i = 0; i < multiplier2; i++) {
            generatedLoot.add(new ItemStack(item2));
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}

