package _02_LinkedList.single;


import _02_LinkedList.MyAbstractList;

/**
 * @author ngt
 * @create 2021-04-29 22:04
 * 增加虚拟头节点 需要调整 6 个地方
 * 构造函数 增加元素 删除元素  indeexOf node toString 所有涉及到元素位置的地方
 */
public class MySingleLinkedList1<E> extends MyAbstractList<E> {
    // 1. 增加构造函数

    public MySingleLinkedList1() {
        first = new Node<>(null, null);
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(element);
        }
    }

    private Node<E> first;

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldValue = node.element;
        node.element = element;
        return oldValue;
    }


    // 2.增加元素
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        Node<E> prev = index == 0 ? first : node(index - 1);
        prev.next = new Node<>(element, prev.next);
        size++;
    }

    // 3.删除元素
    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldValue;

        Node<E> prev = index == 0 ? first : node(index - 1);
        oldValue = prev.next.element;
        prev.next = prev.next.next;

        size--;
        return oldValue;
    }

    // 4. indexOf
    @Override
    public int indexOf(E element) {
        Node<E> node = first.next;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    // 5.node
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first.next;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(node);

            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
