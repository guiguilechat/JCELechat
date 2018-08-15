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

public class EntropicRadiationSink
    extends Module
{
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * Damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double DamageMultiplier;
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
     * Typically scales the firing speed of a weapon.  Reducing speed means faster, strangely..
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double SpeedMultiplier;
    public final static EntropicRadiationSink.MetaGroup METAGROUP = new EntropicRadiationSink.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  50 :
            {
                return Cpu;
            }
            case  64 :
            {
                return DamageMultiplier;
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
            case  204 :
            {
                return SpeedMultiplier;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<EntropicRadiationSink> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EntropicRadiationSink>
    {
        public final static String RESOURCE_PATH = "SDE/items/module/EntropicRadiationSink.yaml";
        private Map<String, EntropicRadiationSink> cache = (null);

        @Override
        public IMetaCategory<? super EntropicRadiationSink> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1988;
        }

        @Override
        public String getName() {
            return "EntropicRadiationSink";
        }

        @Override
        public synchronized Map<String, EntropicRadiationSink> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(EntropicRadiationSink.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, EntropicRadiationSink> items;
        }
    }
}