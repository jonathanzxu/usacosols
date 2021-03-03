import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class feb2021bronze1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader("template.in"));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        HashMap<String, Integer> dob = new HashMap<String, Integer>();
        HashMap<String, Integer> zodiac = new HashMap<String, Integer>();
        int N = Integer.parseInt(reader.readLine());
        dob.put("Bessie", 0);
        zodiac.put("Bessie", 0);
        StringTokenizer st;
        String first, prevnext, year, last;
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            first = st.nextToken();
            st.nextToken();
            st.nextToken();
            prevnext = st.nextToken();
            year = st.nextToken();
            st.nextToken();
            st.nextToken();
            last = st.nextToken();
            zodiac.put(first, zodiac(year));
            if(prevnext.equals("previous"))
            {
                if(zodiac(year) > zodiac.get(last))
                {
                    dob.put(first, dob.get(last) - (zodiac.get(last) - zodiac(year) + 12));
                }
                else if(zodiac(year) == zodiac.get(last))
                {
                    dob.put(first, dob.get(last) - 12);
                }
                else
                {
                    dob.put(first, dob.get(last) - (zodiac.get(last) - zodiac(year)));
                }
            }
            else {
                if(zodiac(year) < zodiac.get(last))
                {
                    dob.put(first, dob.get(last) + (zodiac(year) - zodiac.get(last) + 12));
                }
                else if(zodiac(year) == zodiac.get(last))
                {
                    dob.put(first, dob.get(last) + 12);
                }
                else
                {
                    dob.put(first, dob.get(last) + (zodiac(year) - zodiac.get(last)));
                }
            }
        }
        System.out.println(Math.abs(dob.get("Bessie") - dob.get("Elsie")));
        reader.close();
        //writer.close();
    }
    static int zodiac(String year)
    {
        if(year.equals("Ox"))
        {
            return 0;
        }
        if(year.equals("Tiger"))
        {
            return 1;
        }
        if(year.equals("Rabbit"))
        {
            return 2;
        }
        if(year.equals("Dragon"))
        {
            return 3;
        }
        if(year.equals("Snake"))
        {
            return 4;
        }
        if(year.equals("Horse"))
        {
            return 5;
        }
        if(year.equals("Goat"))
        {
            return 6;
        }
        if(year.equals("Monkey"))
        {
            return 7;
        }
        if(year.equals("Rooster"))
        {
            return 8;
        }
        if(year.equals("Dog"))
        {
            return 9;
        }
        if(year.equals("Pig"))
        {
            return 10;
        }
        return 11;
    }
}
