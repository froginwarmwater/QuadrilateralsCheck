
public class LineSegment {
    Point start, end;

    LineSegment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    double distanceTo(LineSegment other) {
        // 1. 检查线段是否相交
        if (this.intersects(other)) {
            return 0.0;
        }

        // 2. 计算端点到另一线段的距离
        double minDist = Math.min(
                Math.min(pointToSegmentDistance(this.start, other), pointToSegmentDistance(this.end, other)),
                Math.min(pointToSegmentDistance(other.start, this), pointToSegmentDistance(other.end, this))
        );
//         线段中间部分的最短距离
        double lineMinDist = lineToLineDistance(this, other);
        minDist = Math.min(minDist, lineMinDist);
//
        return minDist;
    }

    boolean intersects(LineSegment other) {
        // 计算方向
        int dir1 = direction(this.start, this.end, other.start);
        int dir2 = direction(this.start, this.end, other.end);
        int dir3 = direction(other.start, other.end, this.start);
        int dir4 = direction(other.start, other.end, this.end);

        // 检查是否相交
        if (dir1 != dir2 && dir3 != dir4) {
            return true;
        }
        if (dir1 == 0 && onSegment(this.start, this.end, other.start)) {
            return true;
        }
        if (dir2 == 0 && onSegment(this.start, this.end, other.end)) {
            return true;
        }
        if (dir3 == 0 && onSegment(other.start, other.end, this.start)) {
            return true;
        }
        if (dir4 == 0 && onSegment(other.start, other.end, this.end)) {
            return true;
        }

        return false;
    }

    static int direction(Point a, Point b, Point c) {
        double val = (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);
        if (val == 0.0) return 0;  // colinear
        return (val > 0) ? 1 : 2;  // clockwise or counterclockwise
    }

    static boolean onSegment(Point a, Point b, Point c) {
        return c.x <= Math.max(a.x, b.x) && c.x >= Math.min(a.x, b.x) &&
                c.y <= Math.max(a.y, b.y) && c.y >= Math.min(a.y, b.y);
    }


    static double pointToSegmentDistance(Point p, LineSegment segment) {
        Point v = new Point(segment.start.x - p.x, segment.start.y - p.y);
        Point w = new Point(segment.end.x - p.x, segment.end.y - p.y);

        double c1 = dotProduct(v, w);
        if (c1 <= 0) return p.distance(segment.start);

        double c2 = dotProduct(w, w);
        if (c2 <= c1) return p.distance(segment.end);

        double b = c1 / c2;
        Point Pb = new Point(segment.start.x + b * w.x, segment.start.y + b * w.y);
        return p.distance(Pb);
    }

    static double dotProduct(Point a, Point b) {
        return a.x * b.x + a.y * b.y;
    }

    static double lineToLineDistance(LineSegment seg1, LineSegment seg2) {
        Point u = seg1.end.subtract(seg1.start);
        Point v = seg2.end.subtract(seg2.start);
        Point w = seg1.start.subtract(seg2.start);

        double a = u.dot(u);         // always >= 0
        double b = u.dot(v);
        double c = v.dot(v);         // always >= 0
        double d = u.dot(w);
        double e = v.dot(w);
        double D = a * c - b * b;    // always >= 0
        double sc, sN, sD = D;       // sc = sN / sD, default sD = D >= 0
        double tc, tN, tD = D;       // tc = tN / tD, default tD = D >= 0

        // Compute the line parameters of the two closest points
        if (D < Double.MIN_VALUE) { // the lines are almost parallel
            sN = 0.0;         // force using point P0 on segment S1
            sD = 1.0;         // to prevent possible division by 0.0 later
            tN = e;
            tD = c;
        } else {                 // get the closest points on the infinite lines
            sN = (b * e - c * d);
            tN = (a * e - b * d);
            if (sN < 0.0) {        // sc < 0 => the s=0 edge is visible
                sN = 0.0;
                tN = e;
                tD = c;
            } else if (sN > sD) { // sc > 1  => the s=1 edge is visible
                sN = sD;
                tN = e + b;
                tD = c;
            }
        }

        if (tN < 0.0) {            // tc < 0 => the t=0 edge is visible
            tN = 0.0;
            // recompute sc for this edge
            if (-d < 0.0) {
                sN = 0.0;
            } else if (-d > a) {
                sN = sD;
            } else {
                sN = -d;
                sD = a;
            }
        } else if (tN > tD) {      // tc > 1  => the t=1 edge is visible
            tN = tD;
            // recompute sc for this edge
            if ((-d + b) < 0.0) {
                sN = 0;
            } else if ((-d + b) > a) {
                sN = sD;
            } else {
                sN = (-d + b);
                sD = a;
            }
        }
        // finally do the division to get sc and tc
        sc = (Math.abs(sN) < Double.MIN_VALUE ? 0.0 : sN / sD);
        tc = (Math.abs(tN) < Double.MIN_VALUE ? 0.0 : tN / tD);

        // get the difference of the two closest points
        Point dP = w.add(u.multiply(sc)).subtract(v.multiply(tc));  // =  L1(sc) - L2(tc)

        return dP.length();   // return the closest distance
    }

//


}
