package fr.guiguilechat.jcelechat.model.sde.types.asteroid;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import org.yaml.snakeyaml.Yaml;

public class Bezdnacine
    extends Asteroid
{
    public static final Bezdnacine.MetaGroup METAGROUP = new Bezdnacine.MetaGroup();

    @Override
    public IMetaGroup<Bezdnacine> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Bezdnacine>
    {
        public static final String RESOURCE_PATH = "SDE/types/asteroid/Bezdnacine.yaml";
        private Map<String, Bezdnacine> cache = (null);

        @Override
        public IMetaCategory<? super Bezdnacine> category() {
            return Asteroid.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4031;
        }

        @Override
        public String getName() {
            return "Bezdnacine";
        }

        @Override
        public synchronized Map<String, Bezdnacine> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Bezdnacine.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Bezdnacine> types;
        }
    }
}
