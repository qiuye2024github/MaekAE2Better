package cn.qiuye.betterae2.config;

import cn.qiuye.betterae2.BetterAE2;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

@Config(id = BetterAE2.MOD_ID)
public class MAE2BConfig {

    public static MAE2BConfig INSTANCE;
    private static final Object lock = new Object();

    public static void init() {
        synchronized (lock) {
            if (INSTANCE == null) {
                INSTANCE = Configuration.registerConfig(MAE2BConfig.class, ConfigFormats.YAML).getConfigInstance();
            }
        }
    }

    @Configurable
    @Configurable.Comment(value = "speed", localize = true)
    @Configurable.Range(min = 1, max = 2147483647)
    public int speed = 4096;
}
