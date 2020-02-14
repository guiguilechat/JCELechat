package fr.guiguilechat.jcelechat.model.sde.types.apparel;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Tattoos
    extends Apparel
{
    public static final Tattoos.MetaGroup METAGROUP = new Tattoos.MetaGroup();

    @Override
    public IMetaGroup<Tattoos> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Tattoos>
    {
        public static final String RESOURCE_PATH = "SDE/types/apparel/Tattoos.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(Tattoos.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Tattoos> types;
        }
    }
}
