package fr.guiguilechat.jcelechat.model.sde.items.types.structure;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Structure;
import org.yaml.snakeyaml.Yaml;

public class Refinery
    extends Structure
{
    /**
     * Delay for exploding moon mining chunk into asteroid field
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10800)
    public int AutoFractureDelay;
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
     * This attribute doesn't directly impact the asteroid decay, but is used to expose the decay time to the show-info window
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(48)
    public int MoonAsteroidDecayDisplayValue;
    /**
     * Decay time for asteroid created from moon spew
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MoonAsteroidDecayTimeMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSize;
    /**
     * Time bonus for Refinery Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StrReactionTimeMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StrRefiningYieldBonus;
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
    public final static Refinery.MetaGroup METAGROUP = new Refinery.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2698 :
            {
                return AutoFractureDelay;
            }
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
            case  2728 :
            {
                return MoonAsteroidDecayDisplayValue;
            }
            case  2706 :
            {
                return MoonAsteroidDecayTimeMultiplier;
            }
            case  1547 :
            {
                return RigSize;
            }
            case  2721 :
            {
                return StrReactionTimeMultiplier;
            }
            case  2722 :
            {
                return StrRefiningYieldBonus;
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
    public IMetaGroup<Refinery> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Refinery>
    {
        public final static String RESOURCE_PATH = "SDE/items/structure/Refinery.yaml";
        private Map<String, Refinery> cache = (null);

        @Override
        public IMetaCategory<? super Refinery> category() {
            return Structure.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1406;
        }

        @Override
        public String getName() {
            return "Refinery";
        }

        @Override
        public synchronized Map<String, Refinery> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Refinery.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Refinery> items;
        }
    }
}
