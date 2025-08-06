import java.util.*;

public class RecursiveTreePreview {

    // 資料夾總檔案數
    public static int countFiles(Map<String, Object> folder) {
        int count = 0;
        for (Object obj : folder.values()) {
            if (obj instanceof Map)
                count += countFiles((Map<String, Object>) obj);
            else
                count++; // 單一檔案
        }
        return count;
    }

    // 列印多層選單
    public static void printMenu(Map<String, Object> menu, int level) {
        for (String key : menu.keySet()) {
            for (int i = 0; i < level; i++) System.out.print("  ");
            System.out.println("- " + key);
            if (menu.get(key) instanceof Map)
                printMenu((Map<String, Object>) menu.get(key), level + 1);
        }
    }

    // 展平巢狀陣列
    public static void flatten(List<Object> input, List<Object> result) {
        for (Object item : input) {
            if (item instanceof List)
                flatten((List<Object>) item, result);
            else
                result.add(item);
        }
    }

    // 最大深度
    public static int maxDepth(Object obj) {
        if (!(obj instanceof List)) return 0;
        int max = 0;
        for (Object item : (List<?>) obj)
            max = Math.max(max, maxDepth(item));
        return max + 1;
    }

    public static void main(String[] args) {
        // 模擬資料夾
        Map<String, Object> folder = Map.of(
            "Docs", Map.of("Resume.pdf", "file", "Cover.docx", "file"),
            "Photos", Map.of("Trip", Map.of("img1.jpg", "file", "img2.jpg", "file")),
            "Notes.txt", "file"
        );
        System.out.println("總檔案數: " + countFiles(folder));

        System.out.println("選單:");
        printMenu(folder, 0);

        List<Object> nested = List.of(1, List.of(2, List.of(3, 4)), 5);
        List<Object> flat = new ArrayList<>();
        flatten(nested, flat);
        System.out.println("展平: " + flat);

        System.out.println("最大深度: " + maxDepth(nested));
    }
}
