package io.github.jason13official.healthy_water;

import net.fabricmc.api.ClientModInitializer;

public class HealthyWaterClientFabric implements ClientModInitializer {

  @Override
  public void onInitializeClient() {

    HealthyWaterClient.init();
  }
}
