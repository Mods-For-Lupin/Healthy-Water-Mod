package io.github.jason13official.healthy_water;

import java.util.function.Consumer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class HealthyWaterClientNeoForge {

  public HealthyWaterClientNeoForge(final IEventBus modEventBus) {

    modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> HealthyWaterClient.init());
  }
}
