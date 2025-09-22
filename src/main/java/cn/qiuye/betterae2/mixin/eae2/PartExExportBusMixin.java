package cn.qiuye.betterae2.mixin.eae2;

import cn.qiuye.betterae2.utils.NumberUtil;

import appeng.api.parts.IPartItem;
import appeng.parts.automation.ExportBusPart;

import com.glodblock.github.extendedae.common.parts.PartExExportBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PartExExportBus.class)
public class PartExExportBusMixin extends ExportBusPart {

    public PartExExportBusMixin(IPartItem<?> partItem) {
        super(partItem);
    }

    /**
     * @author .
     * @reason .
     */
    @Overwrite(remap = false)
    public int getOperationsPerTick() {
        int result = super.getOperationsPerTick();
        return NumberUtil.ConvertLongToIntSaturating(result);
    }
}
