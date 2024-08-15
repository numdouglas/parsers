public class BBracketsParser {
    //    Parser language is defined as the language that accepts closed parentheses
//    X-> "" and X-> ( X ) X
//    Using Recursive descent parsing would yield the equivalent rules A-> ( B | "" and B-> A ) A
    static int g_ptr_location = 0;

    static char lookahead(char[] chars) {
        if (g_ptr_location >= chars.length) {
            return 0;
        }
        return chars[g_ptr_location];
    }

    static boolean parseA(char[] chars) {
        //lookahead and consume
        if (lookahead(chars) == '(') {
            g_ptr_location++;
            return parseB(chars);
        }
        //base case
//        else if (chars[idx] == '$') {
//            return true;
//        }
        return true;
    }

    static boolean parseB(char[] chars) {
        //lookahead and consume
        if (parseA(chars) && lookahead(chars) == ')') {
            g_ptr_location++;
            return parseA(chars);
        }
        //base case
//        else if (chars[idx] == '$') {
//            return true;
//        }
        return false;
    }

    public static void main(String[] args) {
//        String chars = "()()$";
        final String chars = "(()()))";

        final boolean grammar_correct = parseA(chars.toCharArray()) && g_ptr_location == chars.length();
        System.out.println(grammar_correct);
    }
}
