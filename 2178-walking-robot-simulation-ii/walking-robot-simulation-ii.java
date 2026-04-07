
class Robot {
    int width, height;
    int d, x, y;
    int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    int total;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        d = 0;
        x = 0;
        y = 0;
        total = width * 2 + (height - 2) * 2;
    }
    
    public void step(int num) {
    while (num > 0) {
        int remain;

        if (d == 0) remain = width - 1 - x;
        else if (d == 1) remain = height - 1 - y;
        else if (d == 2) remain = x;
        else remain = y;

        if (remain >= num) {
            x += dir[d][0] * num;
            y += dir[d][1] * num;
            num = 0;
        } else {
            x += dir[d][0] * remain;
            y += dir[d][1] * remain;
            d = (d + 1) % 4;
            num -= remain;

            num %= total;

            if (num == 0 && atCorner(x, y)) {
                d = (d - 1 + 4) % 4;
            }
        }
    }
}
    
    private boolean atCorner(int x, int y) {
        if (x == 0 && y == 0) return true;
        if (x == 0 && y == height - 1) return true;
        if (x == width - 1 && y == 0) return true;
        if (x == width - 1 && y == height - 1) return true;
        return false;
    }
    
    public int[] getPos() {
        return new int[]{x, y};
    }
    
    public String getDir() {
        if (d == 0) return "East";
        else if (d == 1) return "North";
        else if (d == 2) return "West";
        else return "South";
    }
}
/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */