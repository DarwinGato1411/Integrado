/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador.empresa;

import com.ec.entidad.Candidato;
import com.ec.entidad.Evaluacion;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.Usuario;
import com.ec.entidad.Vacante;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.HelperPersistencia;
import com.ec.servicio.ServicioCandidato;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioUsuario;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.internet.ParseException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;

/**
 *
 * @author gato
 */
public class GenerarEvaluacion {

    ServicioCandidato servicioCandidato = new ServicioCandidato();
    private List<Evaluacion> listaEvaluaciones = new ArrayList<Evaluacion>();
    private String buscar = "";
    ServicioParametrizar servicioParametrizar = new ServicioParametrizar();
    private Parametrizar parametrizar = new Parametrizar();

    ServicioUsuario servicioUsuario = new ServicioUsuario();
    //reporte
    AMedia fileContent = null;
    Connection con = null;
    UserCredential credential = new UserCredential();

    public GenerarEvaluacion() {
        Session sess = Sessions.getCurrent();
        credential = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        parametrizar = servicioParametrizar.findActivo();
    }

//    private void buscarCandidatoLike() {
//        listaCandidatos = servicioCandidato.findAll(buscar, credential.getUsuarioSistema());
//    }
    @Command
    @NotifyChange({"listaVacantes", "buscar"})
    public void buscarLike() {
//        buscarCandidatoLike();
    }

    @Command
    @NotifyChange({"listaVacantes", "buscar"})
    public void nuevo() {
        try {

            org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                        "/empresa/nuevo/vacante.zul", null, null);
            window.doModal();
//            buscarUsuarioLike();
        } catch (Exception e) {
            Clients.showNotification("Ocurrio un error " + e.getMessage(),
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
        }
    }

