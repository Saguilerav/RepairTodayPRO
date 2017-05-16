package ejercicio00.proyecto_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alta (View v)
    {
        this.startActivity(new Intent(this,NuevoActivity.class));
    }
    public void modificar (View v)
    {
        this.startActivity(new Intent(this,BuscarActivity.class));
    }
}
