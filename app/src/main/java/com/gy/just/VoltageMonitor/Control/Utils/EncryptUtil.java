/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gy.just.VoltageMonitor.Control.Utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author tengyu
 */
public class EncryptUtil {
    private static final byte[] key = {-14,-38,-63,115,1,-15,-94,50,116};
    private static final String KEY_ALGORITHM = "DES";    
    // 算法名称/加密模式/填充方式    
    private static final String CIPHER_ALGORITHM_ECB = "DES/ECB/PKCS5Padding";    
    private static final String CIPHER_ALGORITHM_CBC = "DES/CBC/PKCS5Padding";  
    
    public static String encrypt(String txt){
        try {
            return new String(encrypt(txt.getBytes("utf-8"), key),"iso-8859-1");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(EncryptUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static String decrypt(String txt){
        try {
            return new String(decrypt(txt.getBytes("iso-8859-1"), key),"utf-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
     
    
    private static byte[] getIV() {    
        String iv = "asdfivh7"; //IV length: must be 8 bytes long    
        return iv.getBytes();    
    }   
    
    /**  
     * 生成密钥  
     *   
     * @return  
     * @throws Exception  
     */    
    private static byte[] generateKey() throws Exception {    
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);    
        keyGenerator.init(56); //des 必须是56, 此初始方法不必须调用    
        SecretKey secretKey = keyGenerator.generateKey();    
        return secretKey.getEncoded();    
    }    
    
     /**  
     * 还原密钥  
     *   
     * @param key  
     * @return  
     * @throws Exception   
     */    
    private static Key toKey(byte[] key) throws Exception {    
        DESKeySpec des = new DESKeySpec(key);    
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);    
        SecretKey secretKey = keyFactory.generateSecret(des);    
        return secretKey;    
    }    
    
    /**  
     * 加密   
     * @param data 原文  
     * @param key  
     * @return 密文  
     * @throws Exception  
     */    
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {    
        Key k = toKey(key);    
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);    
        cipher.init(Cipher.ENCRYPT_MODE, k);    
        return cipher.doFinal(data);    
    }    
    
    /**  
     * 解密  
     * @param data 密文  
     * @param key  
     * @return 明文、原文  
     * @throws Exception  
     */    
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {    
        Key k = toKey(key);    
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);    
        cipher.init(Cipher.DECRYPT_MODE, k);    
        return cipher.doFinal(data);    
    }   
}
