package io.colligence.api.util.collection;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SingleKeyListTable<K, V extends SingleKeyObject<K>> implements Serializable {
	private static final long serialVersionUID = 4287339036881829123L;

	private final Hashtable<K, List<V>> hlt = new Hashtable<>();

	public Hashtable<K, List<V>> __getRawData() {
		return hlt;
	}

	// ===============================================================
	// key
	// ===============================================================
	public synchronized Enumeration<K> selectKeys() {
		return hlt.keys();
	}

	public synchronized Set<K> selectKeySet() {
		return hlt.keySet();
	}


	// ===============================================================
	// insert/update
	// ===============================================================
	public synchronized boolean insert(V sko) {
		if(!hlt.containsKey(sko.__getSKey__())) hlt.put(sko.__getSKey__(), new ArrayList<>());
		return hlt.get(sko.__getSKey__()).add(sko);
	}


	// ===============================================================
	// select
	// ===============================================================
	public synchronized Collection<List<V>> select() {
		return hlt.values();
	}

	public synchronized List<V> selectList(K key) {
		return hlt.containsKey(key) ? hlt.get(key) : Collections.emptyList();
	}

	public synchronized List<V> selectListOrNull(K key) {
		return hlt.getOrDefault(key, null);
	}


	// ===============================================================
	// delete
	// ===============================================================
	public synchronized void delete(K key) {
		if(hlt.containsKey(key)) hlt.get(key).clear();
		hlt.remove(key);
	}

	public synchronized int deleteIf(Predicate<? super V> filter) {
		int removed = 0;
		for(List<V> _l : hlt.values()) {
			if(_l.removeIf(filter)) removed++;
		}
		return removed;
	}

	public synchronized void deleteEmpty() {
		hlt.values().removeIf(_skol -> _skol.size() == 0);
	}

	public synchronized void deleteAll() {
		hlt.values().forEach(List::clear);
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
		return hlt.values().stream().mapToInt(List::size).sum();
	}


	// ===============================================================
	// stream / collection
	// ===============================================================
	public synchronized Stream<V> stream_V() {
		return hlt.values().stream().flatMap(Collection::stream);
	}

	public synchronized void forEach(Consumer<? super V> action) {
		hlt.values().forEach(_l -> _l.forEach(action));
	}
}