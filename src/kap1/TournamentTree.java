package kap1;

public class TournamentTree {

    /**
     * A node in a tournament tree
     * Each node has a pointer to its children
     */

    public static class Node {
        char value;
        Node left_child;
        Node right_child;

        public Node(char value) {
            this.value = value;
            this.left_child = null;
            this.right_child = null;
        }

        void print() {
            System.out.print(value + ", ");
            if (this.left_child != null) {
                this.left_child.print();
            }
            if (this.right_child != null) {
                this.right_child.print();
            }
        }
    }

    static Node playMatch(Node team_1, Node team_2) {
        char winner;
        if (team_1.value > team_2.value) {
            winner = team_1.value;
        }
        else {
            winner = team_2.value;
        }
        Node parent = new Node(winner);
        parent.left_child = team_1;
        parent.right_child = team_2;

        return parent;
    }

    public static void main(String[] args) {

        Node z = new Node('Z');
        Node c= new Node('C');
        Node f = new Node('F');
        Node k = new Node('K');

        Node semi_1 = playMatch(z, c);
        Node semi_2 = playMatch(f, k);

        Node winner = playMatch(semi_1, semi_2);

        System.out.println("Turenringstreet i pre-orden");
        // forventer Z, Z, Z, C, K, F, K
        winner.print();
    }
}
