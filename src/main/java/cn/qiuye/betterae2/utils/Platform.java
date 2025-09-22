package cn.qiuye.betterae2.utils;

import net.minecraftforge.fml.ModList;

public class Platform {

    private Platform() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }
}
