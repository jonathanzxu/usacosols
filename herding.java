import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class herding {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("herding.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        int[] cows = new int[N];
        for(int i = 0; i < N; i++)
        {
            cows[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(cows);
        int left = 0, right = 0;
        int maxcows = 0;
        while(right < N-1 && cows[right] < cows[left] + N - 1)
        {
            right++;
            while(cows[left] < cows[right] - N + 1) left++;
            maxcows = Math.max(maxcows, right - left + 1);
            if(maxcows == N-1 && cows[right] - cows[left] + 1 == N-1)
            {
                if(left > 0)
                {
                    if(cows[left] - cows[0] > 2)
                    {
                        maxcows = N - 2;
                        break;
                    }
                }
                else
                {
                    if(cows[N-1] - cows[right] > 2)
                    {
                        maxcows = N - 2;
                        break;
                    }
                }
            }
        }

        writer.println(N - maxcows);
        writer.println(cows[N-1] - cows[0] + 1 - N - Math.min(cows[N-1] - cows[N-2] - 1, cows[1] - cows[0] - 1));
        reader.close();
        writer.close();
    }
}
