package com.bridgelabz.fundoonote.user.services;

public interface SocialServices {

	String getName();

	String createFacebookAuthorizationURL();

	void createFacebookAccessToken(String code);
}
