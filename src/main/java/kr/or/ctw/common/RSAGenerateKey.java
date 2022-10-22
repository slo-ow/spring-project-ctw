package kr.or.ctw.common;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

/**
 * @Class Name : RSAGenerateKey.java
 * @Description : RSA 키 생성
 * @author 문성철 
 * @since  2017.04.20
 * @version 1.0
 * @see
 */

public class RSAGenerateKey { 
	
	/**
	 * 공개키, 비밀키를 쌍으로 생성
	 * @param session
	 * @return Map<String, String>
	 */
	public Map<String, String> getGeneratePairKey(HttpSession session) {
		// 공개키, 비밀키를 쌍으로 생성 객체
		KeyPairGenerator generator = null;
		KeyPair keyPair = null;
		KeyFactory keyFactory = null;
		
		// 공개키(가수부, 지수부)
		PublicKey publicKey = null;
		// 지수부, 가수부 별도 저장
		Map<String, String> keyMap = new HashMap<String, String>();
		
		// 비밀키
		PrivateKey privateKey = null;

		try {
			generator = KeyPairGenerator.getInstance("RSA");
			
			// 공개키 사이즈 지정
			generator.initialize(2048);
			// 공개키와 비밀키 생성
			keyPair = generator.genKeyPair();
			// 공개키(지수부, 가수부) 작성하기 위한 팩토리
			// 클라이언트에 오픈 암호화 데이터
			keyFactory = KeyFactory.getInstance("RSA");
			
			// 공개키 취득
			publicKey = keyPair.getPublic();
			// 비밀키 취득
			privateKey = keyPair.getPrivate();
			
			// 비밀키를 세션에 저장
			session.setAttribute("rsaPrivateKey", privateKey);
			
			// javascript에서 활용될 공개키의 부호+지수부+가수부 생성
			RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
			
			// 생성된 공개키의 가수를 취득하고 16진수로 변환
			String publickKeyModulus = publicKeySpec.getModulus().toString(16);
			// 생성된 공개키의 지수를 취득하고 16진수로 변환
			String publicKeyExponent = publicKeySpec.getPublicExponent().toString(16);
			
			keyMap.put("publicKeyExponent", publicKeyExponent);
			keyMap.put("publicKeyModulus", publickKeyModulus);
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (InvalidKeySpecException e2){
			e2.printStackTrace();
		}
		return keyMap;
		
	}
	
	/**
	 * 암호 해독
	 * @param privateKey
	 * @param secureValue
	 * @return String
	 */
	public String decryptRSA(PrivateKey privateKey, String secureValue){
		// 1. 비밀키, 2. 공개키로 암호화된 암호문
		String returnValue = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			byte[] encryptByte = hexToByteArray(secureValue);
			
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			
			// 복호화된 byte 배열 반환
			byte[] decryptBytes = cipher.doFinal(encryptByte);
			
			returnValue  = new String(decryptBytes, "UTF-8");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
	}
	
	/**
	 * Hex to Byte 변환
	 * @param secureValue
	 * @return byte[] 
	 */
	private byte[] hexToByteArray(String secureValue) {
		
		// secureValue 암호문 길이를 2로 나눈 나머지가 0이 아니면 빈 배열 리턴
		if(secureValue == null || secureValue.length() % 2 != 0){
			return new byte[]{};
		}
		
		byte[] bytes = new byte[secureValue.length()/2];
		
		
		for(int i=0; i<secureValue.length(); i+=2){
			byte value = (byte) Integer.parseInt(secureValue.substring(i, i+2), 16);
			bytes[(int) Math.floor(i/2)] = value;
		}
		return bytes;
		
	}
}