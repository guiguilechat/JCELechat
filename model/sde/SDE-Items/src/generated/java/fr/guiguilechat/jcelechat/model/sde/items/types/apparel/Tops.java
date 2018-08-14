package fr.guiguilechat.jcelechat.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Tops
    extends Apparel
{
    public final static Tops.MetaGroup METAGROUP = new Tops.MetaGroup();

    @Override
    public IMetaGroup<Tops> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Tops>
    {
        public final static String RESOURCE_PATH = "SDE/items/apparel/Tops.yaml";
        private Map<String, Tops> cache = (null);

        @Override
        public IMetaCategory<? super Tops> category() {
            return Apparel.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1089;
        }

        @Override
        public String getName() {
            return "Tops";
        }

        @Override
        public synchronized Map<String, Tops> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Tops.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Tops> items;
        }
    }
}
