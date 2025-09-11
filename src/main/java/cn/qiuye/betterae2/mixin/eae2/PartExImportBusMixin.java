package cn.qiuye.betterae2.mixin.eae2;

import cn.qiuye.betterae2.config.MAE2BConfig;

import appeng.api.parts.IPartItem;
import appeng.parts.automation.ImportBusPart;

import com.glodblock.github.extendedae.common.parts.PartExImportBus;
import com.glodblock.github.extendedae.config.EPPConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = PartExImportBus.class, priority = 9999)
public class PartExImportBusMixin extends ImportBusPart {

    public PartExImportBusMixin(IPartItem<?> partItem) {
        super(partItem);
    }

    /**
     * @author .
     * @reason .
     */
    @Overwrite(remap = false)
    protected int getOperationsPerTick() {
        int EAEConfig = EPPConfig.busSpeed;
        int result = super.getOperationsPerTick();
        int BAE2Config = MAE2BConfig.INSTANCE.speed;
        return EAEConfig * result * BAE2Config;
    }
}
