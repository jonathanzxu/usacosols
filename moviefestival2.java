import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class moviefestival2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        ArrayList<Movie> movies = new ArrayList<Movie>(n);
        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(reader.readLine());
            movies.add(new Movie(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(movies);
        int numwatched = 0;
        int endtime;
        int j;
        for(int i = 0; i < k; i++)
        {
            j = 0;
            endtime = 0;
            while(j < movies.size()){
                if(endtime <= movies.get(j).start)
                {
                    endtime = movies.get(j).end;
                    numwatched++;
                    movies.remove(j);
                    j--;
                }
                j++;
            }
        }
        System.out.println(numwatched);
        reader.close();
        //writer.close();
    }
}
class Movie implements Comparable<Movie>
{
    int start, end;
    public Movie(int t, int d)
    {
        start = t;
        end = d;
    }
    @Override
    public int compareTo(Movie t)
    {
        return end - t.end;
    }
}