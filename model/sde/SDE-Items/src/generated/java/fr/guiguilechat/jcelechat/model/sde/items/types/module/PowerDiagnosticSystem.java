package fr.guiguilechat.jcelechat.model.sde.items.types.module;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class PowerDiagnosticSystem
    extends Module
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double CapacitorCapacityMultiplier;
    /**
     * Multiplier to the capacitors recharge rate.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double CapacitorRechargeRateMultiplier;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
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
    public int MetaGroupID;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * Multipier to power core output.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double PowerOutputMultiplier;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * Multiplier to the capacity of a shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ShieldCapacityMultiplier;
    /**
     * Multiplier to a recharge rate time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ShieldRechargeRateMultiplier;
    public final static PowerDiagnosticSystem.MetaGroup METAGROUP = new PowerDiagnosticSystem.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  147 :
            {
                return CapacitorCapacityMultiplier;
            }
            case  144 :
            {
                return CapacitorRechargeRateMultiplier;
            }
            case  50 :
            {
                return Cpu;
            }
            case  1692 :
            {
                return MetaGroupID;
            }
            case  30 :
            {
                return Power;
            }
            case  145 :
            {
                return PowerOutputMultiplier;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  146 :
            {
                return ShieldCapacityMultiplier;
            }
            case  134 :
            {
                return ShieldRechargeRateMultiplier;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<PowerDiagnosticSystem> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PowerDiagnosticSystem>
    {
        public final static String RESOURCE_PATH = "SDE/items/module/PowerDiagnosticSystem.yaml";
        private Map<String, PowerDiagnosticSystem> cache = (null);

        @Override
        public IMetaCategory<? super PowerDiagnosticSystem> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  766;
        }

        @Override
        public String getName() {
            return "PowerDiagnosticSystem";
        }

        @Override
        public synchronized Map<String, PowerDiagnosticSystem> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(PowerDiagnosticSystem.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PowerDiagnosticSystem> items;
        }
    }
}
