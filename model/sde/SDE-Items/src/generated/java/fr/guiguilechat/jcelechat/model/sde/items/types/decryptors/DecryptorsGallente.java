package fr.guiguilechat.jcelechat.model.sde.items.types.decryptors;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsGallente
    extends Decryptors
{
    public final static DecryptorsGallente.MetaGroup METAGROUP = new DecryptorsGallente.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsGallente.yaml";
    private static Map<String, DecryptorsGallente> cache = (null);

    @Override
    public int getGroupId() {
        return  730;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<DecryptorsGallente> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, DecryptorsGallente> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DecryptorsGallente.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, DecryptorsGallente> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<DecryptorsGallente>
    {

        @Override
        public MetaCategory<? super DecryptorsGallente> category() {
            return Decryptors.METACAT;
        }

        @Override
        public String getName() {
            return "DecryptorsGallente";
        }

        @Override
        public Collection<DecryptorsGallente> items() {
            return (load().values());
        }
    }
}
