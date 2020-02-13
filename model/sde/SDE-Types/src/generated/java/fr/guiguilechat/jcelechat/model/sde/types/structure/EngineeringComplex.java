package fr.guiguilechat.jcelechat.model.sde.types.structure;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Structure;
import org.yaml.snakeyaml.Yaml;

public class EngineeringComplex
    extends Structure
{
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiCapitalMissileResistance;
    /**
     * This defines the total capacity of fighters allowed in the fighter bay of the ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterCapacity;
    /**
     * Number of Heavy Fighters the structure can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterStandupHeavySlots;
    /**
     * Number of Light Fighters the structure can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterStandupLightSlots;
    /**
     * Number of Support Fighters the structure can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterStandupSupportSlots;
    /**
     * This defines the total number of fighter launch tubes on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterTubes;
    /**
     * Missile damage attribute used by structures as a workaround for implementing Standup BCS stacking penalties
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int HiddenMissileDamageMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSize;
    /**
     * Cost bonus for Engineering Complexes Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StrEngCostBonus;
    /**
     * Material bonus for Engineering Complexes Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StrEngMatBonus;
    /**
     * Time bonus for Engineering Complexes Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StrEngTimeBonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureAoERoFRoleBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureServiceRoleBonus;
    /**
     * Distance which tethering will engage / disengage piloted ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TetheringRange;
    /**
     * Attribute on ships used for ship upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeCapacity;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeSlotsLeft;
    public static final EngineeringComplex.MetaGroup METAGROUP = new EngineeringComplex.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2244 :
            {
                return FighterAbilityAntiCapitalMissileResistance;
            }
            case  2055 :
            {
                return FighterCapacity;
            }
            case  2739 :
            {
                return FighterStandupHeavySlots;
            }
            case  2737 :
            {
                return FighterStandupLightSlots;
            }
            case  2738 :
            {
                return FighterStandupSupportSlots;
            }
            case  2216 :
            {
                return FighterTubes;
            }
            case  2750 :
            {
                return HiddenMissileDamageMultiplier;
            }
            case  1547 :
            {
                return RigSize;
            }
            case  2601 :
            {
                return StrEngCostBonus;
            }
            case  2600 :
            {
                return StrEngMatBonus;
            }
            case  2602 :
            {
                return StrEngTimeBonus;
            }
            case  2749 :
            {
                return StructureAoERoFRoleBonus;
            }
            case  2339 :
            {
                return StructureServiceRoleBonus;
            }
            case  2268 :
            {
                return TetheringRange;
            }
            case  1132 :
            {
                return UpgradeCapacity;
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
    public IMetaGroup<EngineeringComplex> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EngineeringComplex>
    {
        public static final String RESOURCE_PATH = "SDE/items/structure/EngineeringComplex.yaml";
        private Map<String, EngineeringComplex> cache = (null);

        @Override
        public IMetaCategory<? super EngineeringComplex> category() {
            return Structure.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1404;
        }

        @Override
        public String getName() {
            return "EngineeringComplex";
        }

        @Override
        public synchronized Map<String, EngineeringComplex> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(EngineeringComplex.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, EngineeringComplex> items;
        }
    }
}
