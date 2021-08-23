package 堆;

public class Heap<T extends Comparable<T>> {
    
    //存储堆中的元素
    private T[] items;
    //存储元素个数
    private int size;

    public Heap (int capcity){
        this.items =(T[]) new Object[capcity];  
        this.size = 0;
    }

    //判断i处的值与j处的值
    public boolean less(int i, int j){

        return items[i].compareTo(items[j]) < 0;

    }

    

}
