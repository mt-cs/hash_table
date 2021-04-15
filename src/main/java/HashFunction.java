import java.math.BigInteger;

public class HashFunction {
    int max_size;

    /**
     * constructor
     * @param max The maximum size of a hash table
     */
    public HashFunction (int max) {
        max_size = max;
    }

    /**
     * compute the hash code of a string,
     * using a polynomial rolling hash function
     * @param key String key
     * @return bInt BigInteger key
     */
    public BigInteger hashCode (String key) {
        BigInteger a = BigInteger.valueOf(33);
        char c;
        BigInteger bInt = BigInteger.valueOf(0);
        for (int i = 0; i < key.length(); i++)
        {
            c = key.charAt(i);
            bInt = BigInteger.valueOf(c).add(bInt.multiply(a));
        }
        return bInt;
    }

    /**
     * Compress the bigInt value so that it is in the range from 0 to max_size
     * @param bigInteger hash code of a string
     * @return return key % max_size.
     */
    public int hashFunction (BigInteger bigInteger) {
        return bigInteger.mod(BigInteger.valueOf(max_size)).intValue();
    }

    /**
     * Calculating the load factor
     * @param num_entries number of entries in the table
     * @return the number of entries divided by the maximum size of the table.
     */
    public double loadFactor (int num_entries) {
        return (double) num_entries / max_size;
    }

}
