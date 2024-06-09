package poly.java5divineshop.Divineshop.Service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    private static String UPLOADED_FOLDER = "/Users/phamhuy/Desktop/Code/FPTCODE/JAVA5/IJ/DivineShop-java5/src/main/resources/static/images/";

    public String saveImage(MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }

        try {
            // Get the filename without extension
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            // Get the file extension
            String extension = filename.substring(filename.lastIndexOf("."));
            // Generate a unique filename
            String uniqueFilename = System.currentTimeMillis() + extension;

            // Get the path to save the file
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + uniqueFilename);

            // Save the file
            Files.write(path, bytes);

            return uniqueFilename; // return the filename for further processing if needed
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file";
        }
    }
}
