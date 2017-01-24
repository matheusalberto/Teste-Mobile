package matheus.com.br.testemobile.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import matheus.com.br.testemobile.others.Auxiliar;
import matheus.com.br.testemobile.R;
import matheus.com.br.testemobile.dao.FilmeDAO;
import matheus.com.br.testemobile.model.Filme;

public class MainActivity extends AppCompatActivity {

    private EditText txtFilme;
    private ImageButton btnProcurar;
    private ImageButton btnFavoritos;
    private ImageView imgFilme;
    private AlertDialog info;
    private String resultado;
    private Filme f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "Após achar um filme, clique e segure na imagem para visualizar opções", Toast.LENGTH_LONG).show();

        txtFilme = (EditText) findViewById(R.id.txtFilme);
        btnProcurar = (ImageButton) findViewById(R.id.btnProcurar);
        btnFavoritos = (ImageButton) findViewById(R.id.btnFavoritos);
        imgFilme = (ImageView) findViewById(R.id.imgFilme);

        btnFavoritos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, FavoritosActivity.class);
                startActivity(it);
            }
        });

        btnProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtFilme.getWindowToken(), 0);
                if(verificaConexao(MainActivity.this)){
                    DownloadInformacoes di = new DownloadInformacoes();
                    di.execute(txtFilme.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Sem conexão com a internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem informacoes = menu.add("Visualizar informações");
        MenuItem favoritos = menu.add("Adicionar aos favoritos");

        informacoes.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Informações sobre o filme: ");
                alerta.setMessage(resultado);
                alerta.setNeutralButton("Ok",null);

                info = alerta.create();
                info.show();
                return false;
            }
        });
        favoritos.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FilmeDAO dao = new FilmeDAO(getBaseContext());

                if(dao.procurarPorNome(f.getTitulo())){
                    Toast.makeText(getApplicationContext(), "Filme já existe nos favoritos", Toast.LENGTH_SHORT).show();
                } else {
                    String aux = dao.inserir(f);
                    Toast.makeText(getApplicationContext(), aux, Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    public  boolean verificaConexao(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) ||
                (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected())) {
            return true;
        }
        return false;
    }

    private class DownloadInformacoes extends AsyncTask<String, Integer, Bitmap> {

        private ProgressDialog load;

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(MainActivity.this, "Por favor aguarde...", "Baixando informações...");
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                InputStream input = new URL("http://www.omdbapi.com/?t=" + URLEncoder.encode(params[0], "UTF-8")).openStream();
                Map<String, String> map = new Gson().fromJson(new InputStreamReader(input, "UTF-8"), new TypeToken<Map<String, String>>() {
                }.getType());

                f = new Filme();

                f.setTitulo(map.get("Title"));
                f.setAno(map.get("Year"));
                f.setClassificacao(map.get("Rated"));
                f.setLancamento(map.get("Released")) ;
                f.setDuracao(map.get("Runtime"));
                f.setGenero(map.get("Genre"));
                f.setDiretor(map.get("Director"));
                f.setEscritor( map.get("Writer"));
                f.setAtores(map.get("Actors"));

                Bitmap img = Auxiliar.baixarImagem(map.get("Poster"));

                Bitmap bitmap = img;
                ByteArrayOutputStream saida = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,saida);
                byte[] imagemEmByte = saida.toByteArray();
                f.setImagem(imagemEmByte);

                resultado = "Título: " + f.getTitulo() + "\n"
                        + "Ano: " + f.getAno() + "\n"
                        + "Classificação: " + f.getClassificacao() + "\n"
                        + "Estréia: " + f.getLancamento() + "\n"
                        + "Duração: " + f.getDuracao() + "\n"
                        + "Gênero: " + f.getGenero() + "\n"
                        + "Diretor: " + f.getDiretor() + "\n"
                        + "Escritor: " + f.getEscritor() + "\n"
                        + "Atores: " + f.getAtores();

                return img;
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap img) {
            if (img != null) {
                imgFilme.setImageBitmap(img);
                registerForContextMenu(imgFilme);
            } else {
                Toast.makeText(getApplicationContext(), "Erro, filme não encontrado", Toast.LENGTH_SHORT).show();
                imgFilme.setImageBitmap(null);
            }
            load.dismiss();
        }
    }
}


