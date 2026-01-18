package me.ridiche.superSkibidiElytra.equip;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public abstract class Menu {
    protected final PlayerMenuManager playerMenuManager;

    protected Menu(PlayerMenuManager _playerMenuManager) {
        this.playerMenuManager = _playerMenuManager;
    }

    public abstract String[] getInitialMenuAsciiArt();
    public abstract HashMap<Character, ItemStack> getInitialMenuAsciiArtMeanings();

    public abstract Component getTitle();
}
