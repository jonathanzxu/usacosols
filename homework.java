import java.io.*;
import java.util.*;
import java.lang.*;

public class homework {
    public static void main(String[] args) throws Exception
    {
        int sum = 0;
        BufferedReader reader = new BufferedReader(new FileReader("homework.in"));
        PrintWriter writer = new PrintWriter("homework.out");
        //Scanner reader = new Scanner(System.in);
        int questions = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        //int questions = Integer.parseInt(reader.nextLine());
        //StringTokenizer st = new StringTokenizer(reader.nextLine());
        int[] scores = new int[questions];
        int[] sorted = new int[questions];
        //int lowest = 10001;
        for(int i = 0; i < questions; i++)
        {
            scores[i] = Integer.parseInt(st.nextToken());
            sorted[i] = scores[i];
            sum += scores[i];
            //lowest = Math.min(lowest, scores[i]);
        }
        Arrays.sort(sorted);
        ArrayList<Integer> sorts = new ArrayList<Integer>();
        for(int i : sorted)
        {
            sorts.add(i);
        }
        double newav;
        double maxscore = (sum - sorted[0])/(double) (questions-1);
        ArrayList<Integer> maxskips = new ArrayList<Integer>();
        for(int i = 0; i < questions - 2; i++)
        {
            sum -= scores[i];
            //if(scores[i] == sorts.get(last)) last++;
            sorts.remove((Integer) scores[i]);
            newav = (sum - sorts.get(0))/(double) (questions-2-i);
            if(newav > maxscore)
            {
                maxscore = newav;
                maxskips = new ArrayList<Integer>();
                maxskips.add(i);
            }
            else if(newav == maxscore)
            {
                maxskips.add(i);
            }
            //if(i == 194) System.out.println("195: " + newav);
            //if(i == 195) System.out.println("196: " + newav);
            //if(i == 196) System.out.println("197: " + newav);
            //if(i == 200) System.out.println("201: " + newav);
            //if(i == 201) System.out.println("202: " + newav);
        }
        for(int i : maxskips)
        {
            writer.println(i+1);
            //System.out.println(i+1);
        }
        reader.close();
        writer.close();
    }
}
