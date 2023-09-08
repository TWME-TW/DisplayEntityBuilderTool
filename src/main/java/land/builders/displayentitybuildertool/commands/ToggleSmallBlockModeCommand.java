package land.builders.displayentitybuildertool.commands;

import land.builders.displayentitybuildertool.datas.block.PlaceData;
import land.builders.displayentitybuildertool.datas.block.PlaceMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ToggleSmallBlockModeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            return false;
        }

        Player player = (Player) commandSender;
        PlaceData placeData = new PlaceData().setPlaceMode(PlaceMode.SmallBlock).setPlayer(player);




        return false;
    }
}
