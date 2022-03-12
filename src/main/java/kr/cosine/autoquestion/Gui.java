package kr.cosine.autoquestion;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;

public class Gui {
    public void setting(Player p) {
        Inventory setting = Bukkit.createInventory(null, 9, "설정");
        Boolean choice = AutoQuestion.getPlugin(AutoQuestion.class).getConfig().getBoolean((p.getUniqueId()).toString());
        if(choice.equals(false)) {
            setItem("§a§l자동 답변 기능", Arrays.asList("§7상태: §c꺼짐"), 251, 14, 1, 0, setting);
        }
        else if(choice.equals(true)) {
            setItem("§a§l자동 답변 기능", Arrays.asList("§7상태: §a켜짐"), 251, 5, 1, 0, setting);
        }
        p.openInventory(setting);
    }
    public void setItem(String display, List<String> lore, int ID, int data, int stack, int loc, Inventory inv) {
        ItemStack item = (new MaterialData(ID, (byte)data)).toItemStack(stack);
        ItemMeta items = item.getItemMeta();
        items.setDisplayName(display);
        items.setLore(lore);
        item.setItemMeta(items);
        inv.setItem(loc, item);
    }
}
