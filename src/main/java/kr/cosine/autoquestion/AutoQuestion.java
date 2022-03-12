package kr.cosine.autoquestion;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AutoQuestion extends JavaPlugin implements Listener {

    private FileConfiguration player;
    private File file = new File("plugins/AutoQuestion/config.yml");

    @Override
    public void onEnable() {
        getLogger().info("자동 응답 플러그인 활성화");
        getCommand("설정").setExecutor(new Command());
        getCommand("질문").setExecutor(new Command2());
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Event(), this);
        loadConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("자동 응답 플러그인 비활성화");

    }
    private void loadConfig() {
        player = YamlConfiguration.loadConfiguration(file);
        try {
            if (!file.exists()) {
                player.set("질문.예시질문", "답변");
                player.save(file);
            }
            player.load(file);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }
}
