import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

class HelloServer1 {
public static void main ( String argv [ ] ) throws Exception
    {

        Socket socket = new Socket( "localhost" , 1112);
        InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
        BufferedReader input = new BufferedReader(inputStream);
        System.out.println(input.readLine());

        try (ServerSocket serversocket = new ServerSocket (1113)) {
            Socket socket1 = serversocket.accept();

            PrintStream out = new PrintStream(socket1.getOutputStream()) ;

            out.println( "Hello World!" ) ;

            socket1.close();
        }
        catch (Exception e){
            System.out.println("Aïe" + e);
        }
        socket.close();
    }
}