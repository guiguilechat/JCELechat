package fr.guiguilechat.jcelechat.model.sde.types.decryptors;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsGallente
    extends Decryptors
{
    public static final DecryptorsGallente.MetaGroup METAGROUP = new DecryptorsGallente.MetaGroup();

    @Override
    public IMetaGroup<DecryptorsGallente> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DecryptorsGallente>
    {
        public static final String RESOURCE_PATH = "SDE/types/decryptors/DecryptorsGallente.yaml";
        private Map<String, DecryptorsGallente> cache = (null);

        @Override
        public IMetaCategory<? super DecryptorsGallente> category() {
            return Decryptors.METACAT;
        }

        @Override
        public int getGroupId() {
            return  730;
        }

        @Override
        public String getName() {
            return "DecryptorsGallente";
        }

        @Override
        public synchronized Map<String, DecryptorsGallente> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DecryptorsGallente.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DecryptorsGallente> types;
        }
    }
}
