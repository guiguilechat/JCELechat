package fr.guiguilechat.jcelechat.model.sde.types.module;

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
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class ShieldResistanceAmplifier
    extends Module
{
    /**
     * Bonus to capacity (shield at least).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacityBonus;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double EmDamageResistanceBonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ExplosiveDamageResistanceBonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double KineticDamageResistanceBonus;
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
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
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
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ThermalDamageResistanceBonus;
    public static final ShieldResistanceAmplifier.MetaGroup METAGROUP = new ShieldResistanceAmplifier.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  72 :
            {
                return CapacityBonus;
            }
            case  50 :
            {
                return Cpu;
            }
            case  984 :
            {
                return EmDamageResistanceBonus;
            }
            case  985 :
            {
                return ExplosiveDamageResistanceBonus;
            }
            case  986 :
            {
                return KineticDamageResistanceBonus;
            }
            case  1692 :
            {
                return MetaGroupID;
            }
            case  633 :
            {
                return MetaLevel;
            }
            case  30 :
            {
                return Power;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  422 :
            {
                return TechLevel;
            }
            case  987 :
            {
                return ThermalDamageResistanceBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<ShieldResistanceAmplifier> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ShieldResistanceAmplifier>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/ShieldResistanceAmplifier.yaml";
        private Map<String, ShieldResistanceAmplifier> cache = (null);

        @Override
        public IMetaCategory<? super ShieldResistanceAmplifier> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  295;
        }

        @Override
        public String getName() {
            return "ShieldResistanceAmplifier";
        }

        @Override
        public synchronized Map<String, ShieldResistanceAmplifier> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ShieldResistanceAmplifier.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ShieldResistanceAmplifier> types;
        }
    }
}
