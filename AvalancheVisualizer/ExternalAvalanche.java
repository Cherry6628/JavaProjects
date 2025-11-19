package avalanche;

// import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
// import java.util.Arrays;
import java.util.Random;

final public class ExternalAvalanche extends InternalAvalanche{
    public static long flipBit(long n, int bitIndexToFlip){
        // System.out.println(n);
        long mask = 1<<bitIndexToFlip;
        return n^mask;
    }
    // public static long flipBit(String hexadec, int bitIndexToFlip)
    // throws NumberFormatException{
    //     return flipBit(Long.parseLong(hexadec, 16), bitIndexToFlip);
    // }
    public static int bitLength(long n) {return (int)Math.floor(Math.log10(n)/Math.log10(2))+1;}
    public static int bitLength(String hexadec) {return hexadec.length() * 4;}
    public static int bitLengthOfNormalString(String str){
        return Helper.encodeToUTF8(str).length*8;
    }
    // public static void main(String[] args){
    //     System.out.println(flipBit("abcdefabcdef", 32));
    // }
    // public static double findHammingDistanceInPercent(long num, int bitIndexToFlip){
    //     return Helper.hammingDistanceInPercent(num, flipBit(num, bitIndexToFlip));
    // }
    public static String flipString(String input, int bit){
        byte[] arr = Helper.encodeToUTF8(input);
        // System.out.println(Arrays.toString(arr));
        int index = arr.length-1-(bit/8);
        if (index>=0)arr[index]=(byte)flipBit(arr[index],bit%8);
        // System.out.println(Arrays.toString(arr));
        String flipped = new String(arr, StandardCharsets.UTF_8);
        // System.out.println(flipped);
        return flipped;
    }

    private static String bar(double percent, int length) {
        return percentageColor(percent)+"█".repeat((int)(percent*length/100))+("░".repeat(length-(int)(percent*length/100)))+" - "+formatter.format(percent*0.01)+zeroCode;
    }
    // public static void main(String[]args){
    //     // showStrictAvalancheCriterion("", 3);
    //     String s = "Hhhello World OMGGGGGGGGGGafasdfasdfasdfasfdasdfasfdasdfasdf67uioyty6786578976546kjhgjokjhgljhgjkjhadsfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasasdfG".repeat(100);
    //     // int bit = 50;
    //     // showMD5ExternalAvalanche(s, bit, true);
    //     // showSHAExternalAvalanche(s, 224, bit, true);
    //     // showSHAExternalAvalanche(s, 256, bit, true);
    //     // showSHAExternalAvalanche(s, 384, bit, true);
    //     // showSHAExternalAvalanche(s, 512, bit, true);
    //     boolean shallPrint = false;
    //     // runSACTestWithSHA(s, 224, shallPrint);
    //     runRandomMD5ExternalAvalancheTest(s, 10000, shallPrint);
    // }

    
    public static double showMD5ExternalAvalanche(String input, int bitToFlip, boolean displayLog, boolean shallPrint) {
        String flipped = flipString(input, bitToFlip);
        String hash1 = MD5.hash(input).result();
        String hash2 = MD5.hash(flipped).result();

        double percent = Helper.hammingDistanceInPercent(hash1, hash2, 16);


        StringBuffer visual = new StringBuffer();
        if(displayLog)for (int i = 0; i < hash1.length(); i++) {
            visual.append(hash1.charAt(i) == hash2.charAt(i) ? "\033[91m█\033[0m" : "\033[92m░\033[0m");
        }
        if(displayLog)System.out.println("Input               : " + input);
        if(shallPrint)System.out.println("Flipped String      : " + flipped);

        if(displayLog)System.out.print("Hash A             : ");

        if(displayLog)for (int i=0; i<hash1.length(); i++){
            if (hash1.charAt(i)==hash2.charAt(i)){
                System.out.print("\033[91m"+hash1.charAt(i));
            }
            else System.out.print("\033[92m"+hash1.charAt(i));
        }
        if(displayLog)System.out.println("\033[0m");

        if(displayLog)System.out.print("Hash B             : ");

        if(displayLog)for (int i=0; i<hash2.length(); i++){
            if (hash2.charAt(i)==hash1.charAt(i)){
                System.out.print("\033[91m"+hash2.charAt(i));
            }
            else System.out.print("\033[92m"+hash2.charAt(i));
        }
        if(displayLog)System.out.println("\033[0m");
        if(shallPrint)if(!displayLog)System.out.println("Flipped String Hash: "+hash2);
        int ham = Helper.hammingDistance(hash1, hash2, 16);
        if(displayLog)System.out.println("Diff               : " + visual);
        if(displayLog)System.out.println("Hamming Distance   : "+ham);
        if(displayLog)System.out.printf("Changed bits       : %d / %d (%.2f%%)%n", ham, Math.max(hash1.length(), hash2.length())*4, percent);
        if(shallPrint)System.out.println("Avalanche Strength : " + bar(percent, 20));
        if(shallPrint)System.out.println("");
        return percent;
    }
    public static double showSHAExternalAvalanche(String input, int bits, int bitToFlip, boolean displayLog, boolean shallPrint) {
        String flipped = flipString(input, bitToFlip);
        String hash1 = SHA.hash(input, bits).result();
        String hash2 = SHA.hash(flipped, bits).result();

        double percent = Helper.hammingDistanceInPercent(hash1, hash2, 16);


        StringBuffer visual = new StringBuffer();
        if(displayLog)for (int i = 0; i < hash1.length(); i++) {
            visual.append(hash1.charAt(i) == hash2.charAt(i) ? "\033[91m█\033[0m" : "\033[92m░\033[0m");
        }
        if(displayLog)System.out.println("Input               : " + input);
        if(shallPrint)System.out.println("Flipped String      : " + flipped);

        if(displayLog)System.out.print("Hash A             : ");

        if(displayLog)for (int i=0; i<hash1.length(); i++){
            if (hash1.charAt(i)==hash2.charAt(i)){
                System.out.print("\033[91m"+hash1.charAt(i));
            }
            else System.out.print("\033[92m"+hash1.charAt(i));
        }
        if(displayLog)System.out.println("\033[0m");

        if(displayLog)System.out.print("Hash B             : ");

        if(displayLog)for (int i=0; i<hash2.length(); i++){
            if (hash2.charAt(i)==hash1.charAt(i)){
                System.out.print("\033[91m"+hash2.charAt(i));
            }
            else System.out.print("\033[92m"+hash2.charAt(i));
        }
        if(displayLog)System.out.println("\033[0m");
        if(shallPrint)if(!displayLog)System.out.println("Flipped String Hash: "+hash2);
        int ham = Helper.hammingDistance(hash1, hash2, 16);
        if(displayLog)System.out.println("Diff               : " + visual);
        if(displayLog)System.out.println("Hamming Distance   : "+ham);
        if(displayLog)System.out.printf("Changed bits       : %d / %d (%.2f%%)%n", ham, Math.max(hash1.length(), hash2.length())*4, percent);
        if(shallPrint)System.out.println("Avalanche Strength : " + bar(percent, 20));
        if(shallPrint)System.out.println("");
        return percent;
    }





