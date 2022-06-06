package io.github.escapies.menuapi.listener;

import io.github.escapies.menuapi.Button;
import io.github.escapies.menuapi.Menu;
import io.github.escapies.menuapi.MenuHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class MenuListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }

        if (MenuHandler.isInMenu(player.getUniqueId())) {
            return;
        }

        Menu menu = MenuHandler.getMenu(player.getUniqueId());

        if (menu.getButtons(player).containsKey(event.getSlot())) {
            Button button = menu.getButtons(player).get(event.getSlot());
            button.clicked(player, event.getClick());
            MenuHandler.remove(player.getUniqueId());
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.closeInventory();
                }
            }.runTaskLater(MenuHandler.getPlugin(), 1L);
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onInventoryInteract(InventoryInteractEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }

        if (MenuHandler.isInMenu(player.getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }

        if (MenuHandler.isInMenu(player.getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
