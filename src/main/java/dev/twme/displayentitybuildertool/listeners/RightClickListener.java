package dev.twme.displayentitybuildertool.listeners;

import dev.twme.displayentitybuildertool.datas.block.PlaceData;
import dev.twme.displayentitybuildertool.datas.block.PlaceMode;
import dev.twme.displayentitybuildertool.util.PlaceUtil;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class RightClickListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (playerOpenContainer(event)) return;
        if (!isRightClick(event.getAction())) return;
        if (!player.hasPermission("displayentitybuildtool.place")) return;

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (!itemStack.getType().isBlock()) {
            return;
        }

        PlaceData placeData = new PlaceData();

        placeData.setSlabYaw(player.getLocation().getYaw())
                .setMaterial(itemStack.getType())
                .setLocation(player)
                .setPlaceMode(PlaceMode.ItemDisplay)
                .setLocationWorld(player.getWorld());

        if (!placeData.existLocation()) {
            return;
        }
        // 放置一般方快
        if (!placeData.checkBlockCanBeUse(itemStack)) {

            // 放到實體上
            if (placeData.isPlaceOnEntity()) {
                if (!(EntityType.ITEM_DISPLAY == placeData.getHitEntity().getType())) {
                    return;
                }

                // 直接將方塊放置到方塊內
                if (player.isSneaking()) {
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
        if (placeData.isPlaceOnEntity() && player.isSneaking() && (EntityType.ITEM_DISPLAY == placeData.getHitEntity().getType())) {
            placeData.setLocation(placeData.getEntityLocation());
        }
        PlaceUtil.place(placeData);
        player.playSound(player.getLocation(), Sound.BLOCK_STONE_PLACE, 1f, 1f);

        //Log.log("Place Entity Pass");
    }

    private static boolean playerOpenContainer(PlayerInteractEvent event) {
        return event.getClickedBlock() != null && event.getClickedBlock() instanceof InventoryHolder;
    }

    private static boolean isRightClick(Action action) {
        return action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK);
    }

}
