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

public class NuevoActivity extends AppCompatActivity {
    String[] tipo= new String[] {"","Avenida","Calle","Camino","Paseo","Plaza","Travesia","Plaza"};
    String [] servicio=new String[]{"","Albañil","Carpintero","Climatizacion","Cerrajero","Electricista","Electrodomesticos","Fontanero","Pintor"};
    String tipovia="";
    String tiposervicio="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        Spinner spTipos=(Spinner) this.findViewById(R.id.spTipos);
        ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tipo);
        spTipos.setAdapter(adp1);

        spTipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0)
                {
                    Toast.makeText(NuevoActivity.this,"Seleccione tipo de via",Toast.LENGTH_LONG).show();
                }
                if (position>1)
                {
                    TextView tv=(TextView)view.findViewById(android.R.id.text1);
                    tipovia=tv.getText().toString();
                    Toast.makeText(NuevoActivity.this,"el tipo de via es: "+tipovia,Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(NuevoActivity.this,"Seleccione tipo de via",Toast.LENGTH_LONG).show();
            }
        });

        Spinner spServicios=(Spinner) this.findViewById(R.id.spServicios);
        ArrayAdapter<String> adp2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,servicio);
        spServicios.setAdapter(adp2);

        spServicios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0)
                {
                    Toast.makeText(NuevoActivity.this,"Seleccione servicio",Toast.LENGTH_LONG).show();
                }
                if (position>1)
                {
                    TextView tv=(TextView)view.findViewById(android.R.id.text1);
                    tiposervicio=tv.getText().toString();
                    Toast.makeText(NuevoActivity.this,"el servicio es: "+tipovia,Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(NuevoActivity.this,"Seleccione tipo servicio",Toast.LENGTH_LONG).show();
            }
        });

    }


    public void guardar(View v)
    {

        EditText edtNombre=(EditText)this.findViewById(R.id.edtNombre);
        EditText edtDni=(EditText)this.findViewById(R.id.edtDni);
        EditText edtCalle=(EditText)this.findViewById(R.id.edtCalle);
        EditText edtNum=(EditText)this.findViewById(R.id.edtNum);
        EditText edtCP=(EditText)this.findViewById(R.id.edtCP);
        EditText edtTel=(EditText)this.findViewById(R.id.edtTel);
        EditText edtEmail=(EditText)this.findViewById(R.id.edtEmail);

        String dir=new String (edtCalle.getText().toString()+"_"+edtNum.getText().toString()+"_"+edtCP.getText().toString());

        System.out.println(dir);

       TareaCom tc=new TareaCom();
        tc.execute(edtDni.getText().toString(),edtNombre.getText().toString(),tipovia+"_"+dir,tiposervicio,
                edtEmail.getText().toString(),edtCP.getText().toString(),edtTel.getText().toString(),"a");

        Toast.makeText(NuevoActivity.this,"Sus datos se han guardado correctamente",Toast.LENGTH_LONG).show();
        this.finish();






    }
    class TareaCom extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... params) {
            Datos prof=new Datos(params[0],params[1],params[2],params[3],params[4],params[5],params[6],params[7]);
            GestionDatos gdatos=new GestionDatos();
            gdatos.enviarDatos(prof);
            System.out.println("llamo a enviar datos a eclipse");

            return null;
        }
    }

}
