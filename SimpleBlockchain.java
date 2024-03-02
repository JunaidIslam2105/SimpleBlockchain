import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
public class SimpleBlockchain
{
    public static int difficulty = 5;
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static Wallet walletA;
    public static Wallet walletB;
    public static void main(String args[]){

        walletA = new Wallet();
        walletB = new Wallet();
        blockchain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
		blockchain.get(0).mineBlock(difficulty);

		blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine block 2... ");
		blockchain.get(1).mineBlock(difficulty);

		blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine block 3... ");
		blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isChainValid(blockchain));

        System.out.println("Private and public keys:");
        System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
        System.out.println(StringUtil.getStringFromKey(walletA.publicKey));

        Transaction transaction = new Transaction(walletA.publicKey, walletB.publickey, 10, null);
        transaction.generateSignature(walletA.privateKey);

        System.out.println("Is signature verified");
        System.out.println(transaction.verifiySignature());

    }

    public static boolean isChainValid(ArrayList<Block> blockchain)
    {
        Block currentBlock;
        Block previousBlock;
        String checkHash = "0".repeat(difficulty);

        for(int i = 1; i < blockchain.size(); i++)
        {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            if(!currentBlock.hash.equals(currentBlock.calculateHash()))
            {
                System.out.println("The blockchain is invalid");
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash))
            {
                System.out.println("The blockchain is invalid");
                return false;
            }
            if(!currentBlock.hash.substring(0,difficulty).equals(checkHash))
            {
                System.out.println("This Block hasnt been mined");
                return false;
            }
        }
        return true;
    }
}