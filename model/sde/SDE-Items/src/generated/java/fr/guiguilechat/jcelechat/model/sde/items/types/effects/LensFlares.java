package fr.guiguilechat.jcelechat.model.sde.items.types.effects;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Effects;
import org.yaml.snakeyaml.Yaml;

public class LensFlares
    extends Effects
{
    public final static LensFlares.MetaGroup METAGROUP = new LensFlares.MetaGroup();

    @Override
    public IMetaGroup<LensFlares> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<LensFlares>
    {
        public final static String RESOURCE_PATH = "SDE/items/effects/LensFlares.yaml";
        private Map<String, LensFlares> cache = (null);

        @Override
        public IMetaCategory<? super LensFlares> category() {
            return Effects.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1105;
        }

        @Override
        public String getName() {
            return "LensFlares";
        }

        @Override
        public synchronized Map<String, LensFlares> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(LensFlares.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, LensFlares> items;
        }
    }
}
