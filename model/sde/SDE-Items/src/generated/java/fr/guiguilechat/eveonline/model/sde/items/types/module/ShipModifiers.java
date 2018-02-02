package fr.guiguilechat.eveonline.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class ShipModifiers
    extends Module
{
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeMaxRangePostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeMaxTargetRangePostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeRadarStrengthPostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeLadarStrengthPostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeGravimetricStrengthPostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeMagnetometricStrengthPostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeEmResistancePostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeExplosiveResistancePostDiv;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeArmorRepDurationPostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeThermicResistancePostDiv;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ModeMWDVelocityPostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeKineticResistancePostDiv;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ModeMWDCapPostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeSignatureRadiusPostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeAgilityPostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeVelocityPostDiv;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ModeMWDSigPenaltyPostDiv;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ModeTrackingPostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeDamageBonusPostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ModeEwarResistancePostDiv;
    public final static String RESOURCE_PATH = "SDE/items/module/ShipModifiers.yaml";
    private static LinkedHashMap<String, ShipModifiers> cache = (null);

    @Override
    public int getGroupId() {
        return  1306;
    }

    @Override
    public Class<?> getGroup() {
        return ShipModifiers.class;
    }

    public static synchronized LinkedHashMap<String, ShipModifiers> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ShipModifiers.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ShipModifiers> items;
    }
}
