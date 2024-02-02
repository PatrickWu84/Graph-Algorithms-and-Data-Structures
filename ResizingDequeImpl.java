import java.util.Iterator;
import java.util.NoSuchElementException;
public class ResizingDequeImpl<E> implements ResizingDeque<E> {
    E[] array;
    int head, tail;

    public ResizingDequeImpl() {
        array = (E[]) new Object[2];
        head = -1;
        tail = -1;
    }
    @Override
    public int size() {
        int s = 0;
        if (head == -1 || tail == -1) {
            return s;
        }
        if (head > tail) {
            s = (array.length - head) + (tail + 1);
        } else {
            s = tail - head + 1;
        }
        return s;
    }

    @Override
    public E[] getArray() {
        return array;
    }

    @Override
    public void addFirst(E e) {
        if ((size() + 1) > array.length) {
            E[] arrayNew;
            arrayNew = (E[]) new Object[array.length * 2];
            arrayNew[arrayNew.length - 1] = e;
            if (head > tail) {
                int count = 0;
                for (int i = head; i < array.length; i++) {
                    arrayNew[count] = array[i];
                    count++;
                }
                for (int i = 0; i <= tail; i++) {
                    arrayNew[count] = array[i];
                    count++;
                }

            } else {
                int count = 0;
                for (int i = head; i <= tail; i++) {
                    arrayNew[count] = array[i];
                    count++;
                }
            }
            head = arrayNew.length - 1;
            tail = array.length - 1;
            array = arrayNew;

        } else {
            if (head == -1) {
                head = 0;
                tail = 0;
            } else if (head == 0) {
                head = array.length - 1;
            } else {
                head = head - 1;
            }
            array[head] = e;
        }


    }

    @Override
    public void addLast(E e) {
        if ((size() + 1) > array.length) {
            E[] arrayNew;
            arrayNew = (E[]) new Object[array.length * 2];
            arrayNew[size()] = e;
            if (head > tail) {
                int count = 0;
                for (int i = head; i < array.length; i++) {
                    arrayNew[count] = array[i];
                    count++;
                }
                for (int i = 0; i <= tail; i++) {
                    arrayNew[count] = array[i];
                    count++;
                }

            } else {
                int count = 0;
                for (int i = head; i <= tail; i++) {
                    arrayNew[count] = array[i];
                    count++;
                }
            }
            head = 0;
            tail = array.length;
            array = arrayNew;
        } else {
            if (tail == -1) {
                tail = 0;
                head = 0;
            } else if (tail == array.length - 1) {
                tail = 0;
            } else {
                tail = tail + 1;
            }
            array[tail] = e;
        }

    }

    @Override
    public E pollFirst() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        E poll = array[head];
        array[head] = null;

        if (size() == 1) {
            head = -1;
            tail = -1;
            return poll;
        }

        if (head == array.length - 1) {
            head = 0;
        } else {
            head = head + 1;
        }
        if ((size()) == array.length / 2 && size() > 1) {
            E[] arrayNew;
            arrayNew = (E[]) new Object[array.length / 2];
            if (head > tail) {
                int count = 0;
                for (int i = head; i < array.length; i++) {
                    arrayNew[count] = array[i];
                    count++;
                }
                for (int i = 0; i <= tail; i++) {
                    arrayNew[count] = array[i];
                    count++;
                }
            } else {
                int count = 0;
                for (int i = head; i <= tail; i++) {
                    arrayNew[count] = array[i];
                    count++;
                }
            }
            head = 0;
            tail = arrayNew.length - 1;
            array = arrayNew;
        }
        return poll;
    }

    @Override
    public E pollLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        E poll = array[tail];
        if (size() == 1) {
            head = -1;
            tail = -1;
            return poll;
        }
        array[tail] = null;

        if (tail == 0) {
            tail = array.length - 1;
        } else {
            tail = tail - 1;
        }
        if ((size()) == array.length / 2 && size() > 1) {
            E[] arrayNew;
            arrayNew = (E[]) new Object[array.length / 2];
            if (head > tail) {
                int count = 0;
                for (int i = head; i < array.length; i++) {
                    arrayNew[count] = array[i];
                    count++;
                }
                for (int i = 0; i <= tail; i++) {
                    arrayNew[count] = array[i];
                    count++;
                }

            } else {
                int count = 0;
                for (int i = head; i <= tail; i++) {
                    arrayNew[count] = array[i];
                    count++;
                }
            }
            head = 0;
            tail = arrayNew.length - 1;
            array = arrayNew;
        }
        return poll;
    }

    @Override
    public E peekFirst() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return array[tail];
    }

    @Override
    public Iterator<E> iterator() {
        class DequeIterator implements Iterator<E> {
            int curr;
            int tail;
            public DequeIterator(int head, int tail) {
                curr = head;
                this.tail = tail;
            }

            @Override
            public boolean hasNext() {
                return curr != -1;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E currVal = array[curr];
                    if (curr == tail) {
                        curr = -1;
                    } else if (curr == array.length - 1) {
                        curr = 0;
                    } else {
                        curr = curr + 1;
                    }
                    return currVal;
                } else {
                    throw new NoSuchElementException();
                }
            }
        }
        return new DequeIterator(head, tail);
    }

}
