package io.github.escapies.menuapi;

import io.github.escapies.menuapi.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public abstract class Menu {

    public abstract int getSize(final Player player);

    public abstract String getTitle(final Player player);

    public abstract boolean isFill(final Player player);

    public abstract HashMap<Integer, Button> getButtons(final Player player);

    public void openMenu(final Player player) {
        if (MenuHandler.getPlugin() == null) {
            Bukkit.getLogger().warning("MenuHandler instance has not been initialized, menus will not work.");
            return;
        }

        Inventory inventory = Bukkit.createInventory(null, getSize(player),
                ChatColor.translateAlternateColorCodes('&', getTitle(player)));

        if (isFill(player)) {
            for (int i = 0; i < getSize(player); i++) {
                inventory.setItem(i, ItemBuilder.of(Material.GRAY_STAINED_GLASS_PANE, 1).name("").build());
            }
        }
        for (Map.Entry<Integer, Button> entry : getButtons(player).entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItemStack());
        }

        player.openInventory(inventory);
    }
}
