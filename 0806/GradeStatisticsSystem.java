public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        int sum = 0, max = scores[0], min = scores[0];
        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;
        }
        double average = sum / (double) scores.length;

        int a = 0, b = 0, c = 0, d = 0, f = 0, aboveAverage = 0;
        for (int score : scores) {
            if (score >= 90) a++;
            else if (score >= 80) b++;
            else if (score >= 70) c++;
            else if (score >= 60) d++;
            else f++;

            if (score > average) aboveAverage++;
        }

        System.out.println("平均分數: " + average);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("等第人數: A=" + a + " B=" + b + " C=" + c + " D=" + d + " F=" + f);
        System.out.println("高於平均的學生數: " + aboveAverage);
    }
}
