import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

class TankTest {

    @org.junit.jupiter.api.Test
    void Test() {
        try {
            BufferedImage bufferedImage = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}