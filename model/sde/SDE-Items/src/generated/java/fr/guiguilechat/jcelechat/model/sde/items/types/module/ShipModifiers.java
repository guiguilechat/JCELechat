package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class ShipModifiers
    extends Module
{
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
    public double ModeDamageBonusPostDiv;
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
    @DefaultIntValue(1)
    public int ModeEwarResistancePostDiv;
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
    public double ModeKineticResistancePostDiv;
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
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ModeMWDCapPostDiv;
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
    public double ModeMWDVelocityPostDiv;
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
    public double ModeSignatureRadiusPostDiv;
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
    public double ModeTrackingPostDiv;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ModeVelocityPostDiv;
    public final static String RESOURCE_PATH = "SDE/items/module/ShipModifiers.yaml";
    private static LinkedHashMap<String, ShipModifiers> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2002 :
            {
                return ModeAgilityPostDiv;
            }
            case  2030 :
            {
                return ModeArmorRepDurationPostDiv;
            }
            case  2589 :
            {
                return ModeDamageBonusPostDiv;
            }
            case  1997 :
            {
                return ModeEmResistancePostDiv;
            }
            case  2590 :
            {
                return ModeEwarResistancePostDiv;
            }
            case  1998 :
            {
                return ModeExplosiveResistancePostDiv;
            }
            case  1995 :
            {
                return ModeGravimetricStrengthPostDiv;
            }
            case  2000 :
            {
                return ModeKineticResistancePostDiv;
            }
            case  1994 :
            {
                return ModeLadarStrengthPostDiv;
            }
            case  2032 :
            {
                return ModeMWDCapPostDiv;
            }
            case  2007 :
            {
                return ModeMWDSigPenaltyPostDiv;
            }
            case  2031 :
            {
                return ModeMWDVelocityPostDiv;
            }
            case  1996 :
            {
                return ModeMagnetometricStrengthPostDiv;
            }
            case  1990 :
            {
                return ModeMaxRangePostDiv;
            }
            case  1991 :
            {
                return ModeMaxTargetRangePostDiv;
            }
            case  1992 :
            {
                return ModeRadarStrengthPostDiv;
            }
            case  2001 :
            {
                return ModeSignatureRadiusPostDiv;
            }
            case  1999 :
            {
                return ModeThermicResistancePostDiv;
            }
            case  2008 :
            {
                return ModeTrackingPostDiv;
            }
            case  2003 :
            {
                return ModeVelocityPostDiv;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

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
