package fr.guiguilechat.jcelechat.model.sde.types.effects;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Effects;
import org.yaml.snakeyaml.Yaml;

public class AnimatedLights
    extends Effects
{
    public static final AnimatedLights.MetaGroup METAGROUP = new AnimatedLights.MetaGroup();

    @Override
    public IMetaGroup<AnimatedLights> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AnimatedLights>
    {
        public static final String RESOURCE_PATH = "SDE/types/effects/AnimatedLights.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(AnimatedLights.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AnimatedLights> types;
        }
    }
}
