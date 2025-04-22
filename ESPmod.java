package com.espmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

@Mod("espmod")
public class ESPMod {
    private static boolean espEnabled = false;

    public ESPMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {}

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (event.getKey() == GLFW.GLFW_KEY_RIGHT_SHIFT && event.getAction() == GLFW.GLFW_PRESS) {
            espEnabled = !espEnabled;
            System.out.println("[ESPMod] ESP toggled: " + espEnabled);
        }
    }

    @SubscribeEvent
    public void onRender(RenderLivingEvent.Specials.Pre<LivingEntity, ?> event) {
        if (espEnabled && event.getEntity() != null && event.getEntity().isAlive()) {
            event.getRenderer().getRenderManager().setRenderShadow(false);
            event.getMatrixStack().scale(1.1f, 1.1f, 1.1f);
        }
    }
}
