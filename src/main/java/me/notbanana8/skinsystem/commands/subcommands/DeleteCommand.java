package me.notbanana8.skinsystem.commands.subcommands;

import me.notbanana8.skinsystem.commands.SubCommand;
import me.notbanana8.skinsystem.data.Skin;
import me.notbanana8.skinsystem.utils.SkinStorageUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends SubCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "deletes a skin";
    }

    @Override
    public String getSyntax() {
        return "/skins delete <skin_name>";
    }

    @Override
    public String getPermission() {
        return "skins.delete";
    }

    @Override
    public void preform(Player player, String[] args) {
        if (args.length > 1){
            SkinStorageUtil.deleteSkin(args[1]);
            player.sendMessage("you have successfully deleted the skin!");
        } else if (args.length == 1){
            player.sendMessage(getSyntax());
        }
    }

    @Override
    public List<String> getSubcommandArgs(Player player, String[] args) {
        if(args.length == 2){
            List<String> skinNameList = new ArrayList<>();
            for(Skin skin: SkinStorageUtil.findSkins()){
                skinNameList.add(skin.getName());
            }
            return skinNameList;
        }
        return null;
    }
}
