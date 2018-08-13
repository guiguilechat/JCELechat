package fr.guiguilechat.jcelechat.model.sde.items.types.ship;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class Marauder
    extends Ship
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EliteBonusViolators1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EliteBonusViolators2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EliteBonusViolatorsRole1;
    /**
     * eliteBonusViolatorsRole2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EliteBonusViolatorsRole2;
    /**
     * eliteBonusViolatorsRole3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EliteBonusViolatorsRole3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FwLpKill;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HeatAttenuationHi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HeatAttenuationLow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HeatAttenuationMed;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MainColor;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MaxDirectionalVelocity;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxPassengers;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double MinTargetVelDmgMultiplier;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSize;
    /**
     * The number of rig slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSlots;
    /**
     * Role bonus for Marauders.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RoleBonusMarauder;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanResolution;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanSpeed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonus2CB;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(5.0)
    public double ShipBonusAB;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShipBonusAB2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int ShipBonusCB;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCB3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShipBonusGB;
    /**
     * skill bonus attribute2 for gallente battleship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShipBonusGB2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(5.0)
    public double ShipBonusMB;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShipBonusMB2;
    /**
     * How many upgrades can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeSlotsLeft;
    public final static String RESOURCE_PATH = "SDE/items/ship/Marauder.yaml";
    private static Map<String, Marauder> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1265 :
            {
                return EliteBonusViolators1;
            }
            case  1266 :
            {
                return EliteBonusViolators2;
            }
            case  1268 :
            {
                return EliteBonusViolatorsRole1;
            }
            case  1269 :
            {
                return EliteBonusViolatorsRole2;
            }
            case  1279 :
            {
                return EliteBonusViolatorsRole3;
            }
            case  1555 :
            {
                return FwLpKill;
            }
            case  1259 :
            {
                return HeatAttenuationHi;
            }
            case  1262 :
            {
                return HeatAttenuationLow;
            }
            case  1261 :
            {
                return HeatAttenuationMed;
            }
            case  124 :
            {
                return MainColor;
            }
            case  661 :
            {
                return MaxDirectionalVelocity;
            }
            case  129 :
            {
                return MaxPassengers;
            }
            case  662 :
            {
                return MinTargetVelDmgMultiplier;
            }
            case  183 :
            {
                return RequiredSkill2;
            }
            case  278 :
            {
                return RequiredSkill2Level;
            }
            case  1547 :
            {
                return RigSize;
            }
            case  1137 :
            {
                return RigSlots;
            }
            case  1923 :
            {
                return RoleBonusMarauder;
            }
            case  564 :
            {
                return ScanResolution;
            }
            case  79 :
            {
                return ScanSpeed;
            }
            case  501 :
            {
                return ShipBonus2CB;
            }
            case  492 :
            {
                return ShipBonusAB;
            }
            case  585 :
            {
                return ShipBonusAB2;
            }
            case  491 :
            {
                return ShipBonusCB;
            }
            case  598 :
            {
                return ShipBonusCB3;
            }
            case  500 :
            {
                return ShipBonusGB;
            }
            case  561 :
            {
                return ShipBonusGB2;
            }
            case  490 :
            {
                return ShipBonusMB;
            }
            case  518 :
            {
                return ShipBonusMB2;
            }
            case  1154 :
            {
                return UpgradeSlotsLeft;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  900;
    }

    @Override
    public Class<?> getGroup() {
        return Marauder.class;
    }

    public static synchronized Map<String, Marauder> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Marauder.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Marauder> items;
    }
}
