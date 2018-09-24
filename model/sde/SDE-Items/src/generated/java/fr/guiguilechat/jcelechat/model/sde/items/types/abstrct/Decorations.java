package fr.guiguilechat.jcelechat.model.sde.items.types.abstrct;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Abstrct;
import org.yaml.snakeyaml.Yaml;

public class Decorations
    extends Abstrct
{
    public final static Decorations.MetaGroup METAGROUP = new Decorations.MetaGroup();

    @Override
    public IMetaGroup<Decorations> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Decorations>
    {
        public final static String RESOURCE_PATH = "SDE/items/abstrct/Decorations.yaml";
        private Map<String, Decorations> cache = (null);

        @Override
        public IMetaCategory<? super Decorations> category() {
            return Abstrct.METACAT;
        }

        @Override
        public int getGroupId() {
            return  937;
        }

        @Override
        public String getName() {
            return "Decorations";
        }

        @Override
        public synchronized Map<String, Decorations> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Decorations.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Decorations> items;
        }
    }
}
