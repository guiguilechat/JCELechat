package fr.guiguilechat.jcelechat.model.sde.items.types.ship;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class CombatReconShip
    extends Ship
{
    /**
     * if set to 1 the ship is immune to directional scan
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DscanImmune;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EliteBonusReconShip1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EliteBonusReconShip2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EliteBonusReconShip3;
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
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxPassengers;
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
    @DefaultDoubleValue(0.0)
    public double ShipBonusAC;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShipBonusAC2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int ShipBonusCC;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShipBonusCC2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(5.0)
    public double ShipBonusGC;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShipBonusGC2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShipBonusMC;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShipBonusMC2;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeSlotsLeft;
    public static final CombatReconShip.MetaGroup METAGROUP = new CombatReconShip.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1958 :
            {
                return DscanImmune;
            }
            case  962 :
            {
                return EliteBonusReconShip1;
            }
            case  963 :
            {
                return EliteBonusReconShip2;
            }
            case  1537 :
            {
                return EliteBonusReconShip3;
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
            case  129 :
            {
                return MaxPassengers;
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
            case  564 :
            {
                return ScanResolution;
            }
            case  79 :
            {
                return ScanSpeed;
            }
            case  478 :
            {
                return ShipBonusAC;
            }
            case  656 :
            {
                return ShipBonusAC2;
            }
            case  487 :
            {
                return ShipBonusCC;
            }
            case  657 :
            {
                return ShipBonusCC2;
            }
            case  486 :
            {
                return ShipBonusGC;
            }
            case  658 :
            {
                return ShipBonusGC2;
            }
            case  489 :
            {
                return ShipBonusMC;
            }
            case  659 :
            {
                return ShipBonusMC2;
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
    public IMetaGroup<CombatReconShip> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CombatReconShip>
    {
        public static final String RESOURCE_PATH = "SDE/items/ship/CombatReconShip.yaml";
        private Map<String, CombatReconShip> cache = (null);

        @Override
        public IMetaCategory<? super CombatReconShip> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  906;
        }

        @Override
        public String getName() {
            return "CombatReconShip";
        }

        @Override
        public synchronized Map<String, CombatReconShip> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(CombatReconShip.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CombatReconShip> items;
        }
    }
}
