import java.util.Arrays;

public class MinHeap {
    private int heapSize = 0;   // number of elements in heap
    private int capacity; // size of the array heap
    int[] heap; // element array

    public MinHeap(int maxSize) {
        this.capacity = maxSize;
        this.heap = new int[maxSize];
    }

    private int getLeftChildIndex(int parentIndex) { return parentIndex * 2 + 1; }
    private int getRightChildIndex(int parentIndex) { return parentIndex * 2 + 2; }
    private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; }

    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < heapSize; }
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < heapSize; }
    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }

    private int getLeftChild(int index) { return heap[getLeftChildIndex(index)]; }
    private int getRightChild(int index) { return heap[getRightChildIndex(index)]; }
    private int getParent(int index) { return heap[getParentIndex(index)]; }

    private void swap(int indexONe, int indexTwo) {
        int temp = heap[indexONe];
        heap[indexONe] = heap[indexTwo];
        heap[indexTwo] = temp;
    }

    private void ensureExtraCapacity() {
        if (heapSize == capacity) {
            heap = Arrays.copyOf(heap, capacity * 2);
            capacity *= 2;
        }
    }

    public int peek() { // get first element in heap
        if (heapSize == 0) throw new IllegalStateException();
        return heap[0];
    }

    public int poll() { // get first element in heap and remove it
        if (heapSize == 0) throw new IllegalStateException();
        int item = heap[0];
        heap[0] = heap[heapSize - 1];
        heapSize--;
        heapifyDown();
        return item;
    }

    public void add(int item) {
        ensureExtraCapacity();
        heap[heapSize] = item;
        heapSize++;
        heapifyUp();
    }

    public void heapifyUp() {
        int index = heapSize - 1;
        while (hasParent(index) && getParent(index) > heap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && (getRightChild(index) < getLeftChild(index))) {
                smallerChildIndex = getRightChildIndex(index);
            }
            if (heap[index] < heap[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }
}
