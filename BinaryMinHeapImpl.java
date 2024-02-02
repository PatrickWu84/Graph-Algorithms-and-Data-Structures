import java.util.*;

/**
 * @param <V>   {@inheritDoc}
 * @param <Key> {@inheritDoc}
 */
public class BinaryMinHeapImpl<Key extends Comparable<Key>, V> implements BinaryMinHeap<Key, V> {
    ArrayList<Entry<Key, V>> heap;
    HashMap<V, Integer> map;

    public BinaryMinHeapImpl() {
        heap = new ArrayList<>();
        heap.add(null);
        map = new HashMap<>();
    }

    public void MinHeapify(ArrayList<Entry<Key, V>> heap, HashMap<V, Integer> map, int index) {
        int l = index * 2;
        int r = index * 2 + 1;

        Key key = heap.get(index).key;
        Key sKey = key;
        Entry smallest = heap.get(index);
        if (l <= size()) {
            Key lKey = heap.get(l).key;
            if (lKey.compareTo(key) < 0) {
                sKey = lKey;
                smallest = heap.get(l);
            }
        }
        if (r <= size()) {
            Key rKey = heap.get(r).key;
            if (r <= size() &&  rKey.compareTo(sKey) < 0) {
                sKey = rKey;
                smallest = heap.get(r);
            }
        }
        if (sKey != key) {
            int indexSmallest = heap.indexOf(smallest);
            Entry curr = heap.get(index);

            heap.set(index, smallest);
            heap.set(indexSmallest, curr);

            map.replace((V) curr.value, indexSmallest);
            map.replace((V) smallest.value, index);
            MinHeapify(heap, map, indexSmallest);
        }

    }
    /**
     * {@inheritDoc}
     */

    @Override
    public int size() {
        return heap.size() - 1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(V value) {
        return map.containsKey(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Key key, V value) {
        if (key == null ) {
            throw new IllegalArgumentException();
        }
        if (containsValue(value)) {
            throw new IllegalArgumentException();
        }
        Entry e = new Entry<>(key,value);
        if (isEmpty()) {
            heap.add(e);
            map.put(value, 1);
        } else {
            heap.add(e);
            int currI = size();
            map.put(value, currI);
            int parI = currI / 2;
            Key parKey = heap.get(parI).key;

            while (parKey.compareTo(key) > 0) {
                V parVal =  heap.get(parI).value;
                Entry t = new Entry<>(parKey, parVal);

                heap.set(parI, e);
                heap.set(currI, t);

                map.replace(value, parI);
                map.replace(parVal, currI);

                if (parI > 1) {
                    currI = parI;
                    parI = parI / 2;
                    parKey = heap.get(parI).key;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseKey(V value, Key newKey) {
        if (!map.containsKey(value)) {
            throw new NoSuchElementException();
        }

        int i = map.get(value);
        Key key = heap.get(i).key;

        if (newKey == null || newKey.compareTo(key) > 0) {
            throw new IllegalArgumentException();
        }


        Entry updated = new Entry<>(newKey, value);

        heap.set(i, updated);

        if (i > 1) {
            int parI = i / 2;
            Key parKey = heap.get(parI).key;

            while (parKey.compareTo(newKey) > 0) {
                V parVal =  heap.get(parI).value;
                Entry t = new Entry<>(parKey, parVal);

                heap.set(parI, updated);
                heap.set(i, t);

                map.replace(value, parI);
                map.replace(parVal, i);

                if (parI > 1) {
                    i = parI;
                    parI = parI / 2;
                    parKey = heap.get(parI).key;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<Key, V> peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return heap.get(1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<Key, V> extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Entry min = heap.get(1);
            heap.set(1, heap.get(size()));
            map.remove(min.value);
            map.replace(heap.get(size()).value, 1);
            heap.remove(size());
            if (!isEmpty()) {
                MinHeapify(heap, map, 1);
            }
            return min;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<V> values() {
        Set<V> values = new HashSet<>();
        for (int i = 1; i <= size(); i++) {
            values.add(heap.get(i).value);
        }
        return values;
    }
}