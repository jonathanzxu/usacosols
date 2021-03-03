import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class feb2021bronze3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader("template.in"));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        for(int i = 0; i < N; i++)
        {
            String in = reader.readLine();
            int turns = 0;
            for(int j = 0; j < in.length()-1; j++)
            {
                if(in.charAt(j) == 'N' && in.charAt(j+1) == 'E')
                {
                    turns++;
                }
                else if(in.charAt(j) == 'N' && in.charAt(j+1) == 'W')
                {
                    turns--;
                }
                else if(in.charAt(j) == 'E' && in.charAt(j+1) == 'S')
                {
                    turns++;
                }
                else if(in.charAt(j) == 'E' && in.charAt(j+1) == 'N')
                {
                    turns--;
                }
                else if(in.charAt(j) == 'S' && in.charAt(j+1) == 'W')
                {
                    turns++;
                }
                else if(in.charAt(j) == 'S' && in.charAt(j+1) == 'E')
                {
                    turns--;
                }
                else if(in.charAt(j) == 'W' && in.charAt(j+1) == 'N')
                {
                    turns++;
                }
                else if(in.charAt(j) == 'W' && in.charAt(j+1) == 'S')
                {
                    turns--;
                }
            }
            if(turns > 0)
            {
                System.out.println("CW");
            }
            else
            {
                System.out.println("CCW");
            }
        }
        reader.close();
        //writer.close();
    }
}
