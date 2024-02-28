import java.util.Date;


public class Block
{
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash) // constructor
    {
         this.data = data;
         this.previousHash = previousHash;
         this.timeStamp = new Date().getTime();
         this.hash = calculateHash();
    }
    public String calculateHash()
    {
        return StringUtil.applySHA256(Integer.toString(nonce)+previousHash+data+Long.toString(timeStamp));
    }
    public void mineBlock(int difficulty)
    {
        String target = "0".repeat(difficulty);

        while(!hash.substring(0,difficulty).equals(target))
        {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined: !!");
    }
}
