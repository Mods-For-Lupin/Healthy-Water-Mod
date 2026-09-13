package io.github.jason13official.healthy_water.mixin;

import io.github.jason13official.healthy_water.platform.Services;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

  @Shadow protected ItemStack useItem;

  @Inject(method = "completeUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;finishUsingItem(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;"))
  private void healthy_water$completeUsingItem(CallbackInfo ci) {

    LivingEntity entity = (LivingEntity) (Object) this;

    if (!(entity instanceof ServerPlayer player)) return;

    if (Services.PLATFORM.isDevelopmentEnvironment()) System.out.println("player completeUsingItem");

    if (!this.useItem.is(Items.POTION)) return;

    if (Services.PLATFORM.isDevelopmentEnvironment()) System.out.println("player used potion!");

    PotionContents potion = this.useItem.get(DataComponents.POTION_CONTENTS);

    if (potion != null && potion.is(Potions.WATER)) {
      if (Services.PLATFORM.isDevelopmentEnvironment()) System.out.println("healing!");
      player.getFoodData().eat(1, 0.5F);
      player.heal(1.0F);
    }
  }
}
