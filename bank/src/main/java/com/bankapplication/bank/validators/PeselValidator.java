package com.bankapplication.bank.validators;

public class PeselValidator implements Validator {
    private String pesel;

    public PeselValidator(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public boolean isValid() {

        if (pesel.length() != 11) {
            return false;
        } else {
            if (checkSum()) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean checkSum() {
        byte[] tabPesel = new byte[11];
        for (int i = 0; i < 11; i++) {
            tabPesel[i] = Byte.parseByte(pesel.substring(i, i + 1));
        }
        int sum = 1 * tabPesel[0] +
                3 * tabPesel[1] +
                7 * tabPesel[2] +
                9 * tabPesel[3] +
                1 * tabPesel[4] +
                3 * tabPesel[5] +
                7 * tabPesel[6] +
                9 * tabPesel[7] +
                1 * tabPesel[8] +
                3 * tabPesel[9];
        sum %= 10;
        sum = 10 - sum;
        sum %= 10;
        if (sum == tabPesel[10]) {
            return true;
        } else {
            return false;
        }
    }
}
