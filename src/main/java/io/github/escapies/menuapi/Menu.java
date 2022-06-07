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

    private final int size;
    private final String title;
    private final boolean isFill;

    /**
     * Creates a new menu with the given size and title.
     * @param title The title of the menu.
     * @param size The size of the menu. (Must be a multiple of 9)
     * @param isFill Whether the menu is filled a filler block or not.
     */
    public Menu(String title, int size, boolean isFill) {
        this.title = title;
        this.size = size;
        this.isFill = shouldFill;

    }

    public abstract HashMap<Integer, Button> getButtons(final Player player);

    public void openMenu(final Player player) {
        if (MenuHandler.getPlugin() == null) {
            Bukkit.getLogger().warning("MenuHandler instance has not been initialized, menus will not work.");
            return;
        }

        Inventory inventory = Bukkit.createInventory(null, this.size,
                ChatColor.translateAlternateColorCodes('&', this.title));

        if (isFill) {
            for (int i = 0; i < this.size; i++) {
                inventory.setItem(i, ItemBuilder.of(Material.GRAY_STAINED_GLASS_PANE, 1).name("").build());
            }
        }
        for (Map.Entry<Integer, Button> entry : getButtons(player).entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItemStack());
        }

        player.openInventory(inventory);
    }
}
