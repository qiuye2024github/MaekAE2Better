package cn.qiuye.betterae2.datagen.providers.langs;

import cn.qiuye.betterae2.common.definition.BAE2Items;

import static cn.qiuye.betterae2.datagen.providers.LocalizationProvider.add;

public class ItemAndBlockLanguage {

    public static void init() {
        for (var item : BAE2Items.getItems()) {
            add("item.makeae2better." + item.id().getPath(), item.getChineseName(), item.getEnglishName());
        }

        // for (var block : BAE2ABlocks.getBlocks()) {
        // add("block.batterae2." + block.id().getPath(), block.getChineseName(), block.getEnglishName());
        // }
    }
}
