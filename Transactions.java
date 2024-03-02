import java.security.*;
import java.util.ArrayList;
public class Transactions
{
    public String TransactionID;
    public publicKey sender;
    public publicKey receiver;
    public float value;
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    public Transaction(PublicKey from, PublicKey to, float value,  ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.receiver = to;
        this.value = value;
        this.inputs = inputs;
    }
    private String calculateHash()
    {
        sequence++;
        return StringUtil.applySHA256(StringUtil.getStringFromKey(sender)+
                StringUtil.getStringFromKey(receiver)+float.toString(value)+sequence);
    }
    public void generateSignature(PrivateKey privateKey)
    {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(receiver)
                + float.toString(value);
        signature = StringUtil.applyECDSASig(privateKey,data);
    }
    public boolean verifySignature()
    {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(receiver)
                + float.toString(value);
        return StringUtil.verifyECDSASig(sender, data, signature);
    }
}