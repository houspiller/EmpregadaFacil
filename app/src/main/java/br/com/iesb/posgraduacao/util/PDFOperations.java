package br.com.iesb.posgraduacao.util;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Fernando on 06/11/2015.
 */
public class PDFOperations {

    public PDFOperations() {
    }

    public Boolean write(String fileName, String text) {

        //classe de operacoes com Data
        DateOperations dop = new DateOperations();

        try {



            //O arquivo vai ser gravado no sdcard ou na pasta padrão raiz do celular
            String fpath = "/sdcard/" + fileName + dop.atualDateString() + ".pdf";
            File file = new File(fpath);
            // If file does not exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // step 1
            Document document = new Document();
            // step 2
            PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));
            // step 3
            document.open();
            // step 4
            document.add(new Paragraph(text));
            // step 5
            document.close();

            Log.d("Suceess", "Sucess");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Retorna o Intent responsavel pela abertura do arquivo pdf
    public Intent openPDF(String spath) {

        DateOperations dop = new DateOperations();
        String fpath = "/sdcard/" + spath + dop.atualDateString() + ".pdf";

        File file = new File(fpath);
        Intent intent = new Intent(Intent.ACTION_VIEW);

        if (file.exists()) {
            Uri path = Uri.fromFile(file);
            intent.setDataAndType(path, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        return intent;
    }
}
