package br.com.iesb.posgraduacao.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Fernando on 06/11/2015.
 */
public class DateOperations {

    public DateOperations() {
    }

    public String atualDateString(){
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
