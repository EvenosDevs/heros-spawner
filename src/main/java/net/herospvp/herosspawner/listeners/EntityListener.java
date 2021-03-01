package net.herospvp.herosspawner.listeners;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import net.herospvp.herosspawner.HerosSpawner;
import net.herospvp.herosspawner.objects.Collector;
import net.herospvp.herosspawner.objects.SpawnerDrop;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityListener implements Listener {
    private HerosSpawner plugin;

    public EntityListener(HerosSpawner plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void on(EntityDeathEvent event) {
        Faction faction = Board.getInstance().getFactionAt(new FLocation(event.getEntity().getLocation()));
        if (faction.isWilderness()) return;
        if (event.getEntity().getCustomName() == null) return;

        if (SpawnerDrop.getDrop(event.getEntityType()) == null) return;

        event.getDrops().clear();

        int amount = Integer.parseInt(ChatColor.stripColor(event.getEntity().getCustomName()).replace("x", ""));

        Collector collector = plugin.getCollectorHandler().getCollector(faction.getId());
        if (collector == null) return;
        collector.addDrop(SpawnerDrop.getDrop(event.getEntityType()), amount);
    }
}
