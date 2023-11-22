import java.util.List;

public class Main {


    public static void main(String[] args) {
        String filePath = "textcase/case4.txt"; // 替换为测试用例文件的路径
        List<ConcaveQuadrilateral> quadrilaterals = FileLoader.loadQuadrilaterals(filePath);
        double minDistance = Double.MAX_VALUE;
        long startTime = System.currentTimeMillis();
        for (ConcaveQuadrilateral quadrilateral1:quadrilaterals) {
            for (ConcaveQuadrilateral quadrilateral2:quadrilaterals){
                if (quadrilateral1 == quadrilateral2) {
                    continue;
                }
                double distance = ShortestDistanceCalculator.calculate(quadrilateral1, quadrilateral2);
                if (minDistance > distance) {
                    minDistance = distance;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime; // 计算运行时间（毫秒）
        double durationInSeconds = (endTime - startTime) / 1000.0;
        System.out.println("运行时间：" + durationInSeconds + " 秒");

        System.out.println(minDistance);
    }
}
