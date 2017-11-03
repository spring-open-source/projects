package security;

/**
 *
 * @author hardiku
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Iterator;

public class KeystoreExample
{

    public static void main(String[] args) throws Exception
    {
        KeystoreExample keystore = new KeystoreExample();
        keystore.addCertificate();
        keystore.getCertificate();
    }

    public void getCertificate() throws Exception
    {
        FileInputStream is = new FileInputStream("your.keystore");

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(is, "my-keystore-password".toCharArray());

        // Get certificate
        java.security.cert.Certificate cert = keystore.getCertificate("myalias");
    }

    public void addCertificate() throws Exception
    {
        FileInputStream is = new FileInputStream("your.keystore");

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(is, "my-keystore-password".toCharArray());

        String alias = "myalias";
        char[] password = "password".toCharArray();

        Certificate cert = keystore.getCertificate(alias);

        File keystoreFile = new File("your.keystore");
        // Load the keystore contents
        FileInputStream in = new FileInputStream(keystoreFile);
        keystore.load(in, password);
        in.close();

        // Add the certificate
        keystore.setCertificateEntry(alias, cert);

        // Save the new keystore contents
        FileOutputStream out = new FileOutputStream(keystoreFile);
        keystore.store(out, password);
        out.close();

    }

    public void generateCerificatePath() throws Exception
    {
        FileInputStream is = new FileInputStream("your.keystore");

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(is, "my-keystore-password".toCharArray());

        String alias = "myalias";
        Certificate cert = keystore.getCertificate(alias);

        CertificateFactory certFact = CertificateFactory.getInstance("X.509");
        CertPath path = certFact.generateCertPath(Arrays.asList(new Certificate[]
        {
            cert
        }));

    }

    public void listTrustedCerificateAuthorities() throws Exception
    {

        String filename = System.getProperty("java.home")
                + "/lib/security/cacerts".replace('/', File.separatorChar);
        FileInputStream is = new FileInputStream(filename);
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        String password = "password";
        keystore.load(is, password.toCharArray());

        PKIXParameters params = new PKIXParameters(keystore);

        Iterator it = params.getTrustAnchors().iterator();
        for(; it.hasNext();)
        {
            TrustAnchor ta = (TrustAnchor) it.next();

            X509Certificate cert = ta.getTrustedCert();
            System.out.println(cert.getSigAlgName());
        }
    }

    /*
    Validating a Certification Path using the most-trusted CAs in the JDK's cacerts file.
     */
    public void getTrustedCertificateFromJDKKeyStore() throws Exception
    {
        String filename = System.getProperty("java.home")
                + "/lib/security/cacerts".replace('/', File.separatorChar);
        FileInputStream is = new FileInputStream(filename);
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        String password = "password";
        keystore.load(is, password.toCharArray());

        PKIXParameters params = new PKIXParameters(keystore);

        params.setRevocationEnabled(false);

        CertPathValidator certPathValidator = CertPathValidator.getInstance(CertPathValidator
                .getDefaultType());
        CertPath certPath = null;
        CertPathValidatorResult result = certPathValidator.validate(certPath, params);

        PKIXCertPathValidatorResult pkixResult = (PKIXCertPathValidatorResult) result;
        TrustAnchor ta = pkixResult.getTrustAnchor();
        X509Certificate cert = ta.getTrustedCert();
    }

    /*
    Import certificate from file
     */
    public void importCertificateFromFile() throws Exception
    {

        FileInputStream is = new FileInputStream(new File("your"));

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        java.security.cert.Certificate cert = cf.generateCertificate(is);
    }
}
