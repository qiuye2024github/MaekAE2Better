package cn.qiuye.betterae2.mixin.ae2;

import cn.qiuye.betterae2.common.definition.BAE2Items;

import appeng.api.upgrades.IUpgradeableObject;
import appeng.core.definitions.AEItems;
import appeng.parts.automation.IOBusPart;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(IOBusPart.class)
public class IOBusPartMixin implements IUpgradeableObject {

    /**
     * @author ·
     * @reason .
     */
    @Overwrite(remap = false)
    protected int getOperationsPerTick() {
        int var1 = 2;
        double result = 1;
        int finalvalue;
        int speedUpgrades = getInstalledUpgrades(AEItems.SPEED_CARD);
        int superSpeedUpgrades = getInstalledUpgrades(BAE2Items.SUPER_SPEED_CARD);
        if (speedUpgrades > 0 && superSpeedUpgrades == 0) {
            result = switch (speedUpgrades) {
                case 2 -> 4;
                case 3 -> 8;
                case 4 -> 16;
                default -> 2;
            };
        } else if (superSpeedUpgrades > 0 && speedUpgrades == 0) {
            result = switch (superSpeedUpgrades) {
                case 2 -> 18.7;
                case 3 -> 20.5;
                case 4 -> 22.5;
                default -> 17.6;
            };
        } else if (speedUpgrades > 0 && superSpeedUpgrades > 0) {
            result = switch (superSpeedUpgrades) {
                case 2 -> 5.5;
                case 3 -> 6.5;
                case 4 -> 7.5;
                default -> 4.5;
            };
        }
        finalvalue = (int) (Math.pow(var1, result));
        return finalvalue;
    }
}
