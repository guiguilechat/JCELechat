package fr.guiguilechat.jcelechat.model.sde.types.apparel;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Prosthetics
    extends Apparel
{
    public static final Prosthetics.MetaGroup METAGROUP = new Prosthetics.MetaGroup();

    @Override
    public IMetaGroup<Prosthetics> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Prosthetics>
    {
        public static final String RESOURCE_PATH = "SDE/types/apparel/Prosthetics.yaml";
        private Map<String, Prosthetics> cache = (null);

        @Override
        public IMetaCategory<? super Prosthetics> category() {
            return Apparel.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1271;
        }

        @Override
        public String getName() {
            return "Prosthetics";
        }

        @Override
        public synchronized Map<String, Prosthetics> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Prosthetics.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Prosthetics> types;
        }
    }
}
