package fr.guiguilechat.jcelechat.model.sde.items.types.effects;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Effects;
import org.yaml.snakeyaml.Yaml;

public class AnimatedLights
    extends Effects
{
    public final static AnimatedLights.MetaGroup METAGROUP = new AnimatedLights.MetaGroup();

    @Override
    public IMetaGroup<AnimatedLights> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AnimatedLights>
    {
        public final static String RESOURCE_PATH = "SDE/items/effects/AnimatedLights.yaml";
        private Map<String, AnimatedLights> cache = (null);

        @Override
        public IMetaCategory<? super AnimatedLights> category() {
            return Effects.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1108;
        }

        @Override
        public String getName() {
            return "AnimatedLights";
        }

        @Override
        public synchronized Map<String, AnimatedLights> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AnimatedLights.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AnimatedLights> items;
        }
    }
}
