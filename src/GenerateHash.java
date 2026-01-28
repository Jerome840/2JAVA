import org.mindrot.jbcrypt.BCrypt;

public class GenerateHash {
    public static void main(String[] args) {
        String hash = BCrypt.hashpw("Admin.01", BCrypt.gensalt());
        System.out.println(hash);
    }
}