    public static void runRandomMD5ExternalAvalancheTest(String input, int times, boolean shallPrint){
        double total =0.0;
        Random randObj = new Random();
        int bitLength = bitLengthOfNormalString(input);
        for (int i=0;i<times; i++){
            if(shallPrint)System.out.println("Random Test "+(i+1)+": ");
            total+=showMD5ExternalAvalanche(input, randObj.nextInt(bitLength), false, shallPrint);
        }
        System.out.println("Average Avalanche: "+percentageColor(total/times)+formatter.format(total*0.01/times)+zeroCode);
    }
    public static void runRandomSHAExternalAvalancheTest(String input, int times, int bits, boolean shallPrint){
        double total =0.0;
        Random randObj = new Random();
        int bitLength = bitLengthOfNormalString(input);
        for (int i=0;i<times; i++){
            if(shallPrint)System.out.println("Random Test "+(i+1)+": ");
            total+=showSHAExternalAvalanche(input, bits, randObj.nextInt(bitLength), false, shallPrint);
        }
        System.out.println("Average Avalanche: "+percentageColor(total/times)+formatter.format(total*0.01/times)+zeroCode);
    }

    public static void testMD5ExternalAvalancheWithDefiniteBit(String input, int bitToFlip){
        showMD5ExternalAvalanche(input, bitToFlip, true, true);
    }
    public static void testSHAExternalAvalancheWithDefiniteBit(String input, int bits, int bitToFlip){
        showSHAExternalAvalanche(input, bits, bitToFlip, true, true);
    }
    
