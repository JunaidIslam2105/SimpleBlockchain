import java.security.*;
import java.security.MessageDigest;
import java.util.Base64;

public class StringUtil
{
    public static String applySHA256(String input)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte hash[] = digest.digest(input.getBytes("UTF-8"));

            StringBuffer hexString = new StringBuffer();

            for(int i = 0; i < hash.length; i++)
            {
                String hex = Integer.toString(0xff & hash[i]);
                if(hex.length() == 1)
                hexString.append("0");
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    public static byte[] applyECDSASig(PrivateKey privateKey, String input)
    {
        Signature dsa;
        byte[] output = new byte[0];

        try
        {
            dsa = Signature.getInstance("ECDSA","BC");
            dsa.initSign(privateKey);
            byte[] strByte = input.getBytes();
            dsa.update(strByte);
            byte[] realSig = dsa.sign();
            output = realSig;

            return output;
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature)
    {
        try
        {
            Signature ecdsaVerify = Signature.getInstance("ECDSA","BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    public static String getStringFromKey(Key key)
    {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
