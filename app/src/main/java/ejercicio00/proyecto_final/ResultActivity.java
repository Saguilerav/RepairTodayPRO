package ejercicio00.proyecto_final;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import javabeans.Datos;
import modelo.GestionDatos;

public class ResultActivity extends AppCompatActivity {
    String tipovia="";
    String tiposervicio="";
    EditText edtNombreR;
    EditText edtDniR;
    EditText edtCalleR;
    EditText edtNumR;
    EditText edtCPR;
    EditText edtTelR;
    EditText edtEmailR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent=this.getIntent();
        //recuperamos el dato enviado por la actividad BuscarActivity
        Datos resultado=(Datos)intent.getSerializableExtra("DatosProf");

        edtNombreR=(EditText) this.findViewById(R.id.edtNombreR);
        edtDniR=(EditText) this.findViewById(R.id.edtDniR);
        edtCalleR=(EditText) this.findViewById(R.id.edtCalleR);
        edtNumR=(EditText) this.findViewById(R.id.edtNumR);
        edtCPR=(EditText) this.findViewById(R.id.edtCPR);
        edtTelR=(EditText) this.findViewById(R.id.edtTelR);
        edtEmailR=(EditText) this.findViewById(R.id.edtEmailR);
        edtNombreR.setText(resultado.getNombre());
        System.out.println("nombre: "+resultado.getNombre());

        edtDniR.setText(resultado.getDni());
        System.out.println("dni: "+resultado.getDni());
        String direccion= resultado.getDireccion();
        String dir[]= direccion.split("_");
        System.out.println(dir.length);
        String num=dir[dir.length-2];
        String calle="";
        for (int i=1;i<dir.length-2;i++)
        {
            calle=calle+""+dir[i];
        }

        edtCalleR.setText(calle);
        edtNumR.setText(num);

        Spinner spTiposR=(Spinner) this.findViewById(R.id.spTiposR);
        ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new String[]{dir[0], "Avenida", "Calle", "Camino", "Paseo", "Plaza", "Travesia", "Plaza"});
        spTiposR.setAdapter(adp1);

        spTiposR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    TextView tv=(TextView)view.findViewById(android.R.id.text1);
                    tipovia=tv.getText().toString();
                    Toast.makeText(ResultActivity.this,"el tipo de via es: "+tipovia,Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ResultActivity.this,"Seleccione tipo de via",Toast.LENGTH_LONG).show();
            }
        });


        edtCPR.setText(resultado.getCp());

        Spinner spServicios=(Spinner) this.findViewById(R.id.spServiciosR);
        ArrayAdapter<String> adp2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{resultado.getServicio(),"Albañil","Carpintero","Climatizacion","Cerrajero","Electricista","Electrodomesticos","Fontanero","Pintor"});
        spServicios.setAdapter(adp2);

        spServicios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    TextView tv=(TextView)view.findViewById(android.R.id.text1);
                    tiposervicio=tv.getText().toString();
                    Toast.makeText(ResultActivity.this,"el servicio es: "+tiposervicio,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ResultActivity.this,"Seleccione tipo servicio",Toast.LENGTH_LONG).show();
            }
        });


        edtTelR.setText(resultado.getTelefono());

        edtEmailR.setText(resultado.getEmail());
    }


    public void guardarC(View v)
    {


        String dir=new String (edtCalleR.getText().toString()+"_"+edtNumR.getText().toString()+"_"+edtCPR.getText().toString());

        System.out.println(dir);


        ResultActivity.TareaCom3 tc=new ResultActivity.TareaCom3();
        tc.execute(edtDniR.getText().toString(),edtNombreR.getText().toString(),tipovia+"_"+dir,tiposervicio,
                edtEmailR.getText().toString(),edtCPR.getText().toString(),edtTelR.getText().toString(),"c");
        Toast.makeText(ResultActivity.this,"Los cambios se han guardado correctamente",Toast.LENGTH_LONG).show();
        this.finish();






    }
    class TareaCom3 extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... params) {
            Datos prof=new Datos(params[0],params[1],params[2],params[3],params[4],params[5],params[6],params[7]);
            GestionDatos gdatos=new GestionDatos();
            gdatos.enviarDatos(prof);
            System.out.println("llamo a enviar");

            return null;
        }
    }




}
