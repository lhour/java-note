package desige24.StringBuilder;

public class Client {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        final int count = 260;
        for (int i = 0; i < count; i++) {
            sb.append((char) (i));
        }
        System.out.println(sb.toString());
    }
}
