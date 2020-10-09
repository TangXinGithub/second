import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@EnableAsync
public class test {


    clientTest clientTest = new clientTest();

    @Test
    void name() {
        CompletableFuture<Integer> future=CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    server();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });


        try {
            clientTest.client();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    void server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("server is runing");
        for (; ; ) {
            Socket socket = serverSocket.accept();
            System.out.println("connected form " + socket.getRemoteSocketAddress());
            Thread thread = new Handler(socket);//开线程处理
            thread.start();
        }
    }
}
