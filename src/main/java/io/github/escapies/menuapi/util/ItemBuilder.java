package io.github.escapies.menuapi.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBuilder {
    private final ItemStack item;

    public static ItemBuilder of(final Material material) {
        return new ItemBuilder(material, 1);
    }

    public static ItemBuilder of(final Material material, int amount) {
        return new ItemBuilder(material, amount);
    }

    public static ItemBuilder copyOf(final ItemBuilder builder) {
        return new ItemBuilder(builder.build());
    }

    public static ItemBuilder copyOf(final ItemStack item) {
        return new ItemBuilder(item);
    }

    private ItemBuilder(final Material material, int amount) {
        Preconditions.checkArgument(amount > 0, "Amount cannot be lower than 0.");
        this.item = new ItemStack(material, amount);
    }

    private ItemBuilder(final ItemStack item) {
        this.item = item;
    }

    public ItemBuilder amount(final int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemBuilder data(final short data) {
        this.item.setDurability(data);
        return this;
    }

    public ItemBuilder enchant(final Enchantment enchantment, int level) {
        this.item.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder unenchant(final Enchantment enchantment) {
        this.item.removeEnchantment(enchantment);
        return this;
    }

    public ItemBuilder name(final String displayName) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName((displayName == null) ? null : ChatColor.translateAlternateColorCodes('&', displayName));
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addToLore(final String... parts) {
        ItemMeta meta = this.item.getItemMeta();
        if (meta == null) {
            meta = Bukkit.getItemFactory().getItemMeta(this.item.getType());
        }
        List<String> lore = meta.getLore();
        if (lore == null) {
            lore = Lists.newArrayList();
        }
        lore.addAll(Arrays.stream(parts).map(part -> ChatColor.translateAlternateColorCodes('&', part)).collect(Collectors.toList()));
        meta.setLore(lore);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(final Collection<String> l) {
        List<String> lore = new ArrayList<String>();
        ItemMeta meta = this.item.getItemMeta();
        lore.addAll(l.stream().map(part -> ChatColor.translateAlternateColorCodes('&', part)).collect(Collectors.toList()));
        meta.setLore(lore);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder color(final Color color) {
        ItemMeta meta = this.item.getItemMeta();
        if (!(meta instanceof LeatherArmorMeta)) {
            throw new UnsupportedOperationException("Cannot set color of a non-leather armor item.");
        }
        ((LeatherArmorMeta) meta).setColor(color);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setUnbreakable(final boolean unbreakable) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setUnbreakable(unbreakable);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return this.item.clone();
    }
}

