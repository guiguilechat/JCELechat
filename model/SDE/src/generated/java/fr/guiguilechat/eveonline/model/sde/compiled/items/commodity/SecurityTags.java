
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;
import org.yaml.snakeyaml.Yaml;

public class SecurityTags
    extends Commodity
{

    /**
     * ISK fee per tag to be paid when turning in a tag for a security-status gain
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double SecurityProcessingFee;
    public final static String RESOURCE_PATH = "SDE/commodity/SecurityTags.yaml";
    private static LinkedHashMap<String, SecurityTags> cache = (null);

    @Override
    public int getGroupId() {
        return  1206;
    }

    @Override
    public Class<?> getGroup() {
        return SecurityTags.class;
    }

    public static LinkedHashMap<String, SecurityTags> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SecurityTags> items;

    }

}
