package ejercicio00.proyecto_final;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import javabeans.Datos;
import modelo.GestionDatos;

public class BuscarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

    }

    public void buscarDNI(View v)
    {
        EditText edtBuscarDNI=(EditText)this.findViewById(R.id.edtBuscarDNI);

        TareaCom2 tc2=new BuscarActivity.TareaCom2();
        tc2.execute(edtBuscarDNI.getText().toString());
        this.finish();

    }
    class TareaCom2 extends AsyncTask<String,Void,Datos> {

        @Override
        protected void onPostExecute(Datos datos) {
            Intent intent=new Intent(BuscarActivity.this,ResultActivity.class);
            intent.putExtra("DatosProf", datos);
            BuscarActivity.this.startActivity(intent);

        }

        @Override
        protected Datos doInBackground(String... params) {
            String buscarDNI=new String(params[0]);
            GestionDatos gdatos=new GestionDatos();
            Datos dt=gdatos.recuperarDatos(buscarDNI);
            return dt;
        }


    }
}
