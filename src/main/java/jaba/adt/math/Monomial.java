package jaba.adt.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrador on 29/06/2017.
 */
public class Monomial {
    private int monomialCoefficient;
    private List<VariableTerm> variableTerms;

    public Monomial(int coefficient) {
        this.monomialCoefficient = coefficient;
        variableTerms = new ArrayList<>();
    }

    int getGrade() {
        int maxgrade = Integer.MIN_VALUE;
        for (VariableTerm variableTerm : variableTerms) {
            if (variableTerm.getGrade() > maxgrade) {
                maxgrade = variableTerm.getGrade();
            }
        }
        return maxgrade;
    }

//    void normalize(){
//
//    }

//    String getLiteralPart(){
//
//    }

//    boolean isSemejant(Monomial fist, Monomial second){
//        return fist.getL
//    }


    @Override
    public String toString() {
        return (0 == monomialCoefficient ? "" : monomialCoefficient + "(") + Arrays.toString(variableTerms.toArray()) + (monomialCoefficient == 0 ? "" : ")");
    }

    public void addTerm(VariableTerm variableTerm) {
//        if (isLiteralPartAndGradePesent(variableTerm.getLiteralPart(), variableTerm.getGrade())) {
        //sum to normalize
//        } else {
        variableTerms.add(variableTerm);
//        }
    }

    private boolean isLiteralPartAndGradePesent(String literalPart, int grade) {
        return false;
    }


}
