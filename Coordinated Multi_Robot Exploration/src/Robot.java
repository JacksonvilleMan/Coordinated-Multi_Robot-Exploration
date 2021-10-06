public class Robot {
    private String name;
    private int currentX;
    private int currentY;

    public Robot(String name, int x, int y) {
        this.name = name;
        this.currentX = x;
        this.currentY = y;
    }

    public void setCurrentX(int x) {
        this.currentX = x;
    }
    public void setCurrentY(int y) {
        this.currentY = y;
    }

    public String getName() {
        return this.name;
    }
    public int getCurrentX() {
        return this.currentX;
    }
    public int getCurrentY() {
        return this.currentY;
    }
}