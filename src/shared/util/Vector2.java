package shared.util;

public class Vector2<T> {

    private T x;
    private T y;
    // Static Variables
    public static Vector2<Float> zero = new Vector2<Float>(0.f, 0.f);

    public Vector2() {
    }

    public Vector2(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public T setX(T x) {
        return this.x = x;
    }

    public T getY() {
        return y;
    }

    public T setY(T y) {
        return this.y = y;
    }
}
