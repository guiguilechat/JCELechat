
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;

public abstract class SovereigntyStructures
    extends Item
{

    /**
     * Signature Radius is used for turret tracking and scanning.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * Radar strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanRadarStrength;
    /**
     * Ladar strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanLadarStrength;
    /**
     * Magnetometric strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanMagnetometricStrength;
    /**
     * Gravimetric strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanGravimetricStrength;

    @Override
    public int getCategoryId() {
        return  40;
    }

    @Override
    public Class<?> getCategory() {
        return SovereigntyStructures.class;
    }

}
