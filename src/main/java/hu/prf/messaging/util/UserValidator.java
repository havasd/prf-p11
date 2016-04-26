package hu.prf.messaging.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.inject.Named;

import hu.prf.messaging.entity.User;

@Named
public class UserValidator implements Serializable {

	private static final long serialVersionUID = -6216869673077273686L;

	private Pattern pattern;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public UserValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public boolean validateEmail(String email) {
		return pattern.matcher(email).matches();
	}

	public String validate(User user, String birth) {
		boolean errMail, errName, errDate;
		errMail = errName = errDate = false;

		user.setEmail(user.getEmail().trim());
		user.setName(user.getName().trim());
		user.setPassword(user.getPassword().trim());

		if (!validateEmail(user.getEmail())) {
			errMail = true;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date birthDate = sdf.parse(birth);
			user.setBirthDate(birthDate);
		} catch (ParseException e) {
			errDate = true;
		}

		if (user.getName().length() < 4) {
			errName = true;
		}

		if (!errMail && !errName && !errDate) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("&a=error");
		if (errMail) {
			sb.append("&err_mail=1");
		}
		if (errName) {
			sb.append("&err_name=1");
		}
		if (errDate) {
			sb.append("&err_date=1");
		}
		return sb.toString();
	}

}