    public static void runSACTestWithMD5(String input, boolean shallPrint){
        if (!shallPrint)System.out.println("Running SAC Test with MD5... Please Wait");
        int bitLength = bitLengthOfNormalString(input);
        double total=0.0;
        int c=0;
        long t = System.currentTimeMillis();
        for(int i=0; i<bitLength; i++){
            total+=showMD5ExternalAvalanche(input, i, false, shallPrint);
            c++;
        }
        if(!shallPrint)System.out.println(c+" Tests Completed within "+((System.currentTimeMillis()-t)/1000.0f)+" seconds.");
        if(!shallPrint)System.out.println("SAC Test Completed.");
        System.out.println("Average Avalanche: "+percentageColor(total/bitLength)+formatter.format(total*0.01/bitLength)+zeroCode);
        

    }
    public static void runSACTestWithSHA(String input, int bits, boolean shallPrint){
        if (!shallPrint)System.out.println("Running SAC Test with SHA... Please Wait");
        int bitLength = bitLengthOfNormalString(input);
        double total=0.0;
        int c=0;
        long t = System.currentTimeMillis();
        for(int i=0; i<bitLength; i++){
            total+=showSHAExternalAvalanche(input, bits, i, false, shallPrint);
            c++;
        }
        if(!shallPrint)System.out.println(c+" Tests Completed within "+((System.currentTimeMillis()-t)/1000.0f)+" seconds.");
        if(!shallPrint)System.out.println("SAC Test Completed.");
        System.out.println("Average Avalanche: "+percentageColor(total/bitLength)+formatter.format(total*0.01/bitLength)+zeroCode);
        

    }
    
    public static double compareStringMD5Hashes(String input1, String input2){
        String hash1 = MD5.hash(input1).result();
        String hash2 = MD5.hash(input2).result();

        double percent = Helper.hammingDistanceInPercent(hash1, hash2, 16);


        StringBuffer visual = new StringBuffer();
        for (int i = 0; i < hash1.length(); i++) {
            visual.append(hash1.charAt(i) == hash2.charAt(i) ? "\033[91m█\033[0m" : "\033[92m░\033[0m");
        }
        System.out.println("Input A             : " + input1);
        System.out.println("Input B             : " + input2);

        System.out.print("Hash A             : ");

        for (int i=0; i<hash1.length(); i++){
            if (hash1.charAt(i)==hash2.charAt(i)){
                System.out.print("\033[91m"+hash1.charAt(i));
            }
            else System.out.print("\033[92m"+hash1.charAt(i));
        }
        System.out.println("\033[0m");

        System.out.print("Hash B             : ");

        for (int i=0; i<hash2.length(); i++){
            if (hash2.charAt(i)==hash1.charAt(i)){
                System.out.print("\033[91m"+hash2.charAt(i));
            }
            else System.out.print("\033[92m"+hash2.charAt(i));
        }
        System.out.println("\033[0m");
        int ham = Helper.hammingDistance(hash1, hash2, 16);
        System.out.println("Diff               : " + visual);
        System.out.println("Hamming Distance   : "+ham);
        System.out.printf("Changed bits       : %d / %d (%.2f%%)%n", ham, Math.max(hash1.length(), hash2.length())*4, percent);
        System.out.println("Avalanche Strength : " + bar(percent, 20));
        System.out.println("");
        return percent;
    }
    public static double compareStringSHAHashes(String input1, String input2, int bits){
        String hash1 = SHA.hash(input1, bits).result();
        String hash2 = SHA.hash(input2, bits).result();

        double percent = Helper.hammingDistanceInPercent(hash1, hash2, 16);


        StringBuffer visual = new StringBuffer();
        for (int i = 0; i < hash1.length(); i++) {
            visual.append(hash1.charAt(i) == hash2.charAt(i) ? "\033[91m█\033[0m" : "\033[92m░\033[0m");
        }
        System.out.println("Input A             : " + input1);
        System.out.println("Input B             : " + input2);

        System.out.print("Hash A             : ");

        for (int i=0; i<hash1.length(); i++){
            if (hash1.charAt(i)==hash2.charAt(i)){
                System.out.print("\033[91m"+hash1.charAt(i));
            }
            else System.out.print("\033[92m"+hash1.charAt(i));
        }
        System.out.println("\033[0m");

        System.out.print("Hash B             : ");

        for (int i=0; i<hash2.length(); i++){
            if (hash2.charAt(i)==hash1.charAt(i)){
                System.out.print("\033[91m"+hash2.charAt(i));
            }
            else System.out.print("\033[92m"+hash2.charAt(i));
        }
        System.out.println("\033[0m");
        int ham = Helper.hammingDistance(hash1, hash2, 16);
        System.out.println("Diff               : " + visual);
        System.out.println("Hamming Distance   : "+ham);
        System.out.printf("Changed bits       : %d / %d (%.2f%%)%n", ham, Math.max(hash1.length(), hash2.length())*4, percent);
        System.out.println("Avalanche Strength : " + bar(percent, 20));
        System.out.println("");
        return percent;
    }
}

