import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by caiyusong on 2017/8/30.
 */
public class FileUtils {


    public static String getMd5(String path) throws IOException {
        FileInputStream fis= new FileInputStream(path);
        String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
        IOUtils.closeQuietly(fis);
        return md5;
    }

    public static void main(String[] args) {

    }
}
