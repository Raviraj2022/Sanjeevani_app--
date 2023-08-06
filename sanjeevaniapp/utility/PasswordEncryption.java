
package sanjeevaniapp.utility;

import java.util.Base64;

/**
 *
 * @author Ravi raj sahu
 */
public class PasswordEncryption {
    public static String getEncryptionPassword(String password)
    {
        Base64.Encoder encoder=Base64.getEncoder();
        return encoder.encodeToString(password.getBytes());
    }
}
