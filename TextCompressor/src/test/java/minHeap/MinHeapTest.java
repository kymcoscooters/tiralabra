package minHeap;

import com.kymcoscooters.textcompressor.Node;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MinHeapTest {
    
    MinHeap heap;
    
    @Test
    public void nodesCanBeInserted() throws Exception {
        heap = new MinHeap();
        
        heap.push(new Node('a', 12));
        heap.push(new Node('b', 5));
        heap.push(new Node('c', 15));
    }
    
    @Test
    public void heapReturnsSmallestValue() {
        heap = new MinHeap();
        
        heap.push(new Node('a', 12));
        heap.push(new Node('b', 5));
        heap.push(new Node('c', 15));
        
        int value = heap.pop().getValue();
        
        assertEquals(5, value);
    }
    
    @Test
    public void heapIsEmptyAfterPopping() {
        heap = new MinHeap();
        
        heap.push(new Node('a', 12));
        heap.push(new Node('b', 5));
        heap.push(new Node('c', 15));
        
        heap.pop();
        heap.pop();
        heap.pop();
        
        assertEquals(0, heap.getSize());
    }
}
