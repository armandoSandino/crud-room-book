package com.pineda.bibliotecaon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.pineda.bibliotecaon.DB.AppDatabase;
import com.pineda.bibliotecaon.ETC.Util;
import com.pineda.bibliotecaon.Entity.Usuario;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Principal extends AppCompatActivity implements
        View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    //@BindView(R.id.lbNombreNavHeaderPrincipal)
    TextView lbNombreNavHeaderPrincipal, lbCorreoNavHeaderPrincipal;
    de.hdodenhof.circleimageview.CircleImageView imv_iconoAvatarUsuario;
    private Usuario usuario;
    private AppDatabase db;
    private DrawerLayout drawer ;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    /*
    @BindView(R.id.navRegistrarLibro)
    MenuItem navRegistrarLibro;
    @BindView(R.id.navcambiarContrasena)
    MenuItem navcambiarContrasena;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        /////
        ButterKnife.bind( this );
        db = Room.databaseBuilder( this, AppDatabase.class, Util.nombreDb )
                .allowMainThreadQueries()
                .build();
        obtenerInformacionUsuario();
        //////

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        cargarNavigationView();
    }
    private void cargarNavigationView() {
        drawer = findViewById(R.id.drawer_layout);
        if ( true ) {
            navigationView = findViewById(R.id.nav_view);
        }

        if ( navigationView != null ) {
            View vista = navigationView.getHeaderView(0);
            lbNombreNavHeaderPrincipal = (TextView) vista.findViewById(R.id.lbNombreNavHeaderPrincipal);
            lbNombreNavHeaderPrincipal.setText( usuario.getNombre() +" "+ usuario.getApellido() );
            lbCorreoNavHeaderPrincipal = (TextView) vista.findViewById(R.id.lbCorreoNavHeaderPrincipal);
            lbCorreoNavHeaderPrincipal.setText( usuario.getCorreo() );
            imv_iconoAvatarUsuario = (de.hdodenhof.circleimageview.CircleImageView) vista.findViewById(R.id.imv_iconoAvatarUsuario);
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        if( true ) {
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home ,R.id.nav_galleryInvitado,
                    R.id.navRegistrarLibro, R.id.navBuscarLibro ,
                    R.id.navcambiarContrasena)
                    .setDrawerLayout(drawer)
                    .build();

            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment );
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
            /*
                        mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.navBuscarLibro, R.id.navRegistrarLibro, R.id.navcambiarContrasena)
                    .setDrawerLayout(drawer)
                    .build();

            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
            * */
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navCerrarSesion:
                this.finish();
                // You can handle other cases Here.
            default:
                return  super.onOptionsItemSelected(item);
        }
    }
    private void obtenerInformacionUsuario() {
        Intent in  = getIntent();
        int res =  in.getIntExtra("idUsuario", 0);
        usuario = db.obtenerUsuarioDAO().obtenerUsuario( res );
    }
    @Override
    public void onClick(View v) {
    }

    public void mostrarAcerca(MenuItem item){
        if ( item.getItemId() == R.id.navAcerca ) {
            Toast.makeText(this, "la biblioteca", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() ==R.id.navCerrarSesion ) {
            finish();
        }
    }
    //ciclo de vida
    //cuando de clic en regresar
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}
