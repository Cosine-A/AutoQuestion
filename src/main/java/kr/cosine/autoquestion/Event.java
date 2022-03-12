package kr.cosine.autoquestion;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;

import static kr.cosine.autoquestion.Command2.questionMap;

public class Event implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getTitle().equals("설정")) {
            if(e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta()) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
                if(e.getRawSlot() == 0) {
                    Boolean choice = AutoQuestion.getPlugin(AutoQuestion.class).getConfig().getBoolean((p.getUniqueId()).toString());
                    if(choice.equals(false)) {
                        p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                        AutoQuestion.getPlugin(AutoQuestion.class).getConfig().set((p.getUniqueId()).toString(), true);
                        setItem2("§a§l자동 질문 기능", Arrays.asList("§7상태: §a켜짐"), 251, 5, 1, 0, e.getClickedInventory());
                    }
                    else if(choice.equals(true)) {
                        p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                        AutoQuestion.getPlugin(AutoQuestion.class).getConfig().set((p.getUniqueId()).toString(), false);
                        setItem2("§a§l자동 질문 기능", Arrays.asList("§7상태: §c꺼짐"), 251, 14, 1, 0, e.getClickedInventory());
                    }
                }
            }
        }
    }
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if(e.getInventory().getTitle().equals("설정")) {
            AutoQuestion.getPlugin(AutoQuestion.class).saveConfig();
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        Boolean choice = AutoQuestion.getPlugin(AutoQuestion.class).getConfig().getBoolean((p.getUniqueId()).toString());
        if(choice.equals(true)) {
            if(questionMap != null) {
                for(String answer : questionMap.keySet()) {
                    if (e.getMessage().contains(answer) && e.getMessage().contains("?")) {
                        e.setCancelled(true);
                        String message = e.getMessage();
                        p.sendMessage("┌───────────────────────────────────────────────────┒");
                        p.sendMessage("   §6§l자동 §f§l답변 해주는 코사인 봇");
                        p.sendMessage("");
                        p.sendMessage("   §c§l Q.§f " + message);
                        p.sendMessage("   §a§l A.§f " + questionMap.get(answer));
                        p.sendMessage("");
                        p.sendMessage("   §7자동 답변을 원하시지 않으면 [/설정]에서 꺼주세요.");
                        p.sendMessage("└───────────────────────────────────────────────────┘");
                        return;
                    }
                }
            }
        }
    }
    public void setItem2(String display, List<String> lore, int ID, int data, int stack, int loc, Inventory inv) {
        ItemStack item = (new MaterialData(ID, (byte)data)).toItemStack(stack);
        ItemMeta items = item.getItemMeta();
        items.setDisplayName(display);
        items.setLore(lore);
        item.setItemMeta(items);
        inv.setItem(loc, item);
    }
}
