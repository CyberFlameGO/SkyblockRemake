package me.CarsCupcake.SkyblockRemake.Items.minions;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.UUID;

/**
 * Basic methods that a minion has to provide
 */

public interface Minion {
    /**
     * Starts the work progress
     */
    void startWorking();

    /**
     * removes the minion
     *
     * @param removeReason is the remove reason
     */
    void remove(MinionRemoveReason removeReason);

    /**
     * is a check if the inventory is full
     *
     * @return true if the inventory is full
     */
    boolean isInventoryFull();

    /**
     * is for sending the message
     *
     * @param message  is the message
     * @param duration is the time in ticks how long the message is visable
     */
    void setMinionMessage(String message, long duration);

    /**
     * Show the inventory of the minion
     */
    void showInventory();

    /**
     * Gets the representive stand
     *
     * @return the armor stand
     */
    ArmorStand getArmorStand();

    UUID getId();

    static Minion getMinion(IMinion base, int level, Location location, String minionid, SkyblockPlayer player) {
        if (base instanceof AbstractMiningMinion miningMinion)
            return new MiningMinion(level, miningMinion, location, minionid, player);
        if (base instanceof AbstractCombatMinion combatMinion)
            return new CombatMinion(level, combatMinion, location, minionid, player);
        return null;
    }
}
