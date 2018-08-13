package fr.guiguilechat.jcelechat.model.sde.items.types.subsystem;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Subsystem;
import org.yaml.snakeyaml.Yaml;

public class PropulsionSystems
    extends Subsystem
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double AgilityBonusAdd;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxTargetRange;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SubsystemBonusAmarrPropulsion;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SubsystemBonusAmarrPropulsion2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SubsystemBonusCaldariPropulsion;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SubsystemBonusCaldariPropulsion2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SubsystemBonusGallentePropulsion;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SubsystemBonusGallentePropulsion2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SubsystemBonusMinmatarPropulsion;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SubsystemBonusMinmatarPropulsion2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int WarpBubbleImmuneModifier;
    public final static String RESOURCE_PATH = "SDE/items/subsystem/PropulsionSystems.yaml";
    private static Map<String, PropulsionSystems> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2690 :
            {
                return AgilityBonusAdd;
            }
            case  76 :
            {
                return MaxTargetRange;
            }
            case  552 :
            {
                return SignatureRadius;
            }
            case  1435 :
            {
                return SubsystemBonusAmarrPropulsion;
            }
            case  1512 :
            {
                return SubsystemBonusAmarrPropulsion2;
            }
            case  1445 :
            {
                return SubsystemBonusCaldariPropulsion;
            }
            case  1513 :
            {
                return SubsystemBonusCaldariPropulsion2;
            }
            case  1440 :
            {
                return SubsystemBonusGallentePropulsion;
            }
            case  1520 :
            {
                return SubsystemBonusGallentePropulsion2;
            }
            case  1450 :
            {
                return SubsystemBonusMinmatarPropulsion;
            }
            case  1523 :
            {
                return SubsystemBonusMinmatarPropulsion2;
            }
            case  1539 :
            {
                return WarpBubbleImmuneModifier;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  957;
    }

    @Override
    public Class<?> getGroup() {
        return PropulsionSystems.class;
    }

    public static synchronized Map<String, PropulsionSystems> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PropulsionSystems.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, PropulsionSystems> items;
    }
}
