package top.flyfire.common;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/6/3.
 */
public class CharUtils implements CharPool {

    private final static int ULD_VALUE = 32;

    public static boolean isDigit(char token){
        return token>=ZERO && token <=NINE;
    }

    public static boolean isDigit(int token){
        return token>=ZERO && token <=NINE;
    }


    public static boolean isNotDigit(char token){
        return token<ZERO||token>NINE;
    }

    public static boolean isNotDigit(int token){
        return token<ZERO||token>NINE;
    }

    public static boolean isUpper(char token){
        return token>=A&&token<=Z;
    }

    public static boolean isLower(char token){
        return token>=A_&&token<=Z_;
    }

    public static boolean isUpper(int token){
        return token>=A&&token<=Z;
    }

    public static boolean isLower(int token){
        return token>=A_&&token<=Z_;
    }

    public static boolean isLetter(char token){
        return isUpper(token)||isLower(token);
    }

    public static boolean isLetter(int token){
        return isUpper(token)||isLower(token);
    }

    public static char toLower(char token){
        return isUpper(token)?(char)(token+ULD_VALUE):token;
    }

    public static char toLower(int token){
        return (char)(isUpper(token)?(token+ULD_VALUE):token);
    }

    public static char toUpper(char token){
        return isLower(token)?(char)(token-ULD_VALUE):token;
    }

    public static char toUpper(int token){
        return (char)(isLower(token)?(token-ULD_VALUE):token);
    }

}
