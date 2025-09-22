package cn.qiuye.betterae2.mixin.eae2;

import cn.qiuye.betterae2.utils.NumberUtil;

import appeng.api.parts.IPartItem;
import appeng.parts.automation.ImportBusPart;

import com.glodblock.github.extendedae.common.parts.PartExImportBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PartExImportBus.class)
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
        int result = super.getOperationsPerTick();
        return NumberUtil.ConvertLongToIntSaturating(result);
    }
}
