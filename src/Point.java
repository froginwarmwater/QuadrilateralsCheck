class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // 向量减法
    Point subtract(Point other) {
        return new Point(this.x - other.x, this.y - other.y);
    }

    // 向量加法
    Point add(Point other) {
        return new Point(this.x + other.x, this.y + other.y);
    }

    // 点乘（内积）
    double dot(Point other) {
        return this.x * other.x + this.y * other.y;
    }

    // 向量与标量的乘法
    Point multiply(double scalar) {
        return new Point(this.x * scalar, this.y * scalar);
    }

    // 向量长度
    double length() {
        return Math.sqrt(x * x + y * y);
    }

    double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
