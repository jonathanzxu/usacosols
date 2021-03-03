import java.io.*;

public class cowjump {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("cowjump.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        reader.close();
        writer.close();
    }
}
