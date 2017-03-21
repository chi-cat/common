package top.flyfire.common;

/**
 * Created by shyy_work on 2017/3/21.
 */
public class NumberUtils {

    public static final double fromChars(CharSequence in, int offset, int len){
        if(offset+len>in.length()||offset<0||len<0)
            throw new NumberFormatException();
        int prec = 0;                 // record precision value
        int scl = 0;                  // record scale value
        double rs = 0;                  // the compact value in long
        boolean isneg = false;
        if (in.charAt(offset) == '-') {
            isneg = true;               // leading minus means negative
            offset++;
            len--;
        } else if (in.charAt(offset) == '+') { // leading + allowed
            offset++;
            len--;
        }

        // should now be at numeric part of the significand
        boolean dot = false;             // true when there is a '.'
        int cfirst = offset;             // record start of integer
        long exp = 0;                    // exponent
        char c;                          // current character
        int idx = 0;

        for (; len > 0; offset++, len--) {
            c = in.charAt(offset);
            if ((c >= '0' && c <= '9') || Character.isDigit(c)) {
                int digit = Character.digit(c, 10);
                if (digit == 0) {
                    if (prec == 0)
                        prec = 1;
                    else if (rs != 0) {
                        rs *= 10;
                        ++prec;
                    } // else digit is a redundant leading zero
                } else {
                    if (prec != 1 || rs != 0)
                        ++prec; // prec unchanged if preceded by 0s
                    rs = rs * 10 + digit;
                }
                if (dot)
                    ++scl;
                continue;
            }
            // have dot
            if (c == '.') {
                // have dot
                if (dot)         // two dots
                    throw new NumberFormatException();
                dot = true;
                continue;
            }
            // exponent expected
            if ((c != 'e') && (c != 'E'))
                throw new NumberFormatException();
            offset++;
            c = in.charAt(offset);
            len--;
            boolean negexp = (c == '-');
            // optional sign
            if (negexp || c == '+') {
                offset++;
                c = in.charAt(offset);
                len--;
            }
            if (len <= 0)    // no exponent digits
                throw new NumberFormatException();
            // skip leading zeros in the exponent
            while (len > 10 && Character.digit(c, 10) == 0) {
                offset++;
                c = in.charAt(offset);
                len--;
            }
            if (len > 10)  // too many nonzero exponent digits
                throw new NumberFormatException();
            // c now holds first digit of exponent
            for (;; len--) {
                int v;
                if (c >= '0' && c <= '9') {
                    v = c - '0';
                } else {
                    v = Character.digit(c, 10);
                    if (v < 0)            // not a digit
                        throw new NumberFormatException();
                }
                exp = exp * 10 + v;
                if (len == 1)
                    break;               // that was final character
                offset++;
                c = in.charAt(offset);
            }
            if (negexp)                  // apply sign
                exp = -exp;
            // Next test is required for backwards compatibility
            if ((int)exp != exp)         // overflow
                throw new NumberFormatException();
            break;
        }
        // here when no characters left
        if (prec == 0)              // no digits found
            throw new NumberFormatException();
        // Adjust scale if exp is not zero.
        if (exp != 0) {                  // had significant exponent
            // Can't call checkScale which relies on proper fields value
            long adjustedScale = scl - exp;
            if (adjustedScale > Integer.MAX_VALUE ||
                    adjustedScale < Integer.MIN_VALUE)
                throw new NumberFormatException("Scale out of range.");
            scl = (int)adjustedScale;
        }
        while(scl-->0){
            rs/=10;
        }
        return isneg ? -rs : rs;
    }

}
