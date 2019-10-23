package sat;

import immutable.EmptyImList;
import immutable.ImList;
import sat.env.Environment;
import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;
import sat.formula.NegLiteral;
import sat.formula.PosLiteral;

/**
 * A simple DPLL SAT solver. See http://en.wikipedia.org/wiki/DPLL_algorithm
 */
public class SATSolver {
    /**
     * Solve the problem using a simple version of DPLL with backtracking and
     * unit propagation. The returned environment binds literals of class
     * bool.Variable rather than the special literals used in clausification of
     * class clausal.Literal, so that clients can more readily use it.
     *
     * @return an environment for which the problem evaluates to Bool.TRUE, or
     *         null if no such environment exists.
     */
    public static Environment solve(Formula formula) {
        // TODO: implement this.
        //throw new RuntimeException("not yet implemented.");
        Environment environment = new Environment(); //Create new environment
        ImList<Clause> clauses = formula.getClauses();// get clauses from Formula
        return solve(clauses, environment);

    }

    /**
     * Takes a partial assignment of variables to values, and recursively
     * searches for a complete satisfying assignment.
     *
     * @param clauses
     *            formula in conjunctive normal form
     * @param env
     *            assignment of some or all variables in clauses to true or
     *            false values.
     * @return an environment for which all the clauses evaluate to Bool.TRUE,
     *         or null if no such environment exists.
     */
    private static Environment solve(ImList<Clause> clauses, Environment env) {
        // TODO: implement this.
        //throw new RuntimeException("not yet implemented.");
        //If there are no clauses, the formula is trivially satisfiable...
        if (clauses.isEmpty()) { //return null (empty envt)
            return env;
        }
        Clause minimum = clauses.first(); //find the first clause
        for (Clause c : clauses) { //find the smallest clause
            if (c.size() < minimum.size()) {
                minimum = c;
            }
            if (minimum.isEmpty()) {
                return null;
            }
        }
        if (minimum.isUnit()) { //to find clause with one literal only
            Literal l = minimum.chooseLiteral();
            Environment environment1 = env;
            if (l instanceof PosLiteral) { //put literal == True first
                environment1 = env.putTrue(l.getVariable());
            } else if (l instanceof NegLiteral) { //if its negated var , then == False
                environment1 = env.putFalse(l.getVariable());
            }
            ImList<Clause> clauses1 = substitute(clauses, l); //get newClauses to return for environment1
            return (solve(clauses1, environment1)); //recursively call solve()
        } else {  //Otherwise, pick an arbitrary literal from this small clause: First try setting the literal to TRUE, substitute for it in all the clauses, then solve() recursively

            Literal l = minimum.chooseLiteral();
            Environment envT = env.putTrue(l.getVariable());
            ImList<Clause> clausesT = substitute(clauses, l);
            Environment resultT = solve(clausesT, envT);

            if (resultT == null) { //If that fails, then try setting the literal to FALSE, substitute, and solve() recursively.

                Environment envF = env.putFalse(l.getVariable());
                ImList<Clause> clausesF = substitute(clauses, l.getNegation());
                Environment resultF = solve(clausesF, envF);
                return resultF;
            } else {
                return resultT;

            }

        }
    }

    /**
     * given a clause list and literal, produce a new list resulting from
     * setting that literal to true
     *
     * @param clauses
     *            , a list of clauses
     * @param l
     *            , a literal to set to true
     * @return a new list of clauses resulting from setting l to true
     */
    private static ImList<Clause> substitute(ImList<Clause> clauses, Literal l) {
        // TODO: implement this.
        //throw new RuntimeException("not yet implemented.");
        Clause newC = new Clause();
        ImList<Clause> newClauses = new EmptyImList<Clause>();
        if (clauses.isEmpty()){
            return newClauses; //if clauses is empty then it is deemed as false. Returning a new empty clause == False in boolean , therefore null will be returned when solve() is called.
        }

        for (Clause c : clauses) {
            if (!c.isEmpty() && c!=null){ //safety net
                newC = c.reduce(l);} // Creating a simplified clause (newC) to add into newClauses
            if (newC != null) {
                newClauses = newClauses.add(newC);
            }
        }

        return newClauses; //return the reduced clause form for substitute() , for the ENTIRE formula
    }

}