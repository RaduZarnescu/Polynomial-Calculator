import Model.Operations;
import Model.Monomial;
import Model.Polynomial;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    Polynomial p1 = new Polynomial(), p2 = new Polynomial();
    Monomial m1 = new Monomial(2,4);
    Monomial m2 = new Monomial(-1,3);
    Monomial m3 = new Monomial(4,2);
    Monomial m4 = new Monomial(-10,1);
    Monomial m5 = new Monomial(1,2);
    Monomial m6 = new Monomial(2,1);
    Monomial m7 = new Monomial(5,0);




    @Test
    void sum() {
        p1.getMonomials().add(m1);
        p1.getMonomials().add(m2);
        p1.getMonomials().add(m3);
        p1.getMonomials().add(m4);
        p2.getMonomials().add(m5);
        p2.getMonomials().add(m6);
        p2.getMonomials().add(m7);

        Operations op = new Operations();

        Polynomial res = op.sum(p1, p2);

        Polynomial compRes = new Polynomial();

        Monomial r1 = new Monomial(5, 2);
        Monomial r2 = new Monomial(-8, 1);

        compRes.getMonomials().add(m1);
        compRes.getMonomials().add(m2);
        compRes.getMonomials().add(r1);
        compRes.getMonomials().add(r2);
        compRes.getMonomials().add(m7);


        assertEquals(res.makeStr(), compRes.makeStr());
    }

    @Test
    void diff() {
        p1.getMonomials().add(m1);
        p1.getMonomials().add(m2);
        p1.getMonomials().add(m3);
        p1.getMonomials().add(m4);
        p2.getMonomials().add(m5);
        p2.getMonomials().add(m6);
        p2.getMonomials().add(m7);

        Operations op = new Operations();

        Polynomial res = op.diff(p1, p2);

        Polynomial compRes = new Polynomial();

        Monomial r1 = new Monomial(3, 2);
        Monomial r2 = new Monomial(-12, 1);
        Monomial r3 = new Monomial(-5, 0);

        compRes.getMonomials().add(m1);
        compRes.getMonomials().add(m2);
        compRes.getMonomials().add(r1);
        compRes.getMonomials().add(r2);
        compRes.getMonomials().add(r3);


        assertEquals(res.makeStr(), compRes.makeStr());
    }

    @Test
    void multiply() {
        p1.getMonomials().add(m1);
        p1.getMonomials().add(m2);
        p1.getMonomials().add(m3);
        p1.getMonomials().add(m4);
        p2.getMonomials().add(m5);
        p2.getMonomials().add(m6);
        p2.getMonomials().add(m7);

        Operations op = new Operations();

        Polynomial res = op.multiply(p1, p2);

        Polynomial compRes = new Polynomial();

        Monomial r1 = new Monomial(2, 6);
        Monomial r2 = new Monomial(3, 5);
        Monomial r3 = new Monomial(12, 4);
        Monomial r4 = new Monomial(-7, 3);
        Monomial r5 = new Monomial(-50, 1);

        compRes.getMonomials().add(r1);
        compRes.getMonomials().add(r2);
        compRes.getMonomials().add(r3);
        compRes.getMonomials().add(r4);
        compRes.getMonomials().add(r5);


        assertEquals(res.makeStr(), compRes.makeStr());
    }

    @Test
    void divide() {
        p1.getMonomials().add(m1);
        p1.getMonomials().add(m2);
        p1.getMonomials().add(m3);
        p1.getMonomials().add(m4);
        p2.getMonomials().add(m5);
        p2.getMonomials().add(m6);
        p2.getMonomials().add(m7);

        Polynomial res1 = new Polynomial(), res2 = new Polynomial();

        Monomial r1 = new Monomial(2,2);
        Monomial r2 = new Monomial(-5,1);
        Monomial r3 = new Monomial(4,0);
        Monomial r4 = new Monomial(7,1);
        Monomial r5 = new Monomial(-20,0);

        res1.getMonomials().add(r1);
        res1.getMonomials().add(r2);
        res1.getMonomials().add(r3);
        res2.getMonomials().add(r4);
        res2.getMonomials().add(r5);

        ArrayList<Polynomial>compRes = new ArrayList<>();
        compRes.add(res1);
        compRes.add(res2);

        Operations op = new Operations();

        ArrayList<Polynomial>result = op.divide(p1, p2);

        assertEquals(result.get(0).makeStr() + result.get(1).makeStr(), compRes.get(0).makeStr() + compRes.get(1).makeStr());
    }

    @Test
    void derivate() {
        p1.getMonomials().add(m1);
        p1.getMonomials().add(m2);
        p1.getMonomials().add(m3);
        p1.getMonomials().add(m4);

        Monomial r1 = new Monomial(8,3);
        Monomial r2 = new Monomial(-3,2);
        Monomial r3 = new Monomial(8,1);
        Monomial r4 = new Monomial(-10,0);

        Polynomial compRes = new Polynomial();
        compRes.getMonomials().add(r1);
        compRes.getMonomials().add(r2);
        compRes.getMonomials().add(r3);
        compRes.getMonomials().add(r4);

        Operations op = new Operations();
        Polynomial result = op.derivate(p1);

        assertEquals(result.makeStr(), compRes.makeStr());
    }

    @Test
    void integrate() {
        p1.getMonomials().add(m1);
        p1.getMonomials().add(m2);
        p1.getMonomials().add(m3);
        p1.getMonomials().add(m4);

        Monomial r1 = new Monomial(0.4,5);
        Monomial r2 = new Monomial(-0.25,4);
        Monomial r3 = new Monomial(4.0/3.0,3);
        Monomial r4 = new Monomial(-5,2);

        Polynomial compRes = new Polynomial();
        compRes.getMonomials().add(r1);
        compRes.getMonomials().add(r2);
        compRes.getMonomials().add(r3);
        compRes.getMonomials().add(r4);

        Operations op = new Operations();
        Polynomial result = op.integrate(p1);

        assertEquals(result.makeStr(), compRes.makeStr());
    }
}