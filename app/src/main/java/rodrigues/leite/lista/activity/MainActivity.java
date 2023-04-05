package rodrigues.leite.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import rodrigues.leite.lista.R;
import rodrigues.leite.lista.adapter.MyAdapter;
import rodrigues.leite.lista.model.MyItem;

public class MainActivity extends AppCompatActivity {
    static int NEW_ITEM_REQUEST = 1; // Passo 7 Usado para saber qual a tela está sendo requerida
    MyAdapter myAdapter;
    List<MyItem> itens = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Passo 15------------------------------------------------------------------------------------------------------------------------
        RecyclerView rvItens = findViewById(R.id.rvItens);

        myAdapter = new MyAdapter(this,itens);
        rvItens.setAdapter(myAdapter);

        rvItens .setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(),DividerItemDecoration.VERTICAL);
        //---------------------------------------------------------------------------------------------------------------------------------

        // Passo 07--------------------------------------------------------------------------------------------------------------------------
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem); //Pegando o botão flutuante
        fabAddItem.setOnClickListener(new View.OnClickListener() { //quando ele for clicado
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,NewItemActivity.class); //Cria uma navegação de uma tela para outra
                startActivityForResult(i,NEW_ITEM_REQUEST); // inicia a navegação para o resultado
            }
        });
        // ----------------------------------------------------------------------------------------------------------------------------------------
    }
    // Passo 11--------------------------------------------------------------------------------------------------------
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == NEW_ITEM_REQUEST){
            if (resultCode == Activity.RESULT_OK){ // Verifica se está realmente tudo certo
                //Pegamos os dados de MyItem
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                myItem.photo = data.getData();

                itens.add(myItem); // guardamos os itens na lista de itens
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}