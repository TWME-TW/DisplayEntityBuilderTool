package land.builders.displayentitybuildertool.listeners;

import land.builders.displayentitybuildertool.datas.block.PlaceData;
import land.builders.displayentitybuildertool.datas.block.PlaceMode;
import land.builders.displayentitybuildertool.util.Log;
import land.builders.displayentitybuildertool.util.PlaceUtil;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RightClickListener implements Listener {
    @EventHandler(
            priority = EventPriority.LOW
    )
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if (player.getOpenInventory().getTopInventory().getType() == InventoryType.CHEST)
            return;
        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;
        if (!player.hasPermission("displayentitybuildtool.place"))
            return;

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (!itemStack.getType().isBlock()){
            return;
        }

        PlaceData placeData = new PlaceData();

        placeData.setSlabYaw(player.getLocation().getYaw())
                .setMaterial(itemStack.getType())
                .setLocation(player)
                .setPlaceMode(PlaceMode.ItemDisplay)
                .setLocationWorld(player.getWorld());

        if (!placeData.existLocation()){
            return;
        }
        // 放置一般方快
        if (!placeData.checkBlockCanBeUse(itemStack)) {

            // 放到實體上
            if (placeData.isPlaceOnEntity()){

                // 直接將方塊放置到方塊內
                if (player.isSneaking()){
                    placeData.setPlaceMode(PlaceMode.Block);
                    placeData.setLocation(placeData.getEntityLocation());
                    PlaceUtil.place(placeData);
                    event.setCancelled(true);
                    return;
                }

                placeData.setPlaceMode(PlaceMode.Block);
                PlaceUtil.place(placeData);
                event.setCancelled(true);
            }
            return;
        }

        //Log.log("RightClickListener Pass");

        event.setCancelled(true);

        //Log.log("Placing Entity");
        // 放置實體
        if (placeData.isPlaceOnEntity() && player.isSneaking()){
            placeData.setLocation(placeData.getEntityLocation());
        }
        PlaceUtil.place(placeData);
        player.playSound(player.getLocation(), Sound.BLOCK_STONE_PLACE,1f,1f);

        //Log.log("Place Entity Pass");
    }
}
