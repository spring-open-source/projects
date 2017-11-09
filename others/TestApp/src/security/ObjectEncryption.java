package security;

import java.io.Serializable;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

/**
 *
 * @author hardiku
 */
public class ObjectEncryption
{

    public static void main(String args[]) throws Exception
    {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        SecretKey secretKey;
        Cipher encrypter, decrypter;

        secretKey = KeyGenerator.getInstance("DES").generateKey();

        encrypter = Cipher.getInstance("DES");
        encrypter.init(Cipher.ENCRYPT_MODE, secretKey);

        decrypter = Cipher.getInstance("DES");
        decrypter.init(Cipher.DECRYPT_MODE, secretKey);

        MyClass cust, unsealed;
        SealedObject sealed;

        cust = new MyClass();
        cust.name = "Paul";
        cust.password = "password";

        // Seal it, storing it in a SealedObject
        sealed = (new SealedObject(cust, encrypter));

        // Try unsealing it
        String algorithmName = sealed.getAlgorithm();
        System.out.println(algorithmName);
        unsealed = (MyClass) sealed.getObject(decrypter);

        System.out.println("NAME: " + unsealed.name);
        System.out.println("PASSWORD: " + unsealed.password);

    }
}

class MyClass implements Serializable
{

    private static final long serialVersionUID = 1L;

    public String name;

    public String password;
}
