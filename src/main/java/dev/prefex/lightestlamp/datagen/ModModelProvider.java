package dev.prefex.lightestlamp.datagen;

import dev.prefex.lightestlamp.init.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.PillarBlock;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.registry.Registries;

public class ModModelProvider extends FabricModelProvider {

	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		ModBlocks.BLOCKS.forEach(block -> {
			if (block instanceof PillarBlock){
				blockStateModelGenerator.registerAxisRotated(block, TexturedModel.CUBE_COLUMN);
			}
			else if (block == ModBlocks.CLEAR_LAMP
					|| block == ModBlocks.ALPHA_LAMP
					|| block == ModBlocks.BETA_LAMP
					|| block == ModBlocks.GAMMA_LAMP
					|| block == ModBlocks.DELTA_LAMP
					|| block == ModBlocks.EPSILON_LAMP){
				// TODO: add powered lamp models here
			}
			else if (block == ModBlocks.GLOWSTONE_CENTRIFUGE){
				/*blockStateModelGenerator.register
				blockStateModelGenerator.registerSingleton(ModBlocks.GLOWSTONE_CENTRIFUGE, blockStateModelGenerator.modelCollector.(ForgeRegistries.BLOCKS.getKey(block).getPath(),
						new Identifier(LightestLampsMod.MOD_ID,"block/machine/"+ Registries.BLOCK.getId(block).getPath()+"_side"),
						new Identifier(LightestLampsMod.MOD_ID,"block/machine/"+Registries.BLOCK.getId(block).getPath()+"_bottom"),
						new Identifier(LightestLampsMod.MOD_ID,"block/machine/"+Registries.BLOCK.getId(block).getPath())));*/
			}
			else if (block != ModBlocks.OCC
					&& block != ModBlocks.LAMP_FRUIT
					&& block != ModBlocks.LIGHT_AIR
					&& block != ModBlocks.WATERLOGGABLE_LIGHT_AIR){
				blockStateModelGenerator.registerSimpleCubeAll(block);
			}
		});
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {

	}
}
