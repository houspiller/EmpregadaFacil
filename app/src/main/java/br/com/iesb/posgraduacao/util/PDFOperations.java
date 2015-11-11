package br.com.iesb.posgraduacao.util;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.RomanList;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.ieb.posgraduacao.activity.R;

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

    public Boolean gerarPDFPrimeiraParcela(String fileName, String valorParcela, String salarioBase) {

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

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));
            document.open();
            document.addTitle("Primeira Parcela do 13º Salário");

            Paragraph p0 =  new Paragraph("Recibo 1ª parcela do 13º salário de "
                    + new DateOperations().atualAnoString());
            p0.setAlignment(Element.ALIGN_CENTER);
            Font fontbold = FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD);
            p0.setFont(fontbold);

            document.add(p0);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            //Primeiro parágrafo
            Paragraph p1 = new Paragraph("Recebi de _________________________________________________________________ a importância de R$"
                    + valorParcela
                    + " referente ao pagamento da primeira parcela do décimo terceiro de "
                    + new DateOperations().atualAnoString()
                    + ", a saber:");
            p1.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p1);

            document.add(Chunk.NEWLINE);

            //lista
            List list = new RomanList();
            ListItem item1 = new ListItem("Total devido ao empregado: R$" + valorParcela);
            ListItem item2 = new ListItem("Salário base: R$" + salarioBase);
            ListItem item3 = new ListItem("Os valores do INSS e IRPF sobre o décimo terceiro são descontados da segunda parcela.");

            list.add(item1);
            list.add(item2);
            list.add(item3);
            document.add(list);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            //Local e data mais assinaturas
            document.add(new Paragraph("Local e data: _________________________________, ____ de _________________________ de _______."));

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("____________________________________          ___________________________________"));
            document.add(new Paragraph("                  Nome do empregado                                                           Assinatura"));


            //fechando o documento.
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

    //Copia da primeira com algumas alterações
    public Boolean gerarPDFSegundaParcela(String fileName, String valorParcela, String salarioBase, String valorINSS) {

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

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));
            document.open();
            document.addTitle("Segunda Parcela do 13º Salário");

            Paragraph p0 =  new Paragraph("Recibo 2ª parcela do 13º salário de "
                    + new DateOperations().atualAnoString());
            p0.setAlignment(Element.ALIGN_CENTER);
            Font fontbold = FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD);
            p0.setFont(fontbold);

            document.add(p0);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            //Primeiro parágrafo
            Paragraph p1 = new Paragraph("Recebi de _________________________________________________________________ a importância de R$"
                    + valorParcela
                    + " referente ao pagamento da segunda parcela do décimo terceiro de "
                    + new DateOperations().atualAnoString()
                    + ", a saber:");
            p1.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p1);

            document.add(Chunk.NEWLINE);

            //lista
            List list = new RomanList();
            ListItem item1 = new ListItem("Total devido ao empregado: R$" + valorParcela);
            ListItem item2 = new ListItem("Salário base: R$" + salarioBase);
            ListItem item3 = new ListItem("Os valores do INSS sobre o décimo terceiro são descontados da segunda parcela.");
            ListItem item4 = new ListItem("Valor do INSS pago: R$" + valorINSS);

            list.add(item1);
            list.add(item2);
            list.add(item3);
            list.add(item4);
            document.add(list);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            //Local e data mais assinaturas
            document.add(new Paragraph("Local e data: _________________________________, ____ de _________________________ de _______."));

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("____________________________________          ___________________________________"));
            document.add(new Paragraph("                  Nome do empregado                                                           Assinatura"));


            //fechando o documento.
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
}
