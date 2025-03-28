package parsers;

import java.util.LinkedList;

public class MiCalc {
    private final LinkedList<String> tokens;
    private final String ordered_operations = "^*/+-";

    public MiCalc(String expr_to_eval) {
        expr_to_eval = "(" + expr_to_eval + ")";
        tokens = new LinkedList<>();
        StringBuilder num_token = new StringBuilder();
        String[] chars = expr_to_eval.split("");
        for (String xter : chars) {
            if (xter.matches("[()/*+-^]")) {
                if (!num_token.isEmpty()) {
                    tokens.add(num_token.toString());
                    num_token.setLength(0);
                }
                tokens.add(xter);
            } else {
                if (xter.matches("[0-9]")) {
                    num_token.append(xter);
                }
            }
        }
    }

    //bracket is trigger to start evaluation
    public int eval_brackets() {
        boolean remove_brackets = false;

        for (int i = tokens.size() - 1; i >= 0; i--) {
            if (remove_brackets) {
                tokens.remove(i + 3);
                tokens.remove(i + 1);
                remove_brackets = false;
            }
            if (tokens.get(i).equals("(")) {
                for (String operation : ordered_operations.split("")) {
                    //System.out.println(Arrays.asList(tokens));
                    eval_operations(i, operation);
                }
                remove_brackets = true;
            }
        }
        return Integer.parseInt(tokens.get(1));
    }

    void eval_operations(int start, final String operator) {

        for (int i = start; i < tokens.size(); i++) {
            final String possible_operator = tokens.get(i);

            if (possible_operator.equals(")")) return;
            else if (possible_operator.equals(operator)) {
                int l_exp = Integer.parseInt(tokens.get(i - 1));
                int r_exp = Integer.parseInt(tokens.get(i + 1));
                int result = switch (possible_operator) {
                    case "^" -> (int) Math.pow(l_exp, r_exp);
                    case "/" -> l_exp / r_exp;
                    case "*" -> l_exp * r_exp;
                    case "+" -> l_exp + r_exp;
                    case "-" -> l_exp - r_exp;
                    default -> -1;
                };

                tokens.set(i, String.valueOf(result));
                tokens.remove(i + 1);
                tokens.remove(i - 1);
                //shift back so as not to skip reuse of operand
                i--;
            }
        }
    }
}
