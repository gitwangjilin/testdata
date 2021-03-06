package com.wjl.testdata.sm2;


import java.math.BigInteger;


/**
 * @author 作者 :董学飞
 * @version 创建时间：2013-3-7 上午9:11:00
 */
public class SM3Hash {
    /*
     * 常量iv;
     */
    private static final byte[] iv = new BigInteger(
            "7380166f4914b2b9172442d7da8a0600a96f30bc163138aae38dee4db0fb0e4e",
            16).toByteArray();

    /*
     * 初始化Tj
     */
    private static int[] Tj = new int[64];

    static {
        for (int i = 0; i < 16; i++) {
            Tj[i] = 0x79cc4519;
        }
        for (int i = 16; i < 64; i++) {
            Tj[i] = 0x7a879d8a;
        }
    }

    /*
     * 布尔函数 定义 FFj,GGj
     */
    private static int FFj(int x, int y, int z, int j) {
        int temp = 0;
        if (j >= 0 && j <= 15) {
            temp = x ^ y ^ z;
        } else {
            if (j >= 16 && j <= 63) {
                temp = (x & y) | (x & z) | (y & z);
            } else {
                System.out.println("入参j超出范围[0,63]");
            }
        }
        return temp;
    }

    private static int GGj(int x, int y, int z, int j) {
        int temp = 0;
        if (j >= 0 && j <= 15) {
            temp = x ^ y ^ z;
        } else {
            if (j >= 16 && j <= 64) {
                temp = (x & y) | (~x & z);
            } else {
                System.out.println("入参j超出范围[0,63]");
            }
        }
        return temp;
    }

    /*
     *  置换函数P0,P1
     */
    private static int P0(int X) {
        int t = X ^ leftRotate(X, 9) ^ leftRotate(X, 17);
        return t;
    }

    private static int P1(int X) {
        int t = X ^ leftRotate(X, 15) ^ leftRotate(X, 23);
        return t;
    }

    /*
     *  循环左移函数leftRotate
     */
    private static int leftRotate(int x, int n) {
        return ((x << n) | (x >>> (32 - n)));
    }


    private SM3Hash(byte[] msg) {
        msg = padding(msg);
        msg = iter(msg);
    }

    /*
     *  msg填充函数
     */
    private static byte[] padding(byte[] msg) {
        int l = (msg.length) * 8;
        int k = 0;
        int i = 0;
        for (i = 0; ; i++) {
            k = 448 + 512 * i - l;
            if (k > 0) {
                break;
            }
        }
        int count = ((l + k + 64) / 512); // count为分组数量;
        byte[] out = new byte[count * 64]; // (count)*512/8;
        byte[] add = new byte[(k / 8)];
        add[0] = (byte) 0x80;
        byte[] l_len = new byte[8];
        byte[] l_temp = Util.intToByte(l);
        System.arraycopy(l_temp, 0, l_len, l_len.length - 4, l_temp.length);
        System.arraycopy(msg, 0, out, 0, msg.length);
        System.arraycopy(add, 0, out, msg.length, add.length);
        System.arraycopy(l_len, 0, out, (out.length - 8), l_len.length);
        return out;
    }

    /*
     *  iter分组迭代函数
     */
    private static byte[] iter(byte[] msg) {
        byte[] v = new byte[64];
        byte[] b = new byte[64];

        int n = msg.length / 64;

        System.arraycopy(msg, 0, b, 0, 64);
        v = CF(iv, b);

        if (n > 1) {
            for (int i = 1; i < n; i++) {
                System.arraycopy(msg, 64 * i, b, 0, 64);
                v = CF(v, b);
            }
        }
        return v;
    }


    /*
     * CF压缩函数
     */
    private static byte[] CF(byte[] vv, byte[] b) {
        // TODO Auto-generated method stub
        int[] w = new int[68];
        int[] w1 = new int[64];
        int[] v = new int[8];
        byte[] temp = new byte[4];
        //byte型vv转int[]
        for (int i = 0; i < 8; i++) {

            System.arraycopy(vv, 4 * i, temp, 0, 4);
            v[i] = Util.bigBytesToInt(temp);
        }
        //byte型b转int[] w,w1;
        for (int i = 0; i < 16; i++) {

            System.arraycopy(b, 4 * i, temp, 0, 4);
            w[i] = Util.bigBytesToInt(temp);
        }
        for (int j = 16; j < 68; j++) {
            w[j] = P1(w[j - 16] ^ w[j - 9] ^ leftRotate(w[j - 3], 15))
                    ^ leftRotate(w[j - 13], 7) ^ w[j - 6];
        }
        for (int j = 0; j < 64; j++) {
            w1[j] = w[j] ^ w[j + 4];
        }
        int A, B, C, D, E, F, G, H;
        int SS1, SS2, TT1, TT2;
        A = v[0];
        B = v[1];
        C = v[2];
        D = v[3];
        E = v[4];
        F = v[5];
        G = v[6];
        H = v[7];
        for (int i = 0; i < 64; i++) {


            SS1 = (leftRotate(A, 12) + E + leftRotate(Tj[i], i));
            SS1 = leftRotate(SS1, 7);
            SS2 = SS1 ^ leftRotate(A, 12);
            TT1 = FFj(A, B, C, i) + D + SS2 + w1[i];
            TT2 = GGj(E, F, G, i) + H + SS1 + w[i];
            D = C;
            C = leftRotate(B, 9);
            B = A;
            A = TT1;
            H = G;
            G = leftRotate(F, 19);
            F = E;
            E = P0(TT2);
        }

        StringBuffer ABCDEFGH = new StringBuffer();

        int[] V = new int[8];
        V[0] = A;
        V[1] = B;
        V[2] = C;
        V[3] = D;
        V[4] = E;
        V[5] = F;
        V[6] = G;
        V[7] = H;
        for (int j = 0; j < 8; j++) {
            V[j] = V[j] ^ v[j];
        }

        String[] strResult = new String[8];

        for (int j = 0; j < 8; j++) {
            strResult[j] = Integer.toHexString(V[j]);
            if (strResult[j].length() == 7) {
                strResult[j] = "0" + strResult[j];
            }
            if (strResult[j].length() == 6) {
                strResult[j] = "00" + strResult[j];
            }
            if (strResult[j].length() == 5) {
                strResult[j] = "000" + strResult[j];
            }
            if (strResult[j].length() == 4) {
                strResult[j] = "0000" + strResult[j];
            }
            if (strResult[j].length() == 3) {
                strResult[j] = "00000" + strResult[j];
            }
            if (strResult[j].length() == 2) {
                strResult[j] = "000000" + strResult[j];
            }
            if (strResult[j].length() == 1) {
                strResult[j] = "0000000" + strResult[j];
            }
            if (strResult[j].length() == 0) {
                strResult[j] = "00000000" + strResult[j];
            }
        }

        ABCDEFGH.append(strResult[0]).append(strResult[1])
                .append(strResult[2]).append(strResult[3])
                .append(strResult[4]).append(strResult[5])
                .append(strResult[6]).append(strResult[7]);


        return Util.StringToByteArray(ABCDEFGH.toString());

    }

    /**
     * 做SM3摘要运算接口
     *
     * @param msg 要做哈希运算的源数据
     * @return 哈希值
     */
    public static byte[] GetHashValue(byte[] msg) {
        msg = padding(msg);
        msg = iter(msg);
        return msg;
    }
}
