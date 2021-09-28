package tk.dczippl.lightestlamp.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;
import tk.dczippl.lightestlamp.blocks.entity.AlchemicalLampBlockEntity;

import java.util.List;

import static net.minecraft.block.Blocks.REDSTONE_LAMP;

public class AlchemicalLampBlock extends BlockWithEEntity
{
    public AlchemicalLampBlock()
    {
        super(FabricBlockSettings.copyOf(REDSTONE_LAMP).luminance(__ -> 15));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlchemicalLampBlockEntity(pos, state);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(new TranslatableText("tooltip.lightestlamp.type.alchemical").setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY))));
        tooltip.add(new TranslatableText("tooltip.lightestlamp.always_active").setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY))));

        super.appendTooltip(stack, world, tooltip, options);
    }
}