    @Command
    @NotifyChange({"listaVacantes", "buscar"})
    public void modificar(@BindingParam("valor") Vacante valor) {
        try {
//            if (Messagebox.show("¿Desea modificar el registro, recuerde que debe crear las reteniones nuevamente?", "Atención", Messagebox.YES | Messagebox.NO, Messagebox.INFORMATION) == Messagebox.YES) {
            final HashMap<String, Vacante> map = new HashMap<String, Vacante>();

            map.put("valor", valor);
            org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                        "/empresa/nuevo/vacante.zul", null, map);
            window.doModal();
        } catch (Exception e) {
            Clients.showNotification("Ocurrio un error " + e.getMessage(),
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
        }
    }

//    @Command
//    public void exportListboxToExcel() throws Exception {
//        try {
//            File dosfile = new File(exportarExcel());
//            if (dosfile.exists()) {
//                FileInputStream inputStream = new FileInputStream(dosfile);
//                Filedownload.save(inputStream, new MimetypesFileTypeMap().getContentType(dosfile), dosfile.getName());
//            }
//        } catch (FileNotFoundException e) {
//            Clients.showNotification("Ocurrio un error " + e.getMessage(),
//                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
//        }
//    }
//
//    private String exportarExcel() throws FileNotFoundException, IOException, ParseException {
//        String directorioReportes = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reportes");
//
//        Date date = new Date();
//        SimpleDateFormat fhora = new SimpleDateFormat("HH:mm");
//        SimpleDateFormat sm = new SimpleDateFormat("yyy-MM-dd");
//        String strDate = sm.format(date);
//
//        String pathSalida = directorioReportes + File.separator + "compras.xls";
//        System.out.println("Direccion del reporte  " + pathSalida);
//        try {
//            int j = 0;
//            File archivoXLS = new File(pathSalida);
//            if (archivoXLS.exists()) {
//                archivoXLS.delete();
//            }
//            archivoXLS.createNewFile();
//            FileOutputStream archivo = new FileOutputStream(archivoXLS);
//            HSSFWorkbook wb = new HSSFWorkbook();
//            HSSFSheet s = wb.createSheet("Compras");
//
//            HSSFFont fuente = wb.createFont();
//            fuente.setBoldweight((short) 700);
//            HSSFCellStyle estiloCelda = wb.createCellStyle();
//            estiloCelda.setWrapText(true);
//            estiloCelda.setAlignment((short) 2);
//            estiloCelda.setFont(fuente);
//
//            HSSFCellStyle estiloCeldaInterna = wb.createCellStyle();
//            estiloCeldaInterna.setWrapText(true);
//            estiloCeldaInterna.setAlignment((short) 5);
//            estiloCeldaInterna.setFont(fuente);
//
//            HSSFCellStyle estiloCelda1 = wb.createCellStyle();
//            estiloCelda1.setWrapText(true);
//            estiloCelda1.setFont(fuente);
//
//            HSSFRow r = null;
//
//            HSSFCell c = null;
//            r = s.createRow(0);
//
//            HSSFCell ch0 = r.createCell(j++);
//            ch0.setCellValue(new HSSFRichTextString("Nombre"));
//            ch0.setCellStyle(estiloCelda);
//
//            HSSFCell ch1 = r.createCell(j++);
//            ch1.setCellValue(new HSSFRichTextString("Descripcion"));
//            ch1.setCellStyle(estiloCelda);
//
//            HSSFCell ch2 = r.createCell(j++);
//            ch2.setCellValue(new HSSFRichTextString("Inicio"));
//            ch2.setCellStyle(estiloCelda);
//
//            HSSFCell ch3 = r.createCell(j++);
//            ch3.setCellValue(new HSSFRichTextString("Fin"));
//            ch3.setCellStyle(estiloCelda);
//
//            HSSFCell ch4 = r.createCell(j++);
//            ch4.setCellValue(new HSSFRichTextString("Tipo contratacion"));
//            ch4.setCellStyle(estiloCelda);
//
//            HSSFCell ch5 = r.createCell(j++);
//            ch5.setCellValue(new HSSFRichTextString("Sueldo"));
//            ch5.setCellStyle(estiloCelda);
//
//            int rownum = 1;
//            int i = 0;
//
//            for (Vacante item : listaVacantes) {
//                i = 0;
//
//                r = s.createRow(rownum);
//
//                HSSFCell c0 = r.createCell(i++);
//                c0.setCellValue(new HSSFRichTextString(item.getVacNombre()));
//
//                HSSFCell c1 = r.createCell(i++);
//                c1.setCellValue(new HSSFRichTextString(item.getVacDescripcion()));
//
//                HSSFCell c2 = r.createCell(i++);
//                c2.setCellValue(new HSSFRichTextString(sm.format(item.getVacFechaInicio())));
//
//                HSSFCell c3 = r.createCell(i++);
//                c3.setCellValue(new HSSFRichTextString(sm.format(item.getVacFechaFin())));
//
//                HSSFCell c4 = r.createCell(i++);
//                c4.setCellValue(new HSSFRichTextString(item.getIdTipoContratacion().getTipcNombre().toString()));
//
//                HSSFCell c5 = r.createCell(i++);
//                c5.setCellValue(new HSSFRichTextString(item.getVacSueldo().toString()));
//
//                /*autemta la siguiente fila*/
//                rownum += 1;
//
//            }
//            for (int k = 0; k <= listaVacantes.size(); k++) {
//                s.autoSizeColumn(k);
//            }
//            wb.write(archivo);
//            archivo.close();
//
//        } catch (IOException e) {
//            System.out.println("error " + e.getMessage());
//        }
//        return pathSalida;
//
//    }
    @Command
    public void reporteGeneral(Integer idCabera) throws JRException, IOException, NamingException, SQLException {
        EntityManager emf = HelperPersistencia.getEMF();
        try {

            emf.getTransaction().begin();
            con = emf.unwrap(Connection.class);
            String reportFile = Executions.getCurrent().getDesktop().getWebApp()
                        .getRealPath("/reportes");
            String reportPath = reportFile + File.separator + "facturacompra.jasper";

            Map<String, Object> parametros = new HashMap<String, Object>();

            //  parametros.put("codUsuario", String.valueOf(credentialLog.getAdUsuario().getCodigoUsuario()));
            parametros.put("id_cabecera", idCabera);

            if (con != null) {
                System.out.println("Conexión Realizada Correctamenteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            }
            FileInputStream is = null;
            is = new FileInputStream(reportPath);

            byte[] buf = JasperRunManager.runReportToPdf(is, parametros, con);
            InputStream mediais = new ByteArrayInputStream(buf);
            AMedia amedia = new AMedia("Reporte", "pdf", "application/pdf", mediais);
            fileContent = amedia;
            final HashMap<String, AMedia> map = new HashMap<String, AMedia>();
            //para pasar al visor
            map.put("pdf", fileContent);
            org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                        "/venta/contenedorReporte.zul", null, map);
            window.doModal();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException " + e.getMessage());
        } catch (JRException e) {
            System.out.println("JRException " + e.getMessage());
        } finally {
            if (emf != null) {
                emf.getTransaction().commit();
            }

        }

    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public List<Evaluacion> getListaEvaluaciones() {
        return listaEvaluaciones;
    }

    public void setListaEvaluaciones(List<Evaluacion> listaEvaluaciones) {
        this.listaEvaluaciones = listaEvaluaciones;
    }

    @Command
    public void emportarPlantillaEvaluados() throws Exception {
        try {
            File dosfile = new File(exportarExcel());
            if (dosfile.exists()) {
                FileInputStream inputStream = new FileInputStream(dosfile);
                Filedownload.save(inputStream, new MimetypesFileTypeMap().getContentType(dosfile), dosfile.getName());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR AL DESCARGAR EL ARCHIVO" + e.getMessage());
        }
    }

    private String exportarExcel() throws FileNotFoundException, IOException, ParseException {
        String directorioReportes = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reportes");

        Date date = new Date();
        SimpleDateFormat fhora = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sm = new SimpleDateFormat("yyy-MM-dd");
        String strDate = sm.format(date);

        String pathSalida = directorioReportes + File.separator + "plantillaevaluados.xls";
        System.out.println("Direccion del reporte  " + pathSalida);
        try {
            int j = 1;
            File archivoXLS = new File(pathSalida);
            if (archivoXLS.exists()) {
                archivoXLS.delete();
            }
            archivoXLS.createNewFile();
            FileOutputStream archivo = new FileOutputStream(archivoXLS);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet s = wb.createSheet("Plantilla");

            HSSFFont fuente = wb.createFont();
            fuente.setBoldweight((short) 700);
            HSSFCellStyle estiloCelda = wb.createCellStyle();
            estiloCelda.setWrapText(true);
            estiloCelda.setAlignment((short) 2);
            estiloCelda.setFont(fuente);

            HSSFCellStyle estiloCeldaInterna = wb.createCellStyle();
            estiloCeldaInterna.setWrapText(true);
            estiloCeldaInterna.setAlignment((short) 5);
            estiloCeldaInterna.setFont(fuente);

            HSSFCellStyle estiloCelda1 = wb.createCellStyle();
            estiloCelda1.setWrapText(true);
            estiloCelda1.setFont(fuente);

            HSSFRow r = null;

            HSSFCell c = null;
            r = s.createRow(0);

            HSSFCell chfe = r.createCell(0);
            chfe.setCellValue(new HSSFRichTextString("Cédula"));
            chfe.setCellStyle(estiloCelda);
//            chfe.setCellType(chfe.CELL_TYPE_STRING);

            HSSFCell ch1 = r.createCell(j++);
            ch1.setCellValue(new HSSFRichTextString("Nombre"));
            ch1.setCellStyle(estiloCelda);

            HSSFCell ch2 = r.createCell(j++);
            ch2.setCellValue(new HSSFRichTextString("Correo"));
            ch2.setCellStyle(estiloCelda);

            HSSFCell ch21 = r.createCell(j++);
            ch21.setCellValue(new HSSFRichTextString("Dirección"));
            ch21.setCellStyle(estiloCelda);

            wb.write(archivo);
            archivo.close();

        } catch (IOException e) {
            System.out.println("error " + e.getMessage());
        }
        return pathSalida;

    }

    @Command
    @NotifyChange({"listaProductosModel", "buscarNombre"})
    public void cargarEvaluados() {

        try {
            org.zkoss.util.media.Media media = Fileupload.get();
            if (media instanceof org.zkoss.util.media.AMedia) {
                String nombre = media.getName();

                if (!nombre.contains("xls")) {
                    Clients.showNotification("Su documento debe ser un archivo excel",
                                Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);

                    return;
                }

                System.out.println("media " + nombre);
                Files.copy(new File(parametrizar.getParBase() + File.separator + "CARGAR" + File.separator + nombre),
                            new ByteArrayInputStream(media.getByteData()));

                String rutaArchivo = parametrizar.getParBase() + File.separator + "CARGAR" + File.separator + nombre;

                InputStream myFile = new FileInputStream(new File(rutaArchivo));
                HSSFWorkbook wb = new HSSFWorkbook(myFile);
                HSSFSheet sheet = wb.getSheetAt(0);

                HSSFCell cell;
                HSSFRow row;

                System.out.println("antes de entrar a loops");

                System.out.println("" + sheet.getLastRowNum());
                Candidato candidato = new Candidato();
                Usuario usuario = new Usuario();
                for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                    row = sheet.getRow(i);
//                    for (int j = 0; j < row.getLastCellNum(); j++) {

                    HSSFCell ss = row.getCell(0);
                    String cedula = String.valueOf(new BigDecimal(row.getCell(0).getNumericCellValue()));
                    System.out.println("cedula " + cedula);

                    Candidato candidatoRecup = servicioCandidato.findByCandidatoCedula(cedula);
                    if (candidatoRecup == null) {
//                            cell = row.getCell(j);
                        candidato = new Candidato();
                        usuario = new Usuario();

                        candidato = new Candidato();
                        candidato.setCanDireccion(String.valueOf(row.getCell(3)));
                        usuario.setUsuRuc(cedula);
                        usuario.setUsuNombre(String.valueOf(row.getCell(1)));
                        usuario.setUsuCorreo(String.valueOf(row.getCell(2)));
                        usuario.setUsuLogin(cedula);
                        usuario.setUsuPassword(cedula);
                        usuario.setUsuNivel(1);
                        usuario.setUsuTipoUsuario("CANDIDATO");
                        servicioUsuario.crear(usuario);
                        //*asigna el usuario al candidato*/                    
                        candidato.setIdUsuario(usuario);
                        servicioCandidato.crear(candidato);
//                            System.out.println("Valor: " + cell.toString());
                    } else {

                    }

                }
                System.out.println("Finalizado");

                Clients.showNotification("Productos cargados correctamente",
                            Clients.NOTIFICATION_TYPE_INFO, null, "end_center", 3000, true);
            }
        } catch (IOException e) {
            Clients.showNotification("Verifique le archivo para cargar",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
            e.printStackTrace();
//            Messagebox.show("Upload failed");
        }

    }

}
