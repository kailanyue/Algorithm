package _02_LinkedList;

/**
 * @author ngt
 * @create 2021-04-29 21:52
 */

/*
    1. 因为不能实现接口中的所有方法，因此要声明为抽象类
    2. 由于是抽象类因此常量值不建议放在里面，而需要放在接口里面
    3. 由于接口中只能声明全局静态变量，所以size声明在抽象类中
 */
public abstract class MyAbstractList<E> implements MyList<E> {

    protected int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void add(E element){
        add(size, element);
    }

    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

}
