import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

class HelloClient1 {
public static void main ( String argv [ ] ) throws Exception
{


        try (ServerSocket serversocket = new ServerSocket (1112)) {
            Socket socket1 = serversocket.accept();

            PrintStream out = new PrintStream(socket1.getOutputStream()) ;

            out.println( "Hello World!" ) ;
            Socket socket = new Socket( "localhost" , 1113);
            InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
            BufferedReader input = new BufferedReader(inputStream);
            System.out.println(input.readLine());
            socket.close();
            socket1.close();
        }
        catch (Exception e){
            System.out.println("problème : " + e);
        }
    }
}