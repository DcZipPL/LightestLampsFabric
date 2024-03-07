package dev.prefex.lightestlamp.datagen;

import com.google.common.collect.Lists;
import dev.prefex.lightestlamp.init.ModBlocks;
import dev.prefex.lightestlamp.init.ModItems;
import dev.prefex.lightestlamp.util.Quintet;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;

public class RecipeDataProvider extends FabricRecipeProvider {
	RecipeDataProvider(FabricDataOutput generator) {
		super(generator);
	}

	@Override
	public void generate(RecipeExporter exporter) {
		// Netherite Mesh
		addSmithingCriteria(
			SmithingTransformRecipeJsonBuilder.create(
							Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
							Ingredient.ofItems(ModItems.BORON_MESH),
							Ingredient.ofItems(Items.NETHERITE_INGOT),
							RecipeCategory.MISC,
							ModItems.NETHERITE_MESH),
				Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
				ModItems.BORON_MESH,
				Items.NETHERITE_INGOT
		).offerTo(exporter, Registries.ITEM.getId(ModItems.NETHERITE_MESH));

		createGlowstoneRecipes(exporter);
		createLanthanumRecipes(exporter);

		createFilterRecipe(exporter, Items.STRING, ModItems.BASIC_FILTER);
		createFilterRecipe(exporter, Items.PHANTOM_MEMBRANE, ModItems.NEON_FILTER);
		createFilterRecipe(exporter, ModItems.GLOW_LICHEN_FIBER, ModItems.ARGON_FILTER);
		createFilterRecipe(exporter, ModItems.BORON_MESH, ModItems.KRYPTON_FILTER);
		createFilterRecipe(exporter, ModItems.NETHERITE_MESH, ModItems.XENON_FILTER);
		createFilterRecipe(exporter, ModItems.CHORUS_FIBER, ModItems.RADON_FILTER);

		// Vantablack
		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.VANTA_BLACK, 8)
						.pattern("CC")
						.pattern("CC")
						.input('C', ModItems.CARBON_NANOTUBE),
				ModItems.LANTHANUM_NUGGET
		).offerTo(exporter, Registries.ITEM.getId(ModBlocks.VANTA_BLACK.asItem()));

		// Lanthanum Mesh
		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BORON_MESH)
						.pattern("###")
						.pattern("#I#")
						.pattern("###")
						.input('#', ModItems.LANTHANUM_NUGGET)
						.input('I', ModItems.LANTHANUM_INGOT),
				ModItems.LANTHANUM_INGOT
		).offerTo(exporter, Registries.ITEM.getId(ModItems.BORON_MESH));
	}

	private void createFilterRecipe(RecipeExporter exporter, ItemConvertible filter, ItemConvertible output) {
		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output)
						.pattern(" # ")
						.pattern("#F#")
						.pattern(" # ")
						.input('#', ModItems.LANTHANUM_NUGGET)
						.input('F', filter),
				ModItems.LANTHANUM_NUGGET,
				filter
		).offerTo(exporter, Registries.ITEM.getId(output.asItem()));
	}

	private void createLanthanumRecipes(RecipeExporter exporter) {
		// Lanthanum smelting
		RecipeProvider.offerSmelting(exporter, List.of(ModItems.RAW_LANTHANUM), RecipeCategory.MISC, ModItems.LANTHANUM_INGOT, 0.7F, 400, Registries.ITEM.getId(ModItems.RAW_LANTHANUM) + "_smelting");
		RecipeProvider.offerBlasting(exporter, List.of(ModItems.RAW_LANTHANUM), RecipeCategory.MISC, ModItems.LANTHANUM_INGOT, 0.7F, 200, Registries.ITEM.getId(ModItems.RAW_LANTHANUM) + "_blasting");

		RecipeProvider.offerSmelting(exporter, List.of(ModBlocks.LANTHANUM_ORE), RecipeCategory.MISC, ModItems.LANTHANUM_INGOT, 0.7F, 400, Registries.ITEM.getId(ModBlocks.LANTHANUM_ORE.asItem()) + "_smelting");
		RecipeProvider.offerBlasting(exporter, List.of(ModBlocks.LANTHANUM_ORE), RecipeCategory.MISC, ModItems.LANTHANUM_INGOT, 0.7F, 200, Registries.ITEM.getId(ModBlocks.LANTHANUM_ORE.asItem()) + "_blasting");

		RecipeProvider.offerSmelting(exporter, List.of(ModItems.LANTHANUM_DUST), RecipeCategory.MISC, ModItems.LANTHANUM_INGOT, 0.0F, 200, Registries.ITEM.getId(ModItems.LANTHANUM_DUST) + "_smelting");
		RecipeProvider.offerBlasting(exporter, List.of(ModItems.LANTHANUM_DUST), RecipeCategory.MISC, ModItems.LANTHANUM_INGOT, 0.0F, 100, Registries.ITEM.getId(ModItems.LANTHANUM_DUST) + "_blasting");

		// TODO: Add smelting of Raw Lanthanum to Lanthanum Block

		// Lanthanum Dust to Piles
		addShapelessCriteria(
				ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LANTHANUM_PILE, 4)
						.input(ModItems.LANTHANUM_DUST),
				ModItems.LANTHANUM_DUST
		).offerTo(exporter, Registries.ITEM.getId(ModItems.LANTHANUM_PILE) + "_from_dust");

		// Lanthanum Piles to Dust
		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LANTHANUM_DUST)
						.pattern("##")
						.pattern("##")
						.input('#', ModItems.LANTHANUM_PILE),
				ModItems.LANTHANUM_PILE
		).offerTo(exporter, Registries.ITEM.getId(ModItems.LANTHANUM_DUST) + "_from_pile");

		// Lanthanum Ingot to Nuggets
		addShapelessCriteria(
				ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LANTHANUM_NUGGET, 9)
						.input(ModItems.LANTHANUM_INGOT),
				ModItems.LANTHANUM_INGOT
		).offerTo(exporter, Registries.ITEM.getId(ModItems.LANTHANUM_NUGGET) + "_from_ingot");

		// Lanthanum Nuggets to Ingot
		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LANTHANUM_INGOT)
						.pattern("###")
						.pattern("###")
						.pattern("###")
						.input('#', ModItems.LANTHANUM_NUGGET),
				ModItems.LANTHANUM_NUGGET
		).offerTo(exporter, Registries.ITEM.getId(ModItems.LANTHANUM_INGOT) + "_from_nugget");

		// Raw Lanthanum Block to Raw Lanthanum
		addShapelessCriteria(
				ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_LANTHANUM, 9)
						.input(ModBlocks.RAW_LANTHANUM_BLOCK),
				ModBlocks.RAW_LANTHANUM_BLOCK
		).offerTo(exporter, Registries.ITEM.getId(ModItems.RAW_LANTHANUM) + "_from_block");

		// Raw Lanthanum to Raw Lanthanum Block
		addShapedCriteria(
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_LANTHANUM_BLOCK)
						.pattern("###")
						.pattern("###")
						.pattern("###")
						.input('#', ModItems.RAW_LANTHANUM),
				ModItems.RAW_LANTHANUM
		).offerTo(exporter, Registries.ITEM.getId(ModBlocks.RAW_LANTHANUM_BLOCK.asItem()));
	}

	private void createGlowstoneRecipes(RecipeExporter exporter) {
		ArrayList<Quintet<Item, Item, Item, Item, Item>> glowstoneDusts = Lists.newArrayList(
				new Quintet<>(ModItems.NEON_DUST, ModItems.NEON_PILE, ModBlocks.NEON_BLOCK.asItem(), ModItems.NEON_ROD, ModBlocks.NEON_ROD_BLOCK.asItem()),
				new Quintet<>(ModItems.ARGON_DUST, ModItems.ARGON_PILE, ModBlocks.ARGON_BLOCK.asItem(), ModItems.ARGON_ROD, ModBlocks.ARGON_ROD_BLOCK.asItem()),
				new Quintet<>(ModItems.KRYPTON_DUST, ModItems.KRYPTON_PILE, ModBlocks.KRYPTON_BLOCK.asItem(), ModItems.KRYPTON_ROD, ModBlocks.KRYPTON_ROD_BLOCK.asItem()),
				new Quintet<>(ModItems.XENON_DUST, ModItems.XENON_PILE, ModBlocks.XENON_BLOCK.asItem(), ModItems.XENON_ROD, ModBlocks.XENON_ROD_BLOCK.asItem()),
				new Quintet<>(ModItems.RADON_DUST, ModItems.RADON_PILE, ModBlocks.RADON_BLOCK.asItem(), ModItems.RADON_ROD, ModBlocks.RADON_ROD_BLOCK.asItem())
		);

		for (var dust : glowstoneDusts) {
			// Piles to Dust
			addShapedCriteria(
					ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, dust.getA())
							.pattern("##")
							.pattern("##")
							.input('#', dust.getB()),
					dust.getB()
			).offerTo(exporter, Registries.ITEM.getId(dust.getA()) + "_from_pile");

			// Dust to Pile
			addShapelessCriteria(
					ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, dust.getB(), 4)
							.input(dust.getA()),
					dust.getA()
			).offerTo(exporter, Registries.ITEM.getId(dust.getB()) + "_from_dust");

			// Dust to Block
			addShapedCriteria(
					ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, dust.getC())
							.pattern("##")
							.pattern("##")
							.input('#', dust.getA()),
					dust.getA()
			).offerTo(exporter, Registries.ITEM.getId(dust.getC()));

			// Dust to Rod
			if (dust.getA() != ModItems.RADON_DUST) {
				addShapedCriteria(
						ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, dust.getD())
								.pattern(" # ")
								.pattern("#R#")
								.pattern(" # ")
								.input('R', ModItems.EMPTY_ROD)
								.input('#', dust.getA()),
						ModItems.EMPTY_ROD,
						dust.getA()
				).offerTo(exporter, Registries.ITEM.getId(dust.getD()));
			}

			// Rods to Block
			addShapedCriteria(
					ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, dust.getE())
							.pattern("##")
							.pattern("##")
							.input('#', dust.getD()),
					dust.getD()
			).offerTo(exporter, Registries.ITEM.getId(dust.getE()));

			// Block to Rods
			addShapelessCriteria(
					ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, dust.getD(), 4)
							.input(dust.getE()),
					dust.getE()
			).offerTo(exporter, Registries.ITEM.getId(dust.getD()) + "_from_block");
		}
	}

	private SmithingTransformRecipeJsonBuilder addSmithingCriteria(SmithingTransformRecipeJsonBuilder builder, ItemConvertible... itemConvertible){
		for (ItemConvertible item : itemConvertible) {
			builder = builder.criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item));
		}
		return builder;
	}

	private ShapedRecipeJsonBuilder addShapedCriteria(ShapedRecipeJsonBuilder builder, ItemConvertible... itemConvertible){
		for (ItemConvertible item : itemConvertible) {
			builder = builder.criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item));
		}
		return builder;
	}

	private ShapelessRecipeJsonBuilder addShapelessCriteria(ShapelessRecipeJsonBuilder builder, ItemConvertible... itemConvertible){
		for (ItemConvertible item : itemConvertible) {
			builder = builder.criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item));
		}
		return builder;
	}
}