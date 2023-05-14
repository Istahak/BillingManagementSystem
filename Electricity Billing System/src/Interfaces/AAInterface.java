package Interfaces;

import com.itextpdf.text.pdf.PdfPTable;

import javax.swing.*;

public interface AAInterface {
    public PdfPTable JtableToPdfTable(JTable table);
    public void Generate(String str[]);
    public void Generate(PdfPTable table, String tablename);
}
