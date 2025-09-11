package cn.qiuye.betterae2.mixin.ae2;

import cn.qiuye.betterae2.common.definition.BAE2Items;
import cn.qiuye.betterae2.config.MAE2BConfig;

import appeng.api.upgrades.IUpgradeableObject;
import appeng.core.definitions.AEItems;
import appeng.parts.automation.IOBusPart;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = IOBusPart.class, priority = 9999)
public class IOBusPartMixin implements IUpgradeableObject {

    /**
     * @author ·
     * @reason .
     */
    @Overwrite(remap = false)
    protected int getOperationsPerTick() {
        int result;
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
                case 2 -> 18;
                case 3 -> 20;
                case 4 -> 22;
                default -> 17;
            };
        } else if (speedUpgrades > 0 && superSpeedUpgrades > 0) {
            result = switch (superSpeedUpgrades) {
                case 2 -> 5;
                case 3 -> 6;
                case 4 -> 7;
                default -> 4;
            };
        } else {
            result = 1;
        }
        int value = 100;
        for (int i = 0; i < result; i++) {
            value = value * 2;
        }
        return makeae2better$convertLongToIntSaturating(value);
    }

    @Unique
    public int makeae2better$convertLongToIntSaturating(long value) {
        int MAE2B = MAE2BConfig.INSTANCE.speed;
        long result = value * MAE2B;
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) result;
        }
    }
}
