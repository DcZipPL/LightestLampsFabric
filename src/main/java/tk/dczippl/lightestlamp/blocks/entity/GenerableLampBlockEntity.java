package tk.dczippl.lightestlamp.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import tk.dczippl.lightestlamp.blocks.LampType;
import tk.dczippl.lightestlamp.init.ModBlockEntities;
import tk.dczippl.lightestlamp.init.ModBlocks;

import java.util.Random;

import static net.minecraft.state.property.Properties.POWERED;
import static net.minecraft.state.property.Properties.WATERLOGGED;

public class GenerableLampBlockEntity extends BlockEntity{
	private final LampType lampType;
	private final boolean waterResistant;
	private int cooldown = 0;

	public GenerableLampBlockEntity(LampType lampType, boolean waterResistant, BlockPos pos, BlockState currState) {
		super(ModBlockEntities.GENERAL_LAMP_BE,pos,currState);
		this.lampType = lampType;
		this.waterResistant = waterResistant;
	}

	private static boolean isAir(World world, BlockPos pos)
	{
		return world.getBlockState(pos).getBlock() == Blocks.AIR || world.getBlockState(pos).getBlock() == Blocks.CAVE_AIR;
	}
	
	public static void tick(World world, BlockPos pos, BlockState state, GenerableLampBlockEntity be) {
		if (world.isClient) return;

		be.cooldown++;

		if (!be.waterResistant) {
			if (be.lampType == LampType.ALPHA) {
				if (be.cooldown == 5) {
					if (world.getReceivedRedstonePower(pos) > 0) {
						if (!state.get(POWERED)) {
							world.setBlockState(pos, state.with(POWERED, true));

							BlockPos.iterate(pos.offset(Direction.UP, 1).offset(Direction.NORTH, 1).offset(Direction.WEST, 1), pos.offset(Direction.DOWN, 1).offset(Direction.SOUTH, 1).offset(Direction.EAST, 1)).forEach((pos1) ->
							{
								if (world.getBlockState(pos1).getBlock() == ModBlocks.LIGHT_AIR) {
									world.setBlockState(pos1, Blocks.AIR.getDefaultState());
								}
							});
						}
					} else {
						if (state.get(POWERED))
							world.setBlockState(pos, state.with(POWERED, false));
					}

					if (!state.get(POWERED)) {
						BlockPos pos1 = pos.offset(Direction.UP);
						if (world.getBlockState(pos1).getBlock() == Blocks.AIR || world.getBlockState(pos1).getBlock() == Blocks.CAVE_AIR) {
							world.setBlockState(pos1, ModBlocks.LIGHT_AIR.getDefaultState());
						}

						pos1 = pos.offset(Direction.DOWN);
						if (world.getBlockState(pos1).getBlock() == Blocks.AIR || world.getBlockState(pos1).getBlock() == Blocks.CAVE_AIR) {
							world.setBlockState(pos1, ModBlocks.LIGHT_AIR.getDefaultState());
						}

						pos1 = pos.offset(Direction.NORTH);
						if (world.getBlockState(pos1).getBlock() == Blocks.AIR || world.getBlockState(pos1).getBlock() == Blocks.CAVE_AIR) {
							world.setBlockState(pos1, ModBlocks.LIGHT_AIR.getDefaultState());
						}

						pos1 = pos.offset(Direction.SOUTH);
						if (world.getBlockState(pos1).getBlock() == Blocks.AIR || world.getBlockState(pos1).getBlock() == Blocks.CAVE_AIR) {
							world.setBlockState(pos1, ModBlocks.LIGHT_AIR.getDefaultState());
						}

						pos1 = pos.offset(Direction.WEST);
						if (world.getBlockState(pos1).getBlock() == Blocks.AIR || world.getBlockState(pos1).getBlock() == Blocks.CAVE_AIR) {
							world.setBlockState(pos1, ModBlocks.LIGHT_AIR.getDefaultState());
						}

						pos1 = pos.offset(Direction.EAST);
						if (world.getBlockState(pos1).getBlock() == Blocks.AIR || world.getBlockState(pos1).getBlock() == Blocks.CAVE_AIR) {
							world.setBlockState(pos1, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					}
					be.cooldown = 0;
				}
			} else if (be.lampType == LampType.BETA) {
				if (be.cooldown == 5) {
					if (world.getReceivedRedstonePower(pos) > 0) {
						if (!state.get(POWERED)) {
							world.setBlockState(pos, state.with(POWERED, true));

							BlockPos.iterate(pos.offset(Direction.UP, 2).offset(Direction.NORTH, 2).offset(Direction.WEST, 2), pos.offset(Direction.DOWN, 2).offset(Direction.SOUTH, 2).offset(Direction.EAST, 2)).forEach((pos1) ->
							{
								if (world.getBlockState(pos1).getBlock() == ModBlocks.LIGHT_AIR) {
									world.setBlockState(pos1, Blocks.AIR.getDefaultState());
								}
							});
						}
					} else {
						if (state.get(POWERED))
							world.setBlockState(pos, state.with(POWERED, false));
					}

					if (!state.get(POWERED)) {
						BlockPos pos1 = pos.offset(Direction.UP, 2);
						if (isAir(world, pos1)) {
							world.setBlockState(pos1, ModBlocks.LIGHT_AIR.getDefaultState());
						}

						pos1 = pos.offset(Direction.DOWN, 2);
						if (isAir(world, pos1)) {
							world.setBlockState(pos1, ModBlocks.LIGHT_AIR.getDefaultState());
						}

						pos1 = pos.offset(Direction.NORTH, 2);
						if (isAir(world, pos1)) {
							world.setBlockState(pos1, ModBlocks.LIGHT_AIR.getDefaultState());
						}

						pos1 = pos.offset(Direction.SOUTH, 2);
						if (isAir(world, pos1)) {
							world.setBlockState(pos1, ModBlocks.LIGHT_AIR.getDefaultState());
						}

						pos1 = pos.offset(Direction.WEST, 2);
						if (isAir(world, pos1)) {
							world.setBlockState(pos1, ModBlocks.LIGHT_AIR.getDefaultState());
						}

						pos1 = pos.offset(Direction.EAST, 2);
						if (isAir(world, pos1)) {
							world.setBlockState(pos1, ModBlocks.LIGHT_AIR.getDefaultState());
						}

						BlockPos.iterate(pos.offset(Direction.UP, 1).offset(Direction.NORTH, 1).offset(Direction.WEST, 1), pos.offset(Direction.DOWN, 1).offset(Direction.SOUTH, 1).offset(Direction.EAST, 1)).forEach((pos2) ->
						{
							if (isAir(world, pos2)) {
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
							}
						});
					}

					be.cooldown = 0;
				}
			} else if (be.lampType == LampType.GAMMA) {
				if (be.cooldown == 5) {
					if (world.getReceivedRedstonePower(pos) > 0) {
						if (!state.get(POWERED)) {
							world.setBlockState(pos, state.with(POWERED, true));

							BlockPos.iterate(pos.offset(Direction.UP, 2).offset(Direction.NORTH, 2).offset(Direction.WEST, 2), pos.offset(Direction.DOWN, 2).offset(Direction.SOUTH, 2).offset(Direction.EAST, 2)).forEach((pos1) ->
							{
								if (world.getBlockState(pos1).getBlock() == ModBlocks.LIGHT_AIR) {
									world.setBlockState(pos1, Blocks.AIR.getDefaultState());
								}
							});
						}
					} else {
						if (state.get(POWERED))
							world.setBlockState(pos, state.with(POWERED, false));
					}

					if (!state.get(POWERED)) {
						BlockPos.iterate(pos.offset(Direction.UP, 2).offset(Direction.NORTH, 2).offset(Direction.WEST, 2),
								pos.offset(Direction.DOWN, 2).offset(Direction.SOUTH, 2).offset(Direction.EAST, 2)).forEach((pos2) ->
						{
							if (isAir(world, pos2)) {
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
							}
						});
					}

					be.cooldown = 0;
				}
			} else if (be.lampType == LampType.DELTA) {
				if (be.cooldown == 5) {
					if (world.getReceivedRedstonePower(pos) > 0) {
						if (!state.get(POWERED)) {
							world.setBlockState(pos, state.with(POWERED, true));

							BlockPos.iterate(pos.offset(Direction.UP, 4).offset(Direction.NORTH, 4).offset(Direction.WEST, 4), pos.offset(Direction.DOWN, 4).offset(Direction.SOUTH, 4).offset(Direction.EAST, 4)).forEach((pos1) ->
							{
								if (world.getBlockState(pos1).getBlock() == ModBlocks.LIGHT_AIR) {
									world.setBlockState(pos1, Blocks.AIR.getDefaultState());
								}
							});
						}
					} else {
						if (state.get(POWERED))
							world.setBlockState(pos, state.with(POWERED, false));
					}

					if (!state.get(POWERED)) {
						BlockPos.iterate(pos.offset(Direction.UP, 4).offset(Direction.NORTH, 4).offset(Direction.WEST, 4),
								pos.offset(Direction.DOWN, 4).offset(Direction.SOUTH, 4).offset(Direction.EAST, 4)).forEach((pos2) ->
						{
							if (isAir(world, pos2)) {
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
							}
						});
					}

					be.cooldown = 0;
				}
			} else if (be.lampType == LampType.EPSILON) {
				if (be.cooldown == 5) {
					if (world.getReceivedRedstonePower(pos) > 0) {
						if (!state.get(POWERED)) {
							world.setBlockState(pos, state.with(POWERED, true));

							BlockPos.iterate(pos.offset(Direction.UP, 5).offset(Direction.NORTH, 5).offset(Direction.WEST, 5), pos.offset(Direction.DOWN, 5).offset(Direction.SOUTH, 5).offset(Direction.EAST, 5)).forEach((pos1) ->
							{
								if (world.getBlockState(pos1).getBlock() == ModBlocks.LIGHT_AIR) {
									world.setBlockState(pos1, Blocks.AIR.getDefaultState());
								}
							});
						}
					} else {
						if (state.get(POWERED))
							world.setBlockState(pos, state.with(POWERED, false));
					}

					if (!state.get(POWERED)) {
						BlockPos.iterate(pos.offset(Direction.UP, 5).offset(Direction.NORTH, 5).offset(Direction.WEST, 5),
								pos.offset(Direction.DOWN, 5).offset(Direction.SOUTH, 5).offset(Direction.EAST, 5)).forEach((pos2) ->
						{
							if (isAir(world, pos2)) {
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
							}
						});
					}

					be.cooldown = 0;
				}
			} else if (be.lampType == LampType.ZETA) {
				//TODO: Change this to DELTA
				if (be.cooldown == 14) {
					BlockPos.iterate(pos.offset(Direction.UP, 9).offset(Direction.NORTH, 9).offset(Direction.WEST, 9),
							pos.offset(Direction.DOWN, 9).offset(Direction.SOUTH, 9).offset(Direction.EAST, 9)).forEach((pos2) ->
					{
						if (isAir(world, pos2)) {
							world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					});

					be.cooldown = 0;
				}
			} else if (be.lampType == LampType.ETA) {
				if (be.cooldown == 14) {
					BlockPos.iterate(pos.offset(Direction.UP, 11).offset(Direction.NORTH, 11).offset(Direction.WEST, 11),
							pos.offset(Direction.DOWN, 11).offset(Direction.SOUTH, 11).offset(Direction.EAST, 11)).forEach((pos2) ->
					{
						if (isAir(world, pos2)) {
							world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					});

					be.cooldown = 0;
				}
			} else if (be.lampType == LampType.OMEGA) {
				Random random = new Random();

				if (be.cooldown == 20) {
					BlockPos.iterate(pos.offset(Direction.UP, 15).offset(Direction.NORTH, 15).offset(Direction.WEST, 15),
							pos.offset(Direction.UP, 1).offset(Direction.NORTH, 0).offset(Direction.WEST, 1)).forEach((pos2) ->
					{
						if (isAir(world, pos2) && world.getBlockState(pos2.up()).getBlock() != Blocks.VINE) {
							if (random.nextInt(20) == 5)
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					});
				}
				if (be.cooldown == 30) {
					BlockPos.iterate(pos.offset(Direction.UP, 15).offset(Direction.SOUTH, 15).offset(Direction.WEST, 15),
							pos.offset(Direction.UP, 1).offset(Direction.SOUTH, 1).offset(Direction.WEST, 0)).forEach((pos2) ->
					{
						if (isAir(world, pos2) && world.getBlockState(pos2.up()).getBlock() != Blocks.VINE) {
							if (random.nextInt(20) == 5)
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					});
				}
				if (be.cooldown == 40) {
					BlockPos.iterate(pos.offset(Direction.UP, 15).offset(Direction.NORTH, 15).offset(Direction.EAST, 15),
							pos.offset(Direction.UP, 1).offset(Direction.NORTH, 1).offset(Direction.EAST, 0)).forEach((pos2) ->
					{
						if (isAir(world, pos2) && world.getBlockState(pos2.up()).getBlock() != Blocks.VINE) {
							if (random.nextInt(20) == 5)
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					});
				}
				if (be.cooldown == 50) {
					BlockPos.iterate(pos.offset(Direction.UP, 15).offset(Direction.SOUTH, 15).offset(Direction.EAST, 15),
							pos.offset(Direction.UP, 1).offset(Direction.SOUTH, 0).offset(Direction.EAST, 1)).forEach((pos2) ->
					{
						if (isAir(world, pos2) && world.getBlockState(pos2.up()).getBlock() != Blocks.VINE) {
							if (random.nextInt(20) == 5)
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					});
				}

				//---//---//---//---//---//---//---//---//---//---//---//---//---//---//---//---//---//---//---//---//---//---//---//---//---//---//

				if (be.cooldown == 60) {
					BlockPos.iterate(pos.offset(Direction.DOWN, 15).offset(Direction.NORTH, 15).offset(Direction.WEST, 15),
							pos.offset(Direction.NORTH, 1).offset(Direction.WEST, 0)).forEach((pos2) ->
					{
						if (isAir(world, pos2) && world.getBlockState(pos2.up()).getBlock() != Blocks.VINE) {
							if (random.nextInt(20) == 5)
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					});
				}
				if (be.cooldown == 70) {
					BlockPos.iterate(pos.offset(Direction.DOWN, 15).offset(Direction.SOUTH, 15).offset(Direction.WEST, 15),
							pos.offset(Direction.SOUTH, 0).offset(Direction.WEST, 1)).forEach((pos2) ->
					{
						if (isAir(world, pos2) && world.getBlockState(pos2.up()).getBlock() != Blocks.VINE) {
							if (random.nextInt(20) == 5)
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					});
				}
				if (be.cooldown == 80) {
					BlockPos.iterate(pos.offset(Direction.DOWN, 15).offset(Direction.NORTH, 15).offset(Direction.EAST, 15),
							pos.offset(Direction.NORTH, 0).offset(Direction.EAST, 1)).forEach((pos2) ->
					{
						if (isAir(world, pos2) && world.getBlockState(pos2.up()).getBlock() != Blocks.VINE) {
							if (random.nextInt(20) == 5)
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					});
				}
				if (be.cooldown == 90) {
					BlockPos.iterate(pos.offset(Direction.DOWN, 15).offset(Direction.SOUTH, 15).offset(Direction.EAST, 15),
							pos.offset(Direction.SOUTH, 1).offset(Direction.EAST, 0)).forEach((pos2) ->
					{
						if (isAir(world, pos2) && world.getBlockState(pos2.up()).getBlock() != Blocks.VINE) {
							if (random.nextInt(20) == 5)
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					});
				}
				if (be.cooldown == 100) {
					BlockPos.iterate(pos.offset(Direction.DOWN, 15),
							pos.offset(Direction.DOWN, 1)).forEach((pos2) ->
					{
						if (isAir(world, pos2) && world.getBlockState(pos2.up()).getBlock() != Blocks.VINE) {
							if (random.nextInt(20) == 5)
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					});
					BlockPos.iterate(pos.offset(Direction.UP, 15),
							pos.offset(Direction.UP, 1)).forEach((pos2) ->
					{
						if (isAir(world, pos2) && world.getBlockState(pos2.up()).getBlock() != Blocks.VINE) {
							if (random.nextInt(20) == 5)
								world.setBlockState(pos2, ModBlocks.LIGHT_AIR.getDefaultState());
						}
					});

            /*if (!updated)
            {
                updated = true;
                BlockPos.iterate(pos.offset(Direction.UP, 18).offset(Direction.NORTH,18).offset(Direction.WEST,18), pos.offset(Direction.DOWN,18).offset(Direction.SOUTH,18).offset(Direction.EAST,18)).forEach((pos1) -> {
                    if (isAir(world, pos1))
                    {
                        world.notifyBlockUpdate(pos1, world.getBlockState(pos1), world.getBlockState(pos1), 3);
                    }
                });
            }*/

					be.cooldown = 0;
				}
			}
		} else {
			if (be.lampType == LampType.ALPHA){
				if (be.cooldown == 5)
				{
					BlockPos pos1 = pos.offset(Direction.UP);
					if (isAir(world, pos1))
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
					}
					else if (world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
					}

					pos1 = pos.offset(Direction.DOWN);
					if (isAir(world, pos1))
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
					}
					else if (world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
					}

					pos1 = pos.offset(Direction.NORTH);
					if (isAir(world, pos1))
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
					}
					else if (world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
					}

					pos1 = pos.offset(Direction.SOUTH);
					if (isAir(world, pos1))
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
					}
					else if (world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
					}

					pos1 = pos.offset(Direction.WEST);
					if (isAir(world, pos1))
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
					}
					else if (world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
					}

					pos1 = pos.offset(Direction.EAST);
					if (isAir(world, pos1))
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
					}
					else if (world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
					}

					be.cooldown = 0;
				}
			} else if (be.lampType == LampType.BETA){

				if (be.cooldown == 5)
				{
					BlockPos pos1 = pos.offset(Direction.UP, 2);
					if (isAir(world, pos1))
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
					}
					else if (world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
					}

					pos1 = pos.offset(Direction.DOWN, 2);
					if (isAir(world, pos1))
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
					}
					else if (world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
					}

					pos1 = pos.offset(Direction.NORTH, 2);
					if (isAir(world, pos1))
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
					}
					else if (world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
					}

					pos1 = pos.offset(Direction.SOUTH, 2);
					if (isAir(world, pos1))
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
					}
					else if (world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
					}

					pos1 = pos.offset(Direction.WEST, 2);
					if (isAir(world, pos1))
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
					}
					else if (world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
					}

					pos1 = pos.offset(Direction.EAST, 2);
					if (isAir(world, pos1))
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
					}
					else if (world.getBlockState(pos1).getBlock() == Blocks.WATER)
					{
						world.setBlockState(pos1, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
					}

					BlockPos.iterate(pos.offset(Direction.UP, 1).offset(Direction.NORTH, 1).offset(Direction.WEST, 1), pos.offset(Direction.DOWN, 1).offset(Direction.SOUTH, 1).offset(Direction.EAST, 1)).forEach((pos2) ->
					{
						if (isAir(world, pos2))
						{
							world.setBlockState(pos2, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
						}
						else if (world.getBlockState(pos2).getBlock() == Blocks.WATER)
						{
							world.setBlockState(pos2, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
						}
					});

					be.cooldown = 0;
				}
			} else if (be.lampType == LampType.EPSILON){
				if (be.cooldown == 5)
				{
					BlockPos.iterate(pos.offset(Direction.UP, 3).offset(Direction.NORTH, 3).offset(Direction.WEST, 3),
							pos.offset(Direction.DOWN, 3).offset(Direction.SOUTH, 3).offset(Direction.EAST, 3)).forEach((pos2) ->
					{
						if (isAir(world, pos2))
						{
							world.setBlockState(pos2, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
						}
						else if (world.getBlockState(pos2).getBlock() == Blocks.WATER)
						{
							world.setBlockState(pos2, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
						}
					});

					be.cooldown = 0;
				}
			} else if (be.lampType == LampType.OCEAN_BETWEEN){
				if (be.cooldown == 5)
				{
					BlockPos.iterate(pos.offset(Direction.UP, 4).offset(Direction.NORTH, 4).offset(Direction.WEST, 4),
							pos.offset(Direction.DOWN, 4).offset(Direction.SOUTH, 4).offset(Direction.EAST, 4)).forEach((pos2) ->
					{
						if (isAir(world, pos2))
						{
							world.setBlockState(pos2, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
						}
						else if (world.getBlockState(pos2).getBlock() == Blocks.WATER)
						{
							world.setBlockState(pos2, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
						}
					});

					be.cooldown = 0;
				}
			} else if (be.lampType == LampType.ZETA){
				if (be.cooldown == 5)
				{
					BlockPos.iterate(pos.offset(Direction.UP, 5).offset(Direction.NORTH, 5).offset(Direction.WEST, 5),
							pos.offset(Direction.DOWN, 5).offset(Direction.SOUTH, 5).offset(Direction.EAST, 5)).forEach((pos2) ->
					{
						if (isAir(world, pos2))
						{
							world.setBlockState(pos2, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,false));
						}
						else if (world.getBlockState(pos2).getBlock() == Blocks.WATER)
						{
							world.setBlockState(pos2, ModBlocks.WATERLOGGABLE_LIGHT_AIR.getDefaultState().with(WATERLOGGED,true));
						}
					});

					be.cooldown = 0;
				}
			}
		}
	}
}