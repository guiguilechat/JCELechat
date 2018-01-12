package fr.guiguilechat.eveonline.model.sde.model;

import java.util.HashSet;

import fr.guiguilechat.eveonline.model.sde.fsd.Eblueprints;

/**
 * usages of an item in industry
 *
 */
public class IndustryUsages {

	public HashSet<Eblueprints> asMaterial = new HashSet<>();
	public HashSet<Eblueprints> asProduct = new HashSet<>();

}
