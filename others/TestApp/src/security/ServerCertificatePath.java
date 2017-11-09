package security;

import java.security.cert.Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * Retrieving the Certification Path of an SSL Server
 *
 * @author hardiku
 */
public class ServerCertificatePath
{

    public static void main(String[] argv) throws Exception
    {
        int port = 443;
        String hostname = "hostname";
        SSLSocketFactory factory = HttpsURLConnection.getDefaultSSLSocketFactory();
        SSLSocket socket = (SSLSocket) factory.createSocket(hostname, port);

        socket.startHandshake();

        // Retrieve the server's certificate chain
        Certificate[] serverCerts = socket.getSession().getPeerCertificates();

        socket.close();
    }
}
