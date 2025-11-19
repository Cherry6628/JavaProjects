package avalanche;
import java.util.Arrays;
// import 

public class SHA {
    private static final long[] K256 = {
        0x428a2f98L, 0x71374491L, 0xb5c0fbcfL, 0xe9b5dba5L, 0x3956c25bL, 0x59f111f1L, 0x923f82a4L, 0xab1c5ed5L,
        0xd807aa98L, 0x12835b01L, 0x243185beL, 0x550c7dc3L, 0x72be5d74L, 0x80deb1feL, 0x9bdc06a7L, 0xc19bf174L, 
        0xe49b69c1L, 0xefbe4786L, 0x0fc19dc6L, 0x240ca1ccL, 0x2de92c6fL, 0x4a7484aaL, 0x5cb0a9dcL, 0x76f988daL, 
        0x983e5152L, 0xa831c66dL, 0xb00327c8L, 0xbf597fc7L, 0xc6e00bf3L, 0xd5a79147L, 0x06ca6351L, 0x14292967L, 
        0x27b70a85L, 0x2e1b2138L, 0x4d2c6dfcL, 0x53380d13L, 0x650a7354L, 0x766a0abbL, 0x81c2c92eL, 0x92722c85L, 
        0xa2bfe8a1L, 0xa81a664bL, 0xc24b8b70L, 0xc76c51a3L, 0xd192e819L, 0xd6990624L, 0xf40e3585L, 0x106aa070L, 
        0x19a4c116L, 0x1e376c08L, 0x2748774cL, 0x34b0bcb5L, 0x391c0cb3L, 0x4ed8aa4aL, 0x5b9cca4fL, 0x682e6ff3L, 
        0x748f82eeL, 0x78a5636fL, 0x84c87814L, 0x8cc70208L, 0x90befffaL, 0xa4506cebL, 0xbef9a3f7L, 0xc67178f2L 
    };
    private static final long[] K512 = {
        0x428a2f98d728ae22L, 0x7137449123ef65cdL, 0xb5c0fbcfec4d3b2fL, 0xe9b5dba58189dbbcL, 
        0x3956c25bf348b538L, 0x59f111f1b605d019L, 0x923f82a4af194f9bL, 0xab1c5ed5da6d8118L, 
        0xd807aa98a3030242L, 0x12835b0145706fbeL, 0x243185be4ee4b28cL, 0x550c7dc3d5ffb4e2L, 
        0x72be5d74f27b896fL, 0x80deb1fe3b1696b1L, 0x9bdc06a725c71235L, 0xc19bf174cf692694L, 
        0xe49b69c19ef14ad2L, 0xefbe4786384f25e3L, 0x0fc19dc68b8cd5b5L, 0x240ca1cc77ac9c65L, 
        0x2de92c6f592b0275L, 0x4a7484aa6ea6e483L, 0x5cb0a9dcbd41fbd4L, 0x76f988da831153b5L, 
        0x983e5152ee66dfabL, 0xa831c66d2db43210L, 0xb00327c898fb213fL, 0xbf597fc7beef0ee4L, 
        0xc6e00bf33da88fc2L, 0xd5a79147930aa725L, 0x06ca6351e003826fL, 0x142929670a0e6e70L, 
        0x27b70a8546d22ffcL, 0x2e1b21385c26c926L, 0x4d2c6dfc5ac42aedL, 0x53380d139d95b3dfL, 
        0x650a73548baf63deL, 0x766a0abb3c77b2a8L, 0x81c2c92e47edaee6L, 0x92722c851482353bL, 
        0xa2bfe8a14cf10364L, 0xa81a664bbc423001L, 0xc24b8b70d0f89791L, 0xc76c51a30654be30L, 
        0xd192e819d6ef5218L, 0xd69906245565a910L, 0xf40e35855771202aL, 0x106aa07032bbd1b8L, 
        0x19a4c116b8d2d0c8L, 0x1e376c085141ab53L, 0x2748774cdf8eeb99L, 0x34b0bcb5e19b48a8L, 
        0x391c0cb3c5c95a63L, 0x4ed8aa4ae3418acbL, 0x5b9cca4f7763e373L, 0x682e6ff3d6b2b8a3L, 
        0x748f82ee5defb2fcL, 0x78a5636f43172f60L, 0x84c87814a1f0ab72L, 0x8cc702081a6439ecL, 
        0x90befffa23631e28L, 0xa4506cebde82bde9L, 0xbef9a3f7b2c67915L, 0xc67178f2e372532bL, 
        0xca273eceea26619cL, 0xd186b8c721c0c207L, 0xeada7dd6cde0eb1eL, 0xf57d4f7fee6ed178L, 
        0x06f067aa72176fbaL, 0x0a637dc5a2c898a6L, 0x113f9804bef90daeL, 0x1b710b35131c471bL, 
        0x28db77f523047d84L, 0x32caab7b40c72493L, 0x3c9ebe0a15c9bebcL, 0x431d67c49c100d4cL, 
        0x4cc5d4becb3e42b6L, 0x597f299cfc657e2aL, 0x5fcb6fab3ad6faecL, 0x6c44198c4a475817L 
    };

