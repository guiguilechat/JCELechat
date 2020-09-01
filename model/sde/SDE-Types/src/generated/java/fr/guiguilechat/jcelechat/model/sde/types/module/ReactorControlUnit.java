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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorRechargeRateMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutputMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRateMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class ReactorControlUnit
    extends Module
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double capacitorcapacitymultiplier;
    /**
     * Multiplier to the capacitors recharge rate.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double capacitorrechargeratemultiplier;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
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
     * Multipier to power core output.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double poweroutputmultiplier;
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
     * Multiplier to the capacity of a shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double shieldcapacitymultiplier;
    /**
     * Multiplier to a recharge rate time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double shieldrechargeratemultiplier;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Mass.INSTANCE, TechLevel.INSTANCE, ShieldRechargeRateMultiplier.INSTANCE, Capacity.INSTANCE, Hp.INSTANCE, CapacitorRechargeRateMultiplier.INSTANCE, PowerOutputMultiplier.INSTANCE, ShieldCapacityMultiplier.INSTANCE, Cpu.INSTANCE, CapacitorCapacityMultiplier.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE, MetaLevelOld.INSTANCE, MetaGroupID.INSTANCE, Power.INSTANCE })));
    public static final ReactorControlUnit.MetaGroup METAGROUP = new ReactorControlUnit.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  147 :
            {
                return capacitorcapacitymultiplier;
            }
            case  144 :
            {
                return capacitorrechargeratemultiplier;
            }
            case  50 :
            {
                return cpu;
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
            case  145 :
            {
                return poweroutputmultiplier;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  146 :
            {
                return shieldcapacitymultiplier;
            }
            case  134 :
            {
                return shieldrechargeratemultiplier;
            }
            case  422 :
            {
                return techlevel;
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
    public IMetaGroup<ReactorControlUnit> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ReactorControlUnit>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/ReactorControlUnit.yaml";
        private Map<String, ReactorControlUnit> cache = (null);

        @Override
        public IMetaCategory<? super ReactorControlUnit> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  769;
        }

        @Override
        public String getName() {
            return "ReactorControlUnit";
        }

        @Override
        public synchronized Map<String, ReactorControlUnit> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ReactorControlUnit.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ReactorControlUnit> types;
        }
    }
}
