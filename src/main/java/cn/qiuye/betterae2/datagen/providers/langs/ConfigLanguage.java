package cn.qiuye.betterae2.datagen.providers.langs;

import static cn.qiuye.betterae2.datagen.providers.LocalizationProvider.add;

public class ConfigLanguage {

    public static void init() {
        add("config.screen.makeae2better", "MakeAE2Better", "MakeAE2Better");
        add("config.makeae2better.option.speed", "总线速度", "Bus Speed");
        add("config.makeae2better.option.speed.comment.0", "总线的速度原本的值乘以这个系数", "Bus Speed");
    }
}
