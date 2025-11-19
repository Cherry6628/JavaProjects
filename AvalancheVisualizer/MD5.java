package avalanche;

final public class MD5{
    final static int F(int X, int Y, int Z) {return (X & Y) | ((~X) & Z);}
    final static int G(int X, int Y, int Z) {return (X & Z) | (Y & ~Z);}
    final static int H(int X, int Y, int Z) {return X ^ Y ^ Z;}
    final static int I(int X, int Y, int Z) {return Y ^ (X | ~Z);}

    final private static int[] s = {
            7,12,17,22, 7,12,17,22, 7,12,17,22, 7,12,17,22,
            5,9,14,20, 5,9,14,20, 5,9,14,20, 5,9,14,20,
            4,11,16,23, 4,11,16,23, 4,11,16,23, 4,11,16,23,
            6,10,15,21, 6,10,15,21, 6,10,15,21, 6,10,15,21
        };
    private static int[] K = new int[64];
    static{
        for (int i = 0; i < 64; i++) {
            long v = (long) (Math.floor(Math.abs(Math.sin(i + 1)) * (1L << 32)));
            K[i] = (int) (v & 0xFFFFFFFFL);
        }
    }


    static InternalWorkingVariableHistory hash(String str){
        
        // final int 
        // a0 = 0x67452301,
        // b0 = 0xEFCDAB89,
        // c0 = 0x98BADCFE,
        // // d0 = 0x10325476;
        // final int roundCount = 4;
        // for (int i=0; i<64; i++){
        //     K[i] = (int)Math.floor(Math.abs(Math.sin(i+1)) * Math.pow(2, 32));
        // }
        // System.out.println(Arrays.toString(k));

        InternalWorkingVariableHistory ilwvh = new InternalWorkingVariableHistory(null, 64);

        byte[] message = Helper.encodeToUTF8(str);

        // System.out.println("Bytes: ");
        // Helper.hexDump(message, 16, true);
        // Helper.hexDump(message, false);

        // System.out.println("Bytes after padding: ");
        // {
        //     // byte[] padded = new byte[56+64*(int)Math.floor(message.length/56.0) + 8];
        //     byte[] padded = new byte[((message.length + 8) / 64 + 1) * 64];
        //     for (int i=0; i<message.length;i++){
        //         padded[i]=message[i];
        //     }
        //     padded[message.length]=(byte)0x80;
        //     for (int i=0; i<8; i++){
        //         padded[padded.length-8+i] = (byte)(((long)(message.length*8) >>> (8*i))&0xFF);
        //     }
        //     message=padded;
        // }
        {
            byte[] padded = Helper.padData(message, 64, 8);
            padded[message.length]=(byte)0x80;
            for (int i=0; i<8; i++){
                padded[padded.length-8+i] = (byte)(((long)(message.length*8) >>> (8*i))&0xFF);
            }
            message = padded;
        }
        
        // Helper.hexDump(message, 16, true);
        // System.out.println("\n\n");
        // byte[][] eachRounds = new byte[4][message.length];
        // System.out.println(Arrays.deepToString(eachRounds));
        int 
        a0 = 0x67452301,
        b0 = 0xEFCDAB89,
        c0 = 0x98BADCFE,
        d0 = 0x10325476;
        for (int blockIndex = 0; blockIndex < message.length / 64; blockIndex++) {
            int[] M = new int[16];
            for (int j=0; j<16; j++){
                int start = blockIndex * 64 + j*4;
                // M[j] = (((message[start] & 0xFF)) |
                //     ((message[start+1] & 0xFF) << 8) |
                //     ((message[start+2] & 0xFF) << 16) |
                //     ((message[start+3] & 0xFF) << 24));
                M[j] = Helper.littleEndianBytesToInt(message, start);
            }
            // for (int j=0; j<M.length; j++){
            //     String temp = Integer.toBinaryString(M[j]);
            //     System.out.printf("%32s     ","0".repeat(32-temp.length())+temp);
            //     // System.out.println("");
            //     if (j%4==3)System.out.println("");
            // }

            int A = a0;
            int B = b0;
            int C = c0;
            int D = d0;

            for (int j=0; j<64; j++){
                int Fval, g;

                if (j < 16) {
                    Fval = F(B, C, D);
                    g = j;
                } else if (j < 32) {
                    Fval = G(B, C, D);
                    g = (5*j + 1) % 16;
                } else if (j < 48) {
                    Fval = H(B, C, D);
                    g = (3*j + 5) % 16;
                } else {
                    Fval = I(B, C, D);
                    g = (7*j) % 16;
                }

                int temp = D;
                D = C;
                C = B;
                B = B + Integer.rotateLeft(A + Fval + K[j] + M[g], s[j]);
                A = temp;
                {

                    byte[] digest = new byte[16];
                    int[] parts = { a0, b0, c0, d0 };
                    for (int i = 0; i < 4; i++) {
                        int n = parts[i];
                        digest[i*4]     = (byte)(n & 0xFF);
                        digest[i*4 + 1] = (byte)((n >>> 8) & 0xFF);
                        digest[i*4 + 2] = (byte)((n >>> 16) & 0xFF);
                        digest[i*4 + 3] = (byte)((n >>> 24) & 0xFF);
                        
                    }
                    // Helper.hexDump(digest, 16, false);
                }
                    ilwvh.addData(new long[]{A, B, C, D});
            }

            a0 += A;
            b0 += B;
            c0 += C;
            d0 += D;
            
        }


        byte[] digest = new byte[16];
        int[] parts = { a0, b0, c0, d0 };
        for (int i = 0; i < 4; i++) {
            int n = parts[i];
            digest[i*4]     = (byte)(n & 0xFF);
            digest[i*4 + 1] = (byte)((n >>> 8) & 0xFF);
            digest[i*4 + 2] = (byte)((n >>> 16) & 0xFF);
            digest[i*4 + 3] = (byte)((n >>> 24) & 0xFF);
        }

        StringBuffer hex = new StringBuffer();
        // System.out.println("Final Bytes: ");
        // Helper.hexDump(digest, 16, true);
        for (byte b : digest) hex.append(String.format("%02x", b & 0xFF));
        ilwvh.setResult(hex.toString());
        return ilwvh;
    }
}
