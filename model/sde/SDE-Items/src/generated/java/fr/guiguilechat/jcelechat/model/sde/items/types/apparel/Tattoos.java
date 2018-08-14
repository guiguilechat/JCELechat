package fr.guiguilechat.jcelechat.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Tattoos
    extends Apparel
{
    public final static Tattoos.MetaGroup METAGROUP = new Tattoos.MetaGroup();

    @Override
    public IMetaGroup<Tattoos> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Tattoos>
    {
        public final static String RESOURCE_PATH = "SDE/items/apparel/Tattoos.yaml";
        private Map<String, Tattoos> cache = (null);

        @Override
        public IMetaCategory<? super Tattoos> category() {
            return Apparel.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1084;
        }

        @Override
        public String getName() {
            return "Tattoos";
        }

        @Override
        public synchronized Map<String, Tattoos> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Tattoos.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Tattoos> items;
        }
    }
}
