package land.builders.displayentitybuildertool.util;

import land.builders.displayentitybuildertool.datas.block.ItemType;
import land.builders.displayentitybuildertool.datas.block.PlaceData;
import land.builders.displayentitybuildertool.datas.block.PlaceMode;
import org.bukkit.Location;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

public class PlaceUtil {
    public static void place(PlaceData placeData){
        if (placeData.getPlaceMode().equals(PlaceMode.ItemDisplay)){
            if (placeData.getItemType().equals(ItemType.Slab)){

                ItemDisplay itemDisplay = (ItemDisplay) placeData.getLocation().getWorld().spawnEntity(placeData.getLocation(),EntityType.ITEM_DISPLAY);;
                ItemStack itemStack = new ItemStack(placeData.getMaterial(),1);
                itemDisplay.setItemStack(itemStack);
                itemDisplay.setRotation(placeData.getSlabYaw(),-90);
                //Log.log("PlaceUtil place Pass");

            } else if (placeData.getItemType().equals(ItemType.Stairs)) {
                ItemDisplay itemDisplay = (ItemDisplay) placeData.getLocation().getWorld().spawnEntity(placeData.getLocation(),EntityType.ITEM_DISPLAY);;
                ItemStack itemStack = new ItemStack(placeData.getMaterial(),1);
                itemDisplay.setItemStack(itemStack);
                itemDisplay.setRotation(placeData.getSlabYaw(),-90);
            }
        } else if (placeData.getPlaceMode().equals(PlaceMode.Block)) {
            placeData.getLocation().getBlock().setType(placeData.getMaterial());
        }
    }
}
