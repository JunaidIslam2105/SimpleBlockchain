
import java.util.*;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
   public PrivateKey privateKey;
   public PublicKey publicKey;

   public Wallet()
   {
       generateKeyPair();
   }
   public void generateKeyPair()
   {
       try {
           KeyPairGenerator keygen = keyPairGenerator.getInstance("ECDSA","BC");
           SecureRandom random = SecureRandom.getInstance(SHA1PRNG);
           ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

           keygen.initialize((ecSpec,random));
           keyPair keyPair = keygen.generateKeyPair();

           privateKey = keyPair.getPrivate();
           publicKey = keyPair.getPublic();


       }catch(Exception e) {
           throw new RuntimeException(e);
       }
   }
}
