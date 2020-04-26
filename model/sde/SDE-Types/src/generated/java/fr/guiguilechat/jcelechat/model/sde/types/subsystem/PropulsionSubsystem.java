package fr.guiguilechat.jcelechat.model.sde.types.subsystem;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Subsystem;
import org.yaml.snakeyaml.Yaml;

public class PropulsionSubsystem
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
    @DefaultDoubleValue(0.0)
    public double MaxTargetRange;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(100.0)
    public double SignatureRadius;
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
    @DefaultDoubleValue(0.0)
    public double SubsystemBonusCaldariPropulsion;
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
    @DefaultDoubleValue(0.0)
    public double SubsystemBonusGallentePropulsion;
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
    public static final PropulsionSubsystem.MetaGroup METAGROUP = new PropulsionSubsystem.MetaGroup();

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
    public IMetaGroup<PropulsionSubsystem> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PropulsionSubsystem>
    {
        public static final String RESOURCE_PATH = "SDE/types/subsystem/PropulsionSubsystem.yaml";
        private Map<String, PropulsionSubsystem> cache = (null);

        @Override
        public IMetaCategory<? super PropulsionSubsystem> category() {
            return Subsystem.METACAT;
        }

        @Override
        public int getGroupId() {
            return  957;
        }

        @Override
        public String getName() {
            return "PropulsionSubsystem";
        }

        @Override
        public synchronized Map<String, PropulsionSubsystem> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PropulsionSubsystem.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PropulsionSubsystem> types;
        }
    }
}
