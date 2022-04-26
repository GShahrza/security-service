package az.iktlab.securityservice.util;

import az.iktlab.securityservice.dto.request.AccountRequestDTO;
import az.iktlab.securityservice.exception.WrongFieldException;

public class Validation {

    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$*%^&+=])(?=\\S+$).{8,20}$";
    private static final String USERNAME_REGEX = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z\\d._]+(?<![_.])$";
    private static final String EMAIL_REGEX = "(?:[a-z\\d!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z\\d!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
            "*\")@(?:(?:[a-z\\d](?:[a-z\\d-]*[a-z\\d])?\\.)+[a-z\\d](?:[a-z\\d-]*[a-z\\d])?|\\[(?:(2(5[0-5]|[0-" +
            "4]\\d)|1\\d\\d|[1-9]?\\d)\\.){3}(?:(2(5[0-5]|[0-4]\\d)|1\\d\\d|[1-9]?\\d)|" +
            "[a-z\\d-]*[a-z\\d]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b" +
            "\\x0c\\x0e-\\x7f])+)])";

    public static void validateRequest(AccountRequestDTO request) {
        validateUsername(request.getUsername());

        validateEmail(request.getEmail());

        validatePassword(request.getPassword());
    }

    private static void validatePassword(String password) {
        if (!password.matches(PASSWORD_REGEX)) {
            throw new WrongFieldException("Password:\nIt contains at least 8 characters and at most 20 characters.\n"
                    .concat("It contains at least one digit.\n")
                    .concat("It contains at least one upper case alphabet.\n")
                    .concat("It contains at least one lower case alphabet.\n")
                    .concat("It contains at least one special character which includes !@#$%&*()-+=^.\n")
                    .concat("It doesn't contain any white space."));
        }
    }

    private static void validateUsername(String username) {
        if (!username.matches(USERNAME_REGEX)) {
            throw new WrongFieldException("Username:\nIt contains at least 8 characters and at most 20 characters.\n"
                    .concat("It contains at least one digit.\n")
                    .concat("It doesn't contain any white space.\n")
                    .concat("no _ or . at the beginning\n")
                    .concat("no _ or . at the end\n")
                    .concat("no __ or _. or ._ or .. inside"));
        }
    }

    private static void validateEmail(String email) {
        if (!email.matches(EMAIL_REGEX)) {
            throw new WrongFieldException("Wrong email!");
        }
    }
}
