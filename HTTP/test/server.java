import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
@Service
public class server {
  @Async

    void server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("server is runing");
        for(;;){
            Socket socket=serverSocket.accept();
            System.out.println("connected form "+socket.getRemoteSocketAddress());
            Thread thread=new Handler(socket);//开线程处理
            thread.start();
        }
    }
}
