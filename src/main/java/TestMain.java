public class TestMain {
    public static void main(String[] args) {
        HashFunction hf = new HashFunction(13);
        System.out.println(hf.hashCode("hello"));
        System.out.println(hf.hashFunction(hf.hashCode("hello")));
        System.out.println(hf.loadFactor(7));
    }
}
