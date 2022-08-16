import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class sim_sock
{
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 13085);
        OutputStream outputStream = socket.getOutputStream();

        BufferedImage image = ImageIO.read(new File("C:\\Users\\Jakub\\Pictures\\test.jpg"));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        outputStream.write(size);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();
        System.out.println("Flushed: " + System.currentTimeMillis());

        Thread.sleep(120000);
        System.out.println("Closing: " + System.currentTimeMillis());
        socket.close();
    }
}