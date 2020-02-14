package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
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
    public static final SecurityTags.MetaGroup METAGROUP = new SecurityTags.MetaGroup();

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
    public IMetaGroup<SecurityTags> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SecurityTags>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/SecurityTags.yaml";
        private Map<String, SecurityTags> cache = (null);

        @Override
        public IMetaCategory<? super SecurityTags> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1206;
        }

        @Override
        public String getName() {
            return "SecurityTags";
        }

        @Override
        public synchronized Map<String, SecurityTags> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SecurityTags.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SecurityTags> types;
        }
    }
}
