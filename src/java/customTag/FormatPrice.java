/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customTag;

import entities.Products;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;

/**
 *
 * @author ACER
 */
public class FormatPrice extends TagSupport {
// Using this library to get currency of Vietnam

    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    double price;
    double sale;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    @Override
    public int doStartTag() throws JspException {
        double sprice = price * (1 - sale / 100);

        try {
            JspWriter out = pageContext.getOut();
            out.print(currencyVN.format(sprice));
        } catch (IOException ex) {
            Logger.getLogger(FormatPrice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SKIP_BODY;
    }
}
