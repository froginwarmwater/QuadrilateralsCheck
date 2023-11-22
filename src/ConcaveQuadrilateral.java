public class ConcaveQuadrilateral {
    Point v1, v2, v3, v4;
    ConcaveQuadrilateral(double a, double b, double c, double d, double e, double f, double g, double h) {
        Point A = new Point(a, b);
        Point B = new Point(c, d);
        Point C = new Point(e, f);
        Point D = new Point(g, h);
        this.v1 = A;
        this.v2 = B;
        this.v3 = C;
        this.v4 = D;
    }
    ConcaveQuadrilateral(Point v1, Point v2, Point v3, Point v4) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
    }

    Triangle[] decomposeIntoTriangles() {
        Point[] vertices = {v1, v2, v3, v4};
        int concaveVertexIndex = findConcaveVertex(vertices);

        // 确定凹顶点的对角顶点
        Point concaveVertex = vertices[concaveVertexIndex];
        Point diagonalVertex = vertices[(concaveVertexIndex + 2) % 4];

        // 根据凹顶点和对角顶点进行分割
        Triangle t1 = new Triangle(concaveVertex, vertices[(concaveVertexIndex + 1) % 4], diagonalVertex);
        Triangle t2 = new Triangle(vertices[(concaveVertexIndex + 3) % 4], concaveVertex, diagonalVertex);

        return new Triangle[] { t1, t2 };
    }

    private int findConcaveVertex(Point[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            Point current = vertices[i];
            Point next = vertices[(i + 1) % vertices.length];
            Point nextNext = vertices[(i + 2) % vertices.length];

            if (isConcave(current, next, nextNext)) {
                return (i + 1) % vertices.length;
            }
        }
        return 0;
    }

    private boolean isConcave(Point a, Point b, Point c) {
        Point vec1 = new Point(b.x - a.x, b.y - a.y);
        Point vec2 = new Point(c.x - b.x, c.y - b.y);
        return vec1.x * vec2.y - vec1.y * vec2.x < 0; // 叉积小于0表示凹
    }

    @Override
    public String toString() {
        return v1 + " " + v2 + " " + v3 + " " + v4;
    }
}
