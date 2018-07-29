package fr.guiguilechat.jcelechat.model.sde.items.types.structure;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Structure;

public class AdvertisementCenter
    extends Structure
{
    public final static String RESOURCE_PATH = "SDE/items/structure/AdvertisementCenter.yaml";
    private static LinkedHashMap<String, AdvertisementCenter> cache = (null);

    @Override
    public int getGroupId() {
        return  1410;
    }

    @Override
    public Class<?> getGroup() {
        return AdvertisementCenter.class;
    }

    public static synchronized LinkedHashMap<String, AdvertisementCenter> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AdvertisementCenter.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AdvertisementCenter> items;
    }
}
