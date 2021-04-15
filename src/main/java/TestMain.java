public class TestMain {
    public static void main(String[] args) {
        HashFunction hf = new HashFunction(13);
        System.out.println(hf.hashCode("hello"));
        System.out.println(hf.hashFunction(hf.hashCode("hello")));
        System.out.println(hf.loadFactor(7));
        System.out.println(hf.isPrime(3));
        System.out.println(hf.isPrime(84));
        System.out.println(hf.isPrime(101));
        System.out.println(hf.closestPrime(100));
        System.out.println(hf.closestPrime(90));
        System.out.println(hf.closestPrime(6));
    }
}
