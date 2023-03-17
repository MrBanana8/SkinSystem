package me.notbanana8.skinsystem;

import me.notbanana8.skinsystem.data.Skin;
import me.notbanana8.skinsystem.utils.SkinStorageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;
import java.util.List;

public class Gui implements Listener {
    public Inventory inv;
    String Guiname = "Skins";
    List<Skin> skins = SkinStorageUtil.findSkins();


    public void newGui(Player player) {
        inv = Bukkit.createInventory(null, 9, Guiname);
        player.openInventory(inv);
        initializeItems();
    }

    public void initializeItems() {
        for(Skin skin: skins){
            inv.addItem(createGuiItem(skin.getMaterial(), skin.getModel(), skin.getName()));
        }
        inv.setItem(8, createGuiItem(Material.BARRIER, null, ChatColor.RED + "Clear Skin"));
    }

    protected ItemStack createGuiItem(final Material material, final Integer cmdata, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setCustomModelData(cmdata);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;
    }
    @EventHandler
    public void onInventoryClick (InventoryDragEvent event){
        if (event.getView().getTitle().equals(Guiname)
                || event.getView().getTopInventory().equals(event.getInventory())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void guiClickEvent(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(Guiname) ||
                !event.getView().getTopInventory().equals(event.getClickedInventory())) return;
        Player player = (Player) event.getWhoClicked();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        Integer ModelData = 0;
        if(event.getCurrentItem().getItemMeta().hasCustomModelData())
            ModelData = event.getCurrentItem().getItemMeta().getCustomModelData();
        event.setCancelled(true);
        if(item == null) return;
        if(!isMatching(item, event.getCurrentItem())) return;

        for(Skin skin: SkinStorageUtil.skins){
            if(skin.getMaterial() != item.getType()) return;
            meta.setCustomModelData(ModelData);
            item.setItemMeta(meta);
            player.getInventory().setItemInMainHand(item);
            player.updateInventory();
        }

        if (event.getCurrentItem().getType() == Material.BARRIER) {
            meta.setCustomModelData(0);
            item.setItemMeta(meta);
            player.getInventory().setItemInMainHand(item);
            player.updateInventory();
        }
    }
    public static boolean isMatching(ItemStack invItem, ItemStack guiItem){
        String invItemName = invItem.getType().toString().toLowerCase();
        String guiItemName = guiItem.getType().toString().toLowerCase();
        if(invItemName == guiItemName)
            return true;
        if(invItemName.contains("pickaxe") && guiItemName.contains("pickaxe"))
            return true;
        else if (invItemName.contains("axe") && guiItemName.contains("axe") &&
        !invItemName.contains("pick") && !guiItemName.contains("pick"))
            return true;
        else if (invItemName.contains("shovel") && guiItemName.contains("shovel"))
            return true;
        else if (invItemName.contains("hoe") && guiItemName.contains("hoe"))
            return true;
        if(guiItem.getType() == Material.BARRIER)
            return true;
        return false;
        }
    }
