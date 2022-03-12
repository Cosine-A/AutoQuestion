package kr.cosine.autoquestion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Command2 implements CommandExecutor {

    public static HashMap<String, String> questionMap;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(p.isOp()) {
            if(args.length == 0) {
                p.sendMessage("§f§l[ §b§l질문 §f§l]§a§l 자동 답변 시스템§f§l 도움말");
                p.sendMessage("");
                p.sendMessage("§f§l[ §b§l질문 §f§l]§f /질문 설정 [단어]");
                p.sendMessage("§f§l[ §b§l질문 §f§l]§f /질문 리로드");
                return true;
            }
            switch (args[0]) {
                case "설정": {
                    if(args.length == 1) {
                        p.sendMessage("§f§l[ §b§l질문 §f§l]§f /질문 설정 [단어]");
                    }
                    if(args.length == 2) {
                        p.sendMessage("§f§l[ §b§l질문 §f§l]§f 해당 단어를 자동 답변에 등록하였습니다.");
                        p.sendMessage("§f§l[ §b§l질문 §f§l]§f config 파일 내에서 답변을 수정해주세요.");
                        AutoQuestion.getPlugin(AutoQuestion.class).getConfig().set("질문." + args[1], "");
                        AutoQuestion.getPlugin(AutoQuestion.class).saveConfig();
                    }
                    break;
                }
                case "리로드": {
                    AutoQuestion.getPlugin(AutoQuestion.class).reloadConfig();
                    questionMap = new HashMap<>();
                    for(String value : AutoQuestion.getPlugin(AutoQuestion.class).getConfig().getConfigurationSection("질문").getKeys(false))
                        questionMap.put(value, AutoQuestion.getPlugin(AutoQuestion.class).getConfig().getConfigurationSection("질문").get(value).toString());
                    p.sendMessage("§f§l[ §b§l질문 §f§l]§f config를 리로드 하였습니다.");
                    break;
                }
            }
        }
        return false;
    }
}
