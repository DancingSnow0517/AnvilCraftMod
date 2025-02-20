package dev.dubhe.anvilcraft.block;

import dev.dubhe.anvilcraft.init.ModMenuTypes;
import dev.dubhe.anvilcraft.inventory.RoyalSmithingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SmithingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class RoyalSmithingTableBlock extends SmithingTableBlock {
    private static final Component CONTAINER_TITLE = Component.translatable("container.upgrade");

    public RoyalSmithingTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        ModMenuTypes.open((ServerPlayer) player, state.getMenuProvider(level, pos));
        player.awardStat(Stats.INTERACT_WITH_SMITHING_TABLE);
        return InteractionResult.CONSUME;
    }

    @Override
    public MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return new SimpleMenuProvider((i, inventory, player) -> new RoyalSmithingMenu(i, inventory, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE);
    }
}
