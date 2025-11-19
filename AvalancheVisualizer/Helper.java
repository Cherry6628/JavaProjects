package avalanche;

import java.math.BigInteger;

final public class Helper{
    public static void hexDump(byte[] data, int bytePerLine, boolean withSpacing){
        if (!withSpacing){
            int i=0;
            for (byte d:data){
                System.out.printf("%02x ", (d));
                i++;
                if (i%bytePerLine==0){
                    i=0;
                    System.out.println("");
                }
            }
            if (i%bytePerLine!=0){
                System.out.println("");
            }
        } else {
            // System.out.println(data.length);
            // int c = 0;
            // outer: for (int blockNumber=0; blockNumber<(int)Math.ceil(data.length/64.00); blockNumber++){
            //     for (int pieceNumber=0; pieceNumber<bytePerLine; pieceNumber++){
            //         for (int j=0; j<4; j++){
            //                 int index = 64 * blockNumber + 4 * pieceNumber + j;
            //                 if (index >= data.length){
            //                     System.out.println("");
            //                     break outer;
            //                 }
            //                 c++;
            //                 System.out.printf("%02x ",data[index]);
            //                 // System.out.printf("%s ", String.format("%8s", Integer.toBinaryString(data[index] & 0xFF)).replace(' ', '0'));
            //         }
            //         System.out.print(" ");
            //         if (pieceNumber%(bytePerLine/4)==(bytePerLine/4)-1){
            //             System.out.println("");
            //         }
            //     }
            //     System.out.println(c);
            // }
            int dataIndex = 0;
            int lineNo = 0;
            while (dataIndex < data.length) {
                for (int lineOffset = 0; lineOffset < bytePerLine; lineOffset++) {
                    
                    if (lineOffset > 0 && lineOffset % 4 == 0) {
                        System.out.print(" ");
                    }
                    
                    int currentByteIndex = dataIndex + lineOffset;
                    
                    if (currentByteIndex >= data.length) break;
                    
                    System.out.printf("%02x ", data[currentByteIndex]);
                }
                lineNo++;
                System.out.println("");
                if (lineNo%4==0){System.out.println("");}

                dataIndex += bytePerLine;
            }
            
        }

    }

    public static byte[] encodeToUTF8(String data){
        return data.getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }
    public static byte[] padData(byte[] data, int modulo, int endianLength){
        int additionalLen = ((((modulo-endianLength)-data.length)% modulo)+modulo)% modulo;
        byte[] paddedArray = new byte[data.length+additionalLen+endianLength];
        for(int i=0; i<data.length;i++){
            paddedArray[i]=data[i];
        }
        return paddedArray;
    }
    public static int bigEndianBytesToInt(byte[] data, int offset) {
        return ((data[offset] & 0xFF) << 24)
            | ((data[offset + 1] & 0xFF) << 16)
            | ((data[offset + 2] & 0xFF) << 8)
            | ((data[offset + 3] & 0xFF));
    }
    public static int littleEndianBytesToInt(byte[] data, int offset) {
        return ((data[offset] & 0xFF))
            | ((data[offset + 1] & 0xFF) << 8)
            | ((data[offset + 2] & 0xFF) << 16)
            | ((data[offset + 3] & 0xFF) << 24);
    }
    public static long bigEndianBytesToLong(byte[] data, int offset) {
        long val = 0;
        for (int i = 0; i < 8; i++) {
            val = (val << 8) | (data[offset + i] & 0xFF);
        }
        return val;
    }
    public static StringBuffer toBinary(long num, int minLength){
        StringBuffer result = new StringBuffer(minLength);
        // System.out.println(result.length());
        String data = Long.toBinaryString(num);
        for (int i=0; i<minLength-data.length(); i++){
            result.append("0");
        }
        result.append(data);
        return result;
    }
    public static StringBuffer toHexaDec(long num, int minLength){
        StringBuffer result = new StringBuffer(minLength);

        result.append(String.format("%0"+minLength+"x", num));
        return result;
    }
    public static int hammingDistance(long x, long y){
        if (x==y)return 0;
        // int c=0;
        // for(int i=63; i>=0; i--)if (((x>>>i) & 1l)!=((y>>>i) & 1l))c++;
        // return c;
        return Long.bitCount(x^y);
    }
    public static int hammingDistance(String x, String y, int radix) {
        BigInteger a = new BigInteger(x, radix);
        BigInteger b = new BigInteger(y, radix);
        return a.xor(b).bitCount();
    }
    public static double hammingDistanceInPercent(long x, long y){
        return hammingDistance(x, y)/0.64;
    }
    public static double hammingDistanceInPercent(String x, String y, int radix){
        BigInteger a = new BigInteger(x, radix);
        BigInteger b = new BigInteger(y, radix);
        int totalBits = Math.max(a.bitLength(), b.bitLength());
        int distance = a.xor(b).bitCount();
        return (distance * 100.0) / totalBits;
    }
    
    // public static int toBytes(int bits){return bits/8;}
    // public static int toBits(int bytes){return bytes*8;}

    // public static void main(String[]args){
    //     System.out.println(hammingDistance(0, -14));
    // }

    public static boolean hasOnlyValidCharacters(String str, String validChars){
        for (char c: str.toCharArray()){
            if(str.indexOf(c)==-1)return false;
        }
        return true;
    }
}
