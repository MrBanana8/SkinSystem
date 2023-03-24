package me.notbanana8.skinsystem.commands;

import me.notbanana8.skinsystem.Gui;
import me.notbanana8.skinsystem.commands.subcommands.CreateCommand;
import me.notbanana8.skinsystem.commands.subcommands.DeleteCommand;
import me.notbanana8.skinsystem.commands.subcommands.ListCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {
    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager(){
        subCommands.add(new CreateCommand());
        subCommands.add(new DeleteCommand());
        subCommands.add(new ListCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length > 0){
                for (int i = 0 ;i < getSubCommands().size(); i++){
                    if(!player.hasPermission(getSubCommands().get(i).getPermission())){
                        player.sendMessage(ChatColor.RED + "You don't have the permissions to preform that command!");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase(getSubCommands().get(i).getName())){
                        getSubCommands().get(i).preform(player,args);
                    }
                }
            } else{
                new Gui().newGui(player);
            }
        }
        return true;
    }

    public ArrayList<SubCommand> getSubCommands(){
        return subCommands;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        for(SubCommand subcmd: getSubCommands()){
            if(!sender.hasPermission(subcmd.getPermission())) return null;
        }
        if(args.length == 1){
            ArrayList<String> subCommandArgs = new ArrayList<>();
            for (int i = 0 ; i < getSubCommands().size(); i++){
                subCommandArgs.add(getSubCommands().get(i).getName());
            }
            return subCommandArgs;
        } else if (args.length > 1) {
            for(int i = 0 ; i < getSubCommands().size(); i++){
                if(args[0].equalsIgnoreCase(getSubCommands().get(i).getName())){
                    return getSubCommands().get(i).getSubcommandArgs((Player) sender, args);
                }
            }
        }
        return null;
    }
}
