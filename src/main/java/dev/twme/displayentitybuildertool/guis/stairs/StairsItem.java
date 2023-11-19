package dev.twme.displayentitybuildertool.guis.stairs;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

public class StairsItem extends AbstractItem {
    private Material material;

    public StairsItem(Material material) {
	this.material = material;
    }

    @Override
    public ItemProvider getItemProvider() {
	return new ItemBuilder(material).setDisplayName("Stand " + material.name());
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
	player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1f, 1f);

	ItemStack is = new ItemStack(material);

	ItemMeta im = is.getItemMeta();
	im.setUnbreakable(true);
	im.setDisplayName("Stand " + material.name());

	is.setItemMeta(im);
	player.getInventory().addItem(is);
    }
}
