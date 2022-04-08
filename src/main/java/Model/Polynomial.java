package Model;

import java.util.ArrayList;
import java.util.Comparator;

public class Polynomial {

    private ArrayList<Monomial>monomials = new ArrayList<>();

    public Polynomial() {
    }

    public ArrayList<Monomial> getMonomials() {
        return monomials;
    }

    public void setMonomials(ArrayList<Monomial> monomials) {
        this.monomials = monomials;
    }

    public String makeStr(){

        String res = "", part = null;

        if(this == null){
            return null;
        }

        if(monomials.get(0).getCoeff() != 1) {
            if(monomials.get(0).getDegree() > 1) {
                part = (double) (this.monomials.get(0).getCoeff()) + "x^" + (int) this.monomials.get(0).getDegree();
            }
            else if(monomials.get(0).getDegree() == 1){
                part = (double) (this.monomials.get(0).getCoeff()) + "x";
            }
            else if(monomials.get(0).getDegree() == 0){
                part = "" + (double) (this.monomials.get(0).getCoeff());
            }
        }
        else if(monomials.get(0).getCoeff() == 1){
            if(monomials.get(0).getDegree() > 1) {
                part = "x^" + (int) this.monomials.get(0).getDegree();
            }
            else if(monomials.get(0).getDegree() == 1){
                part = "x";
            }
            else if(monomials.get(0).getDegree() == 0){
                part = "1";
            }
        }


        res = res + part;

        for(int i=1; i<this.monomials.size(); i++){

            Monomial m = this.monomials.get(i);


            if(m.getCoeff() > 1) {
                if(m.getDegree() > 1) {
                    part = "+" + (double) (m.getCoeff()) + "x^" + (int)m.getDegree();
                }
                else if(m.getDegree() == 1){
                    part = "+" + (double) (m.getCoeff()) + "x";
                }
                else if(m.getDegree() == 0){
                    part = "+" + (double) (m.getCoeff());
                }
            }
            else if(m.getCoeff() == 1.0){
                if(m.getDegree() > 1) {
                    part = "+x^" + (int) m.getDegree();
                }
                else if(m.getDegree() == 1){
                    part = "+x";
                }
                else if(m.getDegree() == 0){
                    part = "+1";
                }
            }
            else if(m.getCoeff() == -1.0){
                if(m.getDegree() > 1) {
                    part = "-x^" + (int) m.getDegree();
                }
                else if(m.getDegree() == 1){
                    part = "-x";
                }
                else if(m.getDegree() == 0){
                    part = "-1";
                }
            }
            else{
                if(m.getDegree() > 1) {
                    part = (double) (m.getCoeff()) + "x^" + (int)m.getDegree();
                }
                else if(m.getDegree() == 1.0){
                    part = (double) (m.getCoeff()) + "x";
                }
                else if(m.getDegree() == 0){
                    part = "" + (double) (m.getCoeff());
                }
            }

            //if(m.getCoeff() > 0){
            //    part = "+" + m.getCoeff() + "x^" + (int)m.getDegree();
            //}
            //else if(m.getCoeff() < 0){
            //    part = m.getCoeff() + "x^" + (int)m.getDegree();
            //}
            res = res + part;
        }
        return res;
    }

    public void sort(){
        this.monomials.sort(new SortByDegree() {
            @Override
            public int compare(Monomial m1, Monomial m2) {
                return super.compare(m1, m2);
            }
        });
    }

}
abstract class SortByDegree implements Comparator<Monomial>{
    public int compare(Monomial m1, Monomial m2){
        return (int)(m2.getDegree()-m1.getDegree());
    }
}
