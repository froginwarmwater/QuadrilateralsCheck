
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    public static List<ConcaveQuadrilateral> loadQuadrilaterals(String filePath) {
        List<ConcaveQuadrilateral> quadrilaterals = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] coordinates = line.split(" ");
                if (coordinates.length == 8) {
                    Point[] vertices = new Point[4];
                    for (int i = 0; i < 4; i++) {
                        double x = Double.parseDouble(coordinates[i * 2]);
                        double y = Double.parseDouble(coordinates[i * 2 + 1]);
                        vertices[i] = new Point(x, y);
                    }
                    ConcaveQuadrilateral quad = new ConcaveQuadrilateral(vertices[0],vertices[1],vertices[2],vertices[3]);
                    quadrilaterals.add(quad);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return quadrilaterals;
    }

    public static void main(String[] args) {
        String filePath = "textcase/case1.txt"; // 替换为测试用例文件的路径
        List<ConcaveQuadrilateral> quadrilaterals = loadQuadrilaterals(filePath);
        for (ConcaveQuadrilateral quadrilateral:quadrilaterals){
            System.out.println(quadrilateral);
        }

    }
}
