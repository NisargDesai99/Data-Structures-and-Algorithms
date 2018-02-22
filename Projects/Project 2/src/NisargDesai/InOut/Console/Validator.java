/*
Created by Nisarg Desai.
Validates strings and numbers
*/

package NisargDesai.InOut.Console;

public class Validator implements Acceptable {

    public Validator() {  }

    @Override
    public boolean isNonEmptyString(String s) {
        if (s.isEmpty() || s.equals("")) return false;
        return true;
    }

    //Integers/Long
    @Override
    public boolean isNegative(int i) {
        if (i >= 0) return false;
        return true;
    }

    @Override
    public boolean isPositive(int i) {
        if (i <= 0) return false;
        return true;
    }

    @Override
    public boolean isNegative(long l) {
        if (l >= 0) return false;
        return true;
    }

    @Override
    public boolean isPositive(long l) {
        if (l <= 0) return false;
        return true;
    }

    //Double/Float
    @Override
    public boolean isNegative(double d) {
        if (d >= 0) return false;
        return true;
    }

    @Override
    public boolean isPositive(double d) {
        if (d <= 0) return false;
        return true;
    }

    @Override
    public boolean isNegative(float f) {
        if (f >= 0) return false;
        return true;
    }

    @Override
    public boolean isPositive(float f) {
        if (f <= 0) return false;
        return true;
    }

    //Zero
    @Override
    public boolean isZero(int i) {
        if (i == 0) return true;
        return false;
    }

    @Override
    public boolean isZero(long l) {
        if (l == 0) return true;
        return false;
    }

    @Override
    public boolean isZero(double d) {
        if (d == 0) return true;
        return false;
    }

    @Override
    public boolean isZero(float f) {
        if (f == 0) return true;
        return false;
    }

}
