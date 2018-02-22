/*
Created by Nisarg Desai.
Interface for Validator methods. To be implemented by a Validator.
*/

package NisargDesai.InOut.Console;

interface Acceptable {
    boolean isNonEmptyString(String s);

    //Integers/Long
    boolean isNegative(int i);
    boolean isPositive(int i);
    boolean isNegative(long l);
    boolean isPositive(long l);

    //Double/Floats
    boolean isNegative(double d);
    boolean isPositive(double d);
    boolean isNegative(float f);
    boolean isPositive(float f);

    //Zero
    boolean isZero(int i);
    boolean isZero(long l);
    boolean isZero(double d);
    boolean isZero(float f);
}
