package fr.guiguilechat.eveonline.sde.model;

/**
 * decryptors used in invention.
 *
 */
public class InventionDecryptor {

	public int maxrun = 0;
	public double probmult = 1.0;
	public int id = 0;
	public int me = 0;
	public int te = 0;
	public String name;

	@Override
	public String toString() {
		return name + " id" + id + " me" + me + " te" + te + " mult" + probmult + " run" + maxrun;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			InventionDecryptor o = (InventionDecryptor) obj;
			return name.equals(o.name) && maxrun == o.maxrun && probmult == o.probmult && id == o.id && me == o.me
					&& te == o.te;
		}
		return false;
	}

}
