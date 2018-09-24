package fr.guiguilechat.jcelechat.model.sde.items.types.abstrct;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Abstrct;
import org.yaml.snakeyaml.Yaml;

public class Miscellaneous
    extends Abstrct
{
    public final static Miscellaneous.MetaGroup METAGROUP = new Miscellaneous.MetaGroup();

    @Override
    public IMetaGroup<Miscellaneous> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Miscellaneous>
    {
        public final static String RESOURCE_PATH = "SDE/items/abstrct/Miscellaneous.yaml";
        private Map<String, Miscellaneous> cache = (null);

        @Override
        public IMetaCategory<? super Miscellaneous> category() {
            return Abstrct.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1319;
        }

        @Override
        public String getName() {
            return "Miscellaneous";
        }

        @Override
        public synchronized Map<String, Miscellaneous> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Miscellaneous.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Miscellaneous> items;
        }
    }
}
