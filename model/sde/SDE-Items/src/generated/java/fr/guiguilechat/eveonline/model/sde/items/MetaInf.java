package fr.guiguilechat.eveonline.model.sde.items;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import org.yaml.snakeyaml.Yaml;

public class MetaInf {
    public LinkedHashMap<Integer, String> id2name = new LinkedHashMap<>();
    public LinkedHashMap<String, String> name2group = new LinkedHashMap<>();
    public LinkedHashMap<String, String> group2class = new LinkedHashMap<>();
    public final static String RESOURCE_PATH = "SDE/items/metainf.yaml";
    private static MetaInf cache = (null);

    public static MetaInf load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MetaInf.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), MetaInf.class);
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }
}
