package me.milesokeefe.ninjasneak;
 
import java.util.Hashtable;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
      
    public class NinjaSneak extends JavaPlugin implements Listener {
        public FileConfiguration config; // declare config
    	Player[] players;
    	public void onEnable() {
            getServer().getPluginManager().registerEvents(this, this);
            config = getConfig(); // get config
            config.options().copyDefaults(true); // copy 'em
            saveConfig();
        }
    	//Logger log = Logger.getLogger("Minecraft");
    	Hashtable<String, String> users = new Hashtable<String, String>();
    	Hashtable<String, Boolean> flying = new Hashtable<String, Boolean>();
    	Hashtable<String, Boolean> sneaking = new Hashtable<String, Boolean>();
        public void onDisable() {}
        public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        	if(cmd.getName().equalsIgnoreCase("ninja")){ // If the player typed /basic then do the following...
        		if(sender instanceof Player){
            		users.put(sender.getName(), "ninja");
            		sender.sendMessage(ChatColor.GRAY + "Ninja mode! Sneak around!");
        		}
        		return true;
        	}else if(cmd.getName().equalsIgnoreCase("normal")){
        		if(sender instanceof Player){
            		users.put(sender.getName(), "normal");
            		sender.sendMessage(ChatColor.GOLD + "Back to normal");
        		}
        		return true;
        	}
        	return false; 
        }
        @EventHandler
        public void onPlayerToggleSneak(PlayerToggleSneakEvent event){ 
        	try{
        	if(users.get(event.getPlayer().getName()).equals("ninja") && event.isSneaking() && flying.get(event.getPlayer().getName()) ==false){
        		players = getServer().getOnlinePlayers();
        		for(int i = 0; i< players.length; i++){
        			Location loc = event.getPlayer().getLocation();
    				//event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 0);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 0);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 1);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 2);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 3);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 4);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 5);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 6);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 7);
    				loc.setY(loc.getY()+0.5);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 0);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 1);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 2);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 3);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 4);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 5);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 6);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 7);
    				loc.setY(loc.getY()+0.5);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 0);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 1);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 2);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 3);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 4);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 5);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 6);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 7);
    				loc.setY(loc.getY()+0.5);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 0);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 1);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 2);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 3);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 4);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 5);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 6);
    				event.getPlayer().getWorld().playEffect(loc, Effect.SMOKE, 7);
    				Boolean allowed = true;
    				if(players[i].isOp() == true && config.getBoolean("OP_see_ninjas")== true){
    					allowed = false;
    				}
    				if(allowed){ // only make the player invisible if the current user isn't OP
    					players[i].hidePlayer((Player) event.getPlayer());
    				}
    				//log.info("player-i: " + players[i].getDisplayName() + " :: Hidden:" + event.getPlayer());
    			}
    			sneaking.put(event.getPlayer().getName(), true);
        	}else{
        		for(int i = 0; i< players.length; i++){
    				players[i].showPlayer((Player) event.getPlayer());
    			}
        		sneaking.put(event.getPlayer().getName(), false);
        	}
        	event.getPlayer();
        	}catch(NullPointerException e){
        		users.put(event.getPlayer().getName(), "normal");
        	}

        }

        @EventHandler
        public void onPlayerMove(PlayerMoveEvent event){
        	Location loc = event.getPlayer().getLocation();
        	loc.setY(loc.getY()-1);
        	Block b = loc.getBlock();

	        	if(b.getType() == Material.AIR){
	        		flying.put(event.getPlayer().getName(), true);
	        		for(int i = 0; i< players.length; i++){
	    				players[i].showPlayer((Player) event.getPlayer());
	    			}
	        	}else{
	            	try{
		        		if(sneaking.get(event.getPlayer().getName()) == true){     		
			    				//playSmokeEffect(event.getPlayer(), event.getPlayer(), 0xcccccc, 20); // plays only to the local user
			        			for(int i = 0; i< players.length; i++){
			        				Boolean allowed = true;
			        				if(players[i].isOp() == true && config.getBoolean("OP_see_ninjas")== true){
			        					allowed = false;
			        				}
			        				if(allowed){
			        					players[i].hidePlayer((Player) event.getPlayer());
			        				}
			        			}
		        		}
	            	}catch(NullPointerException e){
	            		sneaking.put(event.getPlayer().getName(), false);
	            	}
	        		flying.put(event.getPlayer().getName(), false);
	        	}
        	
        	//log.info(b.getType().toString());
        }
        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent event){
        	players = getServer().getOnlinePlayers();
        	
    		users.put(event.getPlayer().getName(), "normal");
    		flying.put(event.getPlayer().getName(), false);
    		sneaking.put(event.getPlayer().getName(), false);
        }
        @EventHandler
        public void onPlayerQuit(PlayerQuitEvent event){
        	players = getServer().getOnlinePlayers();
        }
        /*protected void playSmokeEffect(final Player player, final LivingEntity entity, int color, int duration) {
        	try{
        	final DataWatcher dw = new DataWatcher();
        	dw.a(8, Integer.valueOf(0));
        	dw.watch(8, Integer.valueOf(color));
        		Packet40EntityMetadata packet = new Packet40EntityMetadata(entity.getEntityId(), dw);
	        	((CraftPlayer)player).getHandle().netServerHandler.sendPacket(packet);
	        	Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
		        	public void run() {
			        	DataWatcher dwReal = ((CraftLivingEntity)entity).getHandle().getDataWatcher();
			        	dw.watch(8, dwReal.getInt(8));
			        	Packet40EntityMetadata packet = new Packet40EntityMetadata(entity.getEntityId(), dw, initialized);
			        	((CraftPlayer)player).getHandle().netServerHandler.sendPacket(packet);
		        	}
	        	}, duration);
        	}catch(Exception e){
        		
        	}
       }*/

    }
