package io.colligence.api.util.collection;

/**
 * Object has Double Key that used for DoubleKey Collections
 * @param <SK>
 */
public interface DoubleKeyObject<MK, SK> extends SingleKeyObject<SK> {
	MK __getMKey__();
}