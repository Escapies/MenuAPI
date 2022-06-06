package io.github.escapies.menuapi;

import io.github.escapies.menuapi.listener.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class MenuHandler {

    private static JavaPlugin plugin;

    private static final HashMap<UUID, Menu> inMenus = new HashMap<>();

    public MenuHandler(JavaPlugin plugin) {
        MenuHandler.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(new MenuListener(), plugin);
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static boolean isInMenu(UUID uuid) {
        return inMenus.containsKey(uuid);
    }

    public static Menu getMenu(UUID uuid) {
        return inMenus.get(uuid);
    }

    public static void remove(UUID uuid) {
        inMenus.remove(uuid);
    }

    public static void add(UUID uuid, Menu menu) {
        inMenus.put(uuid, menu);
    }
}
