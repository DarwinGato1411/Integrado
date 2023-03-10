/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador.empresa;

import com.ec.entidad.Canton;
import com.ec.entidad.Ciudad;
import com.ec.entidad.Empresa;
import com.ec.entidad.GiroEmpresa;
import com.ec.entidad.Pais;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.Usuario;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioCanton;
import com.ec.servicio.ServicioCiudad;
import com.ec.servicio.ServicioEmpresa;
import com.ec.servicio.ServicioGiroEmpresa;
import com.ec.servicio.ServicioPais;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioUsuario;
import com.ec.utilitario.ArchivoUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.io.Files;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author Darwin
 */
public class EmpresaCtr {

    private Empresa empresa;
    private Usuario usuario;
    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioEmpresa servicioEmpresa = new ServicioEmpresa();
    UserCredential credential = new UserCredential();

    ServicioGiroEmpresa servicioGiroEmpresa = new ServicioGiroEmpresa();
    private List<GiroEmpresa> listaGiro = new ArrayList<GiroEmpresa>();

    ServicioPais servicioPais = new ServicioPais();
    private List<Pais> listaPais = new ArrayList<Pais>();
    private Pais paisSelected = new Pais();
    ServicioCiudad servicioCiudad = new ServicioCiudad();
    private List<Ciudad> listaCiudad = new ArrayList<Ciudad>();
    private Ciudad ciudadSeleted = new Ciudad();
    ServicioCanton servicioCanton = new ServicioCanton();
    private List<Canton> listaCanton = new ArrayList<Canton>();

    ServicioParametrizar servicioParametrizar = new ServicioParametrizar();
    private Parametrizar parametrizar = new Parametrizar();
    //subir pdf
    private String filePath;
    byte[] buffer = new byte[1024 * 1024];
    private AImage fotoGeneral = null;

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("img") String recibido, @ContextParam(ContextType.VIEW) Component view) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, JRException, IOException {
        Selectors.wireComponents(view, this, false);
        if (empresa.getIdUsuario().getUsuFoto() != null) {
            try {
                System.out.println("PATH" + empresa.getIdUsuario().getUsuFoto());
                 fotoGeneral = new AImage("fotoPedido", Imagen_A_Bytes(empresa.getIdUsuario().getUsuFoto()));
//                Imagen_A_Bytes(empresa.getIdUsuario().getUsuFoto());
            } catch (FileNotFoundException e) {
                System.out.println("error imagen " + e.getMessage());
            }
        }
    }

    public EmpresaCtr() {
        Session sess = Sessions.getCurrent();
        credential = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        empresa = servicioEmpresa.findByUsuario(credential.getUsuarioSistema());
        if (empresa.getIdUsuario().getIdCanton() != null) {
            paisSelected = empresa.getIdUsuario().getIdCanton().getIdCiudad().getIdPais();
            ciudadSeleted = empresa.getIdUsuario().getIdCanton().getIdCiudad();
//            paisSelected=empresa.getIdUsuario().getIdCanton();
            buscarCanton();
        }

        cargarDatos();
        parametrizar = servicioParametrizar.findActivo();
    }

    @Command
    public void guardar() {

//        empresa.setIdGiroEmpresa(giroEmpresaSeleted);
        servicioEmpresa.modificar(empresa);
        usuario = empresa.getIdUsuario();
        servicioUsuario.modificar(usuario);
        Clients.showNotification("Guardado correctamente... ",
                    Clients.NOTIFICATION_TYPE_INFO, null, "end_center", 3000, true);
    }

    private void cargarDatos() {
        listaGiro = servicioGiroEmpresa.findAll();
        listaPais = servicioPais.findAll();
        paisSelected = listaPais.get(0);
        listaCiudad = servicioCiudad.findByPais(paisSelected);
//        listaCanton = servicioCanton.findAll();
    }

    @Command
    @NotifyChange({"listaCiudad"})
    public void buscarCiudad() {
        listaCiudad = servicioCiudad.findByPais(paisSelected);
//        ciudadSeleted=null;
    }

    @Command
    @NotifyChange({"listaCanton"})
    public void buscarCanton() {
        listaCanton = servicioCanton.findByCiudad(ciudadSeleted);
    }

    @Command
    @NotifyChange({"fileContent", "empresa", "fotoGeneral"})
    public void subirLogotipo() throws InterruptedException, IOException {

        org.zkoss.util.media.Media media = Fileupload.get();
        if (media instanceof org.zkoss.image.Image) {
            String nombre = media.getName();

            if (media.getByteData().length > 10 * 1024 * 1024) {
                Messagebox.show("El arhivo seleccionado sobrepasa el tama??o de 10Mb.\n Por favor seleccione un archivo m??s peque??o.", "Atenci??n", Messagebox.OK, Messagebox.ERROR);

                return;
            }
            filePath = parametrizar.getParBase() + File.separator + parametrizar.getParImagenes() + File.separator;

            File baseDir = new File(filePath);
            if (!baseDir.exists()) {
                baseDir.mkdirs();
            }
            Files.copy(new File(filePath + File.separator + media.getName()),
                        media.getStreamData());
            Usuario usuFoto = empresa.getIdUsuario();
            usuFoto.setUsuFoto(filePath + File.separator + media.getName());
            System.out.println("PATH SUBIR " + filePath + File.separator + media.getName());
            fotoGeneral = new AImage("fotoPedido", Imagen_A_Bytes(filePath + File.separator + media.getName()));

        }
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<GiroEmpresa> getListaGiro() {
        return listaGiro;
    }

    public void setListaGiro(List<GiroEmpresa> listaGiro) {
        this.listaGiro = listaGiro;
    }

    public List<Pais> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Pais> listaPais) {
        this.listaPais = listaPais;
    }

    public Pais getPaisSelected() {
        return paisSelected;
    }

    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
    }

    public List<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    public void setListaCiudad(List<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;
    }

    public Ciudad getCiudadSeleted() {
        return ciudadSeleted;
    }

    public void setCiudadSeleted(Ciudad ciudadSeleted) {
        this.ciudadSeleted = ciudadSeleted;
    }

    public List<Canton> getListaCanton() {
        return listaCanton;
    }

    public void setListaCanton(List<Canton> listaCanton) {
        this.listaCanton = listaCanton;
    }

    public AImage getFotoGeneral() {
        return fotoGeneral;
    }

    public void setFotoGeneral(AImage fotoGeneral) {
        this.fotoGeneral = fotoGeneral;
    }

    public byte[] Imagen_A_Bytes(String pathImagen) throws FileNotFoundException {
        String reportPath = "";
        reportPath = pathImagen;
        File file = new File(reportPath);

        FileInputStream fis = new FileInputStream(file);
        //create FileInputStream which obtains input bytes from a file in a file system
        //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                //Writes to this byte array output stream
                bos.write(buf, 0, readNum);
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
        }

        byte[] bytes = bos.toByteArray();
        return bytes;
    }
}
