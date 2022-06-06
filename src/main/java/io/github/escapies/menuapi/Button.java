package io.github.escapies.menuapi;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class Button {

    public abstract String getName();

    public abstract List<String> getDescription();

    public abstract Material getMaterial();

    public abstract int getAmount();

    public abstract void clicked(Player player, ClickType clickType);

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(getMaterial(), getAmount());
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
            List<String> lore = new ArrayList<>();
            getDescription().forEach(line -> lore.add(ChatColor.translateAlternateColorCodes('&', line)));
            itemMeta.setLore(lore);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static Button fromItem(final ItemStack item) {
        return new Button() {
            @Override
            public String getName() {
                if (item.getItemMeta() == null) {
                    return null;
                }
                return item.getItemMeta().getDisplayName();
            }

            @Override
            public List<String> getDescription() {
                if (item.getItemMeta() == null) {
                    return null;
                }
                return item.getItemMeta().getLore();
            }

            @Override
            public Material getMaterial() {
                return item.getType();
            }

            @Override
            public int getAmount() {
                return item.getAmount();
            }

            @Override
            public void clicked(Player player, ClickType clickType) {
            }
        };
    }

}
