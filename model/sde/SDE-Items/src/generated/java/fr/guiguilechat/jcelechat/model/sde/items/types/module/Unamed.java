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

public class Unamed
    extends Module
{
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorNeed;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Duration;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxRange;
    /**
     * How much ore gets mined
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MiningAmount;
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
     * The number of slots this module requires.  Only used for launchers, bays and turrets.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int Slots;
    /**
     * Restrict activation to this one module group.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TargetGroup;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int TypeColorScheme;
    public final static Unamed.MetaGroup METAGROUP = new Unamed.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  6 :
            {
                return CapacitorNeed;
            }
            case  50 :
            {
                return Cpu;
            }
            case  73 :
            {
                return Duration;
            }
            case  54 :
            {
                return MaxRange;
            }
            case  77 :
            {
                return MiningAmount;
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
            case  47 :
            {
                return Slots;
            }
            case  189 :
            {
                return TargetGroup;
            }
            case  1768 :
            {
                return TypeColorScheme;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<Unamed> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Unamed>
    {
        public final static String RESOURCE_PATH = "SDE/items/module/Unamed.yaml";
        private Map<String, Unamed> cache = (null);

        @Override
        public IMetaCategory<? super Unamed> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  2004;
        }

        @Override
        public String getName() {
            return "Unamed";
        }

        @Override
        public synchronized Map<String, Unamed> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Unamed.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Unamed> items;
        }
    }
}
