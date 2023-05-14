package electricity.billing.system;

import Interfaces.AAInterface;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class AA extends JFrame implements AAInterface {
    public PdfPTable JtableToPdfTable(JTable table) {
        PdfPTable t = new PdfPTable(table.getColumnCount());
        for (int i = 0; i < table.getColumnCount(); i++) {
            t.addCell(table.getColumnName(i));
        }
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                t.addCell(table.getValueAt(i, j).toString());
            }
        }
        return t;
    }

    public void Generate(String str[]) {

        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("aa.pdf"));
            doc.open();
            Font bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph par = new Paragraph(" ", bold);
            String receiptText = "         %s BILL RECEIPT\n\n" +
                    "Customer Name: %s\n" +
                    "Meter Number: %s\n" +
                    "Month: %s\n\n" +
                    "------------------------------------------------\n\n" +
                    "Unit Consumed: %s\n" +
                    "Total Bill: ৳%s\n" +
                    "Status: Paid\n\n" +
                    "------------------------------------------------\n\n" +
                    "Thank you for your payment.";

            receiptText = String.format(receiptText, str[0], str[1], str[2],
                    str[3], str[4], str[5]);
            par.add(receiptText);
            doc.add(par);
            doc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Generate(PdfPTable table, String tablename) {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("aa.pdf"));
            doc.open();
            Font bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph par = new Paragraph(" ", bold);
            String tname = "         " + tablename + "\n\n";
            par.add(tname);
            par.add(table);
            doc.add(par);
            doc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, DocumentException {

//        var table = new PdfPTable(2);
//        String name = "\nIStahak\n";
        String s[] = {"Ijadhdj", "kasdjskj", "kjadshkj", "kjajhdkja"};
        AA odj = new AA();
        odj.Generate(s);

//        Stream.of("Name", "age").forEach(table::addCell);
//        Arrays.stream(ChronoUnit.values())
//                .forEach(val -> {
//                    table.addCell(val.toString());
//                    table.addCell(val.getDuration().toString());
//                });
//        par.add(table);


    }
}
