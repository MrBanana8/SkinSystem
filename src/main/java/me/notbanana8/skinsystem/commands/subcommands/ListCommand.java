package me.notbanana8.skinsystem.commands.subcommands;

import me.notbanana8.skinsystem.commands.SubCommand;
import me.notbanana8.skinsystem.data.Skin;
import me.notbanana8.skinsystem.utils.SkinStorageUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ListCommand extends SubCommand {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "lists all the skins";
    }

    @Override
    public String getSyntax() {
        return "/skins list";
    }

    @Override
    public String getPermission() {
        return "skins.list";
    }

    @Override
    public void preform(Player player, String[] args) {
        ArrayList<String> nameList = new ArrayList<>();
        if(args.length == 1){
            for (Skin skin: SkinStorageUtil.findSkins()){
                nameList.add(skin.getName());
            }
            player.sendMessage(nameList.toString());
        }// else if (args.length == 1){
        //    player.sendMessage(getSyntax());
        //}

    }
}
