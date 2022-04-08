package Model;

public class Monomial {

    private double degree;
    private double coeff;

    public Monomial(double coeff, double degree) {
        this.degree = degree;
        this.coeff = coeff;
    }

    public Monomial() {
    }

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }
}
