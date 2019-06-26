package com.zhang.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密
 */
public class PasswordEncoderUtils {

	private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public static String passwordEncoder(String password){

		return bCryptPasswordEncoder.encode(password);
	}


	public static void main(String[] args) {
		String s = passwordEncoder("123");
		System.out.println(s);
	}
}
