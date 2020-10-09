import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class httpTest {

    @Test
    void http() {
        http http = new http();
        try {
            http.http() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}