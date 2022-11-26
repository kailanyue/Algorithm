package _02_LinkedList.doublelinked;

import _02_LinkedList.MyAbstractList;


// 双向链表
public class MyDoubleLinkedList<E> extends MyAbstractList<E> {

    private Node<E> first;

    private Node<E> last;


    private static class Node<E> {
        E element;

        Node<E> prev;

        Node<E> next;


        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (prev != null) {
                sb.append(prev.element);
            } else {
                sb.append("null");
            }

            sb.append("_").append(element).append("_");

            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }

            return sb.toString();
        }
    }

//    public MyDoubleLinkedList() {
//
//    }


    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
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
    
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == size) {
            // 往最后面添加元素
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, null);
            if (oldLast == null) {
                first = last;
            } else {
                oldLast.next = last;
            }

        } else {
            Node<E> nextNode = node(index);
            Node<E> prev = nextNode.prev;
            Node<E> node = new Node<>(prev, element, nextNode);

            nextNode.prev = node;

            if (prev == null) {
                first = node;
            } else {
                prev.next = node;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> removeNode = node(index);
        Node<E> prevNode = removeNode.prev;
        Node<E> nextNode = removeNode.next;

        // index == 0
        if (prevNode == null) {
            first = nextNode;
        } else {
            prevNode.next = nextNode;
        }


        // index == size -1
        if (nextNode == null) {
            last = prevNode;
        } else {
            nextNode.prev = prevNode;
        }

        size--;
        return removeNode.element;
    }

    @Override
    public int indexOf(E element) {

        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }


    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first;
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
