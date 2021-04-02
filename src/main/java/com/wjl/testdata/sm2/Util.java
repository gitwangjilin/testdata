package com.wjl.testdata.sm2;



public class Util {

    /*
     * 将十六进制字符串转成byte数组
     */
    public static byte[] StringToByteArray(String s) {
		byte[] b = new byte[s.length() / 2];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
		}
		return b;
	}

	public static byte[] intToByte(int i) {
        byte[] temp1 = new byte[4];
        //byte[] temp1 = new byte[j];
        temp1[3] = (byte) (0xff & i);
        temp1[2] = (byte) ((0xff00 & i) >> 8);
        temp1[1] = (byte) ((0xff0000 & i) >> 16);
        temp1[0] = (byte) ((0xff000000 & i) >> 24);
        
        return temp1;
    }

   public static int bytesToInt(byte[] bytes) {
		int num = 0;
		int temp;
		temp = (0x000000ff & (bytes[0]))<<0;
		num = num | temp;
		temp = (0x000000ff & (bytes[1]))<<8;
		num = num | temp;
		temp = (0x000000ff & (bytes[2]))<<16;
		num = num | temp;
		temp = (0x000000ff & (bytes[3]))<<24;
		num = num | temp;
		
		return num;
	}

public static int bigBytesToInt(byte[] in) {
	// TODO Auto-generated method stub
	byte[] out = new byte[in.length];
	for(int i=0; i<out.length; i++) {
		out[i] = in[out.length-i-1];
	}
	int temp2 = bytesToInt(out);
	return temp2;
}
    
}
