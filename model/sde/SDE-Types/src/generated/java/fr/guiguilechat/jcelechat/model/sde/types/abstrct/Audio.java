package fr.guiguilechat.jcelechat.model.sde.types.abstrct;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Abstrct;
import org.yaml.snakeyaml.Yaml;

public class Audio
    extends Abstrct
{
    public static final Audio.MetaGroup METAGROUP = new Audio.MetaGroup();

    @Override
    public IMetaGroup<Audio> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Audio>
    {
        public static final String RESOURCE_PATH = "SDE/types/abstrct/Audio.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(Audio.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Audio> types;
        }
    }
}
