package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class SecurityTags
    extends Commodity
{
    /**
     * ISK fee per tag to be paid when turning in a tag for a security-status gain
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SecurityProcessingFee;
    public final static String RESOURCE_PATH = "SDE/items/commodity/SecurityTags.yaml";
    private static Map<String, SecurityTags> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1904 :
            {
                return SecurityProcessingFee;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1206;
    }

    @Override
    public Class<?> getGroup() {
        return SecurityTags.class;
    }

    public static synchronized Map<String, SecurityTags> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SecurityTags.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SecurityTags> items;
    }
}
