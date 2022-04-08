package Model;

import Controller.Alert;

import java.util.ArrayList;
import java.util.Arrays;

public class Operations {

    //the monomials should be sorted
    public Polynomial sum(Polynomial p1, Polynomial p2){

        Polynomial result = new Polynomial();

        int i=0, j=0;
        while(i < p1.getMonomials().size() && j < p2.getMonomials().size()){
            Monomial m1 = p1.getMonomials().get(i);
            Monomial m2 = p2.getMonomials().get(j);
            if(m1.getDegree() > m2.getDegree()){
                result.getMonomials().add(m1);
                i++;
            }
            else if(m2.getDegree() > m1.getDegree()){
                result.getMonomials().add(m2);
                j++;
            }
            else{
                if(m1.getCoeff() + m2.getCoeff() != 0) {
                    Monomial m = new Monomial(m1.getCoeff() + m2.getCoeff(), m1.getDegree());
                    result.getMonomials().add(m);
                }
                i++;
                j++;
            }
        }
        if(i < p1.getMonomials().size()){
            for(; i<p1.getMonomials().size(); i++){
                result.getMonomials().add(p1.getMonomials().get(i));
            }
        }
        else if(j < p2.getMonomials().size()){
            for(; j<p2.getMonomials().size(); j++){
                result.getMonomials().add(p2.getMonomials().get(j));
            }
        }
        result.sort();
        return result;
    }

    public Polynomial diff(Polynomial p1, Polynomial p2){

        Polynomial result = new Polynomial();

        int i=0, j=0;
        while(i < p1.getMonomials().size() && j < p2.getMonomials().size()){
            Monomial m1 = p1.getMonomials().get(i);
            Monomial m2 = p2.getMonomials().get(j);
            if(m1.getDegree() > m2.getDegree()){
                result.getMonomials().add(m1);
                i++;
            }
            else if(m2.getDegree() > m1.getDegree()){
                result.getMonomials().add(m2);
                j++;
            }
            else{
                if(m1.getCoeff() - m2.getCoeff() != 0) {
                    Monomial m = new Monomial(m1.getCoeff() - m2.getCoeff(), m1.getDegree());
                    result.getMonomials().add(m);
                }
                i++;
                j++;
            }
        }
        if(i < p1.getMonomials().size()){
            for(; i<p1.getMonomials().size(); i++){
                result.getMonomials().add(p1.getMonomials().get(i));
            }
        }
        else if(j < p2.getMonomials().size()){
            for(; j<p2.getMonomials().size(); j++){
                Monomial mon = new Monomial(-p2.getMonomials().get(j).getCoeff(), p2.getMonomials().get(j).getDegree());
                result.getMonomials().add(mon);
            }
        }
        result.sort();
        return result;
    }

    public Polynomial multiply(Polynomial p1, Polynomial p2){

        int size = (int)(p1.getMonomials().get(0).getDegree()+p2.getMonomials().get(0).getDegree() + 1);

        Double[] coeffs = new Double[size + 1];
        Arrays.fill(coeffs, 0.0);

        Polynomial result = new Polynomial();

        for(Monomial m1: p1.getMonomials()){
            for(Monomial m2: p2.getMonomials()){

                coeffs[(int)(m1.getDegree()+m2.getDegree())] += m1.getCoeff() * m2.getCoeff();
            }
        }

        for(int i=size; i>=0; i--){
            if(coeffs[i] != 0.0){
                Monomial newM = new Monomial(coeffs[i], (double)(i));
                result.getMonomials().add(newM);
            }
        }
        result.sort();
        return result;
    }

    public ArrayList<Polynomial> divide(Polynomial p1, Polynomial p2){

        if(p2.getMonomials().get(0).getDegree() > p1.getMonomials().get(0).getDegree()){
            Alert alert = new Alert();
            alert.errorAlert("Division error", "Degree(g) > Degree(f)");
            return null;
        }
        Polynomial res = new Polynomial(), temp = new Polynomial();
        ArrayList<Polynomial>result = new ArrayList<>();


        while(p1.getMonomials().size() > 0 && p1.getMonomials().get(0).getDegree() >= p2.getMonomials().get(0).getDegree()){

            Operations op = new Operations();
            Monomial m1 = p1.getMonomials().get(0);
            Monomial m2 = p2.getMonomials().get(0);
            double coef, pow;

            coef = m1.getCoeff() / m2.getCoeff();
            pow = m1.getDegree() - m2.getDegree();

            Monomial newM = new Monomial(coef, pow);

            res.getMonomials().add(newM);
            temp.getMonomials().add(newM);
            p1 = op.diff(p1, op.multiply(p2, temp));
            temp.getMonomials().remove(0);
        }

        result.add(res);
        result.add(p1);

        return result;
    }

    public Polynomial derivate(Polynomial p){

        Polynomial result = new Polynomial();

        for(Monomial m: p.getMonomials()){
            if(m.getDegree() != 0) {
                double coef = m.getCoeff() * m.getDegree();
                double pow = m.getDegree() - 1;

                Monomial newM = new Monomial(coef, pow);
                result.getMonomials().add(newM);
            }
        }
        result.sort();
        return result;
    }

    public Polynomial integrate(Polynomial p){

        Polynomial result = new Polynomial();

        for(Monomial m: p.getMonomials()){

            double coef = m.getCoeff() * (1.0 / (m.getDegree() + 1));
            double pow = m.getDegree() + 1;

            Monomial newM = new Monomial(coef, pow);
            result.getMonomials().add(newM);
        }
        result.sort();
        return result;
    }
}
