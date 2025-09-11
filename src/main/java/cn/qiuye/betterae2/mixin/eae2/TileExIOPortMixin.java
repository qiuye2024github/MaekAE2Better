package cn.qiuye.betterae2.mixin.eae2;

import cn.qiuye.betterae2.common.definition.BAE2Items;

import appeng.api.networking.IGridNode;
import appeng.api.networking.ticking.TickRateModulation;
import appeng.api.storage.StorageCells;
import appeng.api.upgrades.IUpgradeableObject;
import appeng.api.upgrades.UpgradeInventories;
import appeng.blockentity.storage.IOPortBlockEntity;
import appeng.core.definitions.AEItems;
import appeng.util.inv.AppEngInternalInventory;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import com.glodblock.github.extendedae.common.EPPItemAndBlock;
import com.glodblock.github.extendedae.common.tileentities.TileExIOPort;
import com.glodblock.github.extendedae.util.Ae2Reflect;
import com.glodblock.github.glodium.util.GlodUtil;
import org.spongepowered.asm.mixin.*;

@Mixin(value = TileExIOPort.class, priority = 9999)
public class TileExIOPortMixin extends IOPortBlockEntity implements IUpgradeableObject {

    @Mutable
    @Final
    @Shadow(remap = false)
    private AppEngInternalInventory inputCells;

    @Final
    @Shadow(remap = false)
    private static int NUMBER_OF_CELL_SLOTS;

    public TileExIOPortMixin(BlockPos pos, BlockState blockState) {
        super(GlodUtil.getTileType(TileExIOPort.class, TileExIOPort::new, EPPItemAndBlock.EX_IO_PORT), pos, blockState);
        Ae2Reflect.setIOPortUpgrade(this, UpgradeInventories.forMachine(EPPItemAndBlock.EX_IO_PORT, 5, this::saveChanges));
        this.inputCells = Ae2Reflect.getInputCellInv(this);
    }

    /**
     * @author ~
     * @reason .
     */
    @Overwrite(remap = false)
    public TickRateModulation tickingRequest(IGridNode node, int ticksSinceLastCall) {
        if (!this.getMainNode().isActive()) {
            return TickRateModulation.IDLE;
        }

        TickRateModulation ret = TickRateModulation.SLEEP;

        int speedUpgrades = this.getUpgrades().getInstalledUpgrades(AEItems.SPEED_CARD);
        int superSpeedUpgrades = this.getUpgrades().getInstalledUpgrades(BAE2Items.SUPER_SPEED_CARD);
        long itemsToMove = betterae2$getItemsToMove(speedUpgrades, superSpeedUpgrades);

        var grid = getMainNode().getGrid();
        if (grid == null) {
            return TickRateModulation.IDLE;
        } else {
            for (int x = 0; x < NUMBER_OF_CELL_SLOTS; x++) {
                var cell = this.inputCells.getStackInSlot(x);
                var cellInv = StorageCells.getCellInventory(cell, null);
                if (cellInv == null) {
                    Ae2Reflect.moveSlotInCell(this, x);
                    continue;
                }
                if (itemsToMove > 0) {

                    itemsToMove = Ae2Reflect.transferItemsFromCell(this, grid, cellInv, itemsToMove);

                    if (itemsToMove > 0) {
                        ret = TickRateModulation.IDLE;
                    } else {
                        ret = TickRateModulation.URGENT;
                    }
                }
                if (itemsToMove > 0 && matchesFullnessMode(cellInv) && Ae2Reflect.moveSlotInCell(this, x)) {
                    ret = TickRateModulation.URGENT;
                }
            }
        }
        return ret;
    }

    @Unique
    private static long betterae2$getItemsToMove(int speedUpgrades, int superSpeedUpgrades) {
        int result = 1;
        int speed = 16;
        long itemsToMove;

        if (speedUpgrades > 0 && superSpeedUpgrades == 0) {
            switch (speedUpgrades) {
                case 1 -> result = 2;
                case 2 -> result = 3;
                case 3 -> result = 4;
                case 4 -> result = 5;
                case 5 -> result = 6;
            }
        } else if (superSpeedUpgrades > 0 && speedUpgrades == 0) {
            switch (superSpeedUpgrades) {
                case 1 -> result = 8;
                case 2 -> result = 9;
                case 3 -> result = 10;
                case 4 -> result = 11;
                case 5 -> result = 12;
            }
        } else if (speedUpgrades > 0 && superSpeedUpgrades > 0) {
            switch (speedUpgrades) {
                case 1 -> result = 4;
                case 2 -> result = 5;
                case 3 -> result = 6;
                case 4 -> result = 7;
                case 5 -> result = 8;
            }
        }

        itemsToMove = (long) Math.pow(speed, result);
        return itemsToMove;
    }
}
