package cos.util;

public final class Color {

    private int r;
    private int g;
    private int b;
    private float a = 1f;
    // Static Variables
    public static Color black = new Color(0, 0, 0);
    public static Color white = new Color(255, 255, 255);

    public Color(int r, int g, int b) {
        this.r = setR(r);
        this.g = setG(g);
        this.b = setB(g);
    }

    public Color(int r, int g, int b, float a) {
        this.r = setR(r);
        this.g = setG(g);
        this.b = setB(g);
        this.a = setA(a);
    }

    public int getR() {
        return r;
    }

    public int setR(int r) {
        return this.r = Functions.clamp(r, 0, 255);
    }

    public int getG() {
        return g;
    }

    public int setG(int g) {
        return this.g = Functions.clamp(g, 0, 255);
    }

    public int getB() {
        return b;
    }

    public int setB(int b) {
        return this.b = Functions.clamp(b, 0, 255);
    }

    public float getA() {
        return a;
    }

    public float setA(float a) {
        return this.a = Functions.clamp01(a);
    }

    public String toRGB() {
        return r + "," + g + "," + b;
    }

    public String toRGBA() {
        return r + "," + g + "," + b + "," + a;
    }

    public static Color parseColor(String s) throws NumberFormatException {
        Color color = null;

        String[] str = s.split(",");
        if (str.length == 3) {
            color = new Color(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
        } else if (str.length == 4) {
            color = new Color(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), Float.parseFloat(str[3]));
        }

        return color;
    }
}
