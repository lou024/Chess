package model;

public class Pair<T, E> {
    T left;
    E right;

    public Pair(T left, E right) {
        this.left = left;
        this.right = right;
    }

    public boolean equals(Pair<T, E> pair) {
        return left == pair.left && right == pair.right;
    }

    public String toString() {
        return "(" + left.toString() + "," + right.toString() + ")";
    }
}
