package fr.guiguilechat.jcelechat.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Tattoos
    extends Apparel
{
    public final static Tattoos.MetaGroup METAGROUP = new Tattoos.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/apparel/Tattoos.yaml";
    private static Map<String, Tattoos> cache = (null);

    @Override
    public int getGroupId() {
        return  1084;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Tattoos> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, Tattoos> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Tattoos>
    {

        @Override
        public MetaCategory<? super Tattoos> category() {
            return Apparel.METACAT;
        }

        @Override
        public String getName() {
            return "Tattoos";
        }

        @Override
        public Collection<Tattoos> items() {
            return (load().values());
        }
    }
}
