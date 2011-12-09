package com.Khorn.TerrainControl.Commands;

import com.Khorn.TerrainControl.TCPlugin;
import com.Khorn.TerrainControl.Util.MapWriter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Player;

import java.util.List;

public class MapCommand extends BaseCommand
{
    public MapCommand(TCPlugin _plugin)
    {
        super(_plugin);
        name = "map";
        usage = "/tc map [World] [-s size]";
        help = "Create biome map with width and height size in chunks";
        workOnConsole = true;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args)
    {
        CraftWorld world = null;
        int size = 200;

        if (args.size() != 0 && !args.get(0).equals("-s"))
        {
            world = (CraftWorld) Bukkit.getWorld(args.get(0));
            args.remove(0);
            if (world == null)
            {
                sender.sendMessage(ErrorColor + "You need to select world");
                return true;
            }
        }
        if (world == null)
            if (sender instanceof ConsoleCommandSender)
            {
                sender.sendMessage(ErrorColor + "You need to select world");
                return true;
            } else
                world = (CraftWorld) ((Player) sender).getWorld();

        if (args.size() == 2 && args.get(0).equals("-s"))
            try
            {
                size = Integer.parseInt(args.get(1));
            } catch (Exception e)
            {
                sender.sendMessage(ErrorColor + "Wrong size " + args.get(1));
            }


        MapWriter map = new MapWriter(this.plugin, world.getHandle(), size, sender);

        this.plugin.getServer().getScheduler().scheduleAsyncDelayedTask(this.plugin, map);

        return true;
    }
}
