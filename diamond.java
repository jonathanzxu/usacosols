import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class diamond {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Integer> diamonds = new ArrayList<Integer>(N);
        for(int i = 0; i < N; i++)
        {
            diamonds.add(Integer.parseInt(reader.readLine()));
        }
        Collections.sort(diamonds);
        int left = 0, right = 0, maxdia = 1, maxl = 0, maxr = 0;
        while(left < N && right < N)
        {
            while(diamonds.get(right) - diamonds.get(left) > K)
            {
                left++;
            }
            if(right - left + 1 > maxdia)
            {
                maxdia = right - left + 1;
                maxl = left;
                maxr = right;
            }
            right++;
        }
        for(int i = maxl; i <= maxr; i++)
        {
            diamonds.remove(maxl);
        }
        int left2 = 0, right2 = 0, maxdia2 = 1;
        if(diamonds.size() == 0) maxdia2 = 0;
        else
        {
            while(left2 < diamonds.size() && right2 < diamonds.size())
            {

                while(diamonds.get(right2) - diamonds.get(left2) > K)
                {
                    left2++;
                }
                if(right2 - left2 + 1 > maxdia2)
                {
                    maxdia2 = right2 - left2 + 1;
                }
                right2++;
            }
        }
        writer.println(maxdia + maxdia2);
        reader.close();
        writer.close();
    }
}
