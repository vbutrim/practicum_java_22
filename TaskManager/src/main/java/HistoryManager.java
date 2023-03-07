import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author butrim
 */
public interface HistoryManager {

    void addTask(int taskId);

    void remove(int taskId);

    List<Integer> getHistoryIds();

    class InMemoryArrayList implements HistoryManager {
        private final ArrayList<Integer> ids;

        public InMemoryArrayList() {
            this.ids = new ArrayList<>();
        }

        public void addTask(int taskId) {
            this.ids.add(taskId);
        }

        @Override
        public void remove(int taskId) {
            ids.remove(taskId); // O(N)
        }

        public List<Integer> getHistoryIds() {
            List<Integer> toReturn = new LinkedList<>();

            Iterator<Integer> iterator = ids.iterator();
            while (iterator.hasNext()) {
                int nextElement = iterator.next();

                toReturn.add(nextElement);
            }

            return toReturn;
        }
    }


    class InMemoryQuickList implements HistoryManager {
        private final CustomLinkedList customLinkedList;
        private final Map<Integer, Node> map;

        public InMemoryQuickList() {
            this.customLinkedList = new CustomLinkedList();
            this.map = new HashMap<>();
        }

        @Override
        public void addTask(int taskId) {
            // 1: add object to list
            Node newNode = customLinkedList.addLast(taskId);

            // 2: add node to <Integer, Node> mapping
            map.put(taskId, newNode);
        }

        @Override
        public void remove(int taskId) {
            // 1: find element in list
            Node nodeToRemove = map.get(taskId); // O(1)

            if (nodeToRemove == null) {
                return;
            }

            // 2: remove element in list
            customLinkedList.remove(nodeToRemove); // O(1)

            // 3: remove element from map
            map.remove(taskId); // O(1)
        }

        @Override
        public List<Integer> getHistoryIds() {
            return customLinkedList.getAll();
        }
    }

    final class CustomLinkedList {
        private final Node head;
        private final Node tail;

        public CustomLinkedList() {
            this.head = new Node(null, -1, null);
            this.tail = new Node(null, -1, null);

            head.next = tail;
            tail.prev = head;
        }

        private Node addLast(int taskId) {
            Node newNode = new Node(tail.prev, taskId, tail);

            tail.prev.next = newNode;
            tail.prev = newNode;

            return newNode;
        }

        public void remove(Node nodeToRemove) {
            nodeToRemove.prev.next = nodeToRemove.next; // 1 - 5
            nodeToRemove.next.prev = nodeToRemove.prev; // 2 - 6

            nodeToRemove.next = null; // 3
            nodeToRemove.prev = null; // 4
        }

        public List<Integer> getAll() {
            List<Integer> toReturn = new LinkedList<>();

            Node current = head.next;

            while (current != tail) {
                toReturn.add(current.taskId);
                current = current.next;
            }

            return toReturn;
        }
    }

    final class Node {
        private final int taskId;
        private Node prev;
        private Node next;

        public Node(Node prev, int taskId, Node next) {
            this.prev = prev;
            this.taskId = taskId;
            this.next = next;
        }
    }


}