    private static final int[] H224 = {0xc1059ed8, 0x367cd507, 0x3070dd17, 0xf70e5939, 0xffc00b31, 0x68581511, 0x64f98fa7, 0xbefa4fa4}; 
    private static final int[] H256 = {0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a, 0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19}; 
    private static final long[] H384 = {0xcbbb9d5dc1059ed8L, 0x629a292a367cd507L, 0x9159015a3070dd17L, 0x152fecd8f70e5939L, 0x67332667ffc00b31L, 0x8eb44a8768581511L, 0xdb0c2e0d64f98fa7L, 0x47b5481dbefa4fa4L}; 
    private static final long[] H512 = {0x6a09e667f3bcc908L, 0xbb67ae8584caa73bL, 0x3c6ef372fe94f82bL, 0xa54ff53a5f1d36f1L, 0x510e527fade682d1L, 0x9b05688c2b3e6c1fL, 0x1f83d9abfb41bd6bL, 0x5be0cd19137e2179L}; 
    // private static final long[] H512_256 = {0x22312194fc2bf72cL, 0x9f555fa3c84c64c2L, 0x2393b86b6f53b151L, 0x963877195940eabdL, 0x96283ee2a88effe3L, 0xbe5e1e2553863992L, 0x2b0199fc2c85b8aaL, 0x0eb72ddc81c52ca2L}; 
    
    private static int rotr32(int x, int n) {
        return (x >>> n) | (x << (32 - n));
    }

    private static long rotr64(long x, int n) {
        return (x >>> n) | (x << (64 - n));
    }

