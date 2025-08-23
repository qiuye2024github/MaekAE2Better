package cn.qiuye.betterae2.integration.expatternprovider;

import cn.qiuye.betterae2.common.definition.BAE2Items;

import appeng.api.upgrades.Upgrades;

import com.glodblock.github.extendedae.common.EPPItemAndBlock;

public class EAECommonLoad {

    public static void init() {
        Upgrades.add(BAE2Items.SUPER_SPEED_CARD, EPPItemAndBlock.EX_IO_PORT, 5);
        Upgrades.add(BAE2Items.SUPER_SPEED_CARD, EPPItemAndBlock.TAG_EXPORT_BUS, 4);
        Upgrades.add(BAE2Items.SUPER_SPEED_CARD, EPPItemAndBlock.EX_IMPORT_BUS, 4, "group.ex_io_bus_part");
        Upgrades.add(BAE2Items.SUPER_SPEED_CARD, EPPItemAndBlock.EX_EXPORT_BUS, 4, "group.ex_io_bus_part");
    }
}
