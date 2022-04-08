package Controller;

import Model.Operations;
import Model.Polynomial;
import Model.Monomial;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeController {


    @FXML private MenuItem item1;
    @FXML private MenuItem item2;
    @FXML private MenuItem item3;
    @FXML private MenuItem item4;
    @FXML private MenuItem item5;
    @FXML private MenuItem item6;

    @FXML private MenuButton menuButton;
    @FXML private Label selection;
    @FXML private Label res;
    @FXML private Label remainder;

    @FXML private TextField polynomial1;
    @FXML private TextField polynomial2;

    private int op;

    public void item1onAction (ActionEvent e){

        selection.setText("f + g");
        op = 1;
    }
    public void item2onAction (ActionEvent e){

        selection.setText("f - g");
        op = 2;
    }
    public void item3onAction (ActionEvent e){

        selection.setText("f * g");
        op = 3;
    }
    public void item4onAction (ActionEvent e){

        selection.setText("f / g");
        op = 4;
    }
    public void item5onAction (ActionEvent e){

        selection.setText("f'(x)");
        op = 5;
    }
    public void item6onAction (ActionEvent e){

        selection.setText("∫fdx");
        op = 6;
    }

    private Polynomial makePol(String[] str){

        Pattern p1 = Pattern.compile("[+-]?\\d+");
        Pattern p2 = Pattern.compile("[+-]?\\d+x");
        Pattern p3 = Pattern.compile("\\+?x");
        Pattern p4 = Pattern.compile("\\-x");
        Pattern p5 = Pattern.compile("[+-]?\\d+x\\^\\d+");
        Pattern p6 = Pattern.compile("\\+?x\\^\\d+");
        Pattern p7 = Pattern.compile("\\-x\\^\\d+");

        Polynomial p = new Polynomial();
        double coef = 0, pow = 0;
        for(String s: str){

            Matcher m1 = p1.matcher(s);
            Matcher m2 = p2.matcher(s);
            Matcher m3 = p3.matcher(s);
            Matcher m4 = p4.matcher(s);
            Matcher m5 = p5.matcher(s);
            Matcher m6 = p6.matcher(s);
            Matcher m7 = p7.matcher(s);


            String[] nrs = s.split("x");

            if(m1.matches()){
                coef = Double.parseDouble(nrs[0]);
                pow = 0;
            }
            else if(m2.matches()){
                coef = Double.parseDouble(nrs[0]);
                pow = 1.0;
            }
            else if(m3.matches()){
                coef = 1.0;
                pow = 1.0;
            }
            else if(m4.matches()){
                coef = -1.0;
                pow = 1.0;
            }
            else if(m5.matches()){
                nrs[1] = nrs[1].replaceAll("\\^", "");
                coef = Double.parseDouble(nrs[0]);
                pow = Double.parseDouble(nrs[1]);
            }
            else if(m6.matches()){
                nrs[1] = nrs[1].replaceAll("\\^", "");
                coef = 1.0;
                pow = Double.parseDouble(nrs[1]);
            }
            else if(m7.matches()){
                nrs[1] = nrs[1].replaceAll("\\^", "");
                coef = -1.0;
                pow = Double.parseDouble(nrs[1]);
            }
            else{
                Alert alert = new Alert();
                alert.errorAlert("Syntax error", "Your syntax is not correct! Please try again. \nCorrect syntax: ax^b+cx^d etc");
                return null;
            }

            Monomial newMon = new Monomial(coef, pow);
            p.getMonomials().add(newMon);
        }
        return p;
    }

    public void calculateOnAction(ActionEvent event){

        if(op == 5 || op == 6) {
            if (polynomial1.getText().equals("")) {
                Alert alert = new Alert();
                alert.errorAlert("Empty field", "f(x) is empty!");
                return;
            }
        }
        if(op == 1 || op==2 || op==3 || op==4){
            if(polynomial2.getText().equals("") || polynomial1.getText().equals("")){
                Alert alert = new Alert();
                alert.errorAlert("Empty field", "For this operation you need 2 polynomials!");
                return;
            }
        }

        String pol1 = polynomial1.getText();
        String pol2 = polynomial2.getText();

        Polynomial result = new Polynomial();
        Operations operations = new Operations();

        String[] arrOfPol1 = pol1.split("((?=[+])|(?=[-]))");
        String[] arrOfPol2 = pol2.split("((?=[+])|(?=[-]))");

        Polynomial p1 = null, p2 = null;

        if(!pol1.equals("")){
            p1 = makePol(arrOfPol1);
            p1.sort();
        }
        if(!pol2.equals("")){
            p2 = makePol(arrOfPol2);
            p2.sort();
        }

        switch (op) {
            case 1 -> {
                result = operations.sum(p1, p2);
                String output = result.makeStr();
                res.setText(output);
                remainder.setText("");
            }
            case 2 -> {
                result = operations.diff(p1, p2);
                String output = result.makeStr();
                res.setText(output);
                remainder.setText("");
            }
            case 3 -> {
                result = operations.multiply(p1, p2);
                String output = result.makeStr();
                res.setText(output);
                remainder.setText("");
            }
            case 4 ->{
                ArrayList<Polynomial>results = operations.divide(p1, p2);
                res.setText(results.get(0).makeStr());
                if(results.get(1) != null) {
                    remainder.setText(results.get(1).makeStr());
                }
                else{
                    remainder.setText("0");
                }
            }
            case 5 -> {
                result = operations.derivate(p1);
                String output = result.makeStr();
                res.setText(output);
                remainder.setText("");
            }
            case 6 -> {
                result = operations.integrate(p1);
                String output = result.makeStr();
                res.setText(output);
                remainder.setText("");
            }
            default -> {
                Alert alert = new Alert();
                alert.errorAlert("Operation Error", "No operation selected!");
            }
        }
    }
}
