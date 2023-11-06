package dev.twme.displayentitybuildertool.datas.block;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class PlaceData {

    private Location location;
    private Location entityLocation;
    private Material material;
    private PlaceMode placeMode;
    private ItemType itemType;
    private float slabYaw = 0;
    private World world;
    private Player player;
    private boolean placeOnEntity;
    private Entity hitEntity;

    public PlaceData setMaterial(Material material){
        this.material = material;
        return this;
    }
    public PlaceData setSlabYaw(float playerYaw){

        playerYaw += 180;

        float itemDisplayYaw;

        if (playerYaw >= 45 && playerYaw < 135  ){
            itemDisplayYaw = -90;
        } else if (playerYaw >= 135 && playerYaw < 225){
            itemDisplayYaw = 0;
        } else if (playerYaw >= 225 && playerYaw < 315) {
            itemDisplayYaw = 90;
        } else {
            itemDisplayYaw = 180;
        }
        this.slabYaw = itemDisplayYaw;

        //Log.log("PlaceData setSlabYaw Pass");

        return this;
    }

    public PlaceData setPlayer(Player player){
        this.player = player;
        return this;
    }
    public PlaceData setLocationWorld(World world){
        this.world = world;

        //Log.log("PlaceData setLocationWorld Pass");

        return this;
    }

    public PlaceData setLocation(Location location){
        this.location = location;
        return this;
    }
    public PlaceData setLocation(Player player){
        this.world = player.getWorld();

        Location eyeLocation = player.getEyeLocation();
        Vector direction = player.getEyeLocation().getDirection();

        RayTraceResult rayTE = world.rayTraceEntities(eyeLocation,direction,5,0.6, p -> !player.getUniqueId().equals(p.getUniqueId()));
        RayTraceResult rayTB = world.rayTraceBlocks(eyeLocation, direction, 5, FluidCollisionMode.ALWAYS, false);

        if (rayTE != null && rayTB != null){

            if (rayTE.getHitEntity().getLocation().distance(eyeLocation) < rayTB.getHitBlock().getLocation().distance(eyeLocation)){
                //player.sendMessage("1: " + rayTE.getHitPosition().getX() + " " + rayTE.getHitPosition().getY() + " " + rayTE.getHitPosition().getZ());
                calculateLocationByEntity(rayTE);
                entityLocation = rayTE.getHitEntity().getLocation();
                placeOnEntity = true;
            } else {
                //player.sendMessage("2: " + rayTB.getHitPosition().getX() + " " + rayTB.getHitPosition().getY() + " " + rayTB.getHitPosition().getZ());
                calculateLocationByEntity(rayTB);
                placeOnEntity = false;
            }
        } else if (rayTE != null){
            //player.sendMessage("3: " + rayTE.getHitPosition().getX() + " " + rayTE.getHitPosition().getY() + " " + rayTE.getHitPosition().getZ());
            calculateLocationByEntity(rayTE);
            entityLocation = rayTE.getHitEntity().getLocation();
            placeOnEntity = true;
        } else if (rayTB != null){
            //player.sendMessage("4: " + rayTB.getHitPosition().getX() + " " + rayTB.getHitPosition().getY() + " " + rayTB.getHitPosition().getZ());
            calculateLocationByEntity(rayTB);
            placeOnEntity = false;
        }

        //Log.log("PlaceData setLocation Pass");
        return this;
    }

    public PlaceData setPlaceMode(PlaceMode placeMode){
        this.placeMode = placeMode;

        //Log.log("PlaceData setPlaceMode Pass");


        return this;
    }

    public boolean existLocation(){

        //Log.log("PlaceData existLocation Pass");

        if (this.location == null){
            return false;
        }
        return true;
    }


    private void calculateLocationByEntity(RayTraceResult rTR){

        //Log.log("PlaceData calculateLocationByEntity ing");

        double blockX,blockY,blockZ,hitPosX,hitPosY,hitPosZ,gapX,gapY,gapZ;

        if (!(rTR.getHitEntity() == null)){
            blockX =  rTR.getHitEntity().getLocation().getBlockX();
            blockY =  rTR.getHitEntity().getLocation().getBlockY();
            blockZ =  rTR.getHitEntity().getLocation().getBlockZ();
            hitEntity = rTR.getHitEntity();
        } else if (!(rTR.getHitBlock() == null)) {
            blockX =  rTR.getHitBlock().getLocation().getBlockX();
            blockY =  rTR.getHitBlock().getLocation().getBlockY();
            blockZ =  rTR.getHitBlock().getLocation().getBlockZ();
        } else {
            return;
        }

        //Log.log("PlaceData calculateLocationByEntity Not Null");

        blockX += 0.5;
        blockY += 0.5;
        blockZ += 0.5;


        hitPosX = rTR.getHitPosition().getX();
        hitPosY = rTR.getHitPosition().getY();
        hitPosZ = rTR.getHitPosition().getZ();
        
        gapX = hitPosX - blockX;
        gapY = hitPosY - blockY;
        gapZ = hitPosZ - blockZ;
        this.location = new Location(world,blockX,blockY,blockZ);

        // Log.log("Gap: " + gapX + " " + gapY + " " + gapZ);

        if        (gapX >= 0.4 && gapX >= gapY && gapX >= gapZ)      {
            this.location.add(1,0,0);
        } else if (gapY >= 0.4 && gapY >= gapX && gapY >= gapZ)      {
            this.location.add(0,1,0);
        } else if (gapZ >= 0.4 && gapZ >= gapX && gapZ >= gapY)      {
            this.location.add(0,0,1);
        } else if (gapX <= -0.4 && gapX <= gapY && gapX <= gapZ){
            this.location.add(-1,0,0);
        } else if (gapY <= -0.4 && gapY <= gapX && gapY <= gapZ){
            this.location.add(0,-1,0);
        } else if (gapZ <= -0.4 && gapZ <= gapX && gapZ <= gapY){
            this.location.add(0,0,-1);
        }

    }


    public boolean checkBlockCanBeUse(ItemStack is){

        //Log.log("PlaceData checkBlockCanBeUse Pass");


        if (!is.getType().isBlock()) {
            return false;
        }
        if (is.getItemMeta() == null) {
            return false;
        }
        if (!is.getItemMeta().isUnbreakable()){
            return false;
        }

        if (is.getType().name().toLowerCase().matches(".*slab+")){
            this.itemType = ItemType.Slab;
            return true;
        }

        if (is.getType().name().toLowerCase().matches(".*stairs+")){
            this.itemType = ItemType.Stairs;
            return true;
        }
        return false;
    }

    public Location getLocation(){
        return location;
    }

    public Material getMaterial() {
        return material;
    }

    public float getSlabYaw() {
        return slabYaw;
    }

    public PlaceMode getPlaceMode() {
        return placeMode;
    }
    public ItemType getItemType(){
        return itemType;
    }
    public World getWorld(){
        return world;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isPlaceOnEntity() {
        return placeOnEntity;
    }

    public Location getEntityLocation() {
        return entityLocation;
    }

    public Entity getHitEntity() {
        return hitEntity;
    }
}
