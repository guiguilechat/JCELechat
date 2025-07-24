package fr.guiguilechat.jcelechat.jcesi.holders.transform;

import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.jcesi.holders.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.Holder;

public class BoolTransformHolder<U> extends TransformHolder<Boolean, U> implements BoolHolder {

	public BoolTransformHolder(Holder<U> source, Predicate<U> predicate) {
		super(source, predicate::test);
	}

	private final Object fieldsLock = new Object();

	private BoolHolder not;

	@Override
	public BoolHolder not() {
		if (not == null) {
			// we can't lock on this because creating the not
			synchronized (fieldsLock) {
				if (not == null) {
					not = not(this);
				}
			}
		}
		return not;
	}

	public static BoolHolder not(BoolHolder source) {
		BoolTransformHolder<Boolean> ret = new BoolTransformHolder<>(source, b -> !b);
		ret.not = source;
		return ret;
	}

}
