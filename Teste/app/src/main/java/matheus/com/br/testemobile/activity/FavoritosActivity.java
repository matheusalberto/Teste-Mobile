package matheus.com.br.testemobile.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import matheus.com.br.testemobile.R;
import matheus.com.br.testemobile.adapter.FilmeAdapter;
import matheus.com.br.testemobile.dao.FilmeDAO;
import matheus.com.br.testemobile.model.Filme;

/**
 * Created by Matheus Alberto on 23/01/2017.
 */
public class FavoritosActivity extends AppCompatActivity {

    private ListView listagem;
    private List<Filme> lista;
    private AlertDialog info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        listagem = (ListView) findViewById(R.id.listaFilme);
        listagem.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        final FilmeDAO dao = new FilmeDAO(getBaseContext());
        lista = dao.carregar();
        FilmeAdapter adapter = new FilmeAdapter(getBaseContext(), lista);
        listagem.setAdapter(adapter);
        registerForContextMenu(listagem);

        listagem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(FavoritosActivity.this);
                alerta.setTitle("Deletar");
                alerta.setMessage("Deseja mesmo deletar este filme dos seus favoritos?");
                alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dao.deletar(lista.get(position))){
                            lista = dao.carregar();
                            FilmeAdapter adapter = new FilmeAdapter(getBaseContext(), lista);
                            listagem.setAdapter(adapter);
                            registerForContextMenu(listagem);
                            Toast.makeText(getApplicationContext(), "Filme deletado com sucesso", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro, tente novamente", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alerta.setNegativeButton("Cancelar",null);

                info = alerta.create();
                info.show();
                return false;
            }
        });
    }
}
