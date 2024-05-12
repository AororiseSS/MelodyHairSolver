package com.github.commandcracker.melodymod;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = "melodymod", version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
public class MelodyMod {
  public static final String MODID = "melodymod";
  
  public static final String VERSION = "1.0";
  
  Minecraft mc = Minecraft.func_71410_x();
  
  private ItemStack lastClickedItem;
  
  @EventHandler
  public void init(FMLInitializationEvent event) {
    MinecraftForge.EVENT_BUS.register(this);
  }
  
  public boolean harpGuiIsOpen() {
    if (this.mc.field_71439_g.field_71070_bA instanceof ContainerChest)
      return ((ContainerChest)this.mc.field_71439_g.field_71070_bA).func_85151_d().func_70005_c_().startsWith("Harp - "); 
    return false;
  }
  
  @SubscribeEvent
  public void onTick(TickEvent tickEvent) {
    try {
      if (tickEvent.phase == TickEvent.Phase.START && this.mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiChest && harpGuiIsOpen()) {
        IInventory inv = ((ContainerChest)this.mc.field_71439_g.field_71070_bA).func_85151_d();
        for (int i = 0; i < inv.func_70302_i_(); i++) {
          ItemStack itemStack = inv.func_70301_a(i);
          if (itemStack != null && Item.func_150891_b(itemStack.func_77973_b()) == 155 && 
            itemStack != this.lastClickedItem) {
            this.mc.func_147114_u().func_147297_a((Packet)new C0EPacketClickWindow(this.mc.field_71439_g.field_71070_bA.field_75152_c, i, 0, 3, itemStack, this.mc.field_71439_g.field_71070_bA
                  
                  .func_75136_a(this.mc.field_71439_g.field_71071_by)));
            this.lastClickedItem = itemStack;
          } 
        } 
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
}
