package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;

public class DeadspaceOverseerSBelongings
    extends Entity
{
    /**
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorcapacity;
    /**
     * If this module is in use and this attribute is 1, then assistance modules cannot be used on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowassistance;
    /**
     * If this module is in use and this attribute is 1, then offensive modules cannot be used on the ship if they apply modifiers for the duration of their effect. If this is put on a ship or NPC with value of 1, then the ship or NPC are immune to offensive modifiers (target jamming, tracking disruption etc.)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowoffensivemodifiers;
    /**
     *  0: white (default)
     *  1: red (hostile NPC)
     *  2: blue (Neutral NPC)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitybracketcolour;
    /**
     * The maximum drops of same group (example: entity can only drop 1 of group: energy laser)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int entityequipmentgroupmax;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityequipmentmax;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityequipmentmin;
    /**
     * How much security status is modified by for killing this entity.  Depending on the entity, this may be a positive or negative amount.
     * Value is a % movement of the character's current security towards the upper/lower limit.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double entitysecuritystatuskillbonus;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * The number of milliseconds before the container replenishes the loot inside itself. There is a constant that will be automatically override this value if set to anything lower than 60 seconds.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(600000.0)
    public double lootrespawntime;
    /**
     * Whether a spawn container should refill itself when there are no guards assigned to it.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int spawnwithoutguardstoo;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {EntityEquipmentGroupMax.INSTANCE, CapacitorCapacity.INSTANCE, LootRespawnTime.INSTANCE, DisallowAssistance.INSTANCE, SpawnWithoutGuardsToo.INSTANCE, EntityEquipmentMin.INSTANCE, DisallowOffensiveModifiers.INSTANCE, Hp.INSTANCE, EntityEquipmentMax.INSTANCE, EntitySecurityStatusKillBonus.INSTANCE, StructureUniformity.INSTANCE, EntityBracketColour.INSTANCE })));
    public static final DeadspaceOverseerSBelongings.MetaGroup METAGROUP = new DeadspaceOverseerSBelongings.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  482 :
            {
                return capacitorcapacity;
            }
            case  854 :
            {
                return disallowassistance;
            }
            case  872 :
            {
                return disallowoffensivemodifiers;
            }
            case  798 :
            {
                return entitybracketcolour;
            }
            case  465 :
            {
                return entityequipmentgroupmax;
            }
            case  457 :
            {
                return entityequipmentmax;
            }
            case  456 :
            {
                return entityequipmentmin;
            }
            case  252 :
            {
                return entitysecuritystatuskillbonus;
            }
            case  9 :
            {
                return hp;
            }
            case  470 :
            {
                return lootrespawntime;
            }
            case  903 :
            {
                return spawnwithoutguardstoo;
            }
            case  525 :
            {
                return structureuniformity;
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
    public IMetaGroup<DeadspaceOverseerSBelongings> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceOverseerSBelongings>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/DeadspaceOverseerSBelongings.yaml";
        private Map<Integer, DeadspaceOverseerSBelongings> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceOverseerSBelongings> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  496;
        }

        @Override
        public String getName() {
            return "DeadspaceOverseerSBelongings";
        }

        @Override
        public synchronized Map<Integer, DeadspaceOverseerSBelongings> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DeadspaceOverseerSBelongings.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, DeadspaceOverseerSBelongings> types;
        }
    }
}
