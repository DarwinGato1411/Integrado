/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador.empresa;

import com.ec.controlador.modelos.ModeloCandidato;
import com.ec.controlador.modelos.ParametrosGenerarEvaluacion;
import com.ec.entidad.Candidato;
import com.ec.entidad.Evaluacion;
import com.ec.entidad.Factura;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.Test;
import com.ec.entidad.Usuario;
import com.ec.entidad.Vacante;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.HelperPersistencia;
import com.ec.servicio.ServicioCandidato;
import com.ec.servicio.ServicioGeneral;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioTest;
import com.ec.servicio.ServicioUsuario;
import com.ec.servicio.ServicioVersionTest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class GenerarEvaluacion {

    @Wire
    Window wBuscarCandidato;

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
    /*Test a selecionar*/
    private String buscarTest = "";
    ServicioTest servicioTest = new ServicioTest();
    private ListModelList<Test> listaTestModel;
    private List<Test> listaTestDatos = new ArrayList<Test>();
    public static Set<Test> seleccionTest = new HashSet<Test>();

    /*candidatos a selecionar*/
    private String buscarCandidato = "";
    private ListModelList<Candidato> listaCandidatoModel = new ListModelList<Candidato>();
    private List<Candidato> listaCandidatoDatos = new ArrayList<Candidato>();
    public static Set<Candidato> seleccionCandidato = new HashSet<Candidato>();

    /*BUSCAR CANDIDATOS DESDE LA BASE DE DATOS*/
    private String buscarCandidatoBase = "";
    private ListModelList<Candidato> listaCandidatoBaseModel = new ListModelList<Candidato>();
    private List<Candidato> listaCandidatoBaseDatos = new ArrayList<Candidato>();
    public static Set<Candidato> seleccionCandidatoBase = new HashSet<Candidato>();

    /*BUSCAR CANDIDATOS TEST SELECCIONADO*/
    private List<ModeloCandidato> candidatosTestSeleccion = new ArrayList<ModeloCandidato>();


    /*Generar Evaluaciones*/
    ServicioGeneral servicioGeneral = new ServicioGeneral();

    ServicioVersionTest servicioVersionTest = new ServicioVersionTest();
    private List<Integer> listaVersiones = new ArrayList<Integer>();
    private Integer versionSelected = 0;
    private Boolean nuevaVersion = false;
    private Test testSelected = null;

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") ParametrosGenerarEvaluacion valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);

        ((ListModelList<Candidato>) listaCandidatoModel).setMultiple(true);
//         ((ListModelList<Candidato>) listaCandidatoBaseModel).setMultiple(true);
        if (valor != null) {
            if (valor.getBandera().equals("CANDIDATO")) {
                getObtenerCandidatoBase();
            }
        }
    }

    @Command
    @NotifyChange({"nuevaVersion"})
    public void verificarVersion() {
        if (nuevaVersion) {
            System.out.println("NUEVA VERSION");
        }else{
         System.out.println("VERSION ANTERIOR");
        }
    }
    @Command
    @NotifyChange({"seleccionCandidatoBase", "nuevaVersion","candidatosTestSeleccion"})
    public void generarEvaluacion() {

        if (!seleccionTest.isEmpty()) {
            Integer evaluacion = 0;
            for (Test itemTest : seleccionTest) {
                evaluacion = itemTest.getIdTest();
            }
            List<Integer> listaCandidatosParam = new ArrayList<Integer>();
            for (Candidato candidato : listaCandidatoModel) {
                listaCandidatosParam.add(candidato.getIdCandidato());
            }
            if (nuevaVersion) {
                if (versionSelected > 0) {
                    versionSelected = versionSelected + 1;
                } else {
                    versionSelected = 1;
                }
            }else{
                if (versionSelected==0) {
                     Clients.showNotification("Debe seleccionar una version o crear una nueva ",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
                     return;
                }
            }
            servicioGeneral.generarEvaluacion(evaluacion, listaCandidatosParam, versionSelected);
            Clients.showNotification("Test generado correctamente ",
                        Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 2000, true);
            nuevaVersion = Boolean.FALSE;
             getCandidatosTestSelected();
        } else {
            Clients.showNotification("Debe seleccionar un Test ",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
        }
    }

    @Command
    @NotifyChange({"seleccionCandidatoBase"})
    public void buscarCandidatoBaseLista() {

        if (testSelected == null) {
            Clients.showNotification("Debe seleccionar un Test",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
            return;
        }
        ParametrosGenerarEvaluacion paramFactura = new ParametrosGenerarEvaluacion();
        paramFactura.setBandera("CANDIDATO");
        final HashMap<String, ParametrosGenerarEvaluacion> map = new HashMap<String, ParametrosGenerarEvaluacion>();
        map.put("valor", paramFactura);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                    "/empresa/buscarcandidatos.zul", null, map);
        window.doModal();
        for (Candidato candidato : seleccionCandidatoBase) {
            if (!listaCandidatoModel.contains(candidato)) {
                ((ListModelList<Candidato>) listaCandidatoModel).add(candidato);
            }
        }

    }

    @Command
    public void seleccionarRegistrosCandidatoBase() {
        wBuscarCandidato.detach();
    }

    /*Buscar candidato de la base de datos */
    @Command
    @NotifyChange({"listaCandidatoBaseModel", "buscarCandidatoBase"})
    public void buscarCandidatoBase() {
        getObtenerCandidatoBase();
    }

    private void getObtenerCandidatoBase() {
        listaCandidatoBaseDatos = servicioCandidato.findCandidatoLike(buscarCandidatoBase);
        setListaCandidatoBaseModel(new ListModelList<Candidato>(getListaCandidatoBaseDatos()));
        ((ListModelList<Candidato>) listaCandidatoBaseModel).setMultiple(true);
    }

    @Command
    public void seleccionarBuscarCandidato() {
        seleccionCandidatoBase = ((ListModelList<Candidato>) getListaCandidatoBaseModel()).getSelection();
    }

    public String getBuscarCandidatoBase() {
        return buscarCandidatoBase;
    }

    public void setBuscarCandidatoBase(String buscarCandidatoBase) {
        this.buscarCandidatoBase = buscarCandidatoBase;
    }

    public ListModelList<Candidato> getListaCandidatoBaseModel() {
        return listaCandidatoBaseModel;
    }

    public void setListaCandidatoBaseModel(ListModelList<Candidato> listaCandidatoBaseModel) {
        this.listaCandidatoBaseModel = listaCandidatoBaseModel;
    }

    public List<Candidato> getListaCandidatoBaseDatos() {
        return listaCandidatoBaseDatos;
    }

    public void setListaCandidatoBaseDatos(List<Candidato> listaCandidatoBaseDatos) {
        this.listaCandidatoBaseDatos = listaCandidatoBaseDatos;
    }

    public static Set<Candidato> getSeleccionCandidatoBase() {
        return seleccionCandidatoBase;
    }

    public static void setSeleccionCandidatoBase(Set<Candidato> seleccionCandidatoBase) {
        GenerarEvaluacion.seleccionCandidatoBase = seleccionCandidatoBase;
    }

    public GenerarEvaluacion() {
        Session sess = Sessions.getCurrent();
        credential = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        parametrizar = servicioParametrizar.findActivo();

        getTest();
    }

    /*TEST */
    @Command
    @NotifyChange({"listaTestModel", "valor"})
    public void buscarTest() {
        getTest();
    }

    private void getTest() {
        listaTestDatos = servicioTest.findByUsuarioTestNombre(credential.getUsuarioSistema(), buscarTest);
        setListaTestModel(new ListModelList<Test>(getListaTestDatos()));
        ((ListModelList<Test>) listaTestModel).setMultiple(false);
    }

    @Command
    @NotifyChange({"listaVersiones", "nuevaVersion"})
    public void seleccionarTest() {
        seleccionTest = ((ListModelList<Test>) getListaTestModel()).getSelection();
        for (Test test : seleccionTest) {
            listaVersiones = servicioVersionTest.findVersionTest(test);
            testSelected = test;
        }
        if (listaVersiones.isEmpty()) {
            nuevaVersion = Boolean.TRUE;
        }
    }

    /*Buscar candidato del test seleccionado */
    @Command
    @NotifyChange({"candidatosTestSeleccion", "versionSelected"})
    public void buscarCandidatoTestSeleccionado() {
        getCandidatosTestSelected();
    }

    private void getCandidatosTestSelected() {
        candidatosTestSeleccion = servicioCandidato.findCandidatoForTest(testSelected, versionSelected);

    }

    public List<ModeloCandidato> getCandidatosTestSeleccion() {
        return candidatosTestSeleccion;
    }

    public void setCandidatosTestSeleccion(List<ModeloCandidato> candidatosTestSeleccion) {
        this.candidatosTestSeleccion = candidatosTestSeleccion;
    }

    public ListModelList<Test> getListaTestModel() {
        return listaTestModel;
    }

    public void setListaTestModel(ListModelList<Test> listaTestModel) {
        this.listaTestModel = listaTestModel;
    }

    public List<Test> getListaTestDatos() {
        return listaTestDatos;
    }

    public void setListaTestDatos(List<Test> listaTestDatos) {
        this.listaTestDatos = listaTestDatos;
    }

    public String getBuscarTest() {
        return buscarTest;
    }

    public void setBuscarTest(String buscarTest) {
        this.buscarTest = buscarTest;
    }

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
    @NotifyChange({"listaCandidatoModel", "valor"})
    public void cargarEvaluados() {

        try {

            if (testSelected == null) {
                Clients.showNotification("Debe seleccionar un Test",
                            Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
                return;
            }
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
                    String cedula = String.valueOf(new BigDecimal(Double.valueOf(String.valueOf(row.getCell(0)))));
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
                        if (!listaCandidatoModel.contains(candidato)) {
                            ((ListModelList<Candidato>) listaCandidatoModel).add(candidato);
                        }

                    } else {
//                        System.out.println("CONTIENE " + listaCandidatoModel.remove(candidatoRecup));
                        if (!listaCandidatoModel.contains(candidatoRecup)) {
                            ((ListModelList<Candidato>) listaCandidatoModel).add(candidatoRecup);
                        }

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

    @Command
    public void seleccionarCandidato() {
        seleccionCandidato = ((ListModelList<Candidato>) getListaCandidatoModel()).getSelection();
    }
    //busqueda del producto

    @Command
    @NotifyChange({"listaCandidatoModel"})
    public void eliminarCandidatosSeleccionados() {
        if (seleccionCandidato.size() > 0) {
            ((ListModelList<Candidato>) listaCandidatoModel).removeAll(seleccionCandidato);
        } else {
            Clients.showNotification("Debe seleccionar un registro para poder borrar",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }

    }

    public String getBuscarCandidato() {
        return buscarCandidato;
    }

    public void setBuscarCandidato(String buscarCandidato) {
        this.buscarCandidato = buscarCandidato;
    }

    public ListModelList<Candidato> getListaCandidatoModel() {
        return listaCandidatoModel;
    }

    public void setListaCandidatoModel(ListModelList<Candidato> listaCandidatoModel) {
        this.listaCandidatoModel = listaCandidatoModel;
    }

    public List<Candidato> getListaCandidatoDatos() {
        return listaCandidatoDatos;
    }

    public void setListaCandidatoDatos(List<Candidato> listaCandidatoDatos) {
        this.listaCandidatoDatos = listaCandidatoDatos;
    }

    public List<Integer> getListaVersiones() {
        return listaVersiones;
    }

    public void setListaVersiones(List<Integer> listaVersiones) {
        this.listaVersiones = listaVersiones;
    }

    public Integer getVersionSelected() {
        return versionSelected;
    }

    public void setVersionSelected(Integer versionSelected) {
        this.versionSelected = versionSelected;
    }

    public Boolean getNuevaVersion() {
        return nuevaVersion;
    }

    public void setNuevaVersion(Boolean nuevaVersion) {
        this.nuevaVersion = nuevaVersion;
    }

}
