package me.ridiche.superSkibidiElytra.equip;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class EquipMenu extends Menu {
    public EquipMenu(PlayerMenuManager _playerMenuManager) {
        super(_playerMenuManager);
    }

    @Override
    public String[] getInitialMenuAsciiArt() {
        return new String[]{
                "....E....",
                "..T . T..",
                "..R B R..",
                "iGGGaGGGi",
                "G       G",
                "G       G"
        };
    }

    @Override
    public HashMap<Character, ItemStack> getInitialMenuAsciiArtMeanings() {
        HashMap<Character, ItemStack> r = new HashMap<>();
        r.put(' ', ItemStack.empty());

        ItemStack blackFiller = ItemStack.of(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = blackFiller.getItemMeta();
        fillerMeta.displayName(Component.text(""));
        blackFiller.setItemMeta(fillerMeta);
        r.put('.', blackFiller);
        ItemStack goldFiller = ItemStack.of(Material.YELLOW_STAINED_GLASS_PANE);
        goldFiller.setItemMeta(fillerMeta);
        r.put('G', goldFiller);
        ItemStack redFiller = ItemStack.of(Material.RED_STAINED_GLASS_PANE);
        redFiller.setItemMeta(fillerMeta);
        r.put('R', redFiller);

        ItemStack thrust = ItemStack.of(Material.SPECTRAL_ARROW).asQuantity(15);
        ItemMeta thrustMeta = thrust.getItemMeta();
        thrustMeta.displayName(Component.text("Rachete nucleare"));
        thrust.setItemMeta(thrustMeta);
        r.put('a', thrust);

        ItemStack backpack = ItemStack.of(Material.DARK_OAK_WOOD);
        ItemMeta backpackMeta = thrust.getItemMeta();
        backpackMeta.displayName(Component.text("Backpack"));
        backpack.setItemMeta(backpackMeta);
        r.put('B', backpack);

        ItemStack tie = ItemStack.of(Material.LEAD);
        ItemMeta tieMeta = thrust.getItemMeta();
        tieMeta.displayName(Component.text("Tie"));
        tie.setItemMeta(tieMeta);
        r.put('T', tie);

        r.put('G', goldFiller);
        r.put('E', ItemStack.of(Material.ELYTRA));
        r.put('i', ItemStack.of(Material.RED_DYE));

        return r;
    }

    @Override
    public Component getTitle() {
        return Component.text("Equipping Elytra");
    }
}
