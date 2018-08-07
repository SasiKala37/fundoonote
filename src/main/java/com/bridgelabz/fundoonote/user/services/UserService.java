package com.bridgelabz.fundoonote.user.services;

import javax.mail.MessagingException;
import javax.security.auth.login.LoginException;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.note.exceptions.UserNotFoundException;
import com.bridgelabz.fundoonote.user.exceptions.RegistrationException;
import com.bridgelabz.fundoonote.user.exceptions.TokenNotFoundException;
import com.bridgelabz.fundoonote.user.exceptions.UserActivationException;
import com.bridgelabz.fundoonote.user.model.LoginDTO;
import com.bridgelabz.fundoonote.user.model.RegistrationDTO;
import com.bridgelabz.fundoonote.user.model.ResetPasswordDTO;

@Service
public interface UserService {
	/**
	 * Register the user Details in fundoonote application
	 * 
	 * @param registerDTO
	 *            {@link RegistrationDTO} registerDTO is reference variable it has
	 *            registered user data
	 * @throws RegistrationException
	 * @throws MessagingException
	 * @throws UserActivationException
	 */
	void registerUser(RegistrationDTO registerDTO, String uri)
			throws RegistrationException, MessagingException, UserActivationException;

	/**
	 * login the user in application if the user is present
	 * 
	 * @param user
	 *            user is reference variable
	 * @throws LoginException
	 * @throws MessagingException
	 * @throws UserActivationException
	 * @throws RegistrationException
	 */
	String loginUser(LoginDTO loginDTO)
			throws LoginException, MessagingException, UserActivationException, RegistrationException;

	/**
	 * @param token
	 * @throws UserActivationException
	 */
	public void setActivationStatus(String token) throws UserActivationException;

	/**
	 * @param resetPasswordDTO
	 * @param token
	 * @throws UserActivationException
	 * @throws RegistrationException
	 * @throws TokenNotFoundException
	 * @throws UserNotFoundException
	 */
	public void resetPassword(ResetPasswordDTO resetPasswordDTO, String token)
			throws UserActivationException, RegistrationException, TokenNotFoundException, UserNotFoundException;

	/**
	 * @param emailId
	 * @param uri
	 * @throws RegistrationException
	 * @throws MessagingException
	 */
	public void forgotPassword(String emailId, String uri) throws RegistrationException, MessagingException;
}
