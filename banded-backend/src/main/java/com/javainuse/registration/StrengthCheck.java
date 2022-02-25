package com.javainuse.registration;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StrengthCheck {
	public String checkPassword(String pword) {
		String flags = "";
		
		if (pword.length() < 8) flags += "There are TOO FEW characters in this password, the minimum is 8\n"; //not enough characters
		else if (pword.length() > 24) flags += "There are TOO MANY characters in this password, the max allowed is 24\n"; //too many characters
		
		//check for lowercase letters
		Pattern p = Pattern.compile("[a-z]");
		Matcher m = p.matcher(pword);
		boolean b = m.find();
		if (!b) flags += "There are no LOWERCASE letters in this password, you must have at least one\n";
		
		//check for uppercase letters
		p = Pattern.compile("[A-Z]");
		m = p.matcher(pword);
		b = m.find();
		if (!b) flags += "There are no UPPERCASE letters in this password, you must have at least one\n";
		
		//check for digits
		p = Pattern.compile("[0-9]");
		m = p.matcher(pword);
		b = m.find();
		if (!b) flags += "There are no DIGITS in this password, you must have at least one\n";
		
		//check for symbols
		p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
		m = p.matcher(pword);
		b = m.find();
		if (!b) flags += "There are no SYMBOLS in this password, you must have at least one\n";
		
		return flags;
	}
	
}