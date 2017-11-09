package security;

import java.security.*;

/**
 *
 * @author hardiku
 */
public class PrivatePubKeyExample
{

    public static void main(String args[]) throws Exception
    {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        // our server, imagine it's a webservice
        KeyServer server = new KeyServer(42);

        // init client with a copy of public key from server
        KeyClient client = new KeyClient(server.getPublicKey());

        // create string that identifies phone and application
        byte[] data = (getPhoneId() + ":" + getApplicationId()).getBytes("utf-8");

        // send data to server for signature creation
        byte[] digitalSignature = server.signData(data);

        // verify on client side
        System.out.println("verified = " + client.verifySig(data, digitalSignature));

        // bad data
        byte[] wrongData = ("anotherPhoneId" + ":" + getApplicationId()).getBytes("utf-8");
        System.out.println("verified = " + client.verifySig(wrongData, digitalSignature));

        // bad signature
        digitalSignature[5] = (byte) 0xff;
        System.out.println("verified = " + client.verifySig(data, digitalSignature));
    }

    private static String getPhoneId()
    {
        return "somephone";
    }

    private static String getApplicationId()
    {
        return "someapp";
    }

    public static class KeyClient
    {

        private PublicKey _publicKey;
        private Signature _signer;

        public KeyClient(PublicKey publicKey)
        {
            if(publicKey == null)
            {
                throw new NullPointerException("publicKey");
            }
            _publicKey = publicKey;

            try
            {
                _signer = Signature.getInstance("SHA1withRSA");
            }
            catch(NoSuchAlgorithmException e)
            {
                throw new RuntimeException("failed to get Signature", e);
            }
        }

        public boolean verifySig(byte[] data, byte[] sig) throws Exception
        {
            synchronized(_signer)
            {
                _signer.initVerify(_publicKey);
                _signer.update(data);
                return (_signer.verify(sig));
            }
        }
    }

    public static class KeyServer
    {

        private KeyPair _keyPair;
        private Signature _signer;

        public KeyServer(int seed)
        {
            try
            {
                _keyPair = generateKeyPair(seed);
            }
            catch(Exception e)
            {
                throw new RuntimeException("failed to generate key pair for seed " + seed, e);
            }

            try
            {
                _signer = Signature.getInstance("SHA1withRSA");
            }
            catch(NoSuchAlgorithmException e)
            {
                throw new RuntimeException("failed to get Signature", e);
            }
        }

        public PublicKey getPublicKey()
        {
            return _keyPair.getPublic();
        }

        public byte[] signData(byte[] data) throws InvalidKeyException, SignatureException
        {
            synchronized(_signer)
            {
                _signer.initSign(_keyPair.getPrivate());
                _signer.update(data);
                return (_signer.sign());
            }
        }

        private KeyPair generateKeyPair(long seed) throws Exception
        {
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom rng = SecureRandom.getInstance("SHA1PRNG", "SUN");
            rng.setSeed(seed);
            keyGenerator.initialize(2048, rng);
            return (keyGenerator.generateKeyPair());
        }

    }
}