    public static InternalWorkingVariableHistory hash(String str, int bits){
        if (bits!=224 && bits!=256 && bits!=384 && bits!=512){return null;}
        byte[] message = Helper.encodeToUTF8(str);
        // System.out.println("Original Data: "+str);
        int rounds=0;
        {
            long originalBitLength = (long)message.length * 8;  
            if (bits==224 || bits==256){
                byte[] padded = Helper.padData(message, 64, 8);
                padded[message.length]=(byte)0x80; 
                int lengthBytes = 8;
                for (int i=0; i<lengthBytes; i++){
                    int shift = (lengthBytes - 1 - i) * 8; 
                    padded[padded.length - lengthBytes + i] = (byte)((originalBitLength >>> shift) & 0xFF);
                }
                rounds=64;
                message = padded;
            } else if (bits==384 || bits==512){
                byte[] padded = Helper.padData(message, 128, 16);
                padded[message.length]=(byte)0x80; 
                int totalLengthBytes = 16;
                int valueLengthBytes = 8;
                for (int i = 0; i < valueLengthBytes; i++){
                    int shift = (valueLengthBytes - 1 - i) * 8;
                    padded[padded.length - totalLengthBytes + valueLengthBytes + i] = (byte)(originalBitLength >>> shift);
                } 
                rounds=80;
                message = padded;
            }
        }
        // System.out.println("After Padding: ");
        // Helper.hexDump(message, 32, true); 
        int blockSize = (bits <= 256) ? 64 : 128;
        InternalWorkingVariableHistory ilwvh = new InternalWorkingVariableHistory(null, rounds);
        if (bits <= 256) {
            int[] H = Arrays.copyOf((bits == 224 ? H224 : H256), 8);
            for (int blockIndex = 0; blockIndex < message.length / blockSize; blockIndex++) {
                int offset = blockIndex * blockSize;
                int[] W = new int[rounds];
                for (int i = 0; i < 16; i++) {
                    W[i] = Helper.bigEndianBytesToInt(message, offset + i * 4);
                } 
                for (int i = 16; i < rounds; i++) {
                    int s0 = rotr32(W[i - 15], 7) ^ rotr32(W[i - 15], 18) ^ (W[i - 15] >>> 3);
                    int s1 = rotr32(W[i - 2], 17) ^ rotr32(W[i - 2], 19) ^ (W[i - 2] >>> 10);
                    W[i] = W[i - 16] + s0 + W[i - 7] + s1;
                }
                int a = H[0], b = H[1], c = H[2], d = H[3];
                int e = H[4], f = H[5], g = H[6], h = H[7];
                for (int i = 0; i < rounds; i++) {
                    int S1 = rotr32(e, 6) ^ rotr32(e, 11) ^ rotr32(e, 25);
                    int ch = (e & f) ^ ((~e) & g);
                    int temp1 = h + S1 + ch + (int) K256[i] + W[i];
                    int S0 = rotr32(a, 2) ^ rotr32(a, 13) ^ rotr32(a, 22);
                    int maj = (a & b) ^ (a & c) ^ (b & c);
                    int temp2 = S0 + maj; 
                    h = g;
                    g = f;
                    f = e;
                    e = d + temp1;
                    d = c;
                    c = b;
                    b = a;
                    a = temp1 + temp2;
                    ilwvh.addData(new long[]{a, b, c, d, e, f, g, h});
                } 
                H[0] += a;
                H[1] += b;
                H[2] += c;
                H[3] += d;
                H[4] += e;
                H[5] += f;
                H[6] += g;
                H[7] += h;
            }
            StringBuffer result = new StringBuffer();
            if (bits == 224) {
                for (int i = 0; i < 7; i++)
                    result.append(String.format("%08x", H[i]));
            } else if (bits == 256) {
                for (int i = 0; i < 8; i++)
                    result.append(String.format("%08x", H[i]));
            }
            ilwvh.setResult(result.toString());
            return ilwvh;
        } else {
            long[] H = Arrays.copyOf((bits == 384 ? H384 : H512), 8);
            for (int blockIndex = 0; blockIndex < message.length / blockSize; blockIndex++) {
                int offset = blockIndex * blockSize;
                long[] W = new long[rounds];
                for (int i = 0; i < 16; i++) {
                    W[i] = Helper.bigEndianBytesToLong(message, offset + i * 8);
                } 
                for (int i = 16; i < rounds; i++) {
                    long s0 = rotr64(W[i - 15], 1) ^ rotr64(W[i - 15], 8) ^ (W[i - 15] >>> 7);
                    long s1 = rotr64(W[i - 2], 19) ^ rotr64(W[i - 2], 61) ^ (W[i - 2] >>> 6);
                    W[i] = (W[i - 16] + s0 + W[i - 7] + s1);
                }
                long a = H[0], b = H[1], c = H[2], d = H[3];
                long e = H[4], f = H[5], g = H[6], h = H[7];
                for (int i = 0; i < rounds; i++) {
                    long S1 = rotr64(e, 14) ^ rotr64(e, 18) ^ rotr64(e, 41);
                    long ch = (e & f) ^ ((~e) & g);
                    long temp1 = h + S1 + ch + K512[i] + W[i];
                    long S0 = rotr64(a, 28) ^ rotr64(a, 34) ^ rotr64(a, 39);
                    long maj = (a & b) ^ (a & c) ^ (b & c);
                    long temp2 = S0 + maj; 
                    h = g;
                    g = f;
                    f = e;
                    e = d + temp1;
                    d = c;
                    c = b;
                    b = a;
                    a = temp1 + temp2;
                    ilwvh.addData(new long[]{a, b, c, d, e, f, g, h});
                } 
                H[0] += a;
                H[1] += b;
                H[2] += c;
                H[3] += d;
                H[4] += e;
                H[5] += f;
                H[6] += g;
                H[7] += h;
            }
            StringBuffer result = new StringBuffer();
            if (bits == 384) {
                for (int i = 0; i < 6; i++)
                    result.append(String.format("%016x", H[i]));
            } else if (bits == 512) {
                for (int i = 0; i < 8; i++)
                    result.append(String.format("%016x", H[i]));
            }
            ilwvh.setResult(result.toString());
            return ilwvh; 
        } 
    }
}
