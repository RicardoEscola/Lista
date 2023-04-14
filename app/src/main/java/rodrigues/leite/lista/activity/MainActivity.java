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
    static int NEW_ITEM_REQUEST = 1; //Usado para saber qual a tela está sendo requerida
    MyAdapter myAdapter;
    List<MyItem> itens = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Passo 12------------------------------------------------------------------------------------------------------------------------
        RecyclerView rvItens = findViewById(R.id.rvItens);// Pegando a recyclerview

        myAdapter = new MyAdapter(this,itens); //Cria o myadapter
        rvItens.setAdapter(myAdapter); //seta o myadapter no recycleview

        rvItens .setHasFixedSize(true); // fala que todos os itens devem ter o mesmo tamanho
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); //cria um gerenciador de layout linear
        rvItens.setLayoutManager(layoutManager); //seta o recycleview no gerenciador

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(),DividerItemDecoration.VERTICAL); //Cria uma linha para separar os itens

        rvItens.addItemDecoration(dividerItemDecoration);
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
        if(requestCode == NEW_ITEM_REQUEST){ // Verifica se está realmente tudo certo
            if (resultCode == Activity.RESULT_OK){

                //Pegamos os dados de MyItem
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                myItem.photo = data.getData();

                itens.add(myItem); // guardamos os itens na lista de itens
                myAdapter.notifyItemInserted(itens.size()-1); //atualiza a recycleview e exiba o novo item
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}