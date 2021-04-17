package hashTable;

import hashTable.openHashing.Node;

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
     * Compress the bigInt value so that it is in the range from 0 to max_size
     * @param key String key
     * @return return key % max_size.
     */
    public int hash (String key) {
        BigInteger bigInteger = getHashCode(key);
        return bigInteger.mod(BigInteger.valueOf(max_size)).intValue();
    }

    /**
     * A secondary hash function d(k) to resolve collisions.
     * @param key
     * @return
     */
    public int getSecondHash (String key) {
        BigInteger bigInteger = getHashCode(key);
        int q = getClosestPrime(max_size / 2 + 1);
        return q - (bigInteger.mod(BigInteger.valueOf(q)).intValue());
    }

    /**
     * Calculating the load factor
     * @param num_entries number of entries in the table
     * @return the number of entries divided by the maximum size of the table.
     */
    public double getLoadFactor (int num_entries) {
        return (double) num_entries / max_size;
    }

    /**
     * @return new size is the smallest prime number that is larger than 2*max_size.
     */
    public int getNewSize (){
        return getClosestPrime(max_size * 2);
    }

    /**
     * compute the hash code of a string,
     * using a polynomial rolling hash function
     * @param key String key
     * @return bInt BigInteger key
     */
    private BigInteger getHashCode (String key) {
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
     * Find the closest prime number.
     * @param num current int
     * @return the smallest prime number that is larger than num
     */
    private int getClosestPrime (int num) {
        int prime_below = 0;
        for (int i = num - 1; i >= 1; i--) {
            if (isPrime(i)) {
                prime_below =  i;
                break;
            }
        }
        int prime_after = prime_below + 1;
        for (int i = num; i < num + prime_below; i++) {
            if (isPrime(i)) {
                prime_after = i;
                break;
            }
        }
        return prime_after;
    }

    /**
     * a helper function to check if a number is a primer number
     * @param num integer number
     * @return true if prime, false otherwise
     */
    private boolean isPrime(int num) {
        if (num == 2 || num == 3 || num == 5 || num == 7) {
            return true;
        } else if (num % 2 == 0 || num % 3 == 0 || num % 5 == 0 || num % 7 == 0) {
            return false;
        } else return Math.ceil(Math.sqrt(num)) - Math.floor(Math.sqrt(num)) != 0;
    }
}
