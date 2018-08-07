package io.colligence.api.util.collection;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DoubleKeyListTable<MK, SK, V extends DoubleKeyObject<MK, SK>> implements Serializable {
	private static final long serialVersionUID = -3698180174625651140L;

	private final Hashtable<MK, SingleKeyListTable<SK, V>> hlt = new Hashtable<>();

	public Hashtable<MK, SingleKeyListTable<SK, V>> __getRawData() {
		return hlt;
	}

	// ===============================================================
	// key
	// ===============================================================
	public synchronized Enumeration<MK> selectKeys() {
		return hlt.keys();
	}

	public synchronized Set<MK> selectKeySet() {
		return hlt.keySet();
	}


	// ===============================================================
	// insert/update
	// ===============================================================
	public synchronized boolean insert(V dko) {
		return Optional.ofNullable(hlt.get(dko.__getMKey__())).orElseGet(() -> {
			SingleKeyListTable<SK, V> sklt = new SingleKeyListTable<>();
			hlt.put(dko.__getMKey__(), sklt);
			return sklt;
		}).insert(dko);
	}


	// ===============================================================
	// select
	// ===============================================================
	public synchronized Collection<SingleKeyListTable<SK, V>> select() {
		return hlt.values();
	}

	public synchronized SingleKeyListTable<SK, V> select(MK mainKey) {
		return hlt.get(mainKey);
	}

	public synchronized List<V> selectList(MK mainKey, SK subKey) {
		return hlt.containsKey(mainKey) ? hlt.get(mainKey).selectList(subKey) : Collections.emptyList();
	}

	public synchronized List<V> selectListOrNull(MK mainKey, SK subKey) {
		return hlt.containsKey(mainKey) ? hlt.get(mainKey).selectListOrNull(subKey) : null;
	}


	// ===============================================================
	// delete
	// ===============================================================
	public synchronized void delete(MK mainKey) {
		if(hlt.containsKey(mainKey)) hlt.get(mainKey).deleteAll();
		hlt.remove(mainKey);
	}

	public synchronized void delete(MK mainKey, SK subKey) {
		if(hlt.containsKey(mainKey)) hlt.get(mainKey).delete(subKey);
	}

	public synchronized int deleteIf(Predicate<? super V> filter) {
		int removed = 0;
		for(SingleKeyListTable<SK, V> _sklt : hlt.values()) {
			removed += _sklt.deleteIf(filter);
		}
		return removed;
	}

	public synchronized void deleteEmpty() {
		hlt.values().removeIf(_sklt -> {
			_sklt.deleteEmpty();
			return _sklt.countKey() == 0;
		});
	}

	public synchronized void deleteAll() {
		hlt.values().forEach(SingleKeyListTable::deleteAll);
		hlt.clear();
	}

	public synchronized void deleteAll(Consumer<? super V> action) {
		forEach(action);
		deleteAll();
	}


	// ===============================================================
	// count
	// ===============================================================
	public synchronized int countKey() {
		return hlt.size();
	}

	public synchronized int countAll() {
		return hlt.values().stream().mapToInt(SingleKeyListTable::countAll).sum();
	}


	// ===============================================================
	// stream / collection
	// ===============================================================
	public synchronized Stream<V> stream_V() {
		return hlt.values().stream().flatMap(SingleKeyListTable::stream_V);
	}

	public synchronized void forEach(Consumer<? super V> action) {
		hlt.values().forEach(_sklt -> _sklt.forEach(action));
	}
}
