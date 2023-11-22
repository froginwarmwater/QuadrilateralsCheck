public class Triangle {
    Point v1, v2, v3;
    LineSegment edge1, edge2, edge3;
    Triangle(Point v1, Point v2, Point v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.edge1 = new LineSegment(v1, v2);
        this.edge2 = new LineSegment(v2, v3);
        this.edge3 = new LineSegment(v3, v1);
    }
    LineSegment[] getEdges() {
        return new LineSegment[]{ edge1, edge2, edge3 };
    }
}

