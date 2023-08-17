package tk.dczippl.lightestlamp.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import tk.dczippl.lightestlamp.blocks.entity.GenerableLampBlockEntity;
import tk.dczippl.lightestlamp.init.ModBlockEntities;
import tk.dczippl.lightestlamp.init.ModBlocks;

import java.util.List;

import static net.minecraft.block.Blocks.REDSTONE_LAMP;
import static net.minecraft.state.property.Properties.POWERED;

public class GenerableLampBlock extends BlockWithEEntity {
	public final LampType lampType;
	public final boolean waterResistant;

	public GenerableLampBlock(LampType lampType, boolean waterResistant) {
		super(FabricBlockSettings.copyOf(REDSTONE_LAMP).luminance(__ -> 15));
		this.lampType = lampType;
		this.waterResistant = waterResistant;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new GenerableLampBlockEntity(lampType,waterResistant,pos, state);
	}

	@Override
	public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {clean(world, pos);}

	@Override
	public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {clean(world, pos);}

	private void clean(WorldAccess world, BlockPos pos){
		if (lampType == LampType.OMEGA) world.setBlockState(pos, ModBlocks.OCC.getDefaultState(),3);
		world.setBlockState(pos, ModBlocks.CHUNK_CLEANER.getDefaultState(),3);
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, ModBlockEntities.GENERAL_LAMP_BE, GenerableLampBlockEntity::tick);
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(POWERED);
		super.appendProperties(builder);
	}
	
	@Override
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		tooltip.add(Text.translatable("tooltip.lightestlamp.type."+lampType.name().toLowerCase()).setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY))));

		if (waterResistant) 		tooltip.add(Text.translatable("tooltip.lightestlamp.underwater").setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY))));
		if (lampType.ordinal() > 2) tooltip.add(Text.translatable("tooltip.lightestlamp.penetration").setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY))));
		if (lampType.ordinal() > 4) tooltip.add(Text.translatable("tooltip.lightestlamp.always_active").setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY))));
		else 						tooltip.add(Text.translatable("tooltip.lightestlamp.inverted").setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY))));

		super.appendTooltip(stack, world, tooltip, options);
	}
}
