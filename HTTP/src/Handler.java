import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Handler extends Thread{

    private final Socket sock;

    public Handler(Socket socket) {
        this.sock  =socket;
    }
    public void run(){
        try( InputStream inputStream = this.sock.getInputStream()) {
           try( OutputStream outputStream = this.sock.getOutputStream()){

        handle(inputStream,outputStream);
         }
        }catch (Exception e){
        try{
            this.sock.close();
        }catch (IOException ioe){
            System.out.println("client discounected");
        }
        }
    }

    private void handle(InputStream inputStream, OutputStream outputStream) throws IOException {
    var writer=new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
    var reader=new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));
    writer.write("hello\n");
    writer.flush();
    for(;;){
        String s=reader.readLine();
        if(s.equals("bye")){
            writer.write("bye\n");
            writer.flush();
            break;
        }
        writer.write("ok"+s+"\n");
        writer.flush();
    }
    }
}