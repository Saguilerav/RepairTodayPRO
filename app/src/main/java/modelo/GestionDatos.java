package modelo;

/**
 * Created by USUARIO on 25/04/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import ejercicio00.proyecto_final.BuscarActivity;
import javabeans.Datos;


/**
 * Created by USUARIO on 25/04/2017.
 */

public class GestionDatos {
    Socket sc;

    //si al mandar el json con todo recibe señal de que el dni ya existe sale un toast.

    public void enviarDatos(Datos profesional){
        try {
            sc= new Socket("192.168.0.117", 8000);
            PrintStream salida = new PrintStream(sc.getOutputStream());
            BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            JSONObject job = new JSONObject();
            job.put("dni", profesional.getDni());
            job.put("nombre", profesional.getNombre());
            job.put("telefono", profesional.getTelefono());
            job.put("email", profesional.getEmail());
            job.put("servicio", profesional.getServicio());
            job.put("direccion",profesional.getDireccion());
            job.put("cp",profesional.getCp());
            job.put("opcion",profesional.getOpcion());
            salida.println(job.toString());
            System.out.println("envio algo");
            sc.close();

        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public Datos recuperarDatos(String dni) {
        Datos dt=null;
        try {
            JSONObject job = new JSONObject();
            job.put("dni", dni);
            sc = new Socket("192.168.0.117", 8000);
            PrintStream salida = new PrintStream(sc.getOutputStream());
            BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            //enviamos objeto al servidor
            salida.println(job.toString());
            //recogemos el array JSON con los datos del profesional
            JSONObject jobRespuesta=new JSONObject(bf.readLine());
            dt=new Datos(jobRespuesta.getString("dni"),
                    jobRespuesta.getString("nombre"),
                    jobRespuesta.getString("direccion"),
                    jobRespuesta.getString("servicio"),
                    jobRespuesta.getString("email"),
                    jobRespuesta.getString("cp"),
                    jobRespuesta.getString("telefono"),
                    "b");
            //cierre del socket
            System.out.println(jobRespuesta.getString("cp"));
            sc.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dt;

    }


}