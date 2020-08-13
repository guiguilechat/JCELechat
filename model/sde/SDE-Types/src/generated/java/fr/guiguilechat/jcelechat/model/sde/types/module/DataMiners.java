package fr.guiguilechat.jcelechat.model.sde.types.module;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AccessDifficultyBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType1;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowRepeatingActivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsArcheology;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsHacking;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.VirusCoherence;
import fr.guiguilechat.jcelechat.model.sde.attributes.VirusElementSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.VirusStrength;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class DataMiners
    extends Module
{
    /**
     * Bonus to chance of opening a container.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int accessdifficultybonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype1;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double capacitorneed;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double cpu;
    /**
     * If set, this module cannot be activated and made to autorepeat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowrepeatingactivation;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double duration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int isarcheology;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ishacking;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double maxrange;
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metagroupid;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * The coherence of a virus.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int viruscoherence;
    /**
     * The number of utility element slots a virus has.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int viruselementslots;
    /**
     * The strength attribute for a Virus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int virusstrength;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Mass.INSTANCE, CapacitorNeed.INSTANCE, TechLevel.INSTANCE, AccessDifficultyBonus.INSTANCE, Capacity.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, Cpu.INSTANCE, IsHacking.INSTANCE, IsArcheology.INSTANCE, VirusCoherence.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, DisallowRepeatingActivation.INSTANCE, MaxRange.INSTANCE, VirusStrength.INSTANCE, CanFitShipType1 .INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, VirusElementSlots.INSTANCE, MetaLevelOld.INSTANCE, MetaGroupID.INSTANCE, Power.INSTANCE })));
    public static final DataMiners.MetaGroup METAGROUP = new DataMiners.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  902 :
            {
                return accessdifficultybonus;
            }
            case  1302 :
            {
                return canfitshiptype1;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  50 :
            {
                return cpu;
            }
            case  1014 :
            {
                return disallowrepeatingactivation;
            }
            case  73 :
            {
                return duration;
            }
            case  1331 :
            {
                return isarcheology;
            }
            case  1330 :
            {
                return ishacking;
            }
            case  54 :
            {
                return maxrange;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  30 :
            {
                return power;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  422 :
            {
                return techlevel;
            }
            case  1909 :
            {
                return viruscoherence;
            }
            case  1911 :
            {
                return viruselementslots;
            }
            case  1910 :
            {
                return virusstrength;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<DataMiners> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DataMiners>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/DataMiners.yaml";
        private Map<String, DataMiners> cache = (null);

        @Override
        public IMetaCategory<? super DataMiners> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  538;
        }

        @Override
        public String getName() {
            return "DataMiners";
        }

        @Override
        public synchronized Map<String, DataMiners> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DataMiners.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DataMiners> types;
        }
    }
}
