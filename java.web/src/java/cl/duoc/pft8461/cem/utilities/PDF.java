/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.utilities;

import cl.duoc.pft8461.cem.controllers.ProgramaController;
import cl.duoc.pft8461.cem.ws.Alumno;
import cl.duoc.pft8461.cem.ws.AlumnoWS;
import cl.duoc.pft8461.cem.ws.AlumnoWS_Service;
import cl.duoc.pft8461.cem.ws.Centro;
import cl.duoc.pft8461.cem.ws.CentroWS;
import cl.duoc.pft8461.cem.ws.CentroWS_Service;
import cl.duoc.pft8461.cem.ws.Curso;
import cl.duoc.pft8461.cem.ws.CursoWS;
import cl.duoc.pft8461.cem.ws.CursoWS_Service;
import cl.duoc.pft8461.cem.ws.Participacion;
import cl.duoc.pft8461.cem.ws.ParticipacionWS;
import cl.duoc.pft8461.cem.ws.ParticipacionWS_Service;
import cl.duoc.pft8461.cem.ws.Postulacion;
import cl.duoc.pft8461.cem.ws.PostulacionWS;
import cl.duoc.pft8461.cem.ws.PostulacionWS_Service;
import cl.duoc.pft8461.cem.ws.Programa;
import cl.duoc.pft8461.cem.ws.ProgramaWS;
import cl.duoc.pft8461.cem.ws.ProgramaWS_Service;
import cl.duoc.pft8461.cem.ws.Usuario;
import cl.duoc.pft8461.cem.ws.UsuarioWS;
import cl.duoc.pft8461.cem.ws.UsuarioWS_Service;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Joe
 */
public class PDF {
    
    private final ProgramaWS programaWS = new ProgramaWS_Service().getProgramaWSPort();
    private final AlumnoWS alumnoWS = new AlumnoWS_Service().getAlumnoWSPort();
    private final UsuarioWS usuarioWS = new UsuarioWS_Service().getUsuarioWSPort();
    private final CursoWS cursoWS = new CursoWS_Service().getCursoWSPort();
    private final CentroWS centroWS = new CentroWS_Service().getCentroWSPort();
    private final PostulacionWS postulacionWS = new PostulacionWS_Service().getPostulacionWSPort();
    private final ParticipacionWS participacionWS = new ParticipacionWS_Service().getParticipacionWSPort();
    protected PropertiesManager pm = new PropertiesManager();
    
    public String generate(HttpServletRequest request, int idAlumno) throws BadElementException, IOException, DocumentException {
        
        Document document = new Document();
        Usuario usuario = this.usuarioWS.findUsuario(idAlumno);
        Postulacion postulacion = this.postulacionWS.findPostulacionPor("id_alumno", String.valueOf(idAlumno)).get(0);
        Participacion participacion = this.participacionWS.findParticipacion(postulacion.getIdParticipacion());
        Centro centro = this.centroWS.findCentro(participacion.getIdCentro());
        Programa programa = this.programaWS.findPrograma(participacion.getIdPrograma());
        List<Curso> cursos = this.cursoWS.findCursoPor("id_programa", String.valueOf(participacion.getIdPrograma()));
        
        String nombre = usuario.getNombre() + " " + usuario.getApellidoPat()
                + " " + usuario.getApellidoMat();
        
        Image image = Image.getInstance(request.getSession().getServletContext().getRealPath("/resources/") + "/dist/img/logo_large.png");
        image.setAbsolutePosition(30, 740);
        image.scaleToFit(200, 100);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(request.getSession().getServletContext().getRealPath("/resources/") + this.pm.get("PDF_PATH") + "cert_" + idAlumno + ".pdf", false));
        PDF.Footer event = new PDF.Footer();
        writer.setPageEvent(event);
        document.setPageSize(PageSize.LETTER);
        document.open();
        document.add(image);
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(100);
        paragraph.setSpacingBefore(150);
        Font font = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);
        Chunk title = new Chunk("CERTIFICADO DE APROBACIÓN", font);
        paragraph.add(title);
        document.add(paragraph);
        paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.setSpacingAfter(30);
        Phrase phrase = new Phrase();
        phrase.add("El CENTRO ");
        phrase.add(new Chunk(centro.getNombreCentro(), font));
        phrase.add(", certifica que: ");
        paragraph.add(phrase);
        document.add(paragraph);
        paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.setSpacingAfter(10);
        phrase = new Phrase();
        phrase.add("Don/a ");
        phrase.add(new Chunk(nombre, font));
        phrase.add(", fue aprobado, dentro del PROGRAMA ");
        phrase.add(new Chunk(programa.getNombrePrograma(), font));
        phrase.add(", en los siguientes cursos:");
        paragraph.add(phrase);
        document.add(paragraph);
        for(Curso c : cursos){
            paragraph = new Paragraph();
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            paragraph.setSpacingAfter(10);
            paragraph.add("- " + c.getNombreCurso());
            document.add(paragraph);
        }
        paragraph = new Paragraph();
        paragraph.setSpacingBefore(30);
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.add("Se extiende el presente CERTIFICADO para los fines que se estimen convenientes.");
        document.add(paragraph);

        document.close();
        
        return "/java.web/resources/dist/img/app/" + "cert_" + idAlumno + ".pdf";
    }

    class Footer extends PdfPageEventHelper {
        Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
 
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            
            PdfContentByte cb = writer.getDirectContent();
            Phrase header = new Phrase("Fecha emisión: " + sdf.format(date), ffont);
            Phrase footer = new Phrase("Documento Generado a través del Sistema CEM - web.huevoscopita.cl", ffont);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    header,
                    document.right() - 30,
                    document.top() + 10, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom(), 0);
        }
    }
}
