package fr.guiguilechat.jcelechat.model.sde.items.types.abstrct;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Abstrct;
import org.yaml.snakeyaml.Yaml;

public class Audio
    extends Abstrct
{
    public final static Audio.MetaGroup METAGROUP = new Audio.MetaGroup();

    @Override
    public IMetaGroup<Audio> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Audio>
    {
        public final static String RESOURCE_PATH = "SDE/items/abstrct/Audio.yaml";
        private Map<String, Audio> cache = (null);

        @Override
        public IMetaCategory<? super Audio> category() {
            return Abstrct.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1109;
        }

        @Override
        public String getName() {
            return "Audio";
        }

        @Override
        public synchronized Map<String, Audio> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Audio.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Audio> items;
        }
    }
}
