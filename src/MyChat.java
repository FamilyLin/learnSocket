import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyChat {
    private String from;
    private  String greeting;
    private Socket s;

    public MyChat(String from, String greeting, Socket s) {
        this.from = from;
        this.greeting = greeting;
        this.s = s;
    }

    public void chatting(){
        Scanner in = new Scanner(System.in);
        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(s.getInputStream(), MyServer.MY_COMM_CHARSET));
                PrintWriter pw = new PrintWriter(
                        new OutputStreamWriter(s.getOutputStream(), MyServer.MY_COMM_CHARSET));
        ) {
            if (greeting != null) {
                pw.println(greeting);
                pw.flush();
            }
            while(true){
                String line = br.readLine();
                if (line.equalsIgnoreCase(MyServer.BYE)){
                    pw.println(MyServer.BYE);
                    pw.flush();
                }else{
                    System.out.println(from+":"+line);
                    String myWord = in.nextLine();
                    pw.println(myWord);
                    pw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
