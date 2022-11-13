package fr.guiguilechat.jcelechat.model.sde.types.charge;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrengthBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrengthBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrengthBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrengthBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class StructureECMScript
    extends Charge
{
    /**
     * The size of the charges that can fit in the turret/whatever.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargesize;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup;
    /**
     * Bonus to Gravimetric Strength bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scangravimetricstrengthbonusbonus;
    /**
     * Bonus to Lader Strength bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanladarstrengthbonusbonus;
    /**
     * Bonus to Magnetometric Strength bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanmagnetometricstrengthbonusbonus;
    /**
     * Bonus to Radar Strength bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanradarstrengthbonusbonus;
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureitemvisualflag;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeSize.INSTANCE, Radius.INSTANCE, Capacity.INSTANCE, ScanGravimetricStrengthBonusBonus.INSTANCE, LauncherGroup.INSTANCE, ScanLadarStrengthBonusBonus.INSTANCE, ScanMagnetometricStrengthBonusBonus.INSTANCE, ScanRadarStrengthBonusBonus.INSTANCE, StructureItemVisualFlag.INSTANCE })));
    public static final StructureECMScript.MetaGroup METAGROUP = new StructureECMScript.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  128 :
            {
                return chargesize;
            }
            case  137 :
            {
                return launchergroup;
            }
            case  2072 :
            {
                return scangravimetricstrengthbonusbonus;
            }
            case  2073 :
            {
                return scanladarstrengthbonusbonus;
            }
            case  2074 :
            {
                return scanmagnetometricstrengthbonusbonus;
            }
            case  2075 :
            {
                return scanradarstrengthbonusbonus;
            }
            case  2334 :
            {
                return structureitemvisualflag;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<StructureECMScript> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureECMScript>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/StructureECMScript.yaml";
        private Map<Integer, StructureECMScript> cache = (null);

        @Override
        public IMetaCategory<? super StructureECMScript> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1549;
        }

        @Override
        public String getName() {
            return "StructureECMScript";
        }

        @Override
        public synchronized Map<Integer, StructureECMScript> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureECMScript.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, StructureECMScript> types;
        }
    }
}
