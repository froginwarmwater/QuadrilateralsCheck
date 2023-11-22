public class ShortestDistanceCalculator {
    static double calculate(ConcaveQuadrilateral q1, ConcaveQuadrilateral q2) {
        Triangle[] trianglesQ1 = q1.decomposeIntoTriangles();
        Triangle[] trianglesQ2 = q2.decomposeIntoTriangles();

        double minDistance = Double.MAX_VALUE;

        for (Triangle t1 : trianglesQ1) {
            for (Triangle t2 : trianglesQ2) {
                for (LineSegment edgeT1 : t1.getEdges()) {
                    for (LineSegment edgeT2 : t2.getEdges()) {
                        double distance = edgeT1.distanceTo(edgeT2);
                        minDistance = Math.min(minDistance, distance);
                    }
                }
            }
        }

        return minDistance;
    }
}

