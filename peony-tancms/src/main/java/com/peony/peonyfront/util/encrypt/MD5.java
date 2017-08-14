package com.peony.peonyfront.util.encrypt;

public class MD5 {
    public static String PASSWORD_ENCRYPT_PASSWORD = "password_peony0513dsj";

    static final int     S11                       = 7;

    static final int     S12                       = 12;

    static final int     S13                       = 17;

    static final int     S14                       = 22;

    static final int     S21                       = 5;

    static final int     S22                       = 9;

    static final int     S23                       = 14;

    static final int     S24                       = 20;

    static final int     S31                       = 4;

    static final int     S32                       = 11;

    static final int     S33                       = 16;

    static final int     S34                       = 23;

    static final int     S41                       = 6;

    static final int     S42                       = 10;

    static final int     S43                       = 15;

    static final int     S44                       = 21;

    static final char    Hex[]                     = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    static final byte    PADDING[]                 = { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    private long         state[];

    private long         count[];

    private byte         buffer[];

    public String        digestHexStr;

    private byte         digest[];

    public MD5() {
        state = new long[4];
        count = new long[2];
        buffer = new byte[64];
        digest = new byte[16];
        count[0] = 0L;
        count[1] = 0L;
        state[0] = 0x67452301L;
        state[1] = 0xefcdab89L;
        state[2] = 0x98badcfeL;
        state[3] = 0x10325476L;
    }

    // 返回MD5串
    public String getMD5ofStr(String s) {
        int i;
        md5Update(s.getBytes(), s.length());
        md5Final();
        digestHexStr = "";
        for (i = 0; i < 16; i++)
            digestHexStr = digestHexStr + byteHEX(digest[i]);
        return digestHexStr;
    }

    public static long b2iu(byte byte0) {
        return byte0 >= 0 ? byte0 : byte0 & 0xff;
    }

    // 字节到十六进制的ASCII码转换
    public static String byteHEX(byte byte0) {
        char ac[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char ac1[] = new char[2];
        ac1[0] = ac[byte0 >>> 4 & 0xf];
        ac1[1] = ac[byte0 & 0xf];
        String s = new String(ac1);
        return s;
    }

    public static String toMD5(String s) {
        MD5 md5 = new MD5();
        return md5.getMD5ofStr(s);
    }

    /**
     * 加密&解密
     * 
     * @param original
     * @param key
     * @return
     */
    public static byte[] Encrypt(byte[] original, int key) {
        for (int i = 0; i < original.length; i++)
            original[i] ^= key;
        return original;
    }

    /**
     * 加密方法一
     * 
     * @param original
     * @param password
     * @return
     */
    public static String Encrypt1(String original, String password) {
        byte[] orig = original.getBytes();
        byte[] pass = password.getBytes();
        int i = 0;
        int j = pass.length - 1;
        int length = (pass.length / 2) + (pass.length % 2);
        while (i < length || j > length) {
            orig = Encrypt(orig, pass[i]);
            if (i != j) {
                orig = Encrypt(orig, pass[j]);
            }
            ++i;
            --j;
        }
        return new String(orig);
    }

    /**
     * 解密方法一
     * 
     * @param original
     * @param password
     * @return
     */
    public static String Decrypt1(String original, String password) {
        byte[] orig = original.getBytes();
        byte[] pass = password.getBytes();
        int i = (pass.length / 2) - ((pass.length % 2) == 1 ? 0 : 1);
        int j = (pass.length / 2);
        while (i >= 0 || j < pass.length) {
            if (j != i) {
                orig = Encrypt(orig, pass[j]);
            }
            orig = Encrypt(orig, pass[i]);
            --i;
            ++j;
        }

        return new String(orig);
    }

    /**
     * 加密方法二
     * 
     * @param original
     * @param password
     * @return
     */
    public static String Encrypt2(String original, String password) {
        byte[] orig = original.getBytes();
        byte[] pass = password.getBytes();
        int i = 0;
        while (i < pass.length) {
            orig = Encrypt(orig, pass[i]);
            i += 2;
        }
        i = 1;
        while (i < pass.length) {
            orig = Encrypt(orig, pass[i]);
            i += 2;
        }
        return new String(orig);
    }

    /**
     * 解密方法二
     * 
     * @param original
     * @param password
     * @return
     */
    public static String Decrypt2(String original, String password) {
        byte[] orig = original.getBytes();
        byte[] pass = password.getBytes();
        int i = (pass.length % 2) == 1 ? (pass.length - 2) : (pass.length - 1);
        while (i >= 0) {
            orig = Encrypt(orig, pass[i]);
            i -= 2;
        }

        i = (pass.length % 2) == 1 ? (pass.length - 1) : (pass.length - 2);
        while (i >= 0) {
            orig = Encrypt(orig, pass[i]);
            i -= 2;
        }
        return new String(orig);
    }

    public static String EDCrypt(String original, int psw) {
        byte[] orig = original.getBytes();
        for (int i = 0; i < orig.length; i++)
            orig[i] ^= psw;
        return new String(orig);
    }

    /**
     * 将字符串编码成 Unicode
     * 
     * @param theString
     *            待转换成Unicode编码的字符串
     * @param escapeSpace
     *            是否忽略空格。
     * @return 返回转换后Unicode编码的字符串
     */
    public static String toUnicode(String theString, boolean escapeSpace) {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);

        for (int x = 0; x < len; x++) {
            char aChar = theString.charAt(x);
            // Handle common case first, selecting largest block that
            // avoids the specials below
            if ((aChar > 61) && (aChar < 127)) {
                if (aChar == '\\') {
                    outBuffer.append('\\');
                    outBuffer.append('\\');
                    continue;
                }
                outBuffer.append(aChar);
                continue;
            }
            switch (aChar) {
            case ' ':
                if (x == 0 || escapeSpace)
                    outBuffer.append('\\');
                outBuffer.append(' ');
                break;
            case '\t':
                outBuffer.append('\\');
                outBuffer.append('t');
                break;
            case '\n':
                outBuffer.append('\\');
                outBuffer.append('n');
                break;
            case '\r':
                outBuffer.append('\\');
                outBuffer.append('r');
                break;
            case '\f':
                outBuffer.append('\\');
                outBuffer.append('f');
                break;
            case '=': // Fall through
            case ':': // Fall through
            case '#': // Fall through
            case '!':
                outBuffer.append('\\');
                outBuffer.append(aChar);
                break;
            default:
                if ((aChar < 0x0020) || (aChar > 0x007e)) {
                    outBuffer.append('\\');
                    outBuffer.append('u');
                    outBuffer.append(toHex((aChar >> 12) & 0xF));
                    outBuffer.append(toHex((aChar >> 8) & 0xF));
                    outBuffer.append(toHex((aChar >> 4) & 0xF));
                    outBuffer.append(toHex(aChar & 0xF));
                } else {
                    outBuffer.append(aChar);
                }
            }
        }
        return outBuffer.toString();
    }

    /**
     * 从 Unicode 码转换成编码前的特殊字符串
     * 
     * @param in
     *            Unicode编码的字符数组
     * @param off
     *            转换的起始偏移量
     * @param len
     *            转换的字符长度
     * @param convtBuf
     *            转换的缓存字符数组
     * @return 完成转换，返回编码前的特殊字符串
     */
    public static String fromUnicode(char[] in, int off, int len, char[] convtBuf) {
        if (convtBuf.length < len) {
            int newLen = len * 2;
            if (newLen < 0) {
                newLen = Integer.MAX_VALUE;
            }
            convtBuf = new char[newLen];
        }
        char aChar;
        char[] out = convtBuf;
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = in[off++];
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++] = (char) value;
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    out[outLen++] = aChar;
                }
            } else {
                out[outLen++] = aChar;
            }
        }
        return new String(out, 0, outLen);
    }

    private long F(long l, long l1, long l2) {
        return l & l1 | ~l & l2;
    }

    private long G(long l, long l1, long l2) {
        return l & l2 | l1 & ~l2;
    }

    private long H(long l, long l1, long l2) {
        return l ^ l1 ^ l2;
    }

    private long I(long l, long l1, long l2) {
        return l1 ^ (l | ~l2);
    }

    private long FF(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
        l += F(l1, l2, l3) + l4 + l6;
        l = (int) l << (int) l5 | (int) l >>> (int) (32L - l5);
        l += l1;
        return l;
    }

    private long GG(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
        l += G(l1, l2, l3) + l4 + l6;
        l = (int) l << (int) l5 | (int) l >>> (int) (32L - l5);
        l += l1;
        return l;
    }

    private long HH(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
        l += H(l1, l2, l3) + l4 + l6;
        l = (int) l << (int) l5 | (int) l >>> (int) (32L - l5);
        l += l1;
        return l;
    }

    private long II(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
        l += I(l1, l2, l3) + l4 + l6;
        l = (int) l << (int) l5 | (int) l >>> (int) (32L - l5);
        l += l1;
        return l;
    }

    // 补位操作，abyte0为需要进行MD5加密的字符串，i为字符串长度
    private void md5Update(byte abyte0[], int i) {
        byte abyte1[] = new byte[64];
        int k = (int) (count[0] >>> 3) & 0x3f;
        if ((count[0] += i << 3) < (i << 3))
            count[1]++;
        count[1] += i >>> 29;
        int l = 64 - k;
        int j;
        if (i >= l) {
            md5Memcpy(buffer, abyte0, k, 0, l);
            md5Transform(buffer);
            for (j = l; j + 63 < i; j += 64) {
                md5Memcpy(abyte1, abyte0, 0, j, 64);
                md5Transform(abyte1);
            }
            k = 0;
        } else {
            j = 0;
        }
        md5Memcpy(buffer, abyte0, k, j, i - j);
    }

    // 最终处理，将得到的128位（16字节）MD5码存放在digest数组中
    private void md5Final() {
        byte abyte0[] = new byte[8];
        Encode(abyte0, count, 8);
        int i = (int) (count[0] >>> 3) & 0x3f;
        int j = i >= 56 ? 120 - i : 56 - i;
        md5Update(PADDING, j);
        md5Update(abyte0, 8);
        Encode(digest, state, 16);
    }

    private void md5Memcpy(byte abyte0[], byte abyte1[], int i, int j, int k) {
        for (int l = 0; l < k; l++)
            abyte0[i + l] = abyte1[j + l];
    }

    private void md5Transform(byte abyte0[]) {
        long l = state[0];
        long l1 = state[1];
        long l2 = state[2];
        long l3 = state[3];
        long al[] = new long[16];
        Decode(al, abyte0, 64);
        l = FF(l, l1, l2, l3, al[0], 7L, 0xd76aa478L);
        l3 = FF(l3, l, l1, l2, al[1], 12L, 0xe8c7b756L);
        l2 = FF(l2, l3, l, l1, al[2], 17L, 0x242070dbL);
        l1 = FF(l1, l2, l3, l, al[3], 22L, 0xc1bdceeeL);
        l = FF(l, l1, l2, l3, al[4], 7L, 0xf57c0fafL);
        l3 = FF(l3, l, l1, l2, al[5], 12L, 0x4787c62aL);
        l2 = FF(l2, l3, l, l1, al[6], 17L, 0xa8304613L);
        l1 = FF(l1, l2, l3, l, al[7], 22L, 0xfd469501L);
        l = FF(l, l1, l2, l3, al[8], 7L, 0x698098d8L);
        l3 = FF(l3, l, l1, l2, al[9], 12L, 0x8b44f7afL);
        l2 = FF(l2, l3, l, l1, al[10], 17L, 0xffff5bb1L);
        l1 = FF(l1, l2, l3, l, al[11], 22L, 0x895cd7beL);
        l = FF(l, l1, l2, l3, al[12], 7L, 0x6b901122L);
        l3 = FF(l3, l, l1, l2, al[13], 12L, 0xfd987193L);
        l2 = FF(l2, l3, l, l1, al[14], 17L, 0xa679438eL);
        l1 = FF(l1, l2, l3, l, al[15], 22L, 0x49b40821L);
        l = GG(l, l1, l2, l3, al[1], 5L, 0xf61e2562L);
        l3 = GG(l3, l, l1, l2, al[6], 9L, 0xc040b340L);
        l2 = GG(l2, l3, l, l1, al[11], 14L, 0x265e5a51L);
        l1 = GG(l1, l2, l3, l, al[0], 20L, 0xe9b6c7aaL);
        l = GG(l, l1, l2, l3, al[5], 5L, 0xd62f105dL);
        l3 = GG(l3, l, l1, l2, al[10], 9L, 0x2441453L);
        l2 = GG(l2, l3, l, l1, al[15], 14L, 0xd8a1e681L);
        l1 = GG(l1, l2, l3, l, al[4], 20L, 0xe7d3fbc8L);
        l = GG(l, l1, l2, l3, al[9], 5L, 0x21e1cde6L);
        l3 = GG(l3, l, l1, l2, al[14], 9L, 0xc33707d6L);
        l2 = GG(l2, l3, l, l1, al[3], 14L, 0xf4d50d87L);
        l1 = GG(l1, l2, l3, l, al[8], 20L, 0x455a14edL);
        l = GG(l, l1, l2, l3, al[13], 5L, 0xa9e3e905L);
        l3 = GG(l3, l, l1, l2, al[2], 9L, 0xfcefa3f8L);
        l2 = GG(l2, l3, l, l1, al[7], 14L, 0x676f02d9L);
        l1 = GG(l1, l2, l3, l, al[12], 20L, 0x8d2a4c8aL);
        l = HH(l, l1, l2, l3, al[5], 4L, 0xfffa3942L);
        l3 = HH(l3, l, l1, l2, al[8], 11L, 0x8771f681L);
        l2 = HH(l2, l3, l, l1, al[11], 16L, 0x6d9d6122L);
        l1 = HH(l1, l2, l3, l, al[14], 23L, 0xfde5380cL);
        l = HH(l, l1, l2, l3, al[1], 4L, 0xa4beea44L);
        l3 = HH(l3, l, l1, l2, al[4], 11L, 0x4bdecfa9L);
        l2 = HH(l2, l3, l, l1, al[7], 16L, 0xf6bb4b60L);
        l1 = HH(l1, l2, l3, l, al[10], 23L, 0xbebfbc70L);
        l = HH(l, l1, l2, l3, al[13], 4L, 0x289b7ec6L);
        l3 = HH(l3, l, l1, l2, al[0], 11L, 0xeaa127faL);
        l2 = HH(l2, l3, l, l1, al[3], 16L, 0xd4ef3085L);
        l1 = HH(l1, l2, l3, l, al[6], 23L, 0x4881d05L);
        l = HH(l, l1, l2, l3, al[9], 4L, 0xd9d4d039L);
        l3 = HH(l3, l, l1, l2, al[12], 11L, 0xe6db99e5L);
        l2 = HH(l2, l3, l, l1, al[15], 16L, 0x1fa27cf8L);
        l1 = HH(l1, l2, l3, l, al[2], 23L, 0xc4ac5665L);
        l = II(l, l1, l2, l3, al[0], 6L, 0xf4292244L);
        l3 = II(l3, l, l1, l2, al[7], 10L, 0x432aff97L);
        l2 = II(l2, l3, l, l1, al[14], 15L, 0xab9423a7L);
        l1 = II(l1, l2, l3, l, al[5], 21L, 0xfc93a039L);
        l = II(l, l1, l2, l3, al[12], 6L, 0x655b59c3L);
        l3 = II(l3, l, l1, l2, al[3], 10L, 0x8f0ccc92L);
        l2 = II(l2, l3, l, l1, al[10], 15L, 0xffeff47dL);
        l1 = II(l1, l2, l3, l, al[1], 21L, 0x85845dd1L);
        l = II(l, l1, l2, l3, al[8], 6L, 0x6fa87e4fL);
        l3 = II(l3, l, l1, l2, al[15], 10L, 0xfe2ce6e0L);
        l2 = II(l2, l3, l, l1, al[6], 15L, 0xa3014314L);
        l1 = II(l1, l2, l3, l, al[13], 21L, 0x4e0811a1L);
        l = II(l, l1, l2, l3, al[4], 6L, 0xf7537e82L);
        l3 = II(l3, l, l1, l2, al[11], 10L, 0xbd3af235L);
        l2 = II(l2, l3, l, l1, al[2], 15L, 0x2ad7d2bbL);
        l1 = II(l1, l2, l3, l, al[9], 21L, 0xeb86d391L);
        state[0] += l;
        state[1] += l1;
        state[2] += l2;
        state[3] += l3;
    }

    // 转换函数，将al中long型的变量输出到byte型的数组abyte0中
    // 低位字节在前，高位字节在后
    private void Encode(byte abyte0[], long al[], int i) {
        int j = 0;
        for (int k = 0; k < i; k += 4) {
            abyte0[k] = (byte) (int) (al[j] & 255L);
            abyte0[k + 1] = (byte) (int) (al[j] >>> 8 & 255L);
            abyte0[k + 2] = (byte) (int) (al[j] >>> 16 & 255L);
            abyte0[k + 3] = (byte) (int) (al[j] >>> 24 & 255L);
            j++;
        }
    }

    private void Decode(long al[], byte abyte0[], int i) {
        int j = 0;
        for (int k = 0; k < i; k += 4) {
            al[j] = b2iu(abyte0[k]) | b2iu(abyte0[k + 1]) << 8 | b2iu(abyte0[k + 2]) << 16 | b2iu(abyte0[k + 3]) << 24;
            j++;
        }
    }

    // convert to unicode format
    private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    private static char toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }

    /**
     * 密码加密
     * 
     * @param str
     * @return
     */
    public static String passwordEncrypt(String str) {
        return MD5.Encrypt2(str, MD5.toMD5(PASSWORD_ENCRYPT_PASSWORD));
    }

    /**
     * 密码解密
     * 
     * @param encrypt
     * @return
     */
    public static String passwordDecrypt(String encrypt) {
        return MD5.Decrypt2(encrypt, MD5.toMD5(PASSWORD_ENCRYPT_PASSWORD));
    }

    public static void main(String[] args) {
        System.out.print(passwordDecrypt(""));

    }
}
