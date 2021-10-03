package tk.dczippl.lightestlamp.util;

import team.reborn.energy.EnergySide;
import team.reborn.energy.EnergyStorage;
import team.reborn.energy.EnergyTier;

/**
 * A base energy storage implementation with fixed capacity, and per-operation insertion and extraction limits.
 */
@SuppressWarnings({"unused", "deprecation", "UnstableApiUsage"})
public class SimpleEnergyStorage implements EnergyStorage {
	public double amount = 0;
	public final double capacity;
	public final double maxInsert, maxExtract;
	
	public SimpleEnergyStorage(double capacity, double maxInsert, double maxExtract) {
		this.capacity = capacity;
		this.maxInsert = maxInsert;
		this.maxExtract = maxExtract;
	}
	
	public boolean supportsInsertion() {
		return maxInsert > 0;
	}
	
	public double insert(double maxAmount) {
		double inserted = Double.min(getTier().getMaxInput(), Double.min(maxAmount, capacity - amount));
		
		if (inserted > 0) {
			amount += inserted;
			return inserted;
		}
		
		return 0;
	}
	
	public boolean supportsExtraction() {
		return maxExtract > 0;
	}
	
	public double extract(double maxAmount) {
		double extracted = Double.min(getTier().getMaxOutput(), Double.min(maxAmount, amount));
		
		if (extracted > 0) {
			amount -= extracted;
			return extracted;
		}
		
		return 0;
	}

	/**
	 * Returns the currently stored energy
	 *
	 * @param face The Side that is accessing the energy
	 * @return the amount of energy stored
	 */
	@Override
	public double getStored(EnergySide face) {
		return amount;
	}
	
	/**
	 * Sets the stored energy to the provided amount
	 *
	 * @param amount the amount of energy to set
	 */
	@Override
	public void setStored(double amount) {
		this.amount = amount;
	}
	
	/**
	 * @return Returns the maximum amount of energy to be stored
	 */
	@Override
	public double getMaxStoredPower() {
		return capacity;
	}
	
	/**
	 * @return the tier of this EnergyStorage
	 */
	@Override
	public EnergyTier getTier() {
		return EnergyTier.MEDIUM;
	}
}