package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class ShieldResistanceAmplifier
    extends Module
{
    /**
     * Bonus to capacity (shield at least).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CapacityBonus;
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
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ThermalDamageResistanceBonus;
    public final static ShieldResistanceAmplifier.MetaGroup METAGROUP = new ShieldResistanceAmplifier.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/module/ShieldResistanceAmplifier.yaml";
    private static Map<String, ShieldResistanceAmplifier> cache = (null);

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
    public int getGroupId() {
        return  295;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ShieldResistanceAmplifier> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, ShieldResistanceAmplifier> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ShieldResistanceAmplifier.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, ShieldResistanceAmplifier> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ShieldResistanceAmplifier>
    {

        @Override
        public MetaCategory<? super ShieldResistanceAmplifier> category() {
            return Module.METACAT;
        }

        @Override
        public String getName() {
            return "ShieldResistanceAmplifier";
        }

        @Override
        public Collection<ShieldResistanceAmplifier> items() {
            return (load().values());
        }
    }
}
