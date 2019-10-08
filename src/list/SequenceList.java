package list;

import java.util.Arrays;

public class SequenceList<T> {
    private int DEFAULT_SIZE = 16;
    private int capacity;   //保存数组的长度
    private Object[] elementData;
    private int size = 0;
//以默认数组长度创建空顺序线性表

    public SequenceList() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    //以一个初始化元素来创建顺序线性表
    public SequenceList(T element) {
        this();
        elementData[0] = element;
        size++;
    }

    public SequenceList(T element, int initSize) {
        capacity = 1;
        while (capacity < initSize) {
            capacity <<= 1;
        }
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }

    //获取顺序线性表的大小
    public int length() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public T get(int i) {
        if (i < 0 || i > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");

        }
        return (T) elementData[i];
    }

    //查找顺序线性表中制定元素的索引
    public int locate(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                return i;
            }

        }
        return -1;
    }

    //向顺心表的指定位置插入一个元素
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    //在线性表的开始处添加一个元素
    public void add(T element) {
        insert(element, size);
    }

    //扩充底层素组长度，很麻烦，性能差
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            //不断的将capacity * 2,直到capacity大于minCapacity为止
            while (capacity < minCapacity) {
                capacity <<= 1;
            }
            elementData = Arrays.copyOf(elementData, capacity);
        }
    }

    //删除顺序线性表中指定索引处的元素
    @SuppressWarnings("unchecked")
    public T delect(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        T oldValue = (T) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        //清空最后一个元素
        elementData[--size] = null;
        return oldValue;
    }

    //删除顺序线性表中最后一个元素
    public T remove() {
        return delect(size - 1);
    }

    //判断线性表是否为空表
    public boolean empty() {
        return size == 0;
    }

    //清空线性表
    public void clear() {
        Arrays.fill(elementData, null);
        size = 0;
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                sb.append(elementData[i].toString() + ",");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}





