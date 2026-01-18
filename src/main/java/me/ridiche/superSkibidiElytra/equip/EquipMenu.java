package me.ridiche.superSkibidiElytra.equip;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class EquipMenu extends Menu {
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
        blackFiller.setItemMeta()
        r.put('.', );
        r.put('#', ItemStack.of(Material.TERRACOTTA).asQuantity(69));
        r.put('H', ItemStack.of(Material.GLASS));
        r.put('l', ItemStack.of(Material.LIME_STAINED_GLASS_PANE));
        ItemStack lubenite = ItemStack.of(Material.MELON_SLICE);
        ItemMeta lubeniteMeta = lubenite.getItemMeta();
        lubeniteMeta.displayName(Component.text("Lubenite"));
        lubenite.setItemMeta(lubeniteMeta);
        r.put('6', lubenite.clone().asQuantity(6));
        r.put('7', lubenite.clone().asQuantity(7));
        return r;
    }

    @Override
    public Component getTitle() {
        return Component.text("Equipping Elytra");
    }
}
