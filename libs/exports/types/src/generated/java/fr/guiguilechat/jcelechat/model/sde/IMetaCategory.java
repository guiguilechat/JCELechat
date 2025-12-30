package fr.guiguilechat.jcelechat.model.sde;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.model.sde.types.*;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import fr.guiguilechat.jcelechat.model.sde.types.System;

public interface IMetaCategory<T extends EveType> {
    @SuppressWarnings("rawtypes") IMetaCategory[] INSTANCES = {System.METACAT, Owner.METACAT, Structure.METACAT, Celestial.METACAT, StructureModule.METACAT, Station.METACAT, Material.METACAT, Accessories.METACAT, Ship.METACAT, Personalization.METACAT, Module.METACAT, Charge.METACAT, Blueprint.METACAT, Trading.METACAT, Entity.METACAT, Bonus.METACAT, Skill.METACAT, Commodity.METACAT, Drone.METACAT, Implant.METACAT, Deployable.METACAT, Starbase.METACAT, Fighter.METACAT, Reaction.METACAT, Asteroid.METACAT, WorldSpace.METACAT, SKINs.METACAT, Abstrct.METACAT, Apparel.METACAT, ColonyResources.METACAT, Subsystem.METACAT, AncientRelics.METACAT, Decryptors.METACAT, InfrastructureUpgrades.METACAT, SovereigntyStructures.METACAT, QAAndDevGroups.METACAT, PlanetaryIndustry.METACAT, PlanetaryResources.METACAT, PlanetaryCommodities.METACAT, Orbitals.METACAT, Placeables.METACAT, ExpertSystems.METACAT, Infantry.METACAT, Effects.METACAT, Lights.METACAT, Cells.METACAT, Mining.METACAT, SpecialEditionAssets.METACAT };

    int getCategoryId();

    Collection<IMetaGroup<? extends T>> groups();

    String getName();

    default Map<Integer, T> load() {
        HashMap<Integer, T> ret = new HashMap<>();
        groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
        return ret;
    }
}
