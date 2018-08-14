package fr.guiguilechat.jcelechat.model.sde.items.types.reaction;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Reaction;
import org.yaml.snakeyaml.Yaml;

public class FreedomPrograms
    extends Reaction
{
    public final static FreedomPrograms.MetaGroup METAGROUP = new FreedomPrograms.MetaGroup();

    @Override
    public IMetaGroup<FreedomPrograms> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<FreedomPrograms>
    {
        public final static String RESOURCE_PATH = "SDE/items/reaction/FreedomPrograms.yaml";
        private Map<String, FreedomPrograms> cache = (null);

        @Override
        public IMetaCategory<? super FreedomPrograms> category() {
            return Reaction.METACAT;
        }

        @Override
        public int getGroupId() {
            return  881;
        }

        @Override
        public String getName() {
            return "FreedomPrograms";
        }

        @Override
        public synchronized Map<String, FreedomPrograms> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(FreedomPrograms.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, FreedomPrograms> items;
        }
    }
}
