package me.notbanana8.skinsystem.commands.subcommands;

import me.notbanana8.skinsystem.commands.SubCommand;
import me.notbanana8.skinsystem.data.Skin;
import me.notbanana8.skinsystem.utils.SkinStorageUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateCommand extends SubCommand {
    public static HashMap<String, Skin> skinMap = new HashMap<String, Skin>();
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "creates a skin";
    }

    @Override
    public String getSyntax() {
        return "/skins create <Name> <Material> <Model>";
    }

    @Override
    public String getPermission() {
        return "skins.create";
    }

    @Override
    public void preform(Player player, String[] args) {
        if (args.length > 1){
            if (!isNum(args[3])) return;

            SkinStorageUtil.createSkin(ChatColor.translateAlternateColorCodes('&',args[1]),Material.valueOf(args[2]),Integer.valueOf(args[3]));
            player.sendMessage("you have successfully created your skin!");
        } else if (args.length < 3){
            player.sendMessage(getSyntax());
        }
    }

    @Override
    public List<String> getSubcommandArgs(Player player, String[] args) {
        List<String> createArgs = new ArrayList<>();
            if(args.length == 2){
                createArgs.add("<name>");
                return createArgs;
            } else if (args.length == 3) {
                createArgs.add("<base_material>");
                return createArgs;
            } else if (args.length == 4) {
                createArgs.add("<custom_model>");
                return createArgs;
            }

        return null;
    }

    public static boolean isNum(String strNum) {
        boolean ret = true;
        try {
            Double.parseDouble(strNum);
        }catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }
}
