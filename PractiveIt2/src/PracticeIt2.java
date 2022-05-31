

public class PracticeIt2 {

    /* Write a method hasTwoConsecutive that returns whether or not a list of
     * integers has two adjacent numbers that are consecutive integers (true if
     * such a pair exists and false otherwise).
     */
    public boolean hasTwoConsecutive() {
        if (front == null)
            return false;

        ListNode prev = front;
        ListNode current = prev.next;

        while (current != null) {
            if (prev.data + 1 == current.data)
                return true;

            prev = current;
            current = prev.next;
        }

        return false;
    }

    /* Write a method isSorted that returns true if the list is in sorted
     * (nondecreasing) order and returns false otherwise. An empty list is
     * considered to be sorted.
     */
    public boolean isSorted() {
        if (front == null)
            return true;

        ListNode prev = front;
        ListNode current = prev.next;

        while (current != null) {
            if (prev.data > current.data)
                return false;

            prev = current;
            current = prev.next;
        }

        return true;
    }

    /* Write a method lastIndexOf that accepts an integer value as a parameter and
     * that returns the index in the list of the last occurrence of that value,
     * or -1 if the value is not found in the list.
     */
    public int lastIndexOf(int val) {
        ListNode current = front;
        int index = -1;
        int i = 0;

        while (current != null) {
            if (current.data == val)
                index = i;

            current = current.next;
            i++;
        }

        return index;
    }

    /* Write a method min that returns the minimum value in a list of integers. If
     * the list is empty, it should throw a NoSuchElementException.
     */
    public int min() {
        if (front == null)
            throw new NoSuchElementException();

        int m = front.data;
        ListNode n = front.next;

        while (n != null) {
            if (n.data < m)
                m = n.data;

            n = n.next;
        }

        return m;
    }

    /* Write a method removeAll that removes all occurrences of a particular value.
     * If the list is empty or the value doesn't appear in the list at all, then
     * the list should not be changed by your method. You must preserve the
     * original order of the elements of the list.
     */
    public void removeAll(int val) {
        ListNode prev = null;
        ListNode current = front;

        while (current != null) {
            if (current.data == val) {
                if (prev == null) {
                    front = current.next;
                } else {
                    prev.next = current.next;
                }
                current = current.next;
            } else {
                prev = current;
                current = prev.next;
            }
        }
    }
}
