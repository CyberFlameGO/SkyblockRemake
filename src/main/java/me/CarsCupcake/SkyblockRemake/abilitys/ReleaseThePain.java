package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ReleaseThePain implements AbilityManager<PlayerInteractEvent> {
    private static final ArrayList<SkyblockPlayer> players = new ArrayList<>();

    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        System.out.println(players);
        if(!players.contains(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()))){
            players.add(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
            startTimer(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        }

        return false;
    }

    public static ArrayList<SkyblockPlayer> getPlayers() {
        return players;
    }
    public void startTimer(final SkyblockPlayer player) {

        player.sendMessage("§aRelease The Pain is now active!");
       player.setHealth((player.currhealth + (Main.getPlayerStat(player, Stats.Health) * 20))*player.healingMulti, HealthChangeReason.Ability);
       Main.updatebar(player);
        new BukkitRunnable() {
            private int timer = 5;
            @Override
            public void run() {
                timer--;
                if(timer <= 0) {
                    players.remove(player);
                    cancel();
                    player.sendMessage("§cRelease The Pain expired!");
                    return;
                }

                if(timer <4)
                    player.sendMessage("§cRelease The Pain expires in "+timer+" seconds!");

            }
        }.runTaskTimer(Main.getMain(), 20,20);
    }
}
