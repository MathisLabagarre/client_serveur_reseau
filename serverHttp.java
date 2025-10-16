import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;
import java.nio.file.Files;

class serverHttp {
    public static void main(String argv[]) throws Exception {

        ServerSocket server = new ServerSocket(8080);
        Socket socket1 = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
        OutputStream out = socket1.getOutputStream();
        String a;
        byte[] response;
        StringBuffer content = new StringBuffer();
        while ((a = in.readLine()) != null) {
            content.append(a);
            if (a.isEmpty()) {
                break;
            }
        }
        if (!content.toString().split(" ")[0].equals("GET")) {
            response = ("HTTP/1.1 404 Not Found\n".getBytes("UTF-8"));
            out.write(response);
        }
        String fileToGet = content.toString().split(" ")[1].split("/")[1];
        File file = new File(fileToGet);
        if(!file.exists()){
            System.out.println(fileToGet);
            response = ("HTTP/1.1 404 Not found\n".getBytes("UTF-8"));
            out.write(response);
        }
        else{
            response = Files.readAllBytes(file.toPath());
            if(fileToGet.split("\\.")[1].equals("pdf")){
                out.write("HTTP/1.1 200 Ok\nContent-type:application/pdf;\n\r\n".getBytes("UTF-8"));
                out.write(response);
            }
            else if(fileToGet.split("\\.")[1].equals("html")){
                out.write("HTTP/1.1 200 OK\nContent-type:text/HTML; charset=utf-8;\n\r\n".getBytes("UTF-8"));
                out.write(response);
            }
            else if(fileToGet.split("\\.")[1].equals("jpg")){
                out.write("HTTP/1.1 200 OK\nContent-type:image/jpg;\n\r\n".getBytes("UTF-8"));
                out.write(response);
            }
        }
        server.close();
    }
}
