package me.ridiche.superSkibidiElytra.equip;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.HashMap;

public class PlayerMenuManager {
    Player player;
    @Nullable
    Menu activeMenu = null;

    private static final ItemStack errorItemStack = new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE, 32);
    public final ItemMeta fillerItemMeta;

    public PlayerMenuManager(Player _player) {
        this.player = _player;
        fillerItemMeta = new ItemStack(Material.BLACK_STAINED_GLASS_PANE).getItemMeta();
        fillerItemMeta.displayName(Component.empty());
    }

    public void openMenu(Menu menu) {
        activeMenu = menu;
        String[] asciiArt = menu.getInitialMenuAsciiArt();
        Inventory inventory = Bukkit.createInventory(player, asciiArt.length*9, menu.getTitle());
        HashMap<Character, ItemStack> asciiArtTable = menu.getInitialMenuAsciiArtMeanings();

        ItemStack[] toSetContents = new ItemStack[asciiArt.length*9];
        for (int lineNumber = 0; lineNumber < asciiArt.length; lineNumber++) {
            for (int columnNumber = 0; columnNumber < asciiArt[lineNumber].length(); columnNumber++) {
                if (columnNumber > 9)
                    continue;
                toSetContents[lineNumber*9+columnNumber] =
                        asciiArtTable.getOrDefault(asciiArt[lineNumber].charAt(columnNumber), errorItemStack);
            }
        }
        inventory.setContents(toSetContents);

        player.openInventory(inventory);
    }
}
