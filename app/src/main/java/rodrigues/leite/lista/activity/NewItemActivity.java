package rodrigues.leite.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

import rodrigues.leite.lista.R;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    Uri photoSelected = null;

    @Override
    // Passo 8 -----------------------------------------------------------------------------------------
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        ImageButton imgCl = findViewById(R.id.imbCl); // Pega o botão com a imagem
        imgCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT); //Abre a galeria para o usuario escolher uma foto
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent,PHOTO_PICKER_REQUEST); // vai pra tela mandando a imagem e o numero identifcador
            }
        });
        // ---------------------------------------------------------------------------------------------------------

        // Passo 09 -------------------------------------------------------------------------------------------------------------
        Button btnAddItem = findViewById(R.id.btnAddItem); // pega o botão

        btnAddItem.setOnClickListener(new View.OnClickListener() { //quando o botão for clicado
            @Override

            public void onClick(View view) {
                if(photoSelected == null){ // Analizando se os campos foram preenchidos e se caso algo esteja nulo dá uma mensagem falando oq faltou
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!",Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if(title.isEmpty()){
                    Toast.makeText(NewItemActivity.this,"É necessário inserir um titulo",Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if(description.isEmpty()){
                    Toast.makeText(NewItemActivity.this,"É necessário inserir uma descrição",Toast.LENGTH_LONG).show();
                    return;
                }
                //Guarda os dados preenchidos e manda pra main
                Intent i = new Intent();
                i.setData(photoSelected);
                i.putExtra("title",title);
                i.putExtra("description",description);
                setResult(Activity.RESULT_OK,i); //falando que está tudo certinho e volta pra main
                finish(); //finaliza a tela
            }
        });
        //-----------------------------------------------------------------------------------------------------------------------------------
    }
    //Passo 8 ------------------------------------------------------------------------------
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){ //3 parametros, 1° é qual a chamada de startactivityforresult a resposta se refere, 2°cod que fala se a activity retornou com sucesso e 3° dados retornados da activity
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_PICKER_REQUEST){ //Vemos se é igual a chamada
            if (resultCode == Activity.RESULT_OK){ // Vemos se a Activity retornou certa
                photoSelected = data.getData(); // pegamos o URI (endereço) da imagem
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview); // pegamos o campo img
                imvfotoPreview.setImageURI(photoSelected); // colocamos o endereço no campo imagem
            }
        }
    }
    //------------------------------------------------------------------------------------------
}